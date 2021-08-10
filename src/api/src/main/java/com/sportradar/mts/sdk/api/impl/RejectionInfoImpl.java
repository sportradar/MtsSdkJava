/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sportradar.mts.sdk.api.RejectionInfo;

/**
 * A basic {@link RejectionInfo} implementation
 */

public class RejectionInfoImpl implements RejectionInfo {
    private final String id;
    private final String eventId;
    private final Integer odds;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RejectionInfoImpl(
            @JsonProperty("id") String id,
            @JsonProperty("eventId") String eventId,
            @JsonProperty("odds") Integer odds) {
        this.id = id;
        this.eventId = eventId;
        this.odds = odds;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    @Override
    public Integer getOdds() {
        return odds;
    }
}
