package ch.zhaw.it.thin;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Mit Threads experimentieren.
 * Achtung noch nicht fertig!
 */
public class CollatzSequenceThreaded extends CommonDef {

    private Map<Long, Long> collatzSequences;

    @Override
    protected void runWith(long upperBound) {
        startTimer();
        //preparation for the threads
        collatzSequences = new ConcurrentHashMap<>((int) upperBound);
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        long upperBoundDivided = upperBound / 16;

        for (int i = 0; i < 16; i++) {
            long start = i * upperBoundDivided + 1;
            long end = (i + 1) * upperBoundDivided;
            if (i == 15) {
                end = upperBound;
            }
            calculateCollatzSequences(start, end, executorService);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            //do nothing
        }
        //finds the smallest number with the largest amount of elements in the collatz sequence
        findSmallestMax(collatzSequences);
        println("Time with threads: " + stopTimer() + " ms");
    }

    private void calculateCollatzSequences(long start, long end, ExecutorService executorService) {
        executorService.submit(() -> {
            for (long i = start; i <= end; i++) {
                long x = i;
                long count = 1;
                while (x != 1) {
                    if (x % 2 == 0) {
                        x = x / 2;
                    } else {
                        x = 3 * x + 1;
                    }
//                    if (collatzSequences.containsKey(x)) {
//                        count += collatzSequences.get(x);
//                        break;
//                    }
                    count++;
                }
                collatzSequences.put(i, count);
            }
        });
    }
}
