package org.example.command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

/**
 * @author 93253
 */
public class WcCommand implements Command {
    private final String filename;
    private static final String BASE_PATH = "ShellSimulator/src/main/resources/";

    public WcCommand() {
        this.filename = null;
    }

    public WcCommand(String filename) {
        this.filename = BASE_PATH + filename;
    }

    @Override
    public List<String> execute(List<String> input) throws IOException {
        if (filename != null) {
            Path filePath = Paths.get(filename);
            long lineCount = Files.lines(filePath).count();
            return Collections.singletonList(String.valueOf(lineCount));
        } else {
            return Collections.singletonList(String.valueOf(input.size()));
        }
    }
}
