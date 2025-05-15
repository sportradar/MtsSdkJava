/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl.builders;

import com.google.common.base.Preconditions;
import com.sportradar.mts.sdk.api.builders.*;
import com.sportradar.mts.sdk.api.caching.MarketDescriptionProvider;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;


public class BuilderFactoryImpl extends SimpleBuilderFactoryImpl implements BuilderFactory {

    private final SdkConfiguration config;
    private MarketDescriptionProvider marketDescriptionProvider;

    public BuilderFactoryImpl(SdkConfiguration config, MarketDescriptionProvider marketDescriptionProvider)
    {
        Preconditions.checkNotNull(config);
        Preconditions.checkNotNull(marketDescriptionProvider);

        this.config = config;
        this.marketDescriptionProvider = marketDescriptionProvider;
    }

    @Override
    public TicketCancelBuilder createTicketCancelBuilder() {
        return new TicketCancelBuilderImpl(config);
    }

    @Override
    public TicketReofferCancelBuilder createTicketReofferCancelBuilder() { return new TicketReofferCancelBuilderImpl(config); }

    @Override
    public TicketCashoutBuilder createTicketCashoutBuilder() {
        return new TicketCashoutBuilderImpl(config);
    }

    @Override
    public SenderBuilder createSenderBuilder() {
        return new SenderBuilderImpl(config);
    }

    @Override
    public SelectionBuilder createSelectionBuilder() { return new SelectionBuilderImpl(marketDescriptionProvider, config, false); }

    @Override
    public SelectionBuilder createSelectionBuilder(boolean isCustomBet) { return new SelectionBuilderImpl(marketDescriptionProvider, config, isCustomBet); }

    @Override
    public TicketAckBuilder createTicketAckBuilder() { return new TicketAckBuilderImpl(config); }

    @Override
    public TicketCancelAckBuilder createTicketCancelAckBuilder() { return new TicketCancelAckBuilderImpl(config); }

    @Override
    public TicketNonSrSettleBuilder createTicketNonSrSettleBuilder() { return new TicketNonSrSettleBuilderImpl(config); }
}