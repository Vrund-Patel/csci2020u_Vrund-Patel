package com.example.lab10_vrundpatel;

import java.net.*;
import java.io.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Client extends Application {

    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    @Override
    public void start(Stage stage) throws IOException {

        Socket socket = new Socket("localhost",1234);

        Scene scene = new Scene(new Group());
        stage.setWidth(530);
        stage.setHeight(500);
        stage.setTitle("Client");

        Label username_label = new Label("username:");
        Label message_label = new Label("message:");

        TextField username = new TextField();

        TextField message = new TextField();

        Button send = new Button("Send");

        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {

                    // takes input from terminal
                    input  = new DataInputStream(System.in);

                    // sends output to the socket
                    out    = new DataOutputStream(socket.getOutputStream());

                } catch(UnknownHostException u)
                {
                    System.out.println(u);
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }

                // string to read message from input
                String line = username.getText() + ": " + message.getText();

                try {
                    out.writeUTF(line);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        Button exit = new Button("Exit");

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // close the connection
                try
                {
                    input.close();
                    out.close();
                    socket.close();
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }

                Platform.exit();
            }
        });

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(username_label,username,message_label,message,send,exit);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    // Run the program.
    public static void main(String[] args) throws IOException {launch();}
}

