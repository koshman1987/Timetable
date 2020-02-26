package by.koshman;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final String FILE_NAME = "src/main/resources/input.txt";

    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("output.txt", false);
        Map<String, Long> map = new HashMap<>();

        Files.lines(Paths.get(FILE_NAME), StandardCharsets.UTF_8).filter(s -> s.charAt(0) == 'P').
                filter(s -> (Duration.between(LocalTime.parse(s.substring(5, 10), DateTimeFormatter.ofPattern("HH:mm")),
                        LocalTime.parse(s.substring(11, 16), DateTimeFormatter.ofPattern("HH:mm"))).getSeconds() <= 3600))
                .sorted()
                .forEach(
                        s -> {
                            map.put(s.substring(5, 10), Duration.between(LocalTime.parse(s.substring(5, 10), DateTimeFormatter.ofPattern("HH:mm")),
                                    LocalTime.parse(s.substring(11, 16), DateTimeFormatter.ofPattern("HH:mm"))).getSeconds());
                            try {
                                writer.write(s);
                                writer.write("\n");
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
        try {
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Files.lines(Paths.get(FILE_NAME), StandardCharsets.UTF_8).filter(s -> s.charAt(0) == 'G').
                filter(s -> (Duration.between(LocalTime.parse(s.substring(7, 12), DateTimeFormatter.ofPattern("HH:mm")),
                        LocalTime.parse(s.substring(13, 18), DateTimeFormatter.ofPattern("HH:mm"))).getSeconds() <= 3600))
                .filter(s -> !(map.containsKey(s.substring(7, 12)) && map.get(s.substring(7, 12)) <= Duration.between(LocalTime.parse(s.substring(7, 12), DateTimeFormatter.ofPattern("HH:mm")),
                        LocalTime.parse(s.substring(13, 18), DateTimeFormatter.ofPattern("HH:mm"))).getSeconds()
                ))
                .sorted()
                .forEach(
                        s -> {
                            map.put(s.substring(13, 18), Duration.between(LocalTime.parse(s.substring(7, 12), DateTimeFormatter.ofPattern("HH:mm")),
                                    LocalTime.parse(s.substring(13, 18), DateTimeFormatter.ofPattern("HH:mm"))).getSeconds());
                            try {
                                writer.write(s);
                                writer.write("\n");
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
    }
}
