package software.RN.org.io;
//yes
import software.RN.org.model.Box;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ManifoldReader {
    public static List<Box> readBoxes(Path path) throws IOException {
        // AtomicInteger es como usar i=0; i++ dentro de la Stream, pero usar esta clase
        // evita problemas de concurrencia dentro de Stream.parallelStream() y es x esta función que dentro
        // de Stream no se pueden modificar variables que se estén usando.
        AtomicInteger idGenerator = new AtomicInteger(0);

        return Files.lines(path)
                .filter(line -> !line.isBlank())
                .map(line -> {
                    String[] parts = line.split(",");
                    return new Box(
                            Integer.parseInt(parts[0].trim()),
                            Integer.parseInt(parts[1].trim()),
                            Integer.parseInt(parts[2].trim()),
                            idGenerator.getAndIncrement()
                    );
                })
                .toList();
    }
}