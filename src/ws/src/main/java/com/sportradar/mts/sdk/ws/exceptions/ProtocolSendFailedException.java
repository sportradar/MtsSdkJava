package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Represents an exception that is thrown when the protocol send fails.
 */
public class ProtocolSendFailedException extends SdkException {

    /**
     * Initializes a new instance of the ProtocolSendFailedException class with the specified cause.
     *
     * @param cause The cause of the exception.
     */
    public ProtocolSendFailedException(final Exception cause) {
        super(ExceptionCode.ProtocolSendFailed, "Protocol send failed", cause);
    }
}
