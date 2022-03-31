package com.example.lab08_vrundpatel;

import com.opencsv.CSVReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static ObservableList<StudentRecord> load(String fileName) {
        ObservableList<StudentRecord> data = FXCollections.observableArrayList();

        String filePath = "src/main/resources/com/example/lab08_vrundpatel/" + fileName;
        try {
           FileReader filereader = new FileReader(filePath);

            CSVReader csvReader = new CSVReader(filereader);
            List<String[]> allData = csvReader.readAll();

            for(int i = 1; i < allData.size(); i++){
                data.add(new StudentRecord(allData.get(i)[0], Float.parseFloat(allData.get(i)[1]), Float.parseFloat(allData.get(i)[2]), Float.parseFloat(allData.get(i)[3])));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void save(String fileName, ObservableList<StudentRecord> currData) {

        List<String[]> dataString = new ArrayList<String[]>();

        String filePath = "src/main/resources/com/example/lab08_vrundpatel/" + fileName + ".csv";
        File file = new File(filePath);

        try {

            FileWriter outputFile = new FileWriter(file);

            CSVWriter writer = new CSVWriter(outputFile);

            String[] header = { "Student ID", "Assignment", "Midterm", "Final Exam" };
            writer.writeNext(header);

            for(int i = 0; i < currData.size(); i++){
                String[] csvData = { currData.get(i).getStudentID(), String.valueOf(currData.get(i).getAssignments()), String.valueOf(currData.get(i).getMidTerm()), String.valueOf(currData.get(i).getFinalExam()) };
                dataString.add(csvData);
                writer.writeNext(dataString.get(i));
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<StudentRecord> getAllMarks() {
        ObservableList<StudentRecord> marks = FXCollections.observableArrayList();

        // Student ID, Assignments, Midterm, Final exam
        marks.add(new StudentRecord("100100100", 75.0f, 68.0f, 54.25f));
        marks.add(new StudentRecord("100100101", 70.0f, 69.25f, 51.5f));
        marks.add(new StudentRecord("100100102", 100.0f, 97.0f, 92.5f));
        marks.add(new StudentRecord("100100103", 90.0f, 88.5f, 68.75f));
        marks.add(new StudentRecord("100100104", 72.25f, 74.75f, 58.25f));
        marks.add(new StudentRecord("100100105", 85.0f, 56.0f, 62.5f));
        marks.add(new StudentRecord("100100106", 70.0f, 66.5f, 61.75f));
        marks.add(new StudentRecord("100100107", 55.0f, 47.0f, 50.5f));
        marks.add(new StudentRecord("100100108", 40.0f, 32.5f, 27.75f));
        marks.add(new StudentRecord("100100109", 82.5f, 77.0f, 74.25f));

        return marks;
    }
}
