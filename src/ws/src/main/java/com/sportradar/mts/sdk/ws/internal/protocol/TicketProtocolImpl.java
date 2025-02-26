package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.ws.entities.request.*;
import com.sportradar.mts.sdk.ws.entities.response.*;
import com.sportradar.mts.sdk.ws.protocol.TicketProtocol;

import java.util.concurrent.CompletableFuture;

class TicketProtocolImpl implements TicketProtocol {

    private final ProtocolEngine engine;

    public TicketProtocolImpl(final ProtocolEngine engine) {
        this.engine = engine;
    }

    @Override
    public CompletableFuture<TicketResponse> sendTicketAsync(final TicketRequest request) {
        return engine.execute("ticket-placement", request, TicketResponse.class);
    }

    @Override
    public CompletableFuture<TicketInformResponse> sendTicketInformAsync(final TicketInformRequest request) {
        return engine.execute("ticket-placement-inform", request, TicketInformResponse.class);
    }

    @Override
    public CompletableFuture<TicketAckResponse> sendTicketAckAsync(final TicketAckRequest request) {
        return engine.execute("ticket-placement-ack", request, TicketAckResponse.class);
    }

    @Override
    public CompletableFuture<CancelResponse> sendCancelAsync(final CancelRequest request) {
        return engine.execute("ticket-cancel", request, CancelResponse.class);
    }

    @Override
    public CompletableFuture<CancelAckResponse> sendCancelAckAsync(final CancelAckRequest request) {
        return engine.execute("ticket-cancel-ack", request, CancelAckResponse.class);
    }

    @Override
    public CompletableFuture<CashoutResponse> sendCashoutAsync(final CashoutRequest request) {
        return engine.execute("ticket-cashout", request, CashoutResponse.class);
    }

    @Override
    public CompletableFuture<CashoutInformResponse> sendCashoutInformAsync(final CashoutInformRequest request) {
        return engine.execute("cashout-inform", request, CashoutInformResponse.class);
    }

    @Override
    public CompletableFuture<CashoutAckResponse> sendCashoutAckAsync(final CashoutAckRequest request) {
        return engine.execute("ticket-cashout-ack", request, CashoutAckResponse.class);
    }

    @Override
    public CompletableFuture<ExtSettlementResponse> sendExtSettlementAsync(final ExtSettlementRequest request) {
        return engine.execute("ticket-ext-settlement", request, ExtSettlementResponse.class);
    }

    @Override
    public CompletableFuture<ExtSettlementAckResponse> sendExtSettlementAckAsync(final ExtSettlementAckRequest request) {
        return engine.execute("ticket-ext-settlement-ack", request, ExtSettlementAckResponse.class);
    }

    @Override
    public CompletableFuture<MaxStakeResponse> sendMaxStakeAsync(MaxStakeRequest request) {
        return engine.execute("max-stake", request, MaxStakeResponse.class);
    }
}
