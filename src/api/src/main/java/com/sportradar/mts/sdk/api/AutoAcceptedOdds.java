/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api;


import java.io.Serializable;

public interface AutoAcceptedOdds extends Serializable {
    /**
     * Selection index from 'ticket.selections' array (zero based)
     *
     * @return selection index
     */
    Integer getSelectionIndex();
    /**
     * Odds with which the ticket was placed
     *
     * @return requested odds
     */
    Integer getRequestedOdds();
    /**
     * Odds with which the ticket was accepted
     *
     * @return used odds
     */
    Integer getUsedOdds();
}
