package de.lubowiecki.covidapp.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {

    private int active;
    private int critical;
    private int recovered;
    private int total;

}
