package com.sportradar.mts.sdk.ws.exceptions;

/**
 * Represents an exception that occurs when a server responds with an error.
 */
public class ServerErrorResponseException extends SdkException {

    private final int errorCode;

    /**
     * Constructs a new ServerErrorResponseException with the specified error code and error message.
     *
     * @param errorCode    the error code returned by the server
     * @param errorMessage the error message returned by the server
     */
    public ServerErrorResponseException(final int errorCode, final String errorMessage) {
        super(ExceptionCode.ServerErrorResponse, errorMessage, null);
        this.errorCode = errorCode;
    }

    /**
     * Gets the error code returned by the server.
     *
     * @return the error code
     */
    public int getErrorCode() {
        return errorCode;
    }
}
