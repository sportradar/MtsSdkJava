package com.sportradar.mts.sdk.ws.internal.config;

import java.net.URI;
import java.time.Duration;

public interface TokenProviderConfig {
    URI getAuthServer();

    String getAuthClientId();

    String getAuthClientSecret();

    String getAuthAudience();

    Duration getAuthRequestTimeout();

    Duration getAuthRetryDelay();
}
