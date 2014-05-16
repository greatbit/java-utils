package ru.greatbit.utils.math;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 5/16/14.
 */
public class Prime {


    /**
     * Get list of simple primes
     * @param depth
     * @return
     */
    public static List<Long> getPrimes(int depth){
        return getPrimes(2, depth);
    }

    /**
     * Get list of simple primes in a range
     * @param from
     * @param to
     * @return
     */
    public static List<Long> getPrimes(int from, int to){
        List<Long> result = new LinkedList<Long>();
        if (from < 2){
            from = 2;
        }
        for (int i = from; i < to; i++){
            if (isPrime(i)){
                result.add(new Long(i));
            }
        }
        return result;
    }


    /**
     * Find out if the number is prime
     * @param value
     * @return
     */
    public static boolean isPrime(long value){
        if (value < 2){
            return false;
        }
        for(int i = 2; i < value; i++){
            if(value % i == 0){
                return false;
            }
        }
        return true;
    }
}
