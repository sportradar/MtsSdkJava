package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Represents an exception that is thrown when an invalid request is made to the protocol.
 */
public class ProtocolInvalidRequestException extends SdkException {

    /**
     * Constructs a new ProtocolInvalidRequestException with the specified content.
     *
     * @param content the content of the exception
     */
    public ProtocolInvalidRequestException(final String content) {
        super(ExceptionCode.ProtocolInvalidRequest, content, null);
    }
}
