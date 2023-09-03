// Задача 2: Файловый менеджер (ООП, исключения)

// Создайте класс FileManager. Этот класс должен иметь методы для выполнения основных 
// операций с файлами: чтение, запись и копирование. Каждый из этих методов должен выбрасывать 
// соответствующее исключение, если в процессе выполнения операции произошла ошибка (например, 
// FileNotFoundException, если файл не найден).

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class HW2 {
    private static final String MYFILENAME = "E:\\GeekBrains\\13. Exceptions\\seminar3.010923\\anyfile.data";
    private static final String COPYFILENAME = "E:\\GeekBrains\\13. Exceptions\\seminar3.010923\\copyfile.data";

    public static void main(String[] args) {
        testWrite();
        testRead();
        testCopy();
    }

    private static void testWrite() {
        FileManager fileManager = new FileManager();
        List<String> data = new ArrayList<>();
        data.add("строка первая");
        data.add("строка вторая");
        data.add("третья строка");
        try {
            fileManager.writeFile(MYFILENAME, data);
            System.out.println("Write OK!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testRead() {
        FileManager fileManager = new FileManager();
        List<String> data;
        try {
            data = fileManager.readFile(MYFILENAME);
            System.out.println("Read OK!");
            System.out.println(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            data = fileManager.readFile(MYFILENAME + "sdfsdf");
            System.out.println("Read OK!");
            System.out.println(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void testCopy() {
        FileManager fileManager = new FileManager();
        try {
            fileManager.copyFile(MYFILENAME, COPYFILENAME);
            System.out.println("Copy OK!");
        } catch (TargetFileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }

        try {
            fileManager.copyFile(MYFILENAME + "asdfsd", COPYFILENAME);
            System.out.println("Copy OK!");
        } catch (TargetFileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

class FileManager {
    public List<String> readFile(String fileName) throws TargetFileNotFoundException, TargetFileReadException {
        List<String> result = new ArrayList<String>();
        File file = new File(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.add(line);
            }
        } catch (FileNotFoundException exception) {
            throw new TargetFileNotFoundException("Файл \"" + fileName + "\" не найден");
        } catch (IOException exception) {
            throw new TargetFileReadException("Непредвиденная ошибка при чтении файла \"" + fileName + "\"");
        }

        return result;
    }

    public void writeFile(String fileName, List<String> data) throws TargetFileWriteException {
        File file = new File(fileName);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String line : data) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException exception) {
            throw new TargetFileWriteException("Непредвиденная ошибка при записи в файл \"" + fileName + "\"");
        }
    }

    public void copyFile(String sourceFileName, String targetFileName) throws TargetFileNotFoundException {
        try (InputStream in = new FileInputStream(sourceFileName);
                OutputStream out = new FileOutputStream(targetFileName);) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } catch (IOException exception) {
            throw new TargetFileNotFoundException("Файл \"" + sourceFileName + "\" не найден");
        }

    }
}

class TargetFileNotFoundException extends Exception {
    TargetFileNotFoundException(String message) {
        super(message);
    }
}

class TargetFileReadException extends Exception {
    TargetFileReadException(String message) {
        super(message);
    }
}

class TargetFileWriteException extends Exception {
    TargetFileWriteException(String message) {
        super(message);
    }
}
