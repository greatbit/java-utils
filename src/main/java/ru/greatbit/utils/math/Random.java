package ru.greatbit.utils.math;

/**
 * Created by azee on 23.06.15.
 */
public class Random {
    /**
     * Returns random within a range (inclusive)
     * @param min - Lower range valie (inclusive)
     * @param max - Upper range valie (inclusive)
     * @return - next random value
     */
    public static int next(int min, int max){
        return new java.util.Random().nextInt((max - min) + 1) + min;
    }
}
