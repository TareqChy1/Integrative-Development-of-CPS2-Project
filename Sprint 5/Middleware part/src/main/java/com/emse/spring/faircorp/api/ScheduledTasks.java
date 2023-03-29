package com.emse.spring.faircorp.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



@Component
@EnableScheduling
public class ScheduledTasks {
    @Scheduled(fixedRate = 10000) // Run every 10 seconds
    public void saveDataToCsv() throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.67.225/data";
        String jsonString = restTemplate.getForObject(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(jsonString);
            LocalDateTime timestamp = LocalDateTime.now();
            //Date timestamp = new Date();
            String temperature = root.get("temperature").asText();
            String humidity = root.get("humidity").asText();
            String CO2 = root.get("CO2").asText();
            //String time = root.get("time").asText();
            String csvRow = String.format("%s,%s,%s,%s\n", timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")), temperature, humidity, CO2);
            FileWriter csvWriter = new FileWriter("data.csv", true);
            csvWriter.append(csvRow);
            csvWriter.close();
            System.out.println("Data has been saved to data.csv");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    public void saveDataForPreviousHours(Long hours) throws IOException, CsvValidationException {
        // Calculate the start time
        LocalDateTime startTime = LocalDateTime.now().minusHours(hours);

        // Open the CSV file
        CSVReader csvReader = new CSVReader(new FileReader("data.csv"));

        // Read the CSV file line by line
        String[] nextLine;
        List<String[]> data = new ArrayList<>();
        while ((nextLine = csvReader.readNext()) != null) {
            // Parse the time from the CSV row
            LocalDateTime rowTime = LocalDateTime.parse(nextLine[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            // If the row time is after the start time, add it to the list of data
            if (rowTime.isAfter(startTime)) {
                data.add(nextLine);
            }
        }

        // Close the CSV reader
        csvReader.close();

        // Write the data to a new CSV file
        FileWriter csvWriter = new FileWriter("data_previous_hours.csv");
        for (String[] row : data) {
            csvWriter.append(String.join(",", row));
            csvWriter.append("\n");
        }
        csvWriter.close();

        System.out.println("Data has been saved to data_previous_hours.csv");
    }


    public void saveDataEveryXMinutes(Long minutes) throws IOException {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(() -> {
            try {
                // Open the CSV file
                CSVReader csvReader = new CSVReader(new FileReader("data.csv"));

                // Read the CSV file line by line
                String[] nextLine;
                List<String[]> data = new ArrayList<>();
                while ((nextLine = csvReader.readNext()) != null) {
                    // Parse the time from the CSV row
                    LocalDateTime rowTime = LocalDateTime.parse(nextLine[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

                    // Calculate the time X minutes ago
                    LocalDateTime xMinutesAgo = LocalDateTime.now().minusMinutes(minutes);

                    // If the row time is after X minutes ago, add it to the list of data
                    if (rowTime.isAfter(xMinutesAgo)) {
                        data.add(nextLine);
                    }
                }

                // Close the CSV reader
                csvReader.close();

                // Write the data to a new CSV file
                FileWriter csvWriter = new FileWriter("data_every_" + minutes + "_minutes.csv");
                for (String[] row : data) {
                    csvWriter.append(String.join(",", row));
                    csvWriter.append("\n");
                }
                csvWriter.close();

                System.out.println("Data has been saved to data_every_" + minutes + "_minutes.csv");
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }, 0, minutes, TimeUnit.MINUTES);
    }


}