package ch.zhaw.it.thin;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public  abstract class CommonDef {

    private long startTime;
    protected static final Logger logger = Logger.getLogger(CommonDef.class.getName());

    protected abstract void runWith(long upperBound);

    protected void startTimer() {
        startTime = System.nanoTime();
    }

    protected double stopTimer() {
        long endTime = System.nanoTime();
        return ((double) endTime - startTime) / 1_000_000;
    }

    protected void println(String message) {
        System.out.println(message);
    }

    protected void findSmallestMax(List<Integer> collatzSequences) {
        int number = 0;
        int countElements = 0;
        for (int i = collatzSequences.size() - 1; i >= 0; i--) {
            if (collatzSequences.get(i) >= countElements) {
                countElements = collatzSequences.get(i);
                number = i + 1;
            }
        }
        println("Smallest number with the most elements in the collatz sequence " + number);
        println("Amount of elements: " + countElements);
    }

    protected  void findSmallestMax(Map<Long, Long> collatzSequences) {
        long number = 0;
        long countElements = 0;
        for (Map.Entry<Long, Long> entry : collatzSequences.entrySet()) {
            if (entry.getValue() >= countElements) {
                countElements = entry.getValue();
                number = entry.getKey();
            }
        }
        println("Smallest number with the most elements in the collatz sequence " + number);
        println("Amount of elements: " + countElements);
    }
}
