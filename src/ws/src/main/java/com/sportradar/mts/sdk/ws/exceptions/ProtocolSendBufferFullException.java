package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Represents an exception that is thrown when the send buffer is full in the protocol.
 */
public class ProtocolSendBufferFullException extends SdkException {

    /**
     * Initializes a new instance of the {@code ProtocolSendBufferFullException} class.
     */
    public ProtocolSendBufferFullException() {
        super(ExceptionCode.ProtocolSendBufferFull, "Send buffer is full.", null);
    }
}
