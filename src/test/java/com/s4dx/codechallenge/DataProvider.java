package com.s4dx.codechallenge;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DataProvider {

    @org.testng.annotations.DataProvider(name = "load-test-array")
    public Object[][] dpLoadTestSimpleArray() {

        return new String[][]{{"asd", "asdd", "fre", "glk", "lkm"}};
    }

    @org.testng.annotations.DataProvider(name = "exactSame-test-array")
    public Object[][] dpExactSameTestSimpleArray() {

        return new String[][]{{"asd", "ads", "ads ", "asdd", "fre", "glk", "lkm"}};
    }

    @org.testng.annotations.DataProvider(name = "simple-array")
    public Object[][] dpSimpleArray() {

        return new String[][]{{"asd", "ads", "ads ", "asdd", "fre", "glk", "lkm"}};
    }


    public String[] dpLargeArray() {

        return buildLargeArray(1000000, 500);
    }


    private String[] buildLargeArray(int arraySize, int stringSize) {

        return IntStream.range(0, arraySize).mapToObj(i -> buildString(stringSize)).toArray(String[]::new);

    }

    // build String with worst Character(z)
    public String buildString(int strSize) {
        char[] array = new char[strSize];
        Arrays.fill(array, 'z');
        return new String(array);
    }
}