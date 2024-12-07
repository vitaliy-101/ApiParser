package org.example;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserParser {

    private static final String urlLine = "https://fake-json-api.mock.beeceptor.com/users";

    public void parse() {
        try {
            var jsonArray = createJsonArray();
            for (int objectIndex = 0; objectIndex < jsonArray.size(); objectIndex++) {
                getResult(objectIndex, jsonArray);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("\n");
    }

    private JsonArray createJsonArray() throws IOException {
        var url = new URL(urlLine);
        var connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        var responseStream = connection.getInputStream();
        var jsonReader = Json.createReader(responseStream);
        var jsonArray = jsonReader.readArray();
        jsonReader.close();
        return jsonArray;
    }

    private void getResult(int objectIndex, JsonArray jsonArray) {
        JsonObject jsonObject = jsonArray.getJsonObject(objectIndex);

        int id = jsonObject.getInt("id", -1);
        var name = jsonObject.getString("name", "none");
        var company = jsonObject.getString("company", "none");
        var username = jsonObject.getString("username", "none");
        var email = jsonObject.getString("email", "none");
        var address = jsonObject.getString("address", "none");
        var country = jsonObject.getString("country", "none");
        var phone = jsonObject.getString("phone", "none");

        System.out.println("ID: " + (id != -1 ? id : "none"));
        System.out.println("Name: " + name);
        System.out.println("Company: " + company);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
        System.out.println("Country: " + country);
        System.out.println("Phone: " + phone);
        System.out.println("----------------------");
    }
}