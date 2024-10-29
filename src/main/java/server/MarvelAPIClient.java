package server;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class MarvelAPIClient {

    private final String publicKey = "ce0e26bf2f195432fdf314e0a488dcc2";
    private final String privateKey = "5e4773062d1847c4ee47b47dbb45127d9ad65ab3";
    private final String baseUrl = "https://gateway.marvel.com/v1/public";

    public String getCharacters() {
        long timestamp = System.currentTimeMillis();
        String hash = generateHash(timestamp);

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseUrl)
                .path("characters")
                .queryParam("ts", timestamp)
                .queryParam("apikey", publicKey)
                .queryParam("hash", hash);

        Response response = target.request(MediaType.APPLICATION_JSON).get();

        String jsonResponse = response.readEntity(String.class);
        response.close();
        return jsonResponse;
    }

    private String generateHash(long timestamp) {
        String input = timestamp + privateKey + publicKey;
        return org.apache.commons.codec.digest.DigestUtils.md5Hex(input);
    }
}
