package com.s4dx.codechallenge;


import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class FinderTest extends BaseTest {

    @Test(dataProvider = "exactSame-test-array", dataProviderClass = DataProvider.class)
    public void givenExactSameCharacters_WhenCharOrderIsNotRelevant(String[] param) {


        Finder finder = new Finder(param);
        String[] actualStr = finder.find("sad");
        String[] expectedStr = {"asd", "ads"};
        Assert.assertEquals(expectedStr, actualStr);

    }

    @Test(dataProvider = "simple-array", dataProviderClass = DataProvider.class)
    public void givenEmptyArray_WhenNotExistAnySameCharacters(String[] param) {


        Finder finder = new Finder(param);
            String[] actualStr = finder.find("sadtot");
        String[] expectedStr = new String[0];
        Assert.assertEquals(expectedStr, actualStr);

    }

    @Test(dataProvider = "load-test-array", dataProviderClass = DataProvider.class, timeOut = 5000)
    public void loadTest(String[] param) throws InterruptedException {

        load(() -> (new Finder(param)).find("sad"), 50000, 120);

    }

    @Test(timeOut = 5000)
    public void givenAllElement_WhenArrayIsLargeWithWorstChar() {

        DataProvider dataProvider = new DataProvider();
        String[] actualArray = dataProvider.dpLargeArray();
        Finder finder = new Finder(actualArray);
        String[] expectedArrayStr = finder.find(dataProvider.buildString(500));
        Assert.assertEquals(expectedArrayStr.length, actualArray.length);

    }

    private void load(Runnable runnable, Integer numberOfThread, Integer threadWaitTimeout) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        IntStream.range(0, numberOfThread).forEach(i -> service.submit(runnable));
        service.shutdown();
        service.awaitTermination(threadWaitTimeout, TimeUnit.SECONDS);

    }


}