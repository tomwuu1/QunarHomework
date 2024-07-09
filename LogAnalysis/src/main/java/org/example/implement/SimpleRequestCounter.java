package org.example.implement;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.example.api.RequestCounter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimpleRequestCounter implements RequestCounter {
    @Override
    public long getPostRequestCount(List<String> lines) {
        return lines.stream().filter(line -> line.contains("POST")).count();
    }

    @Override
    public long getGetRequestCount(List<String> lines) {
        return lines.stream().filter(line -> line.contains("GET")).count();
    }

    @Override
    public long getTotalLineCount(List<String> lines) {
        return lines.size();
    }

    @Override
    public List<Map.Entry<String, Integer>> getTop10FrequentEndpoints(List<String> lines) {
        Multiset<String> uriCounts = HashMultiset.create();
        uriCounts.addAll(lines);
        
        return uriCounts.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getCount(), e1.getCount()))
                .limit(10)
                .map(e -> Map.entry(e.getElement(), e.getCount()))
                .collect(Collectors.toList());
    }
}