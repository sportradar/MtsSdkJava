/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.interfaces;

import com.sportradar.mts.sdk.api.*;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.interfaces.customBet.CustomBetManager;

/**
 * Entry point for the MTS SDK
 */
public interface MtsSdkApi extends Openable {

    /**
     * Opens the SDK using default properties file mts-sdk.properties
     */
    @Override
    void open();

    /**
     * Closes the SDK and all resources associated with it.
     * Once closed, it cannot be reopened
     */
    @Override
    void close();

    /**
     * Gets the {@link BuilderFactory} instance used to construct builders with some of the properties pre-loaded from the configuration
     * @return BuilderFactory
     */
    BuilderFactory getBuilderFactory();

    /**
     * Returns ticket sender which can be used to send {@link Ticket}
     *
     * @param ticketResponseListener client implementation of response handler
     * @return ticket sender
     */
    TicketSender getTicketSender(TicketResponseListener ticketResponseListener);

    /**
     * Returns ticket cancel sender which can be used to send {@link TicketCancel}
     *
     * @param ticketCancelResponseListener client implementation of response handler
     * @return ticket cancel sender
     */
    TicketCancelSender getTicketCancelSender(TicketCancelResponseListener ticketCancelResponseListener);

    /**
     * Returns ticket acknowledgment sender which can be used to send {@link TicketAck}
     *
     * @param ticketAckResponseListener client implementation of response handler
     * @return ticket acknowledgment sender
     */
    TicketAckSender getTicketAckSender(TicketAckResponseListener ticketAckResponseListener);

    /**
     * Returns ticket cancel acknowledgment sender which can be used to send {@link TicketCancelAck}
     *
     * @param ticketCancelAckResponseListener client implementation of response handler
     * @return ticket cancel acknowledgment sender
     */
    TicketCancelAckSender getTicketCancelAckSender(TicketCancelAckResponseListener ticketCancelAckResponseListener);

    /**
     * Returns ticket reoffer cancel sender which can be used to send {@link TicketReofferCancel}
     *
     * @param ticketReofferCancelResponseListener client implementation of response handler
     * @return ticket reoffer cancel sender
     */
    TicketReofferCancelSender getTicketReofferCancelSender(TicketReofferCancelResponseListener ticketReofferCancelResponseListener);

    /**
     * Returns ticket cashout sender which can be used to send {@link com.sportradar.mts.sdk.api.TicketCashout}
     *
     * @param responseListener - client implementation of response handler
     * @return - ticket cashout sender
     */
    TicketCashoutSender getTicketCashoutSender(TicketCashoutResponseListener responseListener);

    /**
     * Returns ticket non-Sportradar settle sender which can be used to send {@link com.sportradar.mts.sdk.api.TicketNonSrSettle}
     *
     * @param responseListener - client implementation of response handler
     * @return - ticket non-Sportradar settle sender
     */
    TicketNonSrSettleSender getTicketNonSrSettleSender(TicketNonSrSettleResponseListener responseListener);

    /**
     * Returns the {@link MtsClientApi} instance used to send requests to MTS Client API
     *
     * @return the {@link MtsClientApi}
     */
    MtsClientApi getClientApi();

    /**
     * Returns the {@link CustomBetManager} instance which can be used to perform custom bet operations
     *
     * @return the {@link CustomBetManager}
     */
    CustomBetManager getCustomBetManager();

    /**
     * Returns the {@link ReportManager} instance which can be used to get report data
     *
     * @return the {@link ReportManager}
     */
    ReportManager getReportManager();

    /**
     * Get the connection status
     * @param connectionChangeListener - client implementation of connection change listener
     * @return the connection status
     */
    ConnectionStatus getConnectionStatus(ConnectionChangeListener connectionChangeListener);
}
