package de.lubowiecki.covidapp.controller;

import com.google.gson.Gson;
import de.lubowiecki.covidapp.model.Country;
import de.lubowiecki.covidapp.model.CountryListOrder;
import de.lubowiecki.covidapp.model.JsonResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/covid")
public class MainController {



    @GetMapping
    public String statistics(@RequestParam Optional<CountryListOrder> order, Model model) throws IOException, InterruptedException {
        JsonResponse json = requestStatisticsFromApi();
        List<Country> countries = json.getResponse();

        if(order.isPresent()) {
            order(countries, order.get());
        }
        model.addAttribute("countries", json.getResponse());
        return "standard";
    }

    @GetMapping("/{country}")
    public String statisticsByCountry(@PathVariable String country, Model model) throws IOException, InterruptedException {
        JsonResponse json = requestStatisticsFromApi("?country=" + country);
        model.addAttribute("countries", json.getResponse());
        return "standard";
    }

    private JsonResponse requestStatisticsFromApi() throws IOException, InterruptedException {
        return requestStatisticsFromApi("");
    }

    private void order(List<Country> countries, CountryListOrder order) {
        Collections.sort(countries, order.getComparator());
    }


    private JsonResponse requestStatisticsFromApi(String params) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://covid-193.p.rapidapi.com/statistics" + params))
                .header("X-RapidAPI-Key", "...") // TODO: Hier kommt der RapidAPI-Key rein
                .header("X-RapidAPI-Host", "covid-193.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        JsonResponse obj = new Gson().fromJson(response.body(), JsonResponse.class);
        return obj;
    }

}
