package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadDataFile {
    public static ArrayList<String> readAllDataAtOnce(String file) {
        ArrayList<String> names = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(file))){
            while (scanner.hasNextLine()) {
                names.add(scanner.nextLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return names;
    }
}
