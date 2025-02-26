/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.impl.builders;

import com.google.common.base.Preconditions;
import com.sportradar.mts.sdk.api.Selection;
import com.sportradar.mts.sdk.api.builders.SelectionBuilder;
import com.sportradar.mts.sdk.api.caching.MarketDescriptionCI;
import com.sportradar.mts.sdk.api.caching.MarketDescriptionProvider;
import com.sportradar.mts.sdk.api.caching.MarketMappingCI;
import com.sportradar.mts.sdk.api.impl.SelectionImpl;
import com.sportradar.mts.sdk.api.interfaces.SdkConfiguration;
import com.sportradar.mts.sdk.api.rest.URN;
import com.sportradar.mts.sdk.api.utils.MtsTicketHelper;
import com.sportradar.mts.sdk.api.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of SelectionBuilder
 */
public class SelectionBuilderImpl implements SelectionBuilder {

    private static final Logger logger = LoggerFactory.getLogger(SelectionBuilderImpl.class);
    private static final String SPORT_ID_MISSING = "sportId is missing";

    private final MarketDescriptionProvider marketDescriptionProvider;
    private final SdkConfiguration config;
    private String eventId;
    private String selectionId;
    private Integer odds;
    private Integer boostedOdds;
    private boolean isBanker;
    private final boolean isCustomBet;

    public SelectionBuilderImpl(MarketDescriptionProvider marketDescriptionProvider, SdkConfiguration config, boolean isCustomBet) {
//        Preconditions.checkNotNull(marketDescriptionProvider);
        Preconditions.checkNotNull(config);

        this.marketDescriptionProvider = marketDescriptionProvider;
        this.config = config;
        this.isCustomBet = isCustomBet;
    }

    @Override
    public SelectionBuilder setEventId(String eventId) {
        this.eventId = eventId;
        validateData(false, true, false);
        return this;
    }

    @Override
    public SelectionBuilder setId(String selectionId) {
        this.selectionId = selectionId;
        validateData(true, false, false);
        return this;
    }

    @Override
    public SelectionBuilder setIdLo(int type, int subType, String sov, String selectionIds) {
        Preconditions.checkArgument(type > 0, "type is missing");
        if (subType < 0)
        {
            subType = 0;
        }
        if (StringUtils.isNullOrEmpty(sov))
        {
            sov = "*";
        }
        selectionId = String.format("live:%s/%s/%s", type, subType, sov);
        if (!StringUtils.isNullOrEmpty(selectionIds))
        {
            selectionId += "/" + selectionIds;
        }
        validateData(true, false, false);
        return this;
    }

    @Override
    public SelectionBuilder setIdLcoo(int type, int sportId, String sov, String selectionIds) {
        Preconditions.checkArgument(type > 0, "type is missing");
        Preconditions.checkArgument(sportId > 0, SPORT_ID_MISSING);

        if (StringUtils.isNullOrEmpty(sov))
        {
            sov = "*";
        }
        selectionId = String.format("lcoo:%s/%s/%s", type, sportId, sov);
        if (!StringUtils.isNullOrEmpty(selectionIds))
        {
            selectionId += "/" + selectionIds;
        }
        validateData(true, false, false);
        return this;
    }

    @Override
    public SelectionBuilder setIdUof(int product, String sportId, int marketId, String selectionIds, String specifiers, Map<String, Object> sportEventStatus) {
        Preconditions.checkArgument(!StringUtils.isNullOrEmpty(sportId), SPORT_ID_MISSING);
        Preconditions.checkArgument(marketId > 0, "marketId is missing");

        Map<String, String> specs = new HashMap<>();
        if(!StringUtils.isNullOrEmpty(specifiers))
        {
            for (String spec : specifiers.split("\\|"))
            {
                String[] s = spec.split("=");
                specs.putIfAbsent(s[0], s[1]);
            }
        }
        return setIdUof(product, sportId, marketId, selectionIds, specs, sportEventStatus);
    }

    @Override
    public SelectionBuilder setIdUof(int product, String sportId, int marketId, String selectionIds, Map<String, String> specifiers, Map<String, Object> sportEventStatus) {
        Preconditions.checkArgument(!StringUtils.isNullOrEmpty(sportId), SPORT_ID_MISSING);
        Preconditions.checkArgument(marketId > 0, "marketId is missing");

        if (!sportId.contains(":"))
        {
            throw new IllegalArgumentException("SportId is not valid. Must be similar to 'sr:sport:1'.");
        }
        if (product < 1)
        {
            throw new IllegalArgumentException("Product is not valid.");
        }

        selectionId = String.format("uof:%s/%s/%s", product, sportId, marketId);
        if (!StringUtils.isNullOrEmpty(selectionIds))
        {
            selectionId += "/" + selectionIds;
        }
        Map<String, String> newSpecifiers = handleMarketDescription(product, sportId, marketId, specifiers, sportEventStatus);
        if (newSpecifiers != null && !newSpecifiers.isEmpty())
        {
            StringBuilder sb = new StringBuilder();
            newSpecifiers.forEach((key, value) -> sb.append("&")
                    .append(key)
                    .append("=")
                    .append(value));
            selectionId += "?" + sb.substring(1);
        }
        validateData(true, false, false);
        return this;
    }

