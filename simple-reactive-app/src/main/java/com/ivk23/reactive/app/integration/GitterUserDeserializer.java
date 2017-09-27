package com.ivk23.reactive.app.integration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.ivk23.reactive.app.integration.model.GitterUser;
import org.springframework.boot.jackson.JsonObjectDeserializer;

import java.io.IOException;

/**
 * @author Ivan Kos
 */
public class GitterUserDeserializer extends JsonObjectDeserializer<GitterUser> {

    @Override
    protected GitterUser deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext,
                                           ObjectCodec objectCodec, JsonNode jsonNode) throws IOException {
        String userId = nullSafeValue(jsonNode.get("id"), String.class);
        String userName = nullSafeValue(jsonNode.get("username"), String.class);
        String userDisplayName = nullSafeValue(jsonNode.get("displayName"), String.class);
        String userAvatar = nullSafeValue(jsonNode.get("avatarUrl"), String.class);

        return new GitterUser(userId, userName, userDisplayName, userAvatar);
    }
}
