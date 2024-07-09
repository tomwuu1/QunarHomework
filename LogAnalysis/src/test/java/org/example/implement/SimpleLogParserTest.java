package org.example.implement;

import com.google.common.base.Optional;

import org.example.api.LogParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleLogParserTest {

    private LogParser logParser;

    @BeforeEach
    public void setUp() {
        logParser = new SimpleLogParser();
    }
    @Test
    void extractUriFromLogLine() {
        String logLine = "GET /user/getUserInfo.json?arg1=var1&arg2=var2";
        Optional<String> uri = logParser.extractUriFromLogLine(logLine);
        assertTrue(uri.isPresent());
        assertEquals("/user/getUserInfo.json?arg1=var1&arg2=var2", uri.get());
    }
}