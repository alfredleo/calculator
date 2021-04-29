package uz.javalearn;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.lang.Math.round;

public class TwoSigma {
    public static void main(String[] args) {
        callCommand(new Rand7());
        callCommand(new Rand7before1_7());
    }

    /**
     * Java Pass Method as Parameter
     * Implemented using Command pattern (behavioral)
     */
    public interface Command {
        public int execute();
    }

    public static void callCommand(Command command) {
        int sum = 1000000000;
        int[] counter = new int[7];
        for (int i = 0; i < sum; i++) {
            int current = command.execute();
            counter[current - 1]++;
            //System.out.println(current);
        }
        (Arrays.stream(counter).mapToObj(i -> (new DecimalFormat("#.##").format((i * 1.0 / sum) * 100)) + " %  ")).
                forEach(System.out::print);
        System.out.println();
    }

    public static class Rand7 implements Command {
        @Override
        public int execute() {
            return ThreadLocalRandom.current().nextInt(1, 8);
        }
    }

    public static class Rand7before1_7 implements Command {
        @Override
        public int execute() {
            return (int) (1 + (Math.random() * 7));
        }
    }

    public static class Rand5 implements Command {
        @Override
        public int execute() {
            // TODO: fix here
            return new Rand7before1_7().execute();
        }
    }
}
