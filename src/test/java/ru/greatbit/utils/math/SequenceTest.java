package ru.greatbit.utils.math;

import org.junit.Test;
import ru.greatbit.utils.exceptions.MathException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by azee on 5/15/14.
 */
public class SequenceTest {

    @Test
    public void factorialTest() throws MathException {
        assertThat(Sequence.factorial(1), is(1L));
        assertThat(Sequence.factorial(5), is(120L));
        assertThat(Sequence.factorial(10), is(3628800L));
        assertThat(Sequence.factorial(15), is(1307674368000L));
    }

    @Test
    public void factorialSequenceTest() throws MathException {
        assertThat(Sequence.factorialSequence(1).toString(), is("[1]"));
        assertThat(Sequence.factorialSequence(2).toString(), is("[1, 2]"));
        assertThat(Sequence.factorialSequence(5).toString(), is("[1, 2, 6, 24, 120]"));
        assertThat(Sequence.factorialSequence(10).toString(), is("[1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800]"));
    }

    @Test(expected = MathException.class)
    public void factorialNegativeTest() throws MathException {
        Sequence.factorial(-15);
        Sequence.factorial(0);
        Sequence.factorialSequence(0);
        Sequence.factorialSequence(-5);
    }

    @Test
    public void fibonacciTest() throws MathException {
        assertThat(Sequence.fibonacci(1), is(1L));
        assertThat(Sequence.fibonacci(5), is(5L));
        assertThat(Sequence.fibonacci(10), is(55L));
        assertThat(Sequence.fibonacci(15), is(610L));
    }

    @Test(expected = MathException.class)
    public void fibonacciNegativeTest() throws MathException {
        Sequence.fibonacci(0);
        Sequence.fibonacci(-7);
    }

}
