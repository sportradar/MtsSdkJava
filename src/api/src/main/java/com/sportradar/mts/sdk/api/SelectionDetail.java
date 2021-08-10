/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api;

import java.io.Serializable;

/**
 * Defines a contract for per-selection rejection reasons
 */
public interface SelectionDetail extends Serializable {

    /**
     * Gets the index of the selection
     * @return index
     */
    int getSelectionIndex();

    /**
     * Gets the selection response reason
     * @return response reason
     */
    ResponseReason getReason();

    /**
     * Returns the selection rejection info
     * @return the selection rejection info
     */
    RejectionInfo getRejectionInfo();
}
