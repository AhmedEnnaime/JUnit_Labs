package org.example.funcs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MathOpsTest {

    @Test
    public void testAdd(){
        MathOps mathOps = new MathOps();

        Integer res = mathOps.add(1, 2);
        assertEquals(3, res);

    }

    @Test
    public void testFailedTest() {
        MathOps mathOps = new MathOps();
        int result = mathOps.add((int) 'a', 4);
        assertEquals(99, result);
    }
}
