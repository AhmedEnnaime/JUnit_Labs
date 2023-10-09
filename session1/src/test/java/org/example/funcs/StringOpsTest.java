package org.example.funcs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class StringOpsTest {

    @Test
    public void testToUpperCase() {

        StringOps stringOps = new StringOps();

        String result = stringOps.toUpperCase("hello");

        assertEquals("HELLO", result);

    }

    @Test
    public void testNullToUpperCase() {
        StringOps stringOps = new StringOps();

        String result = stringOps.toUpperCase(null);

        assertNull(result);
    }
}