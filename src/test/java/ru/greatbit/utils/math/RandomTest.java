package ru.greatbit.utils.math;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by azee on 23.06.15.
 */
public class RandomTest {

    @Test
    public void testGetNext(){
        final int iterations = 100;
        int sum = 0;
        for (int i = 0; i <= iterations; i++){
            int rand = Random.next(1, 10);
            assertThat(rand, both(greaterThanOrEqualTo(1)).and(lessThanOrEqualTo(10)));
            sum += rand;
        }
        assertThat(sum, both(greaterThan(0)).and(lessThan(10 * iterations)));
    }
}
