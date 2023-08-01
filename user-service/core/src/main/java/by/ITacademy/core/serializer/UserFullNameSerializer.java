package by.ITacademy.core.serializer;


import by.ITacademy.core.dto.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class UserFullNameSerializer extends JsonSerializer<User> {
    @Override
    public void serialize(User user,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("fullName", user.getName() + " " + user.getSurname() +
                " " + user.getPatronymic());
        jsonGenerator.writeStringField("email", user.getEmail());
        jsonGenerator.writeStringField("role", String.valueOf(user.getRole()));
        jsonGenerator.writeEndObject();
    }
}
