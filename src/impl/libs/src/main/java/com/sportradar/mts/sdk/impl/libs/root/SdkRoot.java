/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.impl.libs.root;

import com.sportradar.mts.sdk.api.interfaces.*;
import com.sportradar.mts.sdk.api.TicketSenderWs;

public interface SdkRoot extends Openable {

    TicketSender getTicketSender(TicketResponseListener responseListener);

    TicketSenderWs getTicketSenderWs(TicketResponseListener responseListener);

    TicketCancelSender getTicketCancelSender(TicketCancelResponseListener responseListener);

    TicketAckSender getTicketAcknowledgmentSender(TicketAckResponseListener responseListener);

    TicketCancelAckSender getTicketCancelAcknowledgmentSender(TicketCancelAckResponseListener responseListener);

    TicketReofferCancelSender getTicketReofferCancelSender(TicketReofferCancelResponseListener responseListener);

    TicketCashoutSender getTicketCashoutSender(TicketCashoutResponseListener responseListener);

    TicketNonSrSettleSender getTicketNonSrSettleSender(TicketNonSrSettleResponseListener ticketNonSrSettleSender);
}
