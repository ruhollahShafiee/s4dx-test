package com.s4dx.codechallenge;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;


public class BaseTest {

    @AfterMethod
    public void afterMethod(ITestResult result) {

        System.out.println("| " + result.getName() + " | processing time : "
                + (result.getEndMillis() - result.getStartMillis()) + " ms | is success : " + result.isSuccess());
    }

}
