package com.projectcric.cricdata.service;

import com.projectcric.cricdata.model.Venue;
import com.projectcric.cricdata.repository.VenueRepository;
import com.projectcric.cricdata.util.CreateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class VenueService {

    @Autowired
    private VenueRepository venueRepo;

    @Autowired
    private CreateUtility createUtil;

    public List<Venue> createVenue() throws IOException {
        List<Venue> venues = new ArrayList<>();
        for (File file : createUtil.getFiles()) {
            String venueName = "";
            Venue venue = new Venue();
            List<String> fileStr = createUtil.getFileData(file.getPath());
            for (String str : fileStr) {
                String[] splitData = str.split(",");
                if (splitData[0].equalsIgnoreCase("info")) {
                    if (splitData[1].equalsIgnoreCase("venue")) {
                        if (!venuePresent(splitData[2])) {
                            venueName = splitData[2];
                        } else {
                            break;
                        }
                    } else if (splitData[1].equalsIgnoreCase("city") && !venueName.equals("")) {
                        venue.setVenueName(venueName);
                        if (splitData.length > 2) {
                            venue.setCity(splitData[2]);
                        }
                        venue = addVenue(venue);
                        venues.add(venue);
                        break;
                    }
                }
            }
        }
        return venues;
    }

    public Map<String, String> updateCity() throws IOException {
        List<Venue> noCityVenues = getVenueWithNoCity();
        Map<String, String> venueCities = new HashMap<>();
        for (File file : createUtil.getFiles()) {
            String venueName = "";
            Venue venue = new Venue();
            List<String> fileStr = createUtil.getFileData(file.getPath());
            for (String str : fileStr) {
                String[] splitData = str.split(",");
                if (splitData[0].equalsIgnoreCase("info")) {
                    if (splitData[1].equalsIgnoreCase("venue")) {
                        if (!venuePresent(splitData[2])) {
                            venueName = splitData[2];
                        } else {
                            break;
                        }
                    } else if (splitData[1].equalsIgnoreCase("city") && !venueName.equals("")) {
                        if (splitData.length > 2) {
                            venueCities.put(venueName, splitData[2]);
                        }
                        break;
                    }
                }
            }
        }
        Map<String, String> updatedCities = new HashMap<>();
        for (Venue venue : noCityVenues) {
            updatedCities.put(venue.getVenueName(), venueCities.get(venue.getVenueName()));
        }
        return updatedCities;
    }

    private Venue addVenue(Venue venue) {
        return venueRepo.save(venue);
    }

    public boolean venuePresent(String venueName) {
        Optional<Venue> venue = venueRepo.findByVenueName(venueName);
        return venue.isPresent();
    }

    public List<Venue> getVenueWithNoCity() {
        List<Venue> venues = venueRepo.findByCityIsNull();
        return venues;
    }

}
