package com.sportradar.mts.sdk.api.ws;

import com.sportradar.mts.sdk.api.SdkTicket;

import java.util.Map;

/**
 * Represents a response object used in the application.
 */
public class Response<T extends SdkTicket> {

    private T content;
    private Class<T> contentClass;
    private String operation;
    private String correlationId;
    private Map<String, Object> additionalInfo;

    /**
     * Gets the UTC millis timestamp of the response.
     *
     * @return The UTC millis timestamp.
     */
    public String getCorrelationId() {
        return this.correlationId;
    }

    /**
     * Sets the UTC millis timestamp of the response.
     *
     * @param value The UTC millis timestamp to set.
     */
    public void setCorrelationId(String value) {
        this.correlationId = value;
    }

    /**
     * Gets the operation of the response.
     *
     * @return The operation.
     */
    public String getOperation() {
        return this.operation;
    }

    /**
     * Sets the operation of the response.
     *
     * @param value The operation to set.
     */
    public void setOperation(String value) {
        this.operation = value;
    }

    /**
     * Gets the content of the response.
     *
     * @return The content.
     */
    public T getContent() {
        return this.content;
    }

    /**
     * Sets the content of the response.
     *
     * @param value The content to set.
     */
    public void setContent(T value) {
        this.content = value;
    }

    public Map<String, Object> getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(Map<String, Object> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Class<T> getContentClass() {
        return contentClass;
    }

    public void setContentClass(Class<T> contentClass) {
        this.contentClass = contentClass;
    }
}
