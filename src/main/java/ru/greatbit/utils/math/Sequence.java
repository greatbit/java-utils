package ru.greatbit.utils.math;

import ru.greatbit.utils.exceptions.MathException;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 5/15/14.
 */
public class Sequence {

    /**
     * Get factorial of defined depth
     * @param depth - Depth for a factorial
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
     * @param depth - Depth for a factorial
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
     * @param depth  - Depth for a factorial
     * @param values - Values to fill factorial
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
     * @param depth  - Depth for a fibonacci
     * @return - Long result
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


    /**
     * Get a sequence of fibonacci numbers of defined depth
     * @param depth  - Depth for a fibonacci sequence
     * @return - List of fibonacci values
     * @throws MathException
     */
    public static List<Long> fibonacciSequence(int depth) throws MathException {
        List<Long> result = new LinkedList<Long>();
        if (depth <= 0){
            throw new MathException("Factorial is not supported for values less or equal to 0");
        }
        fibonacciSequence(depth, result);
        return result;
    }

    /**
     * Fill fibonacci sequence recursively
     * @param depth -  - Depth for a fibonacci
     * @param result - fill a fibonacci sequence recursively
     */
    private static void fibonacciSequence(int depth, List<Long> result) {
        if (depth == 1) {
            result.add(1L);
            return;
        }
        if (depth == 2) {
            result.add(1L);
            result.add(1L);
            return;
        }
        fibonacciSequence(depth - 1, result);
        result.add(result.get(result.size() - 1) + result.get(result.size() - 2));
    }
}
