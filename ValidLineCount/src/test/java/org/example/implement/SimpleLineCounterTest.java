package org.example.implement;

import org.example.api.LineCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLineCounterTest {

    private LineCounter lineCounter;

    @BeforeEach
    public void setUp() {
        lineCounter = new SimpleLineCounter();
    }

    @Test
    public void testCountValidLines_ValidLinesOnly() {
        List<String> lines = Arrays.asList(
                "public class Test {",
                "    private int value;",
                "    public void doSomething() {",
                "        System.out.println(\"Hello, world!\");",
                "    }",
                "}"
        );

        int validLineCount = lineCounter.countValidLines(lines);
        assertEquals(6, validLineCount);
    }

    @Test
    public void testCountValidLines_WithLineComments() {
        List<String> lines = Arrays.asList(
                "public class Test {",
                "    private int value; ",
                "    public void doSomething() {",
                "        System.out.println(\"Hello, world!\");",
                "    }",
                "    // Another line comment",
                "}"
        );

        int validLineCount = lineCounter.countValidLines(lines);
        assertEquals(6, validLineCount);
    }

    @Test
    public void testCountValidLines_WithBlockComments() {
        List<String> lines = Arrays.asList(
                "public class Test {",
                "    /* ",
                "      */",
                "    private int value;",
                "    public void doSomething() {",
                "        System.out.println(\"Hello, world!\");",
                "    }",
                "}"
        );

        int validLineCount = lineCounter.countValidLines(lines);
        assertEquals(6, validLineCount);
    }

    @Test
    public void testCountValidLines_WithEmptyLines() {
        List<String> lines = Arrays.asList(
                "public class Test {",
                "",
                "    private int value;",
                "    ",
                "    public void doSomething() {",
                "        System.out.println(\"Hello, world!\");",
                "    }",
                "}"
        );

        int validLineCount = lineCounter.countValidLines(lines);
        assertEquals(6, validLineCount);
    }
}