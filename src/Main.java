import java.io.*;
import java.util.*;

public class Main {
    private static final StringBuilder templog = new StringBuilder();
    private static final String path = "C://Games";

    private static final List<String> dir_path = Arrays.asList(
            path + "/src", path + "/res", path + "/savegames", path + "/temp",
            path + "/src/main", path + "/src/test",
            path + "/res/drawables", path + "/res/vectors", path + "/res/icons"
    );

    private static final List<String> file_path = Arrays.asList(
            path + "/src/main/Main.java", path + "/src/main/Utils.java",
            path + "/temp/temp.txt"
    );

    private static void writeToTheLog(File file, boolean result) {
        templog.append(file.isDirectory() ? "директория -> " : "файл -> ")
                .append(file.getName())
                .append(result ? " -> создание завершено успешно" : " -> создание не было завершено")
                .append((!result && file.exists()) ? ", так объект уже был создан ранее" : "")
                .append("\n");
    }

    private static void writeToTheFile(File file) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(templog.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (String dirPath : dir_path) {
            File dir = new File(dirPath);
            writeToTheLog(dir, dir.mkdir());
        }

        for (String filePath : file_path) {
            File file = new File(filePath);
            try {
                writeToTheLog(file, file.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        writeToTheFile(new File(path + "/temp/temp.txt"));
    }

}