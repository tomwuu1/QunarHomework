package org.example.api;

import com.google.common.base.Optional;

public interface LogParser {
    Optional<String> extractUriFromLogLine(String logLine);
}