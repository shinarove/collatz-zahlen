package ch.zhaw.it.thin;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Enter an upper bound:");
        long upperBound = readUpperBound();
        do {
            CollatzSequenceBruteForce collatzSequenceBruteForce = new CollatzSequenceBruteForce();
            CollatzSequenceOptimised collatzSequenceOptimised = new CollatzSequenceOptimised();
            collatzSequenceBruteForce.runWith(upperBound);
            collatzSequenceOptimised.runWith(upperBound);
            System.out.println("Enter another upper bound or 'q' to quit:");
            upperBound = readUpperBound();
        } while (upperBound != 0);
    }

    public static long readUpperBound() {
        String read = scanner.nextLine();
        if (read.matches("[1-9]\\d*")) {
            return Long.parseLong(read);
        } else if (read.equals("q")) {
            return 0;
        } else {
            System.err.println("Please enter an integer.");
            return readUpperBound();
        }
    }
}
