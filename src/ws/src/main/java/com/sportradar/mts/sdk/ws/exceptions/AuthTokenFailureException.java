package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Exception thrown when there is a failure in obtaining the authentication token.
 */
public class AuthTokenFailureException extends SdkException {

    /**
     * Constructs a new AuthTokenFailureException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public AuthTokenFailureException(final Exception cause) {
        super(ExceptionCode.AuthTokenFailure, "Get auth token failed", cause);
    }

    /**
     * Constructs a new AuthTokenFailureException with the specified detail message.
     *
     * @param message the detail message
     */
    public AuthTokenFailureException(final String message) {
        super(ExceptionCode.AuthTokenFailure, message, null);
    }
}
