package com.sportradar.mts.sdk.ws.internal.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sportradar.mts.sdk.ws.entities.internal.Request;
import com.sportradar.mts.sdk.ws.entities.internal.Response;
import com.sportradar.mts.sdk.ws.internal.connection.AuthResponse;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Json {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        mapper.configOverride(BigDecimal.class)
                .setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
        mapper.configOverride(BigInteger.class)
                .setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.STRING));
        OBJECT_MAPPER = mapper;
    }

    public static String serializeRequest(final Request request) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(request);
    }

    public static String serializeResponse(final Response response) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(response);
    }

    public static Request deserializeRequest(final String json) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, Request.class);
    }

    public static Response deserializeResponse(final String json) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, Response.class);
    }

    public static AuthResponse deserializeAuthResponse(final String json) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, AuthResponse.class);
    }
}
