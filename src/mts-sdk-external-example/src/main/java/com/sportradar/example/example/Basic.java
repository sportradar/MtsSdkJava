/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.example.example;

import com.sportradar.example.TicketBuilderHelper;
import com.sportradar.example.listeners.TicketAckHandler;
import com.sportradar.example.listeners.TicketCancelAckHandler;
import com.sportradar.example.listeners.TicketCancelResponseHandler;
import com.sportradar.example.listeners.TicketResponseHandler;
import com.sportradar.mts.sdk.api.Ticket;
import com.sportradar.mts.sdk.api.interfaces.*;
import com.sportradar.mts.sdk.app.MtsSdk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Basic example of creating and sending ticket
 */
public class Basic {
    private static final Logger logger = LoggerFactory.getLogger(Basic.class);

    public static void Run()
    {
        SdkConfiguration config = MtsSdk.getConfiguration();
        MtsSdkApi mtsSdk = new MtsSdk(config);
        mtsSdk.open();
        TicketAckSender ticketAckSender = mtsSdk.getTicketAckSender(new TicketAckHandler());
        TicketCancelAckSender ticketCancelAckSender = mtsSdk.getTicketCancelAckSender(new TicketCancelAckHandler());
        TicketCancelSender ticketCancelSender = mtsSdk.getTicketCancelSender(new TicketCancelResponseHandler(ticketCancelAckSender, mtsSdk.getBuilderFactory()));
        TicketResponseHandler ticketResponseHandler = new TicketResponseHandler(ticketCancelSender, ticketAckSender, mtsSdk.getBuilderFactory());
        TicketSender ticketSender = mtsSdk.getTicketSender(ticketResponseHandler);

        Ticket ticket = new TicketBuilderHelper(mtsSdk.getBuilderFactory()).getTicket();
        //Notice: there are two ways of sending tickets to MTS (non-blocking is recommended)

        //send non-blocking (the TicketResult will we handled in TicketResponseHandler)
        ticketSender.send(ticket);

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mtsSdk.close();
    }
}
