package com.sportradar.mts.sdk.ws.internal.connection;

import com.sportradar.mts.sdk.ws.exceptions.AuthTokenFailureException;
import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.internal.config.ImmutableConfig;
import com.sportradar.mts.sdk.ws.internal.config.TokenProviderConfig;
import com.sportradar.mts.sdk.ws.internal.utils.Json;
import okhttp3.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import static com.sportradar.mts.sdk.ws.internal.utils.TimeUtils.nowUtcMillis;
import static com.sportradar.mts.sdk.ws.internal.utils.TimeUtils.sleep;

public class TokenProvider implements AutoCloseable {

    private final TokenProviderConfig config;
    private final OkHttpClient okHttpClient;

    private String accessToken;
    private long accessTokenExpiry;

    public TokenProvider(final ImmutableConfig config) {
        this.config = config;
        this.okHttpClient = new OkHttpClient.Builder()
                .callTimeout(config.getAuthRequestTimeout())
                .build();
    }

    private static String urlEncode(final String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e); // todo dmuren
        }
    }

    public void connect() {
    }

    @Override
    public void close() {
    }

    public synchronized String getToken() {
        try {
            Exception possibleExc = null;
            String token = null;
            for (int i = 0; i < 10 && token == null; i++) {
                try {
                    token = fetchToken();
                } catch (final Exception e) {
                    possibleExc = e;
                }
            }

            if (token == null) {
                throw possibleExc instanceof SdkException
                        ? (SdkException) possibleExc
                        : new AuthTokenFailureException(possibleExc);
            }
            return token;
        } catch (final SdkException exc) {
            throw exc;
        } catch (final Exception exc) {
            throw new AuthTokenFailureException(exc);
        }
    }

    private String fetchToken() {
        final String oldToken = readToken();
        if (isTokenOK(oldToken)) {
            return oldToken;
        }
        final AuthResponse auth = fetchAuthResponse();
        final String newToken = auth.getAccessToken();
        if (isTokenOK(newToken)) {
            storeToken(newToken, auth.getExpiresIn() != null ? auth.getExpiresIn() : 0);
            return newToken;
        }
        final String authErrMsg = getAuthErrMsg(auth);
        if (authErrMsg != null) {
            throw new AuthTokenFailureException(authErrMsg);
        }
        sleep(config.getAuthRetryDelay().toMillis());
        return null;
    }

    private AuthResponse fetchAuthResponse() {
        try {
            final RequestBody formBody = new FormBody.Builder()
                    .add("grant_type", "client_credentials")
                    .add("client_id", urlEncode(config.getAuthClientId()))
                    .add("client_secret", urlEncode(config.getAuthClientSecret()))
                    .add("audience", urlEncode(config.getAuthAudience()))
                    .build();
            final Request request = new Request.Builder()
                    .url(config.getAuthServer().toURL())
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "application/json")
                    .post(formBody)
                    .build();
            try (Response response = okHttpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new AuthTokenFailureException("Auth request failed: " + response.code()); // todo dmuren handle failure
                }
                return Json.deserializeAuthResponse(response.body().string());
            }
        } catch (final Exception exc) {
            throw new AuthTokenFailureException(exc);
        }
    }

    private String readToken() {
        final String token = accessToken;
        if (!isTokenOK(token)) {
            return null;
        }

        final long now = nowUtcMillis();
        return now > accessTokenExpiry ? null : token;
    }

    private boolean isTokenOK(final String token) {
        return isNotNullOrEmpty(token);
    }

    private String getAuthErrMsg(final AuthResponse authResponse) {
        if (isNullOrEmpty(authResponse.getError())
                && isNullOrEmpty(authResponse.getErrorDescription())) {
            return null;
        }
        final StringBuilder result = new StringBuilder();
        result.append("Auth error");
        if (isNotNullOrEmpty(authResponse.getError())) {
            result.append(": ").append(authResponse.getError());
        }
        if (isNotNullOrEmpty(authResponse.getErrorDescription())) {
            result.append(": ").append(authResponse.getErrorDescription());
        }
        return result.toString();
    }

    private void storeToken(final String token, final int expiresIn) {
        if (expiresIn > 1) {
            accessTokenExpiry = nowUtcMillis() + TimeUnit.SECONDS.toMillis(expiresIn - 1);
            accessToken = token;
        }
    }

    private boolean isNotNullOrEmpty(final String input) {
        return !isNullOrEmpty(input);
    }

    private boolean isNullOrEmpty(final String input) {
        return input == null || input.trim().length() == 0;
    }
}
