package com.example.lab06;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.Group;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new Group());
        stage.setTitle("Lab 06");
        stage.setWidth(1000);
        stage.setHeight(500);

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Average Real Estate Prices");
        xAxis.setLabel("Years");
        xAxis.setStyle("-fx-bar-fill: red;");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Average Housing Prices By Year");
        series1.getData().add(new XYChart.Data("Year 1" , avgHousingPricesByYear[0]));
        series1.getData().add(new XYChart.Data("Year 2" , avgHousingPricesByYear[1]));
        series1.getData().add(new XYChart.Data("Year 3" , avgHousingPricesByYear[2]));
        series1.getData().add(new XYChart.Data("Year 4" , avgHousingPricesByYear[3]));
        series1.getData().add(new XYChart.Data("Year 5" , avgHousingPricesByYear[4]));
        series1.getData().add(new XYChart.Data("Year 6" , avgHousingPricesByYear[5]));
        series1.getData().add(new XYChart.Data("Year 7" , avgHousingPricesByYear[6]));
        series1.getData().add(new XYChart.Data("Year 8" , avgHousingPricesByYear[7]));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Average Commercial Prices By Year");
        series2.getData().add(new XYChart.Data("Year 1" , avgCommercialPricesByYear[0]));
        series2.getData().add(new XYChart.Data("Year 2" , avgCommercialPricesByYear[1]));
        series2.getData().add(new XYChart.Data("Year 3" , avgCommercialPricesByYear[2]));
        series2.getData().add(new XYChart.Data("Year 4" , avgCommercialPricesByYear[3]));
        series2.getData().add(new XYChart.Data("Year 5" , avgCommercialPricesByYear[4]));
        series2.getData().add(new XYChart.Data("Year 6" , avgCommercialPricesByYear[5]));
        series2.getData().add(new XYChart.Data("Year 7" , avgCommercialPricesByYear[6]));
        series2.getData().add(new XYChart.Data("Year 8" , avgCommercialPricesByYear[7]));

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data(ageGroups[0], purchasesByAgeGroup[0]),
                new PieChart.Data(ageGroups[1], purchasesByAgeGroup[1]),
                new PieChart.Data(ageGroups[2], purchasesByAgeGroup[2]),
                new PieChart.Data(ageGroups[3], purchasesByAgeGroup[3]),
                new PieChart.Data(ageGroups[4], purchasesByAgeGroup[4]),
                new PieChart.Data(ageGroups[5], purchasesByAgeGroup[5]));

        final PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Purchases By Age Group");
        pieChart.setLayoutX(500);
        ((Group) scene.getRoot()).getChildren().add(pieChart);
        bc.getData().addAll(series1, series2);

        //set first bar color to red
        for(Node n:bc.lookupAll(".default-color0.chart-bar")) {
            n.setStyle("-fx-bar-fill: red;");
        }
        //second bar color to blue
        for(Node n:bc.lookupAll(".default-color1.chart-bar")) {
            n.setStyle("-fx-bar-fill: blue;");
        }

        int i = 0;
        for (PieChart.Data data : pieChartData) {
            String colorString = "rgb(" + pieColours[i].getRed() * 255 + "," + pieColours[i].getGreen() * 255 + "," + pieColours[i].getBlue() * 255 + ");";
            data.getNode().setStyle("-fx-pie-color: " + colorString + ";");
            i++;
        }

        bc.setLegendVisible(false);
        pieChart.setLegendVisible(false);

        ((Group) scene.getRoot()).getChildren().add(bc);
        stage.setScene(scene);
        stage.show();
    }
    private static String[] ageGroups = {
            "18-25", "26-35", "36-45", "46-55", "56-65", "65+"
    };
    private static int[] purchasesByAgeGroup = {
            648, 1021, 2453, 3173, 1868, 2247
    };
    private static Color[] pieColours = {
            Color.AQUA, Color.GOLD, Color.DARKORANGE,
            Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
    };
    private static double[] avgHousingPricesByYear = {
            247381.0,264171.4,287715.3,294736.1,
            308431.4,322635.9,340253.0,363153.7
    };
    private static double[] avgCommercialPricesByYear = {
            1121585.3,1219479.5,1246354.2,1295364.8,
            1335932.6,1472362.0,1583521.9,1613246.3
    };
    public static void main(String[] args) {
        launch();
    }
}