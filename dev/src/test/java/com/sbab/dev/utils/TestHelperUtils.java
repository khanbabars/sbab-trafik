package com.sbab.dev.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestHelperUtils {
    public static String readFileFromResources(String filename) throws URISyntaxException, IOException {
            URL resource = TestHelperUtils.class.getClassLoader().getResource(filename);
            return Files.readString(Paths.get(resource.toURI()));
        }

    }
