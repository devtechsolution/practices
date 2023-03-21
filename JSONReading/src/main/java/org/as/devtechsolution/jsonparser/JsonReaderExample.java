package org.as.devtechsolution.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;

public class JsonReaderExample {
    public static void main(String[] args) throws IOException {
        String json = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        String name = rootNode.get("name").asText();
        int age = rootNode.get("age").asInt();

        System.out.println("Name: " + name);
        System.out.println("Age: " + age);


        usingJsonObject();
        usingJacson();
    }

    private static void usingJacson() throws JsonProcessingException {
        String json = "[{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"123 Main St\",\"city\":\"New York\",\"state\":\"NY\"},\"phones\":[{\"type\":\"home\",\"number\":\"555-1234\"},{\"type\":\"work\",\"number\":\"555-5678\"}]},{\"name\":\"Jane\",\"age\":25,\"address\":{\"street\":\"456 Elm St\",\"city\":\"Los Angeles\",\"state\":\"CA\"},\"phones\":[{\"type\":\"home\",\"number\":\"555-4321\"},{\"type\":\"work\",\"number\":\"555-8765\"}]}]";

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        for (JsonNode personNode : rootNode) {
            String name = personNode.get("name").asText();
            String street = personNode.get("address").get("street").asText();
            String city = personNode.get("address").get("city").asText();
            String phone = personNode.get("phones").get(0).get("number").asText();

            System.out.println("Name: " + name);
            System.out.println("Street: " + street);
            System.out.println("City: " + city);
            System.out.println("Phone: " + phone);
        }
    }

    private static void usingJsonObject() {
        String json = "{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"123 Main St\",\"city\":\"New York\",\"state\":\"NY\"},\"phones\":[{\"type\":\"home\",\"number\":\"555-1234\"},{\"type\":\"work\",\"number\":\"555-5678\"}]}";

        JSONObject obj = new JSONObject(json);

        String name = obj.getString("name");
        String street = obj.getJSONObject("address").getString("street");
        String city = obj.getJSONObject("address").getString("city");
        String phone = obj.getJSONArray("phones").getJSONObject(0).getString("number");

        System.out.println("Name: " + name);
        System.out.println("Street: " + street);
        System.out.println("City: " + city);
        System.out.println("Phone: " + phone);
    }
}
