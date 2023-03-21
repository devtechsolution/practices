package org.as.devtechsolution.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonReaderExample {
    public static void main(String[] args) {
        String json = "[{\"name\":\"John\",\"age\":30,\"address\":{\"street\":\"123 Main St\",\"city\":\"New York\",\"state\":\"NY\"},\"phones\":[{\"type\":\"home\",\"number\":\"555-1234\"},{\"type\":\"work\",\"number\":\"555-5678\"}]},{\"name\":\"Jane\",\"age\":25,\"address\":{\"street\":\"456 Elm St\",\"city\":\"Los Angeles\",\"state\":\"CA\"},\"phones\":[{\"type\":\"home\",\"number\":\"555-4321\"},{\"type\":\"work\",\"number\":\"555-8765\"}]}]";

        JSONArray arr = new JSONArray(json);

        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);

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
}
