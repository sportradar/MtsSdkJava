package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Represents an exception that is thrown when the response received from the protocol is invalid.
 */
public class ProtocolInvalidResponseException extends SdkException {

    /**
     * Initializes a new instance of the {@code ProtocolInvalidResponseException} class with the specified content.
     *
     * @param content The content of the invalid response.
     */
    public ProtocolInvalidResponseException(final String content) {
        super(ExceptionCode.ProtocolInvalidResponse, content, null);
    }
}
