package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.*;
import com.emse.spring.faircorp.dto.RuleDto;
import com.emse.spring.faircorp.model.Action;
import com.emse.spring.faircorp.model.Condition;
import com.emse.spring.faircorp.model.Rule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/rule")
@Transactional
public class RuleController {
    private final RuleDao ruleDao;
    private final ActionDao actionDao;
    private final ConditionDao conditionDao;

    //logging part
    private static final Logger logging = LoggerFactory.getLogger(RuleController.class);
    public RuleController(RuleDao ruleDao, ConditionDao conditionDao, ActionDao actionDao) {
        this.ruleDao = ruleDao;
        this.conditionDao = conditionDao;
        this.actionDao = actionDao;
    }


    @GetMapping
    public List<RuleDto> findAll() {
        //logging part
        logging.info("Searching all Rule list");
        return ruleDao.findAll().stream().map(RuleDto::new).collect(Collectors.toList());
    }

    @GetMapping(path = "/{rule_id}")
    public RuleDto findById(@PathVariable Long rule_id) {
        //logging part
        logging.info("Searching  Rule using ruleId");
        return ruleDao.findById(rule_id).map(RuleDto::new).orElse(null);
    }


    @PostMapping
    public RuleDto create(@RequestBody RuleDto dto) {
        Rule rule = null;
        if (dto.getId() == null) {
            rule = ruleDao.save(new Rule(dto.getName(), dto.getActive(), dto.getDate(), dto.getTime()));
        } else {
            rule = ruleDao.getReferenceById(dto.getId());
            rule.setName(dto.getName());
            rule.setActive(dto.getActive());
            rule.setDate(dto.getDate());
            rule.setTime(dto.getTime());
        }
        return new RuleDto(rule);

    }

