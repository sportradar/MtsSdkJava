/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api;

import java.io.Serializable;

/**
 * Defines a contract for ticket cancellation response
 */
public interface ResponseReason extends Serializable {

    /**
     * Gets the reason code
     * @return code
     */
    int getCode();

    /**
     * Gets the reason message
     * @return message
     */
    String getMessage();

    /**
     * Gets the additional information about the error (internal exception message)
     * @return internal message
     * @deprecated in favour of the new {@link RejectionInfo}
     */
    @Deprecated
    String getInternalMessage();
}
