/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.caching;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;

public class MarketDescriptionProvider {
    private final static Logger logger = LoggerFactory.getLogger(MarketDescriptionProvider.class);

    private final MarketDescriptionCache marketCache;
    private final List<Locale> locales;

//    @Inject
    public MarketDescriptionProvider(MarketDescriptionCache marketDescriptionCache,
                                     List<Locale> locales) {
        Preconditions.checkNotNull(marketDescriptionCache);
        Preconditions.checkNotNull(locales);
        Preconditions.checkArgument(locales.size() > 0);

        this.marketCache = marketDescriptionCache;
        this.locales = locales;
    }

    public MarketDescriptionCI getMarketDescription(int marketId, String variant) {
        Preconditions.checkArgument(marketId > 0);
        Preconditions.checkNotNull(locales);
        Preconditions.checkArgument(!locales.isEmpty());

        if(marketCache == null)
        {
            throw new IllegalArgumentException("No access token provided.");
        }

        MarketDescriptionCI marketDescription = marketCache.getMarketDescription(marketId);

        if(marketDescription == null)
        {
            logger.warn("No market description found for marketId " + marketId);
        }

        return marketDescription;
    }
}
