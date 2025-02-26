package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Exception thrown when the SDK is not connected or is disposed.
 */
public class SdkNotConnectedException extends SdkException {

    /**
     * Constructs a new SdkNotConnectedException with no specified detail message.
     */
    public SdkNotConnectedException() {
        this(null);
    }

    /**
     * Constructs a new SdkNotConnectedException with the specified detail message and cause.
     *
     * @param cause the cause of the exception
     */
    public SdkNotConnectedException(final Exception cause) {
        super(ExceptionCode.SdkNotConnected, "Sdk is not connected or is disposed", cause);
    }
}
