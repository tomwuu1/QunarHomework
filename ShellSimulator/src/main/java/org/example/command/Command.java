package org.example.command;

import java.io.IOException;
import java.util.List;

/**
 * @author 93253
 */
public interface Command {
    List<String> execute(List<String> input) throws IOException;
}
