package com.sportradar.mts.sdk.ws.exceptions;

/**
 * The base class for all SDK exceptions.
 */
public abstract class SdkException extends RuntimeException {

    private final int code;

    /**
     * Constructs a new SdkException with the specified code, message, and cause.
     *
     * @param code    the error code associated with the exception
     * @param message the detail message
     * @param cause   the cause (which is saved for later retrieval by the getCause() method)
     */
    public SdkException(final int code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    /**
     * Get the error code associated with the exception.
     *
     * @return The error code.
     */
    public int getCode() {
        return code;
    }
}
