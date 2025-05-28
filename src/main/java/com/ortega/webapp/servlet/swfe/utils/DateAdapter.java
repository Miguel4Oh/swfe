package com.ortega.webapp.servlet.swfe.utils;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(dateFormat.format(date));
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return new Date(dateFormat.parse(json.getAsString()).getTime());
        } catch (ParseException e) {
            throw new JsonParseException("Failed to parse Date", e);
        }
    }
}
