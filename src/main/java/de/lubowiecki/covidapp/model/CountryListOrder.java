package de.lubowiecki.covidapp.model;

import java.util.Comparator;

public enum CountryListOrder {

    COUNTRY, COUNTRY_DESC, POPULATION, POPULATION_DESC, TESTS, TESTS_DESC, CASES, CASES_DESC, DEATHS, DEATHS_DESC;

    public Comparator<Country> getComparator() {

        switch(this) {
            /* case COUNTRY: return (a, b) -> a.getCountry().compareTo(b.getCountry()); */
            case COUNTRY_DESC: return CountryListOrder.COUNTRY.getComparator().reversed();
            case POPULATION: return (a, b) -> Integer.compare(a.getPopulation(), b.getPopulation());
            case POPULATION_DESC: return CountryListOrder.POPULATION.getComparator().reversed();
            case TESTS: return (a, b) -> Integer.compare(a.getTests().getTotal(), b.getTests().getTotal());
            case TESTS_DESC: return CountryListOrder.TESTS.getComparator().reversed();
            case CASES: return (a, b) -> Integer.compare(a.getCases().getTotal(), b.getCases().getTotal());
            case CASES_DESC: return CountryListOrder.CASES.getComparator().reversed();
            case DEATHS: return (a, b) -> Integer.compare(a.getDeaths().getTotal(), b.getDeaths().getTotal());
            case DEATHS_DESC: return CountryListOrder.DEATHS.getComparator().reversed();
            default: return (a, b) -> a.getCountry().compareTo(b.getCountry());
        }
    }
}
