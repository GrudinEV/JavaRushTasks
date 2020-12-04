package com.javarush.task.task39.task3910;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest extends Assert {

    @Test
    public void isPowerOfThree() {
        Assert.assertFalse(Solution.isPowerOfThree(0));
        Assert.assertTrue(Solution.isPowerOfThree(1));
        Assert.assertTrue(Solution.isPowerOfThree(3));
        Assert.assertFalse(Solution.isPowerOfThree(5));
        Assert.assertFalse(Solution.isPowerOfThree(6));
        Assert.assertTrue(Solution.isPowerOfThree(9));
        Assert.assertFalse(Solution.isPowerOfThree(15));
        Assert.assertTrue(Solution.isPowerOfThree(81));
    }

}