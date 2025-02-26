package com.sportradar.mts.sdk.ws;

import com.sportradar.mts.sdk.ws.exceptions.SdkException;
import com.sportradar.mts.sdk.ws.exceptions.SdkNotConnectedException;
import com.sportradar.mts.sdk.ws.internal.protocol.ProtocolProvider;
import com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress;
import com.sportradar.mts.sdk.ws.protocol.TicketProtocol;

import java.util.function.BiConsumer;

/**
 * The MbsSdk class represents the main entry point for interacting with the MBS SDK.
 * It provides methods for connecting to the MBS server, retrieving the ticket protocol, and closing the SDK.
 */
public class MbsSdk implements AutoCloseable {

    private final Object lock;
    private final ProtocolProvider protocolProvider;
    private final BiConsumer<MbsSdk, Exception> unhandledExceptionHandler;

    private boolean connected = false;
    private boolean closed = false;

    /**
     * Constructs a new instance of the MbsSdk class with the specified configuration.
     *
     * @param config The configuration for the MBS SDK.
     */
    public MbsSdk(final MbsSdkConfig config) {
        this.unhandledExceptionHandler = config.getUnhandledExceptionHandler();
        this.protocolProvider = new ProtocolProvider(config, this::handleException);
        this.lock = new Object();
    }

    /**
     * Gets the ticket protocol for interacting with the MBS server.
     *
     * @return The ticket protocol.
     */
    public TicketProtocol getTicketProtocol() {
        return this.protocolProvider.getTicketProtocol();
    }

    /**
     * Connects the SDK to the MBS server.
     * If the SDK is already connected, this method does nothing.
     *
     * @throws SdkException If an error occurs during the connection process.
     */
    public void connect() {
        try {
            synchronized (this.lock) {
                if (this.closed) throw new RuntimeException("MbsSdk is closed.");
                if (this.connected) return;
                this.protocolProvider.connect();
                this.connected = true;
            }
        } catch (final SdkException sdkException) {
            throw sdkException;
        } catch (final Exception exception) {
            throw new SdkNotConnectedException(exception);
        }
    }

    /**
     * Closes the SDK and releases any resources associated with it.
     * If the SDK is already closed, this method does nothing.
     */
    @Override
    public void close() {
        synchronized (this.lock) {
            if (this.closed) return;
            this.connected = false;
            ExcSuppress.close(this.protocolProvider);
            this.closed = true;
        }
    }

    /**
     * Handles the exception by invoking the unhandled exception handler, if available.
     *
     * @param exception The exception to handle.
     */
    private void handleException(final Exception exception) {
        final BiConsumer<MbsSdk, Exception> handler = unhandledExceptionHandler;
        if (handler == null) {
            return;
        }
        try {
            handler.accept(this, exception);
        } catch (final Exception ignored) {
        }
    }
}
