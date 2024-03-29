/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.example.listeners;

import com.sportradar.mts.sdk.api.TicketAck;
import com.sportradar.mts.sdk.api.interfaces.TicketAckResponseListener;

public class TicketAckHandler extends PublishResultHandler<TicketAck> implements TicketAckResponseListener {
}
