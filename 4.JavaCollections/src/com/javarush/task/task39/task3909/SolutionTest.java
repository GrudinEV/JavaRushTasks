package com.javarush.task.task39.task3909;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest extends Assert{

    @Test
    public void isOneEditAwayTest() {
        Assert.assertTrue(Solution.isOneEditAway("letter", "letter"));
        Assert.assertFalse(Solution.isOneEditAway("leTTer", "letter"));
        Assert.assertTrue(Solution.isOneEditAway("leter", "letter"));
        Assert.assertTrue(Solution.isOneEditAway("letterA", "letterB"));
        Assert.assertTrue(Solution.isOneEditAway("letterA", "letter"));
        Assert.assertTrue(Solution.isOneEditAway("letter", "letterB"));
        Assert.assertFalse(Solution.isOneEditAway("letter", "letterBB"));
        Assert.assertFalse(Solution.isOneEditAway("leTter", "letterB"));
        Assert.assertTrue(Solution.isOneEditAway("A", "B"));
        Assert.assertTrue(Solution.isOneEditAway("A", ""));
        Assert.assertTrue(Solution.isOneEditAway("", ""));
    }
}