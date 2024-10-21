package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;

public class StoryReader {
    private ObjectMapper mapper;

    public StoryReader() {
        mapper = new ObjectMapper(new YAMLFactory());
        // Falls nötig, können wir hier zusätzliche Module für Jackson konfigurieren.
    }

    public Story readStory(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), Story.class);
    }
}