    // Executing rules
    @GetMapping(path = "/execute")
    public void executeRules() throws IOException, CsvValidationException {
        List<Rule> rules = ruleDao.findAll();
        for (Rule rule : rules) {
            // Find the condition name for the current rule by ID
            List<Condition> conditions = conditionDao.findCondition(rule.getId());
            // execute the output for each rule
            System.out.println("Executing rule: " + rule.getName());
            // ... add your logic here
            boolean presence = false;
            for (Condition condition : conditions) {
                //System.out.print(condition.getName() + " ");
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://192.168.67.225/data";
                String jsonString = restTemplate.getForObject(url, String.class);
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode root = mapper.readTree(jsonString);
                    presence = root.get("presence").asBoolean();
                    String temp = root.get("temperature").asText();
                    //System.out.println("Presence: " + presence);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                if (condition.getName().equals("presence detected") && presence) {

                    System.out.println("Result of presence: " +presence);
                    //for action part
                    Action action = actionDao.findActionByRuleId(rule.getId()).get(0);
                    System.out.println("Result of Action Name: " +action.getName());

                    // Turn heating on
                    if (action.getName().equals("turnn heating on")) {
                        double temperature = 25.0;
                        sendTemperatureRequest(temperature);
                        System.out.println("turning heating on, 25.0 °C");
                    }
                } else if (condition.getName().equals("presence not detected") && !presence) {
                    System.out.println("Result of presence: " +presence);
                    //for action part
                    Action action = actionDao.findActionByRuleId(rule.getId()).get(0);
                    System.out.println("Result of Action Name: " +action.getName());
                    // Turn heating off
                    if (action.getName().equals("turnn heating off")) {
                        sendHeatingModeRequest("COMFORT_1");
                        System.out.println("turning heating off, Mode:COMFORT_1");
                    }

                } else if (condition.getName().equals("store data") && condition.getConditionType().equals("previous")) {
                    System.out.println("store data in a CSV file");
                    //for action part
                    Action action = actionDao.findActionByRuleId(rule.getId()).get(0);
                    System.out.println("Result of Action Name: " + action.getName());
                    // Store data in CSV file
                    if (action.getName().equals("store data in a CSV file")) {
                        // Save the data for the previous hour(s)
                        ScheduledTasks scheduledTasks = new ScheduledTasks();
                        scheduledTasks.saveDataForPreviousHours(condition.getConditionValue());
                        System.out.println("Data stored successfully!");
                    }
                }else if (condition.getName().equals("store data") && condition.getConditionType().equals("every")) {
                    System.out.println("store data in  CSV file");
                    //for action part
                    Action action = actionDao.findActionByRuleId(rule.getId()).get(0);
                    System.out.println("Result of Action Name: " + action.getName());
                    // Store data in CSV file
                    if (action.getName().equals("store data in a CSV file")) {
                        // Save the data for the previous hour(s)
                        ScheduledTasks scheduledTasks = new ScheduledTasks();
                        scheduledTasks.saveDataEveryXMinutes(condition.getConditionValue());
                        System.out.println("Data stored successfully!");
                    }
                }


            }
            }
        }


    private void sendTemperatureRequest(double temperature) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/heater/1/set-temperature";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject requestBody = new JSONObject();
        requestBody.put("temperature", temperature);
        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            // Handle error: failed to set temperature
            throw new RuntimeException("Failed to set temperature");
        }
    }

    private void sendHeatingModeRequest(String mode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/api/heater/1/set-mode";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject requestBody = new JSONObject();
        requestBody.put("mode", mode);
        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            // Handle error: failed to set heating mode
            throw new RuntimeException("Failed to set heating mode");
        }
    }


    @GetMapping("/data/{hours}")
    public void downloadDataForPreviousHours(@PathVariable Long hours, HttpServletResponse response) throws IOException {
        List<String[]> data = new ArrayList<>();
        File csvFile = new File("data.csv");
        if (csvFile.exists()) {
            try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
                String[] row;
                while ((row = csvReader.readNext()) != null) {
                    try {
                        LocalDateTime rowTime = LocalDateTime.parse(row[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        LocalDateTime currentTime = LocalDateTime.now();
                        long diffInHours = ChronoUnit.HOURS.between(rowTime, currentTime);
                        if (diffInHours <= hours) {
                            data.add(row);
                        }
                    } catch (DateTimeParseException e) {
                        // Handle invalid date format in CSV row
                        System.err.println("Error parsing date in CSV row: " + Arrays.toString(row));
                    }
                }
            } catch (CsvValidationException e) {
                // Handle invalid CSV row
                System.err.println("Error reading CSV row: " + e.getMessage());
            }
        }
        // Set the response headers
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"data.csv\"");

        // Write the data to the response
        try (CSVWriter csvWriter = new CSVWriter(response.getWriter())) {
            csvWriter.writeAll(data);
        }
    }


    @DeleteMapping(path = "/{rule_id}")
    public void delete(@PathVariable Long rule_id) {
        //logging part
        logging.info("Removed rule");
        ruleDao.deleteById(rule_id);
    }
}


// For json data download
   /* @GetMapping("/data/{hours}")
    public List<String[]> getDataForPreviousHours(@PathVariable int hours) throws IOException {
        List<String[]> data = new ArrayList<>();
        File csvFile = new File("data.csv");
        if (csvFile.exists()) {
            try (CSVReader csvReader = new CSVReader(new FileReader(csvFile))) {
                String[] row;
                while ((row = csvReader.readNext()) != null) {
                    try {
                        LocalDateTime rowTime = LocalDateTime.parse(row[0], DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                        LocalDateTime currentTime = LocalDateTime.now();
                        long diffInHours = ChronoUnit.HOURS.between(rowTime, currentTime);
                        if (diffInHours <= hours) {
                            data.add(row);
                        }
                    } catch (DateTimeParseException e) {
                        // Handle invalid date format in CSV row
                        System.err.println("Error parsing date in CSV row: " + Arrays.toString(row));
                    }
                }
            } catch (CsvValidationException e) {
                // Handle invalid CSV row
                System.err.println("Error reading CSV row: " + e.getMessage());
            }
        }
        return data;
    }*/
