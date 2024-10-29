package service;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import server.MarvelClient;
import server.response.MarvelResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.security.MessageDigest;
import java.util.Formatter;

@ApplicationScoped
public class MarvelService {

    private final String publicKey = "ce0e26bf2f195432fdf314e0a488dcc2";
    private final String privateKey = "5e4773062d1847c4ee47b47dbb45127d9ad65ab3";


    @Inject
    @RestClient
    MarvelClient marvelClient;

    public MarvelResponse getMarvelCharacters() {
        long timestamp = System.currentTimeMillis();
        String hash = generateHash(timestamp, publicKey, privateKey);
        return marvelClient.getCharacters(timestamp, publicKey, hash, 100);
    }

    private String generateHash(long timestamp, String publicKey, String privateKey) {
        try {
            String value = timestamp + privateKey + publicKey;
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(value.getBytes());
            Formatter formatter = new Formatter();
            for (byte b : hash) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
