package com.projectcric.cricdata.controller;

import com.projectcric.cricdata.model.Venue;
import com.projectcric.cricdata.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/venue")
public class VenueController {
    @Autowired
    private VenueService venueService;

    @PostMapping("/upload")
    public List<Venue> uploadVenues() throws IOException {

        List<Venue> venues = venueService.createVenue();
        return venues;
    }

    @PostMapping("/no-city")
    public List<Venue> getNoCityVenues() {
        List<Venue> venues = venueService.getVenueWithNoCity();
        return venues;
    }

    @GetMapping("/get-updated-cities")
    public Map<String,String> noCities() throws IOException {
        return venueService.updateCity();
    }


}
