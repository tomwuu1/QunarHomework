package org.example.implement;

import org.example.api.LineCounter;

import java.util.List;

public class SimpleLineCounter implements LineCounter {
    private static final String BLOCK_COMMENT_START = "/*";
    private static final String BLOCK_COMMENT_END = "*/";
    private static final String LINE_COMMENT = "//";

    @Override
    public int countValidLines(List<String> lines) {
        boolean inBlockComment = false;
        int validLineCount = 0;

        for (String line : lines) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            if (inBlockComment) {
                if (line.endsWith(BLOCK_COMMENT_END)) {
                    inBlockComment = false;
                }
                continue;
            }

            if (line.startsWith(BLOCK_COMMENT_START)) {
                inBlockComment = true;
                continue;
            }

            if (line.startsWith(LINE_COMMENT)) {
                continue;
            }

            validLineCount++;
        }

        return validLineCount;
    }
}
