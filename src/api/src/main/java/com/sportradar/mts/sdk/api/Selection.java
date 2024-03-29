/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api;

import java.io.Serializable;

/**
 * Ticket selection
 */
public interface Selection extends Serializable {

    /**
     * Gets betradar event (match or outright) id
     * Mandatory
     * @return event id
     */
    String getEventId();

    /**
     * Gets selection id, should be composed according to specification
     * Mandatory
     * @return selection id
     */
    String getId();

    /**
     * Gets odds multiplied by 10000 and rounded to long value
     * Odds are mandatory unless custom bet
     * @return odds
     */
    Integer getOdds();

    /**
     * Gets boosted odds multiplied by 10000 and rounded to long value
     * @return boosted odds
     */
    Integer getBoostedOdds();

    /**
     * Gets a value indicating whether this instance is banker
     * @return isBanker
     */
    boolean getIsBanker();
}
