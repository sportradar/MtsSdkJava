package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Represents an exception that is thrown when a response is not received in the configured time window.
 */
public class ProtocolTimeoutException extends SdkException {

    /**
     * Initializes a new instance of the {@code ProtocolTimeoutException} class.
     */
    public ProtocolTimeoutException() {
        super(ExceptionCode.SdkNotConnected, "Response not received in configured time window", null);
    }
}
