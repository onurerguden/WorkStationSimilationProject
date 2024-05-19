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
}