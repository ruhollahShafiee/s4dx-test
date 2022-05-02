package com.s4dx.codechallenge;


import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class FinderTest extends BaseTest {

    @Test
    public void givenExactSameCharacters_WhenCharOrderIsNotRelevant() {

        String[] thisIsAStringArray = new String[]{"asd", "ads", "ads ", "asdd", "fre", "glk", "lkm"};
        Finder finder = new Finder(thisIsAStringArray);
        String[] actualStr = finder.find("sad");
        String[] expectedStr = {"asd", "ads"};
        Assert.assertEquals(expectedStr, actualStr);

    }

    @Test(timeOut = 5000)
    public void loadTest() throws InterruptedException {

        String[] actualArray = new String[]{"asd", "asdd", "fre", "glk", "lkm"};
        load(() -> (new Finder(actualArray)).find("sad"), 50000, 120);

    }

    @Test(timeOut = 5000)
    public void givenAllElement_WhenArrayIsLargeWithWorstChar() {

        String[] actualArray = buildLargeArray(1000000, 500);
        Finder finder = new Finder(actualArray);
        String[] expectedArrayStr = finder.find(buildString(500));
        Assert.assertEquals(expectedArrayStr.length, actualArray.length);

    }




    private void load(Runnable runnable, Integer numberOfThread, Integer threadWaitTimeout) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IntStream.range(0, numberOfThread).forEach(i -> service.submit(runnable));
        service.shutdown();
        service.awaitTermination(threadWaitTimeout, TimeUnit.SECONDS);

    }

    private String[] buildLargeArray(int arraySize, int stringSize) {

        return IntStream.range(0, arraySize).mapToObj(i -> buildString(stringSize)).toArray(String[]::new);

    }

    // build String with worst Character(z)
    private String buildString(int strSize) {
        char[] array = new char[strSize];
        Arrays.fill(array, 'z');
        return new String(array);
    }

}
