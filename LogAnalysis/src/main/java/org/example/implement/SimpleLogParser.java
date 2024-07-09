package org.example.implement;

import com.google.common.base.Optional;
import org.example.api.LogParser;

/**
 * @author 93253
 */
public class SimpleLogParser implements LogParser {

    private static final String SPACE_DELIMITER = " ";
    private static final int URI_POSITION = 1;

    @Override
    public Optional<String> extractUriFromLogLine(String logLine) {
        String[] logLineParts = logLine.split(SPACE_DELIMITER);
        if (logLineParts.length > URI_POSITION) {
            return Optional.of(logLineParts[URI_POSITION]);
        }
        return Optional.absent();
    }
}