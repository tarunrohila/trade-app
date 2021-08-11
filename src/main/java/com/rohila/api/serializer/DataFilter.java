package com.rohila.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.rohila.api.response.DefaultData;
import com.rohila.api.response.Response;

/**
 * Class file to parse if it modified from default value
 *
 * @author Tarun Rohila
 */
public class DataFilter extends SimpleBeanPropertyFilter {

    /**
     * Overridden method to serialize as field
     *
     * @param bean     - bean
     * @param jgen     - jgen
     * @param provider - provider
     * @param writer   - writer
     * @throws Exception
     */
    @Override
    public void serializeAsField(
            Object bean, JsonGenerator jgen, SerializerProvider provider, BeanPropertyWriter writer)
            throws Exception {
        if (writer.getName().equals("data") && (((Response) bean).getData() instanceof DefaultData)) {
            return;
        } else {
            writer.serializeAsField(bean, jgen, provider);
        }
    }
}
