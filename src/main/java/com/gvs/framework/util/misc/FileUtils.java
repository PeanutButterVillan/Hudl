package com.gvs.framework.util.misc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

//import org.apache.log4j.Logger;

public class FileUtils {

//    private static final Logger LOGGER = Logger.getLogger(FileUtils.class);
    private static final Random randomGenerator = new Random();

    private static String getLineFromFile(String fileName, int maxLines) throws IOException {
        int lineNumber = randomGenerator.nextInt(maxLines);
        String line = "";

        InputStream inputStream  = FileUtils.class.getClassLoader().getResourceAsStream("data/companyHouseNumbers.txt");
        Stream<String> lines = new BufferedReader(new InputStreamReader(inputStream)).lines();

        line = lines
            .skip(lineNumber)
            .findFirst()
            .get();

//        LOGGER.info("Selected random line: " + lineNumber + " from file: " + fileName + " with value: " + line);

        return line;
    }

    public static List<String> getRandomLinesFromFile(String fileName, int randomLinesCount, int linesInFile)
      throws IOException {
      List<String> lines = new ArrayList<>();
      for (int i = 1; i <= randomLinesCount; i++) {
        String line = getLineFromFile(fileName, linesInFile);
        lines.add(line);
      }

      return lines;
    }
}
