/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.com.mts.sdk.app.root;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.sportradar.mts.api.rest.sportsapi.datamodel.MarketDescriptions;
import com.sportradar.mts.sdk.api.builders.BuilderFactory;
import com.sportradar.mts.sdk.api.caching.MarketDescriptionCI;
import com.sportradar.mts.sdk.api.caching.MarketDescriptionCache;
import com.sportradar.mts.sdk.api.caching.MarketDescriptionCacheImpl;
import com.sportradar.mts.sdk.api.caching.MarketDescriptionProvider;
import com.sportradar.mts.sdk.api.impl.builders.BuilderFactoryImpl;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.api.rest.DataProvider;
import com.sportradar.mts.sdk.api.rest.Deserializer;
import com.sportradar.mts.sdk.api.rest.DeserializerJaxbApi;
import com.sportradar.mts.sdk.api.rest.LogHttpDataFetcher;
import com.sportradar.mts.sdk.api.settings.SdkConfigurationImpl;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

public class SdkHelper {

    public static SdkConfiguration getSdkConfiguration()
    {
        Properties properties = new Properties();
        properties.setProperty("mts.sdk.hostname", "10.27.26.83");
        properties.setProperty("mts.sdk.vhost", "/klika");
        properties.setProperty("mts.sdk.username", "test");
        properties.setProperty("mts.sdk.password", "test");
        properties.setProperty("mts.sdk.test", "false");
        properties.setProperty("mts.sdk.ssl", "false");
        properties.setProperty("version.properties", "1.0.2");
        properties.setProperty("mts.sdk.rootExchangeName", "klika");
        properties.setProperty("mts.sdk.accessToken", "t16Mojb2lRoTwSckxa");
        return SdkConfigurationImpl.getConfiguration(properties);
    }

    private MarketDescriptionCache marketDescriptionCache;
    private MarketDescriptionProvider marketDescriptionProvider;
    private BuilderFactory builderFactory;

    public MarketDescriptionCache getMarketDescriptionCache() { return getMarketDescriptionCache(getSdkConfiguration()); }

    public MarketDescriptionCache getMarketDescriptionCache(SdkConfiguration config)
    {
        if(config == null)
        {
            config = getSdkConfiguration();
        }
        loadBuilderFactoryData(config);
        return marketDescriptionCache;
    }

    public MarketDescriptionProvider getMarketDescriptionProvider() { return getMarketDescriptionProvider(getSdkConfiguration()); }

    public MarketDescriptionProvider getMarketDescriptionProvider(SdkConfiguration config)
    {
        if(config == null)
        {
            config = getSdkConfiguration();
        }
        loadBuilderFactoryData(config);
        return marketDescriptionProvider;
    }

    public BuilderFactory getBuilderFactory()
    {
        return getBuilderFactory(getSdkConfiguration());
    }

    public BuilderFactory getBuilderFactory(SdkConfiguration config)
    {
        if(config == null)
        {
            config = getSdkConfiguration();
        }
        loadBuilderFactoryData(config);
        return builderFactory;
    }

    private void loadBuilderFactoryData(SdkConfiguration config)
    {
        if(this.builderFactory != null)
        {
            return;
        }
        String uriFormat = "https://global.api.betradar.com/v1/descriptions/%s/markets.xml?include_mappings=true";
        JAXBContext apiJaxbContext;
        List<Locale> locales = new ArrayList<>();
        locales.add(Locale.ENGLISH);

        try {
            apiJaxbContext = JAXBContext.newInstance("com.sportradar.mts.api.rest.sportsapi.datamodel");
        } catch (JAXBException e) {
            throw new IllegalStateException("JAXB contexts creation failed, ex: ", e);
        }

        Deserializer deserializer;
        try {
            deserializer = new DeserializerJaxbApi(apiJaxbContext.createUnmarshaller(), apiJaxbContext.createMarshaller());
        } catch (JAXBException e) {
            throw new IllegalStateException("Failed to create unmarshaller for 'api', ex: ", e);
        }

        CloseableHttpClient closableHttpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        LogHttpDataFetcher logHttpDataFetcher = new LogHttpDataFetcher(config, closableHttpClient);
        DataProvider<MarketDescriptions> dataProvider = new DataProvider<>(uriFormat, logHttpDataFetcher, deserializer, MarketDescriptions.class);

        Cache<String, MarketDescriptionCI> invariantMarketCache = CacheBuilder.newBuilder().build();
        MarketDescriptionCache marketDescriptionCache = new MarketDescriptionCacheImpl(invariantMarketCache, dataProvider, locales, config.getAccessToken());

        MarketDescriptionProvider marketDescriptionProvider = new MarketDescriptionProvider(marketDescriptionCache, locales);

        BuilderFactory builderFactory = new BuilderFactoryImpl(config, marketDescriptionProvider);

        this.marketDescriptionCache = marketDescriptionCache;
        this.marketDescriptionProvider = marketDescriptionProvider;
        this.builderFactory = builderFactory;
    }
}