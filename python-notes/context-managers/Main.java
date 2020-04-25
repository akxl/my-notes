import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Main {

    public static void main(final String[] args) throws IOException {

        readTextFileBasic();
        readTextFileBasicWithManualWhileLoop();
        readTextFileWithTryResources();
        readTextFileWithStream();

    }

    static void readTextFileBasic() throws IOException {
        final File file = new File("my_text_file.txt");
        final Reader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        System.out.println(bufferedReader.readLine());
        bufferedReader.close();
    }

    static void readTextFileBasicWithManualWhileLoop() throws IOException {
        final File file = new File("my_text_file.txt");
        final Reader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    static void readTextFileWithTryResources() throws IOException {
        final File file = new File("my_text_file.txt");
        final Reader fileReader = new FileReader(file);
        try(BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    static void readTextFileWithStream() throws IOException {
        Files.lines(Path.of("my_text_file.txt"))
                .forEach(System.out::println);
    }

}
