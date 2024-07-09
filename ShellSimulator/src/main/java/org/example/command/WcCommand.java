package org.example.command;

import java.util.Collections;
import java.util.List;

/**
 * @author 93253
 */
public class WcCommand implements Command {
    @Override
    public List<String> execute(List<String> input) {
        return Collections.singletonList(String.valueOf(input.size()));
    }
}
