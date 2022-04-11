package com.example.lab10_vrundpatel;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

// A Java program for a Server
import java.net.*;
import java.io.*;

public class Server extends Application
{
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;

    @Override
    public void start (Stage stage) throws IOException {
        Scene scene = new Scene(new Group());
        stage.setWidth(530);
        stage.setHeight(500);
        stage.setTitle("Server");

        Text client_message = new Text();

        Button exit = new Button("Exit");

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // close the connection
                try {
                    System.out.println("Closing connection");

                    // close connection
                    socket.close();
                    in.close();
                } catch (IOException i) {
                    System.out.println(i);
                }

                Platform.exit();
            }
        });

        server = new ServerSocket(1234);
        System.out.println("Server started");

        String line = "";

        System.out.println("Waiting for a client ...");

        socket = server.accept();
        System.out.println("Client accepted");


            try {
                while (true) {
                    in = new DataInputStream(
                            new BufferedInputStream(socket.getInputStream()));

                    line += in.readUTF();
                    line += "\n";
                    client_message.setText(line);

                    VBox vbox = new VBox();
                    vbox.setSpacing(5);
                    vbox.setPadding(new Insets(10, 0, 0, 10));
                    vbox.getChildren().addAll(client_message, exit);

                    ((Group) scene.getRoot()).getChildren().addAll(vbox);

                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException i) {
                System.out.println(i);
            }
    }
}

