package com.webshop.utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.webshop.listeners.ExtentTestManager;

public class Helper {

    public static void printLog(String msg){
        ExtentTest extentTest = ExtentTestManager.getTest();
        if (extentTest == null) {
            System.out.println(msg);
        }
        else {
            extentTest.log(Status.INFO, msg);
        }
    }
}
