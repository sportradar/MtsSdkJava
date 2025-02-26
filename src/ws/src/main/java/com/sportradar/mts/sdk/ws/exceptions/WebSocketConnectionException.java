package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Represents an exception that occurs when there is a failure in establishing a WebSocket connection.
 */
public class WebSocketConnectionException extends SdkException {

    /**
     * Constructs a new WebSocketConnectionException with the specified cause.
     *
     * @param cause the cause of the exception
     */
    public WebSocketConnectionException(final Exception cause) {
        super(ExceptionCode.WebSocketFailure, "WebSocket connect failed", cause);
    }

    /**
     * Constructs a new WebSocketConnectionException with the specified message.
     *
     * @param message the detail message
     */
    public WebSocketConnectionException(final String message) {
        super(ExceptionCode.WebSocketFailure, message, null);
    }
}
