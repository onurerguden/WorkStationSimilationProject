import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Task> taskTypes = new ArrayList<>();
    private static ArrayList<Job> jobTypes = new ArrayList<>();
    private static ArrayList<Station> stationss = new ArrayList<>();

    public static void main(String[] args) {
        takeInputFile();

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
}
