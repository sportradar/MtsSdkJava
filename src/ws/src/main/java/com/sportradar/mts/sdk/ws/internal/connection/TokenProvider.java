package com.sportradar.mts.sdk.ws.internal.connection;

import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.ws.exceptions.AuthTokenFailureException;
import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.internal.utils.Json;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static com.sportradar.mts.sdk.ws.internal.utils.TimeUtils.nowUtcMillis;
import static com.sportradar.mts.sdk.ws.internal.utils.TimeUtils.sleep;

public class TokenProvider implements AutoCloseable {

    private final SdkConfiguration config;
    private final HttpClient httpClient;

    private String accessToken;
    private long accessTokenExpiry;
    private String accessAuthError;

    public TokenProvider(final SdkConfiguration config) {
        this.config = config;
        this.httpClient = HttpClient.newHttpClient();
    }

    private static String urlEncode(final String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
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
            for (var i = 0; i < 3 && token == null; i++) {
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
        if (isNotNullOrEmpty(oldToken)) {
            return oldToken;
        }
        final AuthResponse auth = fetchAuthResponse();
        final String newToken = auth.getAccessToken();
        if (isNotNullOrEmpty(newToken)) {
            storeToken(newToken, auth.getExpiresIn() != null ? auth.getExpiresIn() : 0);
            return newToken;
        }
        final String authErrMsg = getAuthErrMsg(auth);
        if (isNotNullOrEmpty(authErrMsg)) {
            storeAuthError(authErrMsg);
            throw new AuthTokenFailureException(authErrMsg);
        }
        sleep(config.getAuthRetryDelay().toMillis());
        return null;
    }

    private AuthResponse fetchAuthResponse() {
        try {
            final String urlEncodedForm = "grant_type=client_credentials"
                    + "&client_id=" + urlEncode(config.getAuthClientId())
                    + "&client_secret=" + urlEncode(config.getAuthClientSecret())
                    + "&audience=" + urlEncode(config.getAuthAudience());
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(config.getAuthServer())
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "application/json")
                    .timeout(config.getAuthRequestTimeout())
                    .POST(HttpRequest.BodyPublishers.ofString(urlEncodedForm))
                    .build();
            final HttpResponse<String> response = httpClient
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return Json.deserializeAuthResponse(response.body());
        } catch (final Exception exc) {
            throw new AuthTokenFailureException(exc);
        }
    }

    private String readToken() {
        final String token = accessToken;
        if (isNullOrEmpty(token)) {
            return readAuthError();
        }
        final long now = nowUtcMillis();
        return now > accessTokenExpiry ? null : token;
    }

    private String readAuthError() {
        final String err = accessAuthError;
        if (isNotNullOrEmpty(err)) {
            final long now = nowUtcMillis();
            if (now < accessTokenExpiry) {
                throw new AuthTokenFailureException(err);
            }
        }
        return null;
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
            accessAuthError = null;
        }
    }

    private void storeAuthError(final String error) {
        accessTokenExpiry = nowUtcMillis() + TimeUnit.SECONDS.toMillis(60);
        accessToken = null;
        accessAuthError = error;
    }

    private boolean isNotNullOrEmpty(final String input) {
        return !isNullOrEmpty(input);
    }

    private boolean isNullOrEmpty(final String input) {
        return input == null || input.trim().isEmpty();
    }
}
