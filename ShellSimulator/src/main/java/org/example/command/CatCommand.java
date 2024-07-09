package org.example.command;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 93253
 */
public class CatCommand implements Command {
    private final File file;

    public CatCommand(String filename) {
        this.file = new File("ShellSimulator/src/main/resources/" +filename);
    }

    @Override
    public List<String> execute(List<String> input) throws IOException {
        return Files.readLines(file, StandardCharsets.UTF_8);
    }
}
