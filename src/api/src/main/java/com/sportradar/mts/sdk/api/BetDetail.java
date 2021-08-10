/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api;

import java.io.Serializable;
import java.util.List;

/**
 * Defines a contract for bet-level response details
 */
public interface BetDetail extends Serializable {

    /**
     * Gets the id of the bet
     * @return betId
     */
    String getBetId();

    /**
     * Get the bet response reason
     * @return reason
     */
    ResponseReason getReason();

    /**
     * Gets the list of the selection details
     * @return selectionDetails
     */
    List<SelectionDetail> getSelectionDetails();

    /**
     * Gets the bet reoffer details (mutually exclusive with AlternativeStake)
     * @return betReoffer
     */
    BetReoffer getReoffer();

    /**
     * Gets the alternative stake, mutually exclusive with BetReoffer
     * @return alternativeStake
     */
    long getAlternativeStake();
}
