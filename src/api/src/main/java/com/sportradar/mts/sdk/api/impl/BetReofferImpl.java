/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.sportradar.mts.sdk.api.BetReoffer;
import com.sportradar.mts.sdk.api.enums.BetReofferType;

/**
 * Implementation of BetReoffer interface
 */
public class BetReofferImpl implements BetReoffer {

    private final long stake;
    private final BetReofferType type;


    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BetReofferImpl(
            @JsonProperty("stake") long stake,
            @JsonProperty("betReofferType") BetReofferType betReofferType)
    {
        Preconditions.checkArgument(stake > 0, "stake must be greater then zero");

        this.stake = stake;
        this.type = betReofferType;
    }

    @Override
    public long getStake() {
        return stake;
    }

    @Override
    public BetReofferType getType() {
        return type;
    }
}
