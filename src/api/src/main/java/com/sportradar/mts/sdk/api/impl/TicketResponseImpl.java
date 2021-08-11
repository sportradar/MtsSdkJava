/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.sportradar.mts.sdk.api.AutoAcceptedOdds;
import com.sportradar.mts.sdk.api.ResponseReason;
import com.sportradar.mts.sdk.api.SelectionDetail;
import com.sportradar.mts.sdk.api.TicketResponse;
import com.sportradar.mts.sdk.api.enums.TicketAcceptance;
import com.sportradar.mts.sdk.api.impl.mtsdto.ticketresponse.AutoAcceptedOdd;
import com.sportradar.mts.sdk.api.impl.mtsdto.ticketresponse.BetDetail;
import com.sportradar.mts.sdk.api.utils.MtsDtoMapper;
import com.sportradar.mts.sdk.api.utils.MtsTicketHelper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Implementation of TicketResponse
 */
public class TicketResponseImpl implements TicketResponse {

    private final String ticketId;
    private final Date timestampUtc;
    private final String version;
    private final ResponseReason reason;
    private final TicketAcceptance status;
    private final List<com.sportradar.mts.sdk.api.BetDetail> betDetails;
    private final String signature;
    private final long exchangeRate;
    private final String correlationId;
    private final Map<String, String> additionalInfo;
    private final List<AutoAcceptedOdds> autoAcceptedOddsList;
    private final String msgBody;
    private final List<BetDetail> sdkBetDetails;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TicketResponseImpl(@JsonProperty("ticketId") String ticketId,
                              @JsonProperty("reason") ResponseReason reason,
                              @JsonProperty("status") TicketAcceptance status,
                              @JsonProperty("sdkBetDetails") List<BetDetail> betDetails,
                              @JsonProperty("signature") String signature,
                              @JsonProperty("exchangeRate") long exchangeRate,
                              @JsonProperty("timestampUtc") Date timestampUtc,
                              @JsonProperty("version") String version,
                              @JsonProperty("correlationId") String correlationId,
                              @JsonProperty("additionalInfo") Map<String, String> additionalInfo,
                              @JsonProperty("autoAcceptedOdds") List<AutoAcceptedOdd> autoAcceptedOdds,
                              @JsonProperty("msgBody") String msgBody)
    {
        Preconditions.checkNotNull(ticketId, "ticketId cannot be null");
        Preconditions.checkArgument(MtsTicketHelper.validateTicketId(ticketId), "ticketId is not valid");
        Preconditions.checkNotNull(timestampUtc, "timestamp cannot be null");
        Preconditions.checkArgument(MtsTicketHelper.validateTimestamp(timestampUtc), "timestampUtc is not valid");
        Preconditions.checkNotNull(version, "version cannot be null");
        Preconditions.checkArgument(version.length() == 3, "version is not valid");
        Preconditions.checkNotNull(msgBody);

        this.ticketId = ticketId;
        this.reason = reason;
        this.status = status;
        this.signature = signature;
        this.exchangeRate = exchangeRate;
        this.timestampUtc = timestampUtc;
        this.version = version;
        this.sdkBetDetails = betDetails;

        if(betDetails != null && !betDetails.isEmpty())
        {
            List<com.sportradar.mts.sdk.api.BetDetail> newBetDetails = Lists.newArrayList();
            for (BetDetail betDetail : betDetails) {
                List<SelectionDetail> newSelectionDetails = Lists.newArrayList();
                for(com.sportradar.mts.sdk.api.impl.mtsdto.ticketresponse.SelectionDetail selDetail : betDetail.getSelectionDetails())
                {
                    newSelectionDetails.add(new SelectionDetailsImpl(selDetail.getSelectionIndex(), MtsDtoMapper.map(selDetail.getReason()), MtsDtoMapper.map(selDetail.getRejectionInfo())));
                }
                newBetDetails.add(
                        new BetDetailImpl(
                                betDetail.getBetId(),
                                MtsDtoMapper.map(betDetail.getReason()),
                                newSelectionDetails,
                                MtsDtoMapper.map(betDetail.getReoffer()),
                                betDetail.getAlternativeStake() == null ? 0 : betDetail.getAlternativeStake().getStake()));
            }
            this.betDetails = newBetDetails;
        }
        else
        {
            this.betDetails = null;
        }
        this.correlationId = correlationId;
        this.additionalInfo = additionalInfo;
        if(autoAcceptedOdds != null){
            autoAcceptedOddsList = Lists.newArrayList();
            for (AutoAcceptedOdd autoAcceptedOdd : autoAcceptedOdds) {
                autoAcceptedOddsList.add(new AutoAcceptedOddsImpl(autoAcceptedOdd));
            }
        }
        else {
            autoAcceptedOddsList = null;
        }
        this.msgBody = msgBody;
    }

    @Override
    public String getTicketId() {
        return ticketId;
    }

    @Override
    public Date getTimestampUtc() {
        return timestampUtc;
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public TicketAcceptance getStatus() {
        return status;
    }

    @Override
    public ResponseReason getReason() {
        return reason;
    }

    @Override
    public List<com.sportradar.mts.sdk.api.BetDetail> getBetDetails() {
        return betDetails;
    }

    public List<BetDetail> getSdkBetDetails() {
        return sdkBetDetails;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public long getExchangeRate() {
        return exchangeRate;
    }

    @Override
    public String getCorrelationId() { return correlationId; }

    @Override
    public Map<String, String> getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Gets the auto accepted odd
     *
     * @return the auto accepted oddd
     */
    @Override
    public List<AutoAcceptedOdds> getAutoAcceptedOdds() {
        return autoAcceptedOddsList;
    }

    /**
     * Returns the associated ticket in the required MTS JSON format
     *
     * @return the associated ticket in the required MTS JSON format
     */
    @Override
    public String getJsonValue() {
        return msgBody;
    }

    @Override
    public String toString() {
        return "TicketResponseImpl{" +
                "ticketId='" + ticketId + '\'' +
                ", reason=" + reason +
                ", status=" + status +
                ", betDetails='" + betDetails + '\'' +
                ", timestamp=" + timestampUtc +
                ", version='" + version + '\'' +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}
