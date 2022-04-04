package com.example.assignment2_vrundpatel;

import javafx.application.Application;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domBuilder = domFactory.newDocumentBuilder();

            Document newDoc = domBuilder.newDocument();
            
            Element srcElement = newDoc.createElement("converted_airline_safety");
            newDoc.appendChild(srcElement);

            BufferedReader csvReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\assignment2_vrundpatel\\airline_safety.csv"));
            int fldCount;
            String[] csvFlds = null;
            String[] csvVal = null;

            String curLine = csvReader.readLine();
            if (curLine != null) {
                csvFlds = curLine.split(",", -1);
            }

            String[] newCsvFlds = Arrays.copyOf(csvFlds, (csvFlds.length + 1));
            newCsvFlds[csvFlds.length] = "total_number_of_incidents_85_14";
            
            int i = 0;
            while ((curLine = csvReader.readLine()) != null) {
                csvVal = curLine.split(",", -1);
                Element rowElement = newDoc.createElement("record-" + i);
                try {
                    for (fldCount = 0; fldCount < csvVal.length; fldCount++) {
                        String curValue = csvVal[fldCount];
                        Element curElement = newDoc.createElement(newCsvFlds[fldCount]);
                        curElement.appendChild(newDoc.createTextNode(curValue));
                        rowElement.appendChild(curElement);
                    }
                    int tmp = (Integer.parseInt(csvVal[2])) + (Integer.parseInt(csvVal[5]));
                    String curValue = String.valueOf(tmp);
                    Element curElement = newDoc.createElement(newCsvFlds[csvVal.length]);
                    curElement.appendChild(newDoc.createTextNode(curValue));
                    rowElement.appendChild(curElement);
                } catch (Exception exp) {
                }
                srcElement.appendChild(rowElement);
                i++;
            }

            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
            aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            Source src = new DOMSource(newDoc);
            Result result = new StreamResult(new File("src\\main\\java\\com\\example\\assignment2_vrundpatel\\converted_airline_safety.xml"));
            aTransformer.transform(src, result);

            csvReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder domBuilder = domFactory.newDocumentBuilder();
            
            BufferedReader csvReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\assignment2_vrundpatel\\airline_safety.csv"));
            
            String[] tmp = null;
            String xmlData = "";
            ArrayList<Double> col1 = new ArrayList<>();
            ArrayList<Double> col2 = new ArrayList<>();
            ArrayList<Double> col3 = new ArrayList<>();
            ArrayList<Double> col4 = new ArrayList<>();
            ArrayList<Double> col5 = new ArrayList<>();
            ArrayList<Double> col6 = new ArrayList<>();
            ArrayList<Double> col7 = new ArrayList<>();
            ArrayList<String> min = new ArrayList<>();
            ArrayList<String> max = new ArrayList<>();
            ArrayList<String> avg = new ArrayList<>();
            String[] val;
            String[] labels = {"Min", "Max", "Average","Name"};
            String[] avgLabels = {"avg_85_99","avg_00_14"};
            double total, counter;

            String line = csvReader.readLine();
            if (line != null) {
                tmp = line.split(",", -1);
            }

            // starts at 1 to not include airline name and
            for (int i = 1; i < tmp.length; i++) {
                counter = 0;
                total = 0;
                while ((line = csvReader.readLine()) != null) {
                    val = line.split(",", -1);
                    if (i == 1){
                        col1.add(Double.parseDouble(val[i]));
                    }
                    else if (i == 2){
                        col2.add(Double.parseDouble(val[i]));
                    }
                    else if (i == 3){
                        col3.add(Double.parseDouble(val[i]));
                    }
                    else if (i == 4){
                        col4.add(Double.parseDouble(val[i]));
                    }
                    else if (i == 5){
                        col5.add(Double.parseDouble(val[i]));
                    }
                    else if (i == 6){
                        col6.add(Double.parseDouble(val[i]));
                    } else {
                        col7.add(Double.parseDouble(val[i]));
                    }
                    total += Double.parseDouble(val[i]);

                    counter++;
                }
                avg.add(String.valueOf(total / counter));
                csvReader.close();
                csvReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\assignment2_vrundpatel\\airline_safety.csv"));
                line = csvReader.readLine();
            }

            min.add(getMin(col1));
            min.add(getMin(col2));
            min.add(getMin(col3));
            min.add(getMin(col4));
            min.add(getMin(col5));
            min.add(getMin(col6));
            min.add(getMin(col7));

            max.add(getMax(col1));
            max.add(getMax(col2));
            max.add(getMax(col3));
            max.add(getMax(col4));
            max.add(getMax(col5));
            max.add(getMax(col6));
            max.add(getMax(col7));

            xmlData += """
                    <?xml version="1.0" encoding="UTF-8" standalone="no"?> \n
                    <Summary>
                    """;

            for (int j = 1; j < tmp.length; j++) {
                xmlData += "   <Stat>\n";
                xmlData += "      <" + labels[labels.length -1] + "> " + tmp[j] + " </" + labels[labels.length - 1] + ">\n";
                for (int i = 0; i < labels.length - 1; i++) {
                    if (i == 0) {
                        xmlData += "      <" + labels[i] + "> " + min.get(j - 1) + " </" + labels[i] + ">\n";
                    } else if (i == 1) {
                        xmlData += "      <" + labels[i] + "> " + max.get(j - 1) + " </" + labels[i] + ">\n";
                    } else {
                        xmlData += "      <" + labels[i] + "> " + avg.get(j - 1) + " </" + labels[i] + ">\n";
                    }
                }
                xmlData += "   </Stat>\n";
            }


            for(int j = 0; j < avgLabels.length; j++){
                xmlData += "   <Stat>\n";
                xmlData += "      <" + labels[labels.length - 1] + "> " + avgLabels[j] + " </" + labels[labels.length - 1] + ">\n";
                for(int i = 0; i < labels.length - 1; i++){
                    if (i == 0 || i == 1) {
                        xmlData += "      <" + labels[i] + "> " + " </" + labels[i] + ">\n";
                    } else {
                        if (j == 0) {
                            xmlData += "      <" + labels[i] + "> " + avg.get(j + 2) + " </" + labels[i] + ">\n";
                        } else {
                            xmlData += "      <" + labels[i] + "> " + avg.get(j + 4) + " </" + labels[i] + ">\n";
                        }
                    }
                }
                xmlData += "   </Stat>\n";
            }

            xmlData += "</Summary>";
            csvReader.close();

            try {
                
                Document doc = domBuilder.parse(new InputSource(new StringReader(xmlData)));

                TransformerFactory tranFactory = TransformerFactory.newInstance();
                Transformer aTransformer = tranFactory.newTransformer();

                Source src = new DOMSource(doc);
                Result result = new StreamResult(new File("src\\main\\java\\com\\example\\assignment2_vrundpatel\\airline_summary_statistics.xml"));
                aTransformer.transform(src, result);


            } catch (Exception e) {
                e.printStackTrace();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> airlineName = new ArrayList<>();
        ArrayList<Double> fatal_accidents_85_99 = new ArrayList<>();
        ArrayList<Double> fatal_accidents_00_14 = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        String [] val;
        String [] tmp = null;

        BufferedReader csvReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\assignment2_vrundpatel\\airline_safety.csv"));

        String line = csvReader.readLine();
        if (line != null) {
            tmp = line.split(",", -1);
        }

        labels.add(tmp[0]);
        labels.add(tmp[2]);
        labels.add(tmp[5]);
        
        for (int i = 0; i < 3; i++) {
            while ((line = csvReader.readLine()) != null) {
                val = line.split(",", -1);
                if (i == 0) {
                    airlineName.add(val[0]);
                } else if (i == 1) {
                    fatal_accidents_85_99.add(Double.parseDouble(val[2]));
                } else {
                    fatal_accidents_00_14.add(Double.parseDouble(val[5]));
                }
            }
            csvReader.close();
            csvReader = new BufferedReader(new FileReader("src\\main\\java\\com\\example\\assignment2_vrundpatel\\airline_safety.csv"));
            line = csvReader.readLine();
        }

        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Country Summary");
        xAxis.setLabel("Airline");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName(labels.get(1));
        for(int i = 0; i < airlineName.size(); i++) {
            series1.getData().add(new XYChart.Data(airlineName.get(i), fatal_accidents_85_99.get(i)));
        }

        XYChart.Series series2 = new XYChart.Series();
        series2.setName(labels.get(2));
        for(int i = 0; i < airlineName.size(); i++) {
            series2.getData().add(new XYChart.Data(airlineName.get(i), fatal_accidents_00_14.get(i)));
        }

        Scene scene  = new Scene(bc,800,600);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
        stage.show();

    }

    public String getMax(ArrayList<Double> arr){
        double tmp = arr.get(0);

        for(int i = 1; i < arr.size(); i++){
            if(tmp < arr.get(i)){
                tmp = arr.get(i);
            }
        }
        return String.valueOf(tmp);
    }

    public String getMin(ArrayList<Double> arr){
        double tmp = arr.get(0);

        for(int i = 1; i < arr.size(); i++){
            if(tmp > arr.get(i)){
                tmp = arr.get(i);
            }
        }
        return String.valueOf(tmp);
    }

    public static void main(String[] args) {
        launch();
    }
}