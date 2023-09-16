package com.projectcric.cricdata.util;

import com.projectcric.cricdata.model.Team;
import com.projectcric.cricdata.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateUtility {


    public File[] getFiles() {
        return new File("/Users/jagadeeshroyal/Downloads/all_male_csv").listFiles();
    }

    public List<String> getFileData(String path) throws IOException {
        return Files.lines(Path.of(path)).collect(Collectors.toList());
    }
}
