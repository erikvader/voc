import org.python.stdlib.datetime.DateTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Sprint3 {
    public static int countFuture(String[] isoDateTimes) {
        int count = 0;
        DateTime today = (DateTime) DateTime.today();

        for (String iso : isoDateTimes) {
            DateTime dateTime = (DateTime) DateTime.fromisoformat(iso);
            if (dateTime.__gt__(today).toBoolean()) {
                count++;
            }
        }

        return count;
    }

    private static String[] loadIsos(String filename) throws IOException {
        return Files.readString(Paths.get(filename)).split(",");
    }

    public static void main(String[] args) throws IOException {
        String[] isos = loadIsos("isos-250001.txt");

        long startTime = System.currentTimeMillis();

        int count = countFuture(isos);

        long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime-startTime) + "ms");

        System.out.println("Future count: " + count);
    }
}
