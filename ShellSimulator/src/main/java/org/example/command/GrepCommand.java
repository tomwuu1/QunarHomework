package org.example.command;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 93253
 */
public class GrepCommand implements Command {
    private final String keyword;

    public GrepCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public List<String> execute(List<String> input) {
        return input.stream()
                .filter(line -> line.contains(keyword))
                .collect(Collectors.toList());
    }
}
