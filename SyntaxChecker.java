import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;

class SyntaxChecker {
    private List<String> errors;
    public SyntaxChecker() {
        errors = new ArrayList<>();
    }

    public void add(String error) {
        errors.add(error);
    }

    public void printErrors() {
        if (errors.isEmpty()) {
            System.out.println("No errors found.");
        }
        else {
            System.out.println("Errors detected are:");
            for (String error : errors) {
                System.out.println(error);
            }
        }
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }


    private static boolean checkAccessibility(String fileName, SyntaxChecker SC) {
        File file = new File(fileName);
        if (!file.exists() || !file.canRead()) {
            SC.add("The file does not exist or is not accessible: " + fileName);
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            SC.add("An error occurred while reading the file: " + e.getMessage());
            return false;
        }

        return true;
    }

}