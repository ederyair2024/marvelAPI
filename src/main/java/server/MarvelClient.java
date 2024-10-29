package server;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import server.response.MarvelResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@RegisterRestClient(baseUri = "https://gateway.marvel.com/v1/public")
public interface MarvelClient {

    @GET
    @Path("/characters")
    MarvelResponse getCharacters(@QueryParam("ts") long timestamp,
                                 @QueryParam("apikey") String publicKey,
                                 @QueryParam("hash") String hash,
                                 @QueryParam("limit") int limit);

}
