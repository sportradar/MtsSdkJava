package com.sportradar.mts.sdk.ws.internal.protocol;

import com.sportradar.mts.sdk.ws.MbsSdkConfig;
import com.sportradar.mts.sdk.ws.internal.config.ImmutableConfig;
import com.sportradar.mts.sdk.ws.internal.utils.ExcSuppress;
import com.sportradar.mts.sdk.ws.protocol.TicketProtocol;

import java.util.function.Consumer;

public class ProtocolProvider implements AutoCloseable {

    private final ProtocolEngine engine;
    private final TicketProtocol ticketProtocol;

    public ProtocolProvider(final MbsSdkConfig sdkConfig, final Consumer<Exception> unhandledExceptionHandler) {
        final ImmutableConfig config = new ImmutableConfig(sdkConfig);
        this.engine = new ProtocolEngine(config, unhandledExceptionHandler);
        this.ticketProtocol = new TicketProtocolImpl(this.engine);
    }

    public TicketProtocol getTicketProtocol() {
        return ticketProtocol;
    }

    public void connect() {
        this.engine.connect();
    }

    @Override
    public void close() {
        ExcSuppress.close(this.engine);
    }
}
