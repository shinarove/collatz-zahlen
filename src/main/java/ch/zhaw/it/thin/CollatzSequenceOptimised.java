package ch.zhaw.it.thin;

import java.util.ArrayList;
import java.util.List;

public class CollatzSequenceOptimised extends CommonDef {

    private final List<Integer> collatzSequences = new ArrayList<>();

    protected void runWith(long upperBound) {
        startTimer();
        long i = 1;
        while (i <= upperBound) {
            long x = i;
            int count = 1;
            while (x != 1) {
                if (x % 2 == 0) {
                    x = x / 2;
                } else {
                    x = 3 * x + 1;
                }
                if (x < collatzSequences.size()) {
                    count += collatzSequences.get((int) x - 1);
                    break;
                }
                count++;
            }
            collatzSequences.add(count);
            i++;
        }
        //finds the smallest number with the largest amount of elements in the collatz sequence
        findSmallestMax(collatzSequences);
        println("Time with optimisation: " + stopTimer() + " ms");
    }
}
