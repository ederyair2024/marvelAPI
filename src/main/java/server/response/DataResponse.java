package server.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DataResponse {

    public int total;
    public List<ElementsResponse> results;

}
