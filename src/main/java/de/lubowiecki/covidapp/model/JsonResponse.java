package de.lubowiecki.covidapp.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class JsonResponse {

    private String get;
    private Map<String, String> parameters;

    private String[] errors;

    private int results;

    private List<Country> response;
}
