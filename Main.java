import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    private static ArrayList<Task> taskTypes = new ArrayList<>();
    private static ArrayList<Job> jobTypes = new ArrayList<>();
    private static ArrayList<Station> stations = new ArrayList<>();


    public static void main(String[] args) {

        takeInputFile();
        readJobFile();
    }

    public static void takeInputFile() {
        String fileName = "sample_workflow.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            System.out.println("--File is accessible--");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Error: The file does not exist or is not accessible.");
            } else {
                e.printStackTrace();
            }
        }
    }
    public static void readJobFile() {
        String fileName = "sample_jobFile.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            int lineNumber = 0;
            HashSet<String> jobIDs = new HashSet<>(); // To check uniqueness of job IDs

            System.out.println("--Job File Contents--");

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                String[] tokens = line.split("\\s+");

                if (tokens.length != 4) {
                    System.out.println("Syntax error at line " + lineNumber + ": " + line);
                    continue;
                }

                String jobID = tokens[0];
                String jobType = tokens[1];
                String startTime = tokens[2];
                int duration;
                try {
                    duration = Integer.parseInt(tokens[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Semantic error at line " + lineNumber + ": Duration must be a valid integer.");
                    continue;
                }

                // Check uniqueness of job ID
                if (!jobIDs.add(jobID)) {
                    System.out.println("Semantic error at line " + lineNumber + ": Duplicate job ID '" + jobID + "'.");
                    continue;
                }

                if (duration < 0) {
                    System.out.println("Semantic error at line " + lineNumber + ": Duration must be non-negative.");
                    continue;
                }

                String deadline = computeDeadline(duration, startTime);

                Job job = new Job(jobID, jobType, duration, startTime, deadline);

                jobTypes.add(job);

                System.out.println("Job ID: " + job.getJobID() + ", Job Type: " + job.getJobType() +
                        ", Start Time: " + job.getStartTime() + ", Duration: " + job.getDuration() +
                        " minutes, Deadline: " + job.getDeadline());
            }
            reader.close();
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                System.out.println("Error: The job file does not exist or is not accessible.");
            } else {
                e.printStackTrace();
            }
        }
    }
    public static String computeDeadline(int duration, String startTime) {
        int start = Integer.parseInt(startTime);
        int deadline = start + duration;
        return String.valueOf(deadline);
    }


}

