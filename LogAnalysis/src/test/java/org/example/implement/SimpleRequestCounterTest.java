package org.example.implement;

import org.example.LogAnalysis;
import org.example.api.RequestCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SimpleRequestCounterTest {

    private RequestCounter requestCounter;

    @BeforeEach
    void setUp() {
        requestCounter = new SimpleRequestCounter();
    }

    @Test
    void getPostRequestCount() {
        List<String> lines = Arrays.asList(
                "GET /index.html",
                "POST /submit-form",
                "POST /login",
                "GET /home",
                "POST /update-profile"
        );

        long postCount = requestCounter.getPostRequestCount(lines);
        assertEquals(3, postCount);
    }

    @Test
    void getGetRequestCount() {
        List<String> lines = Arrays.asList(
                "GET /index.html",
                "POST /submit-form",
                "POST /login",
                "GET /home",
                "POST /update-profile"
        );

        long getCount = requestCounter.getGetRequestCount(lines);
        assertEquals(2, getCount);
    }

    @Test
    void getTotalLineCount() {
        List<String> lines = Arrays.asList(
                "GET /index.html",
                "POST /submit-form",
                "POST /login",
                "GET /home",
                "POST /update-profile"
        );
        long totalLineCount = requestCounter.getTotalLineCount(lines);
        assertEquals(5, totalLineCount);
    }

    @Test
    void getTop10FrequentEndpoints() {
        List<String> lines = Arrays.asList(
                "/index.html",
                "/submit-form",
                "/login",
                "/home",
                "/update-profile",
                "/index.html",
                "/index.html",
                "/submit-form",
                "/home",
                "/home",
                "/home",
                "/dashboard",
                "/dashboard",
                "/dashboard",
                "/dashboard",
                "/dashboard"
        );

        List<Map.Entry<String, Integer>> top10Endpoints = requestCounter.getTop10FrequentEndpoints(lines);

        assertEquals(6, top10Endpoints.size(), "The size of the top 10 endpoints should be 5");

        assertEquals("/dashboard", top10Endpoints.get(0).getKey());
        assertEquals(5, top10Endpoints.get(0).getValue().intValue());

        assertEquals("/home", top10Endpoints.get(1).getKey());
        assertEquals(4, top10Endpoints.get(1).getValue().intValue());

        assertEquals("/index.html", top10Endpoints.get(2).getKey());
        assertEquals(3, top10Endpoints.get(2).getValue().intValue());

        assertEquals("/submit-form", top10Endpoints.get(3).getKey());
        assertEquals(2, top10Endpoints.get(3).getValue().intValue());

        assertEquals("/login", top10Endpoints.get(4).getKey());
        assertEquals(1, top10Endpoints.get(4).getValue().intValue());
    }
}