package org.example;


import org.example.api.LineCounter;
import org.example.implement.SimpleLineCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * @author 93253
 */
public class ValidLineCount {
    private static final String INPUT_FILE_PATH = "ValidLineCount/src/main/resources/StringUtils.java";
    private static final String OUTPUT_FILE_PATH = "ValidLineCount/src/main/resources/validLineCount.txt";

    public static void main(String[] args) throws IOException {
        LineCounter lineCounter = new SimpleLineCounter();

        // 读取输入
        List<String> lines = Files.readAllLines(Path.of(INPUT_FILE_PATH));

        int validLineCount = lineCounter.countValidLines(lines);
        Path filePath = Paths.get(OUTPUT_FILE_PATH);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }

        Files.createFile(filePath);

        // 将数字转换为字符串并写入文件
        Files.writeString(filePath, String.valueOf(validLineCount));
    }

}
