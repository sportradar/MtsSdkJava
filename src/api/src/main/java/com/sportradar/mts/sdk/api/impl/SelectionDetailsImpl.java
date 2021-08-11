/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.sportradar.mts.sdk.api.RejectionInfo;
import com.sportradar.mts.sdk.api.ResponseReason;
import com.sportradar.mts.sdk.api.SelectionDetail;

/**
 * Implementation of SelectionDetails interface
 */
public class SelectionDetailsImpl implements SelectionDetail {

    private final int index;
    private final ResponseReason reason;
    private final RejectionInfo rejectionInfo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SelectionDetailsImpl(
            @JsonProperty("selectionIndex") int index,
            @JsonProperty("reason") ResponseReason reason,
            @JsonProperty("rejectionInfo") RejectionInfo rejectionInfo)
    {
        Preconditions.checkArgument(index >= 0 && index <= 62, "index is not valid");
        Preconditions.checkNotNull(reason, "reason cannot be null");

        this.index = index;
        this.reason = reason;
        this.rejectionInfo = rejectionInfo;
    }

    @Override
    public int getSelectionIndex() {
        return index;
    }

    @Override
    public ResponseReason getReason() {
        return reason;
    }

    @Override
    public RejectionInfo getRejectionInfo() {
        return rejectionInfo;
    }
}
