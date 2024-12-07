package org.example;

import javax.json.Json;
import javax.json.JsonArray;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Parser {
    private static final String urlLine = "https://dummy-json.mock.beeceptor.com/todos";

    public void parse() {
        System.out.println("-----TASKS-----");
        try {
            var jsonArray = createJsonArray();
            for (int objectIndex = 0; objectIndex < jsonArray.size(); objectIndex++) {
                getResult(objectIndex, jsonArray);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("-----USERS-----");
        System.out.println("\n");
        new UserParser().parse();
    }

    private void getResult(int objectIndex, JsonArray jsonArray) {
        var jsonObject = jsonArray.getJsonObject(objectIndex);
        var userId = jsonObject.getInt("userId", -1);
        var id = jsonObject.getInt("id", -1);
        var title = jsonObject.getString("title", "none");
        System.out.println("userID: " + (userId != -1 ? userId : "none"));
        System.out.println("ID: " + (id != -1 ? id : "none"));
        System.out.println("Title: " + title);
        System.out.println("----------------------");
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
}