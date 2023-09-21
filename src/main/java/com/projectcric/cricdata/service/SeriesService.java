package com.projectcric.cricdata.service;

import com.projectcric.cricdata.model.Series;
import com.projectcric.cricdata.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepo;


    public Series getSeries(String seriesName, String season) {
        Optional<Series> series = seriesRepo.findByNameAndSeason(seriesName, season);
        if (series.isPresent())
            return series.get();
        else {
            return saveSeries(seriesName, season);
        }
    }

    public Series saveSeries(String seriesName, String season) {
        Series series = new Series().builder().name(seriesName)
                .season(season)
                .build();
        series = seriesRepo.save(series);
        return series;

    }
}