    @Override
    public SelectionBuilder setOdds(int odds) {
        this.odds = odds;
        validateData(false, false, !isCustomBet);
        return this;
    }

    @Override
    public SelectionBuilder setBoostedOdds(int boostedOdds) {
        this.boostedOdds = boostedOdds;
        validateData(false, false, !isCustomBet);
        return this;
    }

    @Override
    public SelectionBuilder setBanker(boolean isBanker) {
        this.isBanker = isBanker;
        return this;
    }

    @Override
    public SelectionBuilder set(String eventId, String selectionId, Integer odds, Integer boostedOdds, boolean isBanker) {
        this.eventId = eventId;
        this.selectionId = selectionId;
        this.odds = odds;
        this.boostedOdds = boostedOdds;
        this.isBanker = isBanker;
        validateData(true, true, !isCustomBet);
        return this;
    }

    @Override
    public Selection build() {
        validateData(true, true, !isCustomBet);
        return new SelectionImpl(eventId, selectionId, odds, boostedOdds, isBanker);
    }

    private void validateData(boolean id, boolean eventId, boolean odds)
    {
        if (id && (StringUtils.isNullOrEmpty(this.selectionId) || !MtsTicketHelper.validateId(this.selectionId, false, false, 1, 1000))) {
            throw new IllegalArgumentException("Id not valid.");
        }
        if (eventId && (StringUtils.isNullOrEmpty(this.eventId) || !MtsTicketHelper.validateId(this.eventId, false, false, 1, 100))) {
            throw new IllegalArgumentException("EventId not valid.");
        }
        if (odds && (this.odds == null || !(this.odds >= 10000 && this.odds <= 1000000000))) {
            throw new IllegalArgumentException("Odds not valid.");
        }
    }

    private Map<String, String> handleMarketDescription(int productId, String sportId, int marketId, Map<String, String> specifiers, Map<String, Object> sportEventStatus)
    {
        if(!this.config.getProvideAdditionalMarketSpecifiers())
        {
            return specifiers;
        }

        MarketDescriptionCI marketDescriptionCI = null;
        try
        {
            marketDescriptionCI = marketDescriptionProvider.getMarketDescription(marketId);
        }
        catch (Exception ignored)
        {
            // logged on MarketDescriptionProvider level
        }

        if(marketDescriptionCI == null)
        {
            logger.info("No market description found for marketId={}, sportId={}, productId={}.", marketId, sportId, productId);
            return specifiers;
        }

        //handle market 215
        if(marketId == 215)
        {
            return handleMarket215(specifiers, sportEventStatus);
        }

        MarketMappingCI marketMapping = marketDescriptionCI.getMappings().stream()
                .filter(f ->
                        f.getProducerId() == productId &&
                                f.getSportId() != null &&
                                f.getSportId().equals(URN.parse(sportId)))
                .findFirst()
                .orElse(null);
        if(marketMapping == null || marketMapping.getProducerId() == 0)
        {
            logger.info("Market description {}, has no mapping.", marketDescriptionCI.getId());
            return specifiers;
        }

        //handle $score
        if(!StringUtils.isNullOrEmpty(marketMapping.getSovTemplate()) && marketMapping.getSovTemplate().equals("{$score}"))
        {
            return handleMarketScore(specifiers, sportEventStatus);
        }

        return specifiers;
    }

    private Map<String, String> handleMarket215(Map<String, String> specifiers, Map<String, Object> sportEventStatus){
        Map<String, String> newSpecifiers = new HashMap<>();
        if(specifiers != null && specifiers.size() > 0)
        {
            newSpecifiers = specifiers;
        }
        if(sportEventStatus == null || !sportEventStatus.containsKey("CurrentServer"))
        {
            throw new IllegalArgumentException("SportEventStatus or CurrentServer key is missing");
        }
        newSpecifiers.put("$server", sportEventStatus.get("CurrentServer").toString());
        return newSpecifiers;
    }

    private Map<String, String> handleMarketScore(Map<String, String> specifiers, Map<String, Object> sportEventStatus){
        if(sportEventStatus == null)
        {
            throw new IllegalArgumentException("SportEventStatus is missing");
        }
        if(!sportEventStatus.containsKey("HomeScore"))
        {
            throw new IllegalArgumentException("SportEventStatus is missing HomeScore property");
        }
        if(!sportEventStatus.containsKey("AwayScore"))
        {
            throw new IllegalArgumentException("SportEventStatus is missing AwayScore property");
        }

        Map<String, String> newSpecifiers = new HashMap<>();
        if(specifiers != null && specifiers.size() > 0)
        {
            newSpecifiers = specifiers;
        }
        newSpecifiers.put("$score", sportEventStatus.get("HomeScore") + ":" + sportEventStatus.get("AwayScore"));

        return newSpecifiers;
    }
}
