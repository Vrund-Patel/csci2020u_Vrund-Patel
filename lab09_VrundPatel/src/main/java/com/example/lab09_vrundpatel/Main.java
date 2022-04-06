package com.example.lab09_vrundpatel;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

public class Main extends Application {

    static ArrayList<String> stockNames = new ArrayList<>();
    ArrayList<String> stock1 = new ArrayList<>();
    ArrayList<String> stock2 = new ArrayList<>();
    Group root = new Group();
    Group rootGraph = new Group();
    int counter = 0;

    @Override
    public void start(Stage stage) throws IOException {
        downloadStockPrices("GOOG");
        downloadStockPrices("AAPL");

        for (int i = 0; i < stockNames.size(); i++) {

            drawLinePlot(stockNames.get(i) + ".csv", stage);
        }
    }

    public static void downloadStockPrices(String stockTicker) throws IOException {

        stockNames.add(stockTicker);

        String tickerSymbol = "https://query1.finance.yahoo.com/v7/finance/download/" + stockTicker + "?period1=1262322000&period2=1451538000&interval=1mo&events=history&includeAdjustedClose=true";
        URL website = new URL(tickerSymbol);
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(stockTicker + ".csv");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
    }

    public void drawLinePlot(String fileName, Stage stage) throws IOException {
        String[] values;
        BufferedReader csvReader = new BufferedReader(new FileReader(fileName));
        String line = csvReader.readLine();

        Line drawLine = new Line();
        drawLine.setStartX(50);
        drawLine.setEndX(50);
        drawLine.setStartY(50);
        drawLine.setEndY(600);

        Line drawLine2 = new Line();
        drawLine2.setStartX(50);
        drawLine2.setEndX(600);
        drawLine2.setStartY(600);
        drawLine2.setEndY(600);


        if (stock1.isEmpty()) {
            while ((line = csvReader.readLine()) != null) {
                values = line.split(",", -1);
                stock1.add(values[4]);
            }
            plotLine();
        } else {
            while ((line = csvReader.readLine()) != null) {
                values = line.split(",", -1);
                stock2.add(values[4]);
            }
            plotLine();
        }
        csvReader.close();
        if ((stock1 != null) && (stock2 != null) && (counter == 2)) {
            rootGraph.getChildren().add(drawLine);
            rootGraph.getChildren().add(drawLine2);
            rootGraph.getChildren().add(root);
            Scene scene = new Scene(rootGraph, 650, 650);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void plotLine() {
        if (counter == 0) {
            for (int i = 0; i < stock1.size() - 1; i++) {
                Line line = new Line();
                line.setStartX(50 + (i * 7));
                line.setEndX(57 + (i * 7));
                line.setStartY(600 - (Float.parseFloat(stock1.get(i)))/ 2);
                line.setEndY(600 - (Float.parseFloat(stock1.get(i+1)))/ 2);
                line.setStroke(Color.RED);
                root.getChildren().add(line);
            }
            counter++;
        } else {
            for (int j = 0; j < stock2.size() - 1; j++) {
                Line line2 = new Line();
                line2.setStartX(50 + (j * 7));
                line2.setEndX(57 + (j * 7));
                line2.setStartY(600 - (Float.parseFloat(stock2.get(j)))/ 2);
                line2.setEndY(600 - (Float.parseFloat(stock2.get(j+1)))/ 2);
                line2.setStroke(Color.BLUE);
                root.getChildren().add(line2);
            }
            counter++;
        }
    }
    public static void main(String[] args){launch();}
}