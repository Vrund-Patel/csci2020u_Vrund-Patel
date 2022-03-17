package com.example.lab05;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.scene.Group;

public class Main extends Application{
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setWidth(530);
        stage.setHeight(500);
        stage.setTitle("Lab 05 Solution");

        TableView tableView = new TableView();

        TableColumn studentIDColumn = new TableColumn("Student ID");
        TableColumn assignmentColumn = new TableColumn("Assignment");
        TableColumn midTermColumn = new TableColumn("Midterm");
        TableColumn finalExamColumn = new TableColumn("Final Exam");
        TableColumn finalMarkColumn = new TableColumn("Final Mark");
        TableColumn letterGradeColumn = new TableColumn("Letter Grade");

        tableView.getColumns().addAll(studentIDColumn, assignmentColumn, midTermColumn, finalExamColumn, finalMarkColumn, letterGradeColumn);

        studentIDColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("studentID"));
        assignmentColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("midTerm"));
        midTermColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("assignments"));
        finalExamColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Float>("finalExam"));
        finalMarkColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, Double>("finalMark"));
        letterGradeColumn.setCellValueFactory(new PropertyValueFactory<StudentRecord, String>("letterGrade"));

        tableView.setItems(DataSource.getAllMarks());

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(tableView);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}