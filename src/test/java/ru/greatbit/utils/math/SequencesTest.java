package ru.greatbit.utils.math;

import org.junit.Test;
import ru.greatbit.utils.exceptions.MathException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by azee on 5/15/14.
 */
public class SequencesTest {

    @Test
    public void factorialTest() throws MathException {
        assertThat(Sequences.factorial(1), is(1L));
        assertThat(Sequences.factorial(5), is(120L));
        assertThat(Sequences.factorial(10), is(3628800L));
        assertThat(Sequences.factorial(15), is(1307674368000L));
    }

    @Test
    public void factorialSequenceTest() throws MathException {
        assertThat(Sequences.factorialSequence(1).toString(), is("[1]"));
        assertThat(Sequences.factorialSequence(2).toString(), is("[1, 2]"));
        assertThat(Sequences.factorialSequence(5).toString(), is("[1, 2, 6, 24, 120]"));
        assertThat(Sequences.factorialSequence(10).toString(), is("[1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800]"));
    }

    @Test(expected = MathException.class)
    public void factorialNegativeTest() throws MathException {
        Sequences.factorial(-15);
        Sequences.factorial(0);
        Sequences.factorialSequence(0);
        Sequences.factorialSequence(-5);
    }

    @Test
    public void fibonacciTest() throws MathException {
        assertThat(Sequences.fibonacci(1), is(1L));
        assertThat(Sequences.fibonacci(5), is(5L));
        assertThat(Sequences.fibonacci(10), is(55L));
        assertThat(Sequences.fibonacci(15), is(610L));
    }

    @Test(expected = MathException.class)
    public void fibonacciNegativeTest() throws MathException {
        Sequences.fibonacci(0);
        Sequences.fibonacci(-7);
    }

}
