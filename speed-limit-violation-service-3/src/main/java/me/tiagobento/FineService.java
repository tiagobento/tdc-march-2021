package me.tiagobento;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class FineService {

    private static final String FINE_SERVICE_URL = "localhost:8080";

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    @SuppressWarnings("unchecked")
    public static Map<String, Object> calculateFine(Map<String, Object> violation) throws Exception {

        var objMapper = new ObjectMapper();

        var request = HttpRequest.newBuilder() //
                .uri(URI.create("http://" + FINE_SERVICE_URL + "/Fine/Fine")) //
                .header("Content-Type", "application/json") //
                .POST(BodyPublishers.ofString(objMapper.writeValueAsString(Map.of("Violation", violation)))) //
                .build();

        var response = HTTP_CLIENT.send(request, BodyHandlers.ofString());

        return objMapper.readValue(response.body(), HashMap.class);
    }
}
