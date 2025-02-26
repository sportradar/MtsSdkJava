package com.sportradar.mts.sdk.ws.protocol;

import com.sportradar.mts.sdk.ws.entities.request.*;
import com.sportradar.mts.sdk.ws.entities.response.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * The TicketProtocol interface defines the contract for sending various types of ticket-related requests asynchronously.
 * Implementations of this interface should provide the necessary logic to send the requests and handle the responses.
 */
public interface TicketProtocol {

    /**
     * Sends a ticket request synchronously and returns the corresponding response.
     *
     * @param request the ticket request to be sent
     * @return the ticket response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default TicketResponse sendTicket(TicketRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendTicketAsync(request).get();
    }

    /**
     * Sends a ticket inform request synchronously and returns the corresponding response.
     *
     * @param request the ticket inform request to be sent
     * @return the ticket inform response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default TicketInformResponse sendTicketInform(TicketInformRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendTicketInformAsync(request).get();
    }

    /**
     * Sends a ticket acknowledgment request synchronously and returns the corresponding response.
     *
     * @param request the ticket acknowledgment request to be sent
     * @return the ticket acknowledgment response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default TicketAckResponse sendTicketAck(TicketAckRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendTicketAckAsync(request).get();
    }

    /**
     * Sends a cancel request synchronously and returns the corresponding response.
     *
     * @param request the cancel request to be sent
     * @return the cancel response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default CancelResponse sendCancel(CancelRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendCancelAsync(request).get();
    }

    /**
     * Sends a cancel acknowledgment request synchronously and returns the corresponding response.
     *
     * @param request the cancel acknowledgment request to be sent
     * @return the cancel acknowledgment response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default CancelAckResponse sendCancelAck(CancelAckRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendCancelAckAsync(request).get();
    }

    /**
     * Sends a cashout request synchronously and returns the corresponding response.
     *
     * @param request the cashout request to be sent
     * @return the cashout response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default CashoutResponse sendCashout(CashoutRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendCashoutAsync(request).get();
    }

    /**
     * Sends a cashout inform request synchronously and returns the corresponding response.
     *
     * @param request a {@link CashoutInformRequest} to be sent
     * @return a {@link CashoutInformResponse} received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default CashoutInformResponse sendCashoutInform(CashoutInformRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendCashoutInformAsync(request).get();
    }

    /**
     * Sends a cashout acknowledgment request synchronously and returns the corresponding response.
     *
     * @param request the cashout acknowledgment request to be sent
     * @return the cashout acknowledgment response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default CashoutAckResponse sendCashoutAck(CashoutAckRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendCashoutAckAsync(request).get();
    }

    /**
     * Sends an external settlement request synchronously and returns the corresponding response.
     *
     * @param request the external settlement request to be sent
     * @return the external settlement response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default ExtSettlementResponse sendExtSettlement(ExtSettlementRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendExtSettlementAsync(request).get();
    }

    /**
     * Sends an external settlement acknowledgment request synchronously and returns the corresponding response.
     *
     * @param request the external settlement acknowledgment request to be sent
     * @return the external settlement acknowledgment response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default ExtSettlementAckResponse sendExtSettlementAck(ExtSettlementAckRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendExtSettlementAckAsync(request).get();
    }

    /**
     * Sends a max stake request synchronously and returns the corresponding response.
     *
     * @param request the max stake request to be sent
     * @return the max stake response received
     * @throws ExecutionException   if the execution of the request encounters an exception wrapping the cause exception
     * @throws InterruptedException if the execution of the request is interrupted
     */
    default MaxStakeResponse sendMaxStake(MaxStakeRequest request)
            throws ExecutionException, InterruptedException {
        return this.sendMaxStakeAsync(request).get();
    }

    /**
     * Sends a ticket request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the ticket request to be sent
     * @return a CompletableFuture representing the ticket response
     */
    CompletableFuture<TicketResponse> sendTicketAsync(TicketRequest request);

    /**
     * Sends a ticket inform request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the ticket inform request to be sent
     * @return a CompletableFuture representing the ticket inform response
     */
    CompletableFuture<TicketInformResponse> sendTicketInformAsync(TicketInformRequest request);

    /**
     * Sends a ticket acknowledgment request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the ticket acknowledgment request to be sent
     * @return a CompletableFuture representing the ticket acknowledgment response
     */
    CompletableFuture<TicketAckResponse> sendTicketAckAsync(TicketAckRequest request);

    /**
     * Sends a cancel request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the cancel request to be sent
     * @return a CompletableFuture representing the cancel response
     */
    CompletableFuture<CancelResponse> sendCancelAsync(CancelRequest request);

    /**
     * Sends a cancel acknowledgment request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the cancel acknowledgment request to be sent
     * @return a CompletableFuture representing the cancel acknowledgment response
     */
    CompletableFuture<CancelAckResponse> sendCancelAckAsync(CancelAckRequest request);

    /**
     * Sends a cashout request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the cashout request to be sent
     * @return a CompletableFuture representing the cashout response
     */
    CompletableFuture<CashoutResponse> sendCashoutAsync(CashoutRequest request);

    /**
     * Sends a {@link CashoutInformRequest} asynchronously and returns a {@link CompletableFuture} of {@link CashoutInformResponse}.
     *
     * @param request the cashout inform request to be sent
     * @return a CompletableFuture of CashoutInformResponse
     */
    CompletableFuture<CashoutInformResponse> sendCashoutInformAsync(CashoutInformRequest request);

    /**
     * Sends a cashout acknowledgment request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the cashout acknowledgment request to be sent
     * @return a CompletableFuture representing the cashout acknowledgment response
     */
    CompletableFuture<CashoutAckResponse> sendCashoutAckAsync(CashoutAckRequest request);

    /**
     * Sends an external settlement request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the external settlement request to be sent
     * @return a CompletableFuture representing the external settlement response
     */
    CompletableFuture<ExtSettlementResponse> sendExtSettlementAsync(ExtSettlementRequest request);

    /**
     * Sends an external settlement acknowledgment request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the external settlement acknowledgment request to be sent
     * @return a CompletableFuture representing the external settlement acknowledgment response
     */
    CompletableFuture<ExtSettlementAckResponse> sendExtSettlementAckAsync(ExtSettlementAckRequest request);

    /**
     * Sends a max stake request asynchronously and returns a CompletableFuture representing the response.
     *
     * @param request the max stake request to be sent
     * @return a CompletableFuture representing the max stake response
     */
    CompletableFuture<MaxStakeResponse> sendMaxStakeAsync(MaxStakeRequest request);
}
