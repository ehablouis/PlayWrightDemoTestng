package com.webshop.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentManager {
    private static ExtentReports extent;
    private static String FILE_NAME = "Test-Automaton-Report.html";
    private static String OUTPUT_FILE = "./outputs/";

    public static ExtentReports getInstance() {
        if (extent == null)
            extent = createInstance();
        return extent;
    }

    //Create an extent report instance
    private static ExtentReports createInstance() {
        String fileName = getReportPath(OUTPUT_FILE);

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

        htmlReporter.config().setDocumentTitle(FILE_NAME);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(FILE_NAME);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        //Set environment details
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("AUT", "QA");

        return extent;
    }

    //Create the report path
    private static String getReportPath (String path)  {
        String reportFileLocation = OUTPUT_FILE + FILE_NAME;
        File testDirectory = new File(path);
        if (!testDirectory.exists()) {
            if (testDirectory.mkdir()) {
                System.out.println("Directory: " + path + " is created!" );
                return reportFileLocation;
            } else {
                System.out.println("Failed to create directory: " + path);
                return path;
            }
        } else {
            System.out.println("Directory already exists: " + path);
            try {
                Files.deleteIfExists(Paths.get(reportFileLocation));
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        return reportFileLocation;
    }

}
