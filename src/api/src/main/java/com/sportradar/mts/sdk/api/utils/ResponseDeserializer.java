package com.sportradar.mts.sdk.api.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportradar.mts.sdk.api.SdkTicket;
import com.sportradar.mts.sdk.api.TicketCancelResponse;
import com.sportradar.mts.sdk.api.TicketResponse;
import com.sportradar.mts.sdk.api.ws.Response;

import java.io.IOException;

public class ResponseDeserializer extends JsonDeserializer<Response<? extends SdkTicket>> {
    @Override
    public Response<? extends SdkTicket> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        String operation = node.get("operation").asText();

        Class<? extends SdkTicket> clazz;
        switch (operation) {
            case "ticket":
                clazz = TicketResponse.class;
                break;
            case "ticketCancel":
                clazz = TicketCancelResponse.class;
                break;
            default:
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }

        SdkTicket content = mapper.treeToValue(node.get("content"), clazz);

        Response<SdkTicket> response = new Response<>();
        response.setOperation(operation);
        response.setContent(content);
        return response;

    }
}
