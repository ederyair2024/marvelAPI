package server;

import server.response.MarvelResponse;
import service.MarvelService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

@Path("/marvel")
public class MarvelAPIResource {

    @Inject
    MarvelService marvelService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/allList")
    public String getCharacterList() {
        MarvelResponse response = marvelService.getMarvelCharacters();
        StringBuilder result = new StringBuilder();

        result.append("Número total de personajes: ").append(response.data.total).append("\n\n");

        String characters = response.data.results.stream()
                .map(character -> "ID: " + character.getId() + ", Nombre: " + character.getName())
                .collect(Collectors.joining("\n"));

        result.append(characters);
        return result.toString();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/find")
    public String findCharacter(@QueryParam("nombre") String  name) {
        MarvelResponse response = marvelService.getMarvelCharacters();
        StringBuilder result = new StringBuilder();
        String characters = response.data.results.stream().filter(data -> data.getName().equals(name))
                .map(character -> "ID: " + character.getId() + ", Nombre: " + character.getName())
                .collect(Collectors.joining("\n"));

        result.append(characters);
        return result.toString();
    }




}
