/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api;

import java.io.Serializable;

/**
 * Represents rejection information
 */
public interface RejectionInfo extends Serializable {
    /**
     * Returns the rejected selection's related Selection id
     * @return the rejected selection's related Selection id
     */
    String getId();

    /**
     * Returns the rejected selection's related Betradar event id
     * @return the rejected selection's related Betradar event id
     */
    String getEventId();

    /**
     * Returns the rejected selection's related Odds
     * @return the rejected selection's related Odds
     */
    Integer getOdds();
}
