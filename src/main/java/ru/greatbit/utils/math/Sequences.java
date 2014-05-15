package ru.greatbit.utils.math;

import ru.greatbit.utils.exceptions.MathException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 5/15/14.
 */
public class Sequences {

    /**
     * Get factorial of defined depth
     * @param depth
     * @return
     */
    public static long factorial(int depth) throws MathException {
        if (depth <= 0){
            throw new MathException("Factorial is not supported for values less or equal to 0");
        }
        if (depth == 1){
            return 1;
        }
        return factorial(depth - 1) * depth;
    }

    /**
     * Get a sequence of factorial numbers of defined depth
     * @param depth
     * @return
     */
    public static List<Long> factorialSequence(int depth) throws MathException {
        List<Long> result = new LinkedList<Long>();
        if (depth <= 0){
            throw new MathException("Factorial is not supported for values less or equal to 0");
        }

        factorialSequence(depth, result);
        return result;
    }

    /**
     * Fill factorial sequence recursively
     * @param depth
     * @param values
     */
    private static void factorialSequence(int depth, List<Long> values){
        if (depth == 1){
            values.add(1L);
            return;
        }
        factorialSequence(depth - 1, values);
        values.add(values.get(values.size() - 1) * depth);
    }


    /**
     * Get fibonacchi number of defined depth
     * @param depth
     * @return
     */
    public static long fibonacci(int depth) throws MathException {
        if (depth <= 0) {
            throw new MathException("Fibonacci is not supported for values less or equal to 0");
        } else if (depth == 1 || depth == 2) {
            return 1;
        } else {
            return fibonacci(depth - 1) + fibonacci(depth - 2);
        }
    }
}
