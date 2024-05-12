import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        parseWorkflow("sample_workflow.txt");
    }

    public static void parseWorkflow(String fileName) {
        Set<String> taskTypes = new HashSet<>();
        Map<String, Integer> jobTypes = new HashMap<>();
        Set<String> stationTaskTypes = new HashSet<>();

        boolean stationsSection = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove leading and trailing whitespace
                line = line.trim();

                // Ignore empty lines and comments
                if (line.isEmpty() || line.startsWith("//")) {
                    continue;
                }

                // Check if stations section has started or ended
                if (line.equals("(STATIONS")) {
                    stationsSection = true;
                    continue;
                } else if (stationsSection && line.equals(")")) {
                    stationsSection = false;
                    continue;
                }

                // Split the line by spaces
                String[] parts = line.split("\\s+");

                // Check if there are enough parts
                if (parts.length < 2) {
                    System.out.println("Syntax error: " + line);
                    continue;
                }

                // Check for task types declaration
                if (parts[0].equals("(TASKTYPES")) {
                    for (int i = 1; i < parts.length; i++) {
                        taskTypes.add(parts[i]);
                    }
                }

                // Check for job types declaration
                if (parts[0].equals("(JOBTYPES")) {
                    for (int i = 1; i < parts.length; i++) {
                        String jobType = parts[i];
                        int index = line.indexOf(jobType);
                        jobTypes.put(jobType, index);
                    }
                }

                // Check for station declaration
                if (stationsSection) {
                    // Get task types executed by this station
                    for (int i = 5; i < parts.length; i += 2) {
                        stationTaskTypes.add(parts[i]);
                    }
                }
            }

            // Print TASKTYPES
            System.out.println("TASKTYPES:");
            for (String taskType : taskTypes) {
                System.out.println(" - " + taskType);
            }
            System.out.println();

            // Print JOBTYPES
            System.out.println("JOBTYPES:");
            for (Map.Entry<String, Integer> entry : jobTypes.entrySet()) {
                System.out.println(" - " + entry.getKey() + " (Line " + entry.getValue() + ")");
            }
            System.out.println();

            // Print STATIONS
            System.out.println("STATIONS:");
            for (String taskType : stationTaskTypes) {
                System.out.println(" - " + taskType);
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to read file " + fileName);
            e.printStackTrace();
        }
    }
}
