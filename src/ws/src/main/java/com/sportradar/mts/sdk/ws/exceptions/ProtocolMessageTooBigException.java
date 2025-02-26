package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Exception thrown when a protocol message exceeds the maximum allowed size.
 */
public class ProtocolMessageTooBigException extends SdkException {

    /**
     * Constructs a new ProtocolMessageTooBigException with a default error message.
     */
    public ProtocolMessageTooBigException() {
        super(ExceptionCode.ProtocolMessageTooBig, "Max message size is 120kB", null);
    }
}
