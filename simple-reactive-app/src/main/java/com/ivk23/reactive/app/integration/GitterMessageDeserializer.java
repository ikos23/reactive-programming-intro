package com.ivk23.reactive.app.integration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.ivk23.reactive.app.integration.model.GitterMessage;
import com.ivk23.reactive.app.integration.model.GitterUser;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;
import java.time.Instant;

/**
 * @author Ivan Kos
 */
public class GitterMessageDeserializer extends JsonObjectDeserializer<GitterMessage> {

    @Override
    protected GitterMessage deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext,
                                              ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {

        String id = nullSafeValue(jsonNode.get("id"), String.class);
        String text = nullSafeValue(jsonNode.get("text"), String.class);
        String html = nullSafeValue(jsonNode.get("html"), String.class);
        JsonNode userNode = jsonNode.get("fromUser");
        GitterUser user = objectCodec.readValue(userNode.traverse(objectCodec), GitterUser.class);
        String sent = nullSafeValue(jsonNode.get("sent"), String.class);

        return new GitterMessage(id, text, html, Instant.parse(sent), user);
    }

}

