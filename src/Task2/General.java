package Task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class General {
    public static void main(String[] args) {
        File file1 = new File(System.getProperty("user.dir"), "file1.txt");
        File file2 = new File(System.getProperty("user.dir"), "file2.txt");
        File file3 = new File(System.getProperty("user.dir"), "file3.txt");
        File file4 = new File(System.getProperty("user.dir"), "file4.txt");
        try (FileInputStream fileInputStream1 = new FileInputStream(file1);
             FileInputStream fileInputStream2 = new FileInputStream(file2);
             FileInputStream fileInputStream3 = new FileInputStream(file3);
             FileOutputStream fileOutputStream = new FileOutputStream(file4)) {
            byte[] first = new byte[fileInputStream1.available()];
            byte[] second = new byte[fileInputStream2.available()];
            byte[] third = new byte[fileInputStream3.available()];
            fileInputStream1.read(first);
            fileInputStream2.read(second);
            fileInputStream3.read(third);
            System.out.println("Размер первого файла: " + first.length + " байт");
            System.out.println("Размер второго файла: " + second.length + " байт");
            System.out.println("Размер третьего файла: " + third.length + " байт");
            byte[] filesTogether = new byte[first.length + second.length + third.length];
            System.out.println("Общий размер: " + filesTogether.length + " байт");
            int index = 0;
            for (int i = 0; i < first.length; i++) {
                filesTogether[index] = first[i];
                index++;
            }
            for (int i = 0; i < second.length; i++) {
                filesTogether[index] = second[i];
                index++;
            }
            for (int i = 0; i < third.length; i++) {
                filesTogether[index] = third[i];
                index++;
            }
            fileOutputStream.write(filesTogether);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
