/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.sportradar.mts.sdk.api.BetDetail;
import com.sportradar.mts.sdk.api.BetReoffer;
import com.sportradar.mts.sdk.api.ResponseReason;
import com.sportradar.mts.sdk.api.SelectionDetail;

import java.util.List;

/**
 * Implementation of BetDetail interface
 */
public class BetDetailImpl implements BetDetail {

    private final String betId;
    private final ResponseReason reason;
    private final List<SelectionDetail> selectionDetails;
    private final BetReoffer betReoffer;
    private final long altStake;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BetDetailImpl(@JsonProperty("betId") String betId,
                         @JsonProperty("reason") ResponseReason responseReason,
                         @JsonProperty("selectionDetails") List<SelectionDetail> selectionDetails,
                         @JsonProperty("betReoffer") BetReoffer betReoffer,
                         @JsonProperty("alternativeStake") long alternativeStake)
    {
        Preconditions.checkArgument(betId.length() > 0, "betId is missing");
        Preconditions.checkArgument(betId.length() <= 128, "betId is too long");
        Preconditions.checkNotNull(responseReason, "responseReason cannot be null");

        this.betId = betId;
        this.reason = responseReason;
        this.selectionDetails = selectionDetails;
        this.betReoffer = betReoffer;
        this.altStake = alternativeStake;
    }

    @Override
    public String getBetId() {
        return betId;
    }

    @Override
    public ResponseReason getReason() {
        return reason;
    }

    @Override
    public List<SelectionDetail> getSelectionDetails() {
        return selectionDetails;
    }

    @Override
    public BetReoffer getReoffer() {
        return betReoffer;
    }

    @Override
    public long getAlternativeStake() {
        return altStake;
    }
}
