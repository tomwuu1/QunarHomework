package org.example;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextDecrytion {
    private static final String PROP_FILE_PATH = "TextDecryption/src/main/resources/sdxl_prop.txt";
    private static final String TEMPLATE_FILE_PATH = "TextDecryption/src/main/resources/sdxl_template.txt";
    private static final String OUTPUT_FILE_PATH = "TextDecryption/src/main/resources/sdxl.txt";

    public static void main(String[] args) throws IOException {
        // 读取文件
        List<String> propLines = Files.readAllLines(Path.of(PROP_FILE_PATH));
        List<String> templateLines = Files.readAllLines(Path.of(TEMPLATE_FILE_PATH));

        Map<String, List<String>> propMap = new HashMap<>();

        // 自然排序
        List<String> natureOrderList = propLines.stream()
                .map(line -> line.replaceFirst("^\\S+\\s+", ""))
                .collect(Collectors.toList());

        // 索引排序
        List<String> indexOrderList = propLines.stream()
                .sorted(Comparator.comparingInt(line -> Integer.parseInt(line.split("\\s+", 2)[0])))
                .map(line -> line.replaceFirst("^\\S+\\s+", ""))
                .collect(Collectors.toList());

        // 文本排序
        List<String> charOrderList = propLines.stream()
                .sorted(Comparator.comparing(line -> line.split("\\s+", 2)[1]))
                .map(line -> line.replaceFirst("^\\S+\\s+", ""))
                .collect(Collectors.toList());

        // 文本排序逆序
        List<String> charOrderDescList = propLines.stream()
                .sorted(Comparator.comparing((String line) -> line.split("\\s+", 2)[1]).reversed())
                .map(line -> line.replaceFirst("^\\S+\\s+", ""))
                .collect(Collectors.toList());

        // 加入map
        propMap.put("natureOrder", natureOrderList);
        propMap.put("indexOrder", indexOrderList);
        propMap.put("charOrder", charOrderList);
        propMap.put("charOrderDESC", charOrderDescList);

        // 替换
        Pattern pattern = Pattern.compile("\\$(\\w+)\\((\\d+)\\)");
        List<String> outputLines = new ArrayList<>();
        for (String line : templateLines) {
            Matcher matcher = pattern.matcher(line);
            StringBuilder sb = new StringBuilder();
            while (matcher.find()) {
                String functionName = matcher.group(1);
                int index = Integer.parseInt(matcher.group(2));
                List<String> values = propMap.get(functionName);
                if (values != null && index < values.size()) {
                    matcher.appendReplacement(sb, values.get(index));
                }
            }
            matcher.appendTail(sb);
            outputLines.add(sb.toString());
        }

        // 输出
        Files.write(Path.of(OUTPUT_FILE_PATH), outputLines);
    }
}