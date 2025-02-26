package com.sportradar.mts.sdk.api;

import com.sportradar.mts.sdk.api.exceptions.ResponseTimeoutException;
import com.sportradar.mts.sdk.api.interfaces.TicketResponseListener;

public interface TicketSenderWs {
    static boolean isTicketPrematch(SdkTicket ticket) { // todo dmuren not sure zakaj je public, ampak zdj je.
        return ticket instanceof Ticket && ((Ticket) ticket).getSelections().stream().anyMatch(a -> a.getId().contains("lcoo"));
    }

    void open();

    void close();

    boolean isOpen();

    void send(Ticket ticket);

    TicketResponse sendBlocking(Ticket ticket) throws ResponseTimeoutException;

    void setListener(TicketResponseListener responseListener);

    void ticketResponseReceived(TicketResponse ticketResponse);
}
