/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api;

import com.sportradar.mts.sdk.api.enums.BetReofferType;

import java.io.Serializable;

/**
 * Defines a contract for bet reoffer details, mutually exclusive with AlternativeStake
 *
 */
public interface BetReoffer extends Serializable {

    /**
     * Gets the stake
     * @return stake
     */
    long getStake();

    /**
     * Gets the reoffer type. If auto then stake will be present. If manual you should wait for reoffer stake over Reply channel.
     * @return type
     */
    BetReofferType getType();
}
