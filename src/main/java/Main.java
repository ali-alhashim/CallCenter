import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public   JButton selectCSVBtn = new JButton();

    public static void main(String[] args)
    {

        System.out.println("main method Start");

        Main main = new Main();
        main.initiateGUI();

        main.selectCSVBtn.addActionListener(e -> {
            System.out.println("you clicked on select CSV");

            JFileChooser csvFile = new JFileChooser(System.getProperty("user.dir"));
            int Response =  csvFile.showOpenDialog(null);

            if(Response ==JFileChooser.APPROVE_OPTION)
            {
                // you select file start create html file
                System.out.println("you select the following " + csvFile.getSelectedFile());

                main.formatCSV(csvFile.getSelectedFile().toString());

            }

        });

    }

    public  void initiateGUI()
    {
        // JPanel
        JPanel jPanel = new JPanel();

        //JButton for Select CSV file

        selectCSVBtn.setText("Select CSV File");


        // adding button to jPanel
        jPanel.add(selectCSVBtn);

        // JFrame
        JFrame jFrame= new JFrame();// creating a Frame
        jFrame.setSize(600,200);
        jFrame.setTitle("Java App for Call Center By Ali Alhashim");

        // Exit the application and free memory
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);

        jFrame.setLayout(new BorderLayout());
        jFrame.add(jPanel, BorderLayout.CENTER);


    }

    public void formatCSV(String csvFile)
    {
        System.out.println("Now you called formatCSV method");

        StringBuilder sb = new StringBuilder();

        sb.append("""
                
                      <!DOCTYPE html>
                      <html lang="en">
                          <!----Created By Ali alhashim----->
                      <head>
                          <meta charset="UTF-8">
                          <meta http-equiv="X-UA-Compatible" content="IE=edge">
                          <meta name="viewport" content="width=device-width, initial-scale=1.0">
                          <title>Call Center Report</title>
                         <style>
                         table, th, td {
                             border: 1px solid black;
                             border-collapse: collapse;
                             padding: 5px;
                           }
                         </style>
                      </head>
                      <body>
                      <table>
                      <tr>
                      <td>Date & Time </td>
                      <td> Total Calls </td>
                      </tr>
                """);

        // create a reader
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFile)))
        {

            // read csv file
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);
            for (CSVRecord record : records)
            {

              /*
              compare all value to each other and hold the match and merge them with total calls
              record.get(0) and total record.get(11)
               */



            }





        }//end try
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        sb.append("""
                 </table>
                 </body>
                 </html>
                 """);



        //--------- Create file ---------------
        try {
            File htmlFile = new File("filename.html");
            if (htmlFile.createNewFile()) {
                System.out.println("File created: " + htmlFile.getName());
            } else {
                System.out.println("File already exists.");
                htmlFile.delete();
                htmlFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //----------------------------------

        //----------Write to file ---------
        try {
            FileWriter myWriter = new FileWriter("filename.html");
            myWriter.write(String.valueOf(sb));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        //-----------------------------

    }
}
