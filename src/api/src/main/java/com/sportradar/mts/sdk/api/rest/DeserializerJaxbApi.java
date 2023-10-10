/*
 * Copyright (C) Sportradar AG. See LICENSE for full license governing this code
 */

package com.sportradar.mts.sdk.api.rest;

import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.JAXBIntrospector;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.StringWriter;

/**
 * An implementation of the {@link Deserializer} used to deserialize/unmarshall the content of the Unified API
 * endpoint request
 */
public class DeserializerJaxbApi implements Deserializer {
    private static final Logger logger = LoggerFactory.getLogger(DeserializerJaxbApi.class);
    private final Unmarshaller unmarshaller;
    private final Marshaller marshaller;

    public DeserializerJaxbApi(Unmarshaller unmarshaller, Marshaller marshaller) {
        this.unmarshaller = unmarshaller;
        this.marshaller = marshaller;
    }

    @SuppressWarnings("unchecked")
    @Override
    public synchronized <T> T deserialize(InputStream inStr, Class<T> clazz){
        try {
            return (T) JAXBIntrospector.getValue(unmarshaller.unmarshal(inStr));
        } catch (JAXBException e) {
            logger.warn("There was a problem unmarshalling an object, ex: ", e);
        }
        return null;
    }

    @Override
    public synchronized <T> String serialize(T inObj) {
        try {
            StringWriter writer = new StringWriter();
            marshaller.marshal(inObj, writer);
            return  writer.toString();
        } catch (JAXBException e) {
            logger.warn("There was a problem marshaling the provided data, ex: ", e);
        }
        return null;
    }
}
