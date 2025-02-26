/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.app;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.exceptions.MtsPropertiesException;
import com.sportradar.mts.sdk.api.impl.ConnectionStatusImpl;
import com.sportradar.mts.sdk.api.interfaces.*;
import com.sportradar.mts.sdk.api.interfaces.customBet.CustomBetManager;
import com.sportradar.mts.sdk.api.settings.SdkConfigurationBuilderImpl;
import com.sportradar.mts.sdk.api.settings.SdkConfigurationImpl;
import com.sportradar.mts.sdk.api.utils.SdkInfo;
import com.sportradar.mts.sdk.impl.di.SdkInjectionModule;
import com.sportradar.mts.sdk.api.TicketSenderWs;
import com.sportradar.mts.sdk.impl.libs.root.SdkRoot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Main entry point for the MTS SDK
 */
public class MtsSdk implements MtsSdkApi {

    private static final Logger logger = LoggerFactory.getLogger(MtsSdk.class);
    private SdkRoot sdkRoot;
    private final Object stateLock = new Object();
    private boolean opened;
    private boolean closed;
    private final SdkConfiguration config;
    private BuilderFactory builderFactory;
    private MtsClientApi mtsClientApi;
    private CustomBetManager customBetManager;
    private ReportManager reportManager;
    private ConnectionStatusImpl connectionStatus;

    /**
     * Creates new MTS SDK instance
     * @param config Instance of {@link SdkConfiguration} filled with valid configuration values
     */
    public MtsSdk(SdkConfiguration config) {
        Preconditions.checkNotNull(config, "config cannot be null");

        this.config = config;
        Injector injector = Guice.createInjector(new SdkInjectionModule(this.config));
        builderFactory = injector.getInstance(BuilderFactory.class);
        mtsClientApi = injector.getInstance(MtsClientApi.class);
        customBetManager = injector.getInstance(CustomBetManager.class);
        reportManager = injector.getInstance(ReportManager.class);
        connectionStatus = (ConnectionStatusImpl) injector.getInstance(ConnectionStatus.class);
        sdkRoot = injector.getInstance(SdkRoot.class);
        logInit();
    }

    @Override
    public void open() {
        synchronized (stateLock) {
            checkState(!opened, "MTS SDK is already open");
            checkState(!closed, "MTS SDK cannot be reopened");
            checkNotNull(config, "SdkConfiguration cannot be null");
            logger.info("Starting the MTS SDK using provided configuration");
            try {
                openWithConfig();
            } catch (Exception e) {
                throw new MtsPropertiesException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void close() {
        synchronized (stateLock) {
            if (opened) {
                logger.info("Closing the MTS SDK");
                opened = false;
                closed = true;
                sdkRoot.close();
                logger.info("MTS SDK closed");
            }
        }
    }

    @Override
    public BuilderFactory getBuilderFactory() { return builderFactory; }

    /**
     * Gets the SdkConfiguration from the default settings file ("/mts-sdk.properties")
     * @return  SdkConfiguration
     */
    public static SdkConfiguration getConfiguration() { return SdkConfigurationImpl.getConfiguration(); }

    /**
     * Gets the SdkConfiguration from the specified settings file
     * @param path to the settings file
     * @return {@link SdkConfiguration}
     */
    public static SdkConfiguration getConfiguration(String path) {
        return SdkConfigurationImpl.getConfiguration(path);
    }

    /**
     * Gets the SdkConfiguration from the Properties
     * @param properties to be used to construct {@link SdkConfiguration}
     * @return {@link SdkConfiguration}
     */
    public static SdkConfiguration getConfiguration(Properties properties) { return SdkConfigurationImpl.getConfiguration(properties); }

    /**
     * Gets the SdkConfiguration from the 'application.yml' file located in the resources folder
     *
     * @return {@link SdkConfiguration}
     */
    public static SdkConfiguration getConfigurationFromYaml() { return SdkConfigurationImpl.getConfigurationFromYaml(); }

    /**
     * Gets the {@link SdkConfigurationBuilder} to construct {@link SdkConfiguration}
     * @return {@link SdkConfiguration}
     */
    public static SdkConfigurationBuilder getConfigurationBuilder() { return new SdkConfigurationBuilderImpl(); }

    @Override
    public TicketSender getTicketSender(TicketResponseListener ticketResponseListener) {
        checkOpened();
        return sdkRoot.getTicketSender(ticketResponseListener);
    }

    @Override
    public TicketSenderWs getTicketSenderWs(TicketResponseListener ticketResponseListener) {
        checkOpened();
        return sdkRoot.getTicketSenderWs(ticketResponseListener);
    }

    @Override
    public TicketCancelSender getTicketCancelSender(TicketCancelResponseListener responseListener) {
        checkOpened();
        return sdkRoot.getTicketCancelSender(responseListener);
    }

    @Override
    public TicketAckSender getTicketAckSender(TicketAckResponseListener responseListener) {
        checkOpened();
        return sdkRoot.getTicketAcknowledgmentSender(responseListener);
    }

    @Override
    public TicketCancelAckSender getTicketCancelAckSender(TicketCancelAckResponseListener responseListener) {
        checkOpened();
        return sdkRoot.getTicketCancelAcknowledgmentSender(responseListener);
    }

    @Override
    public TicketReofferCancelSender getTicketReofferCancelSender(TicketReofferCancelResponseListener responseListener) {
        checkOpened();
        return sdkRoot.getTicketReofferCancelSender(responseListener);
    }

    @Override
    public TicketCashoutSender getTicketCashoutSender(TicketCashoutResponseListener responseListener) {
        checkOpened();
        return sdkRoot.getTicketCashoutSender(responseListener);
    }

    @Override
    public TicketNonSrSettleSender getTicketNonSrSettleSender(TicketNonSrSettleResponseListener responseListener) {
        checkOpened();
        return sdkRoot.getTicketNonSrSettleSender(responseListener);
    }

    @Override
    public MtsClientApi getClientApi() {
        return mtsClientApi;
    }

    @Override
    public CustomBetManager getCustomBetManager() {
        return customBetManager;
    }

    @Override
    public ReportManager getReportManager() {
        return reportManager;
    }


    @Override
    public ConnectionStatus getConnectionStatus(ConnectionChangeListener connectionChangeListener) {
        if(connectionChangeListener != null) {
            connectionStatus.setConnectionChangeListener(connectionChangeListener);
        }
        return connectionStatus;
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    private void openWithConfig() {
        checkNotNull(config, "configuration cannot be null");
        logger.info("Opening the MTS SDK");
        opened = true;
        sdkRoot.open();
        logger.info("MTS SDK opened");
    }

    private void checkOpened() {
        checkState(opened, "SDK is not open, call open first");
    }

    private void logInit()
    {
        String javaVersion = System.getProperty("java.version");
        String jarVersion = SdkInfo.getVersion();
        String msg = "/*** MtsSdk initialization. Sdk version: " + jarVersion + ". Java: " + javaVersion + ". ***/";
        Logger initLogger = LoggerFactory.getLogger(MtsSdk.class);
        initLogger.info(msg);
        initLogger = LoggerFactory.getLogger("com.sportradar.mts.traffic");
        initLogger.info(msg);
        initLogger = LoggerFactory.getLogger("com.sportradar.mts.rest");
        initLogger.info(msg);
    }
}
