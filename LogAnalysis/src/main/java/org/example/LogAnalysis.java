package org.example;

import com.google.common.base.Optional;

import org.example.api.CategoryExtractor;
import org.example.api.LogParser;
import org.example.api.RequestCounter;
import org.example.implement.SimpleCategoryExtractor;
import org.example.implement.SimpleLogParser;
import org.example.implement.SimpleRequestCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


/**
 * @author 22
 */
public class LogAnalysis {
    private static final String LOG_FILE_PATH = "LogAnalysis/src/main/resources/access.log";

    private final LogParser logParser;
    private final CategoryExtractor categoryExtractor;
    private final RequestCounter requestCounter;

    public LogAnalysis(LogParser logParser, CategoryExtractor categoryExtractor, RequestCounter requestCounter) {
        this.logParser = logParser;
        this.categoryExtractor = categoryExtractor;
        this.requestCounter = requestCounter;
    }

    public static void main(String[] args) throws IOException {
        LogParser logParser = new SimpleLogParser();
        CategoryExtractor categoryExtractor = new SimpleCategoryExtractor();
        RequestCounter requestCounter = new SimpleRequestCounter();

        LogAnalysis logAnalysis = new LogAnalysis(logParser, categoryExtractor, requestCounter);

        List<String> lines = Files.readAllLines(Path.of(LOG_FILE_PATH));

        if (lines.isEmpty()) {
            System.out.println("Log file is empty or not found.");
            return;
        }

        // 1. 请求总量；
        logAnalysis.printTotalLineCount(lines);

        // 2. 请求最频繁的 10 个 HTTP 接口，及其相应的请求数量；
        logAnalysis.printTop10FrequentEndpoints(lines);

        // 3. POST 和 GET 请求量分别为多少；
        logAnalysis.printPostAndGetRequestCounts(lines);

        // 4. URI 格式均为 /AAA/BBB 或者 /AAA/BBB/CCC 格式，按 AAA 分类，输出各个类别下 URI 都有哪些。
        logAnalysis.printUrisByCategory(lines);
    }

    public void printTotalLineCount(List<String> lines) {
        long totalRequests = requestCounter.getTotalLineCount(lines);

        System.out.println("1.\nTotal requests: " + totalRequests + "\n");
    }

    public void printTop10FrequentEndpoints(List<String> lines) {
        List<Map.Entry<String, Integer>> top10Endpoints = requestCounter.getTop10FrequentEndpoints(lines);

        System.out.println("2.\nTop 10 most frequent HTTP endpoints:");
        top10Endpoints.forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
        System.out.println();
    }

    public void printPostAndGetRequestCounts(List<String> lines) {
        long getRequests = requestCounter.getGetRequestCount(lines);
        long postRequests = requestCounter.getPostRequestCount(lines);

        System.out.println("3.\nGET requests: " + getRequests);
        System.out.println("POST requests: " + postRequests + "\n");
    }

    public void printUrisByCategory(List<String> lines) {
        Map<String, Set<String>> categorizedUris = new HashMap<>();

        for (String line : lines) {
            Optional<String> uriOptional = logParser.extractUriFromLogLine(line);
            if (uriOptional.isPresent()) {
                String uri = uriOptional.get();
                String category = categoryExtractor.extractCategory(uri);
                categorizedUris.computeIfAbsent(category, k -> new TreeSet<>()).add(uri);
            }
        }

        System.out.println("4. Categorized URIs:");
        for (Map.Entry<String, Set<String>> entry : categorizedUris.entrySet()) {
            System.out.println("Category: " + entry.getKey());
            for (String uri : entry.getValue()) {
                System.out.println("  " + uri);
            }
        }
    }
}