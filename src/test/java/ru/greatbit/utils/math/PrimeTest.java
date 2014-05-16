package ru.greatbit.utils.math;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by azee on 5/16/14.
 */
public class PrimeTest {

    @Test
    public void isPrimeTest(){
        assertTrue(Prime.isPrime(2));
        assertTrue(Prime.isPrime(3));
        assertTrue(Prime.isPrime(103));
        assertTrue(Prime.isPrime(157));

        assertFalse(Prime.isPrime(0));
        assertFalse(Prime.isPrime(-5));
        assertFalse(Prime.isPrime(1));
        assertFalse(Prime.isPrime(4));
        assertFalse(Prime.isPrime(152));
        assertFalse(Prime.isPrime(153));
        assertFalse(Prime.isPrime(154));
        assertFalse(Prime.isPrime(155));
        assertFalse(Prime.isPrime(156));
        assertFalse(Prime.isPrime(158));
        assertFalse(Prime.isPrime(160));
    }

    @Test
    public void getPrimesTest(){
        assertThat(Prime.getPrimes(20).toString(), is("[2, 3, 5, 7, 11, 13, 17, 19]"));
        assertThat(Prime.getPrimes(5, 20).toString(), is("[5, 7, 11, 13, 17, 19]"));
    }
}
