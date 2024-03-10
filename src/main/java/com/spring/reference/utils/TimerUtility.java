package com.spring.reference.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;

@Slf4j
public class TimerUtility {

    public static StopWatch stopWatch = new StopWatch();

    public static void startTimer(){
        stopWatch.start();
    }

    public static void stopTimer(){
        stopWatch.stop();
        log.info("Total Execution Time in milliseconds: " +stopWatch.getTime() + " ms");
        resetTimer();
    }

    public static void resetTimer(){
        stopWatch.reset();
    }

    public static int noOfCores(){
        return Runtime.getRuntime().availableProcessors();
    }
}
