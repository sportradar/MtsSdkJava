package com.sportradar.mts.sdk.api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportradar.mts.sdk.api.*;
import com.sportradar.mts.sdk.api.impl.mtsdto.ticketcancelresponse.TicketCancelResponseSchema;
import com.sportradar.mts.sdk.api.impl.mtsdto.ticketcashoutresponse.TicketCashoutResponseSchema;
import com.sportradar.mts.sdk.api.impl.mtsdto.ticketnonsrsettle.TicketNonSrSettleResponseSchema;
import com.sportradar.mts.sdk.api.impl.mtsdto.ticketresponse.TicketResponseSchema;
import com.sportradar.mts.sdk.api.ws.Response;

import java.io.IOException;
import java.util.Map;

public class ResponseDeserializer<T extends SdkTicket> extends JsonDeserializer<Response<T>> {
    @Override
    public Response<T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        String contentString = node.has("content") && !node.get("content").isNull()
                ? node.get("content").toString()
                : null;
        String operation = node.has("operation") && !node.get("operation").isNull()
                ? node.get("operation").asText()
                : null;
        String correlationId = node.has("correlationId") && !node.get("correlationId").isNull()
                ? node.get("correlationId").asText()
                : null;
        Map<String, Object> additionalInfo =
                node.has("additionalInfo") && !node.get("additionalInfo").isNull()
                        ? mapper.convertValue(node.get("additionalInfo"), new TypeReference<Map<String, Object>>() {
                })
                        : null;

        Object r;
        Class<T> contentClass;
        switch (operation) {
            case "ticket.confirm": {
                TicketResponseSchema c = JsonUtils.deserialize(contentString, TicketResponseSchema.class);
                r = MtsDtoMapper.map(c, correlationId, additionalInfo, contentString);
                contentClass = (Class<T>) TicketResponse.class;
                break;
            }
            case "cancel": {
                TicketCancelResponseSchema c = JsonUtils.deserialize(contentString, TicketCancelResponseSchema.class);
                r = MtsDtoMapper.map(c, correlationId, additionalInfo, contentString);
                contentClass = (Class<T>) TicketCancelResponse.class;
                break;
            }
            case "ticket.cashout": {
                TicketCashoutResponseSchema c = JsonUtils.deserialize(contentString, TicketCashoutResponseSchema.class);
                r = MtsDtoMapper.map(c, correlationId, additionalInfo, contentString);
                contentClass = (Class<T>) TicketCashoutResponse.class;
                break;
            }
            case "ticket.nonsrsettle": {
                TicketNonSrSettleResponseSchema c = JsonUtils.deserialize(contentString, TicketNonSrSettleResponseSchema.class);
                r = MtsDtoMapper.map(c, correlationId, additionalInfo, contentString);
                contentClass = (Class<T>) TicketNonSrSettleResponse.class;
                break;
            }
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }

        Response<T> response = new Response<>();
        response.setContent((T) r);
        response.setOperation(operation);
        response.setCorrelationId(correlationId);
        response.setAdditionalInfo(additionalInfo);
        response.setContentClass(contentClass);
        return response;

    }
}
