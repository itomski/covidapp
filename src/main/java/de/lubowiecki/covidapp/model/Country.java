package de.lubowiecki.covidapp.model;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Country {

    private String continent;

    private String country;

    private int population;

    private String day;

    private String time;

    private Result cases;

    private Result deaths;

    private Result tests;
}
