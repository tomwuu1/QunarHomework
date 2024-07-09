package org.example.api;

import java.util.List;
import java.util.Map;

public interface RequestCounter {
    long getPostRequestCount(List<String> lines);

    long getGetRequestCount(List<String> lines);

    long getTotalLineCount(List<String> lines);

    List<Map.Entry<String, Integer>> getTop10FrequentEndpoints(List<String> lines);
}