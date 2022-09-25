package Task1;

import java.io.*;

public class General {
    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir"), "poem.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] poem = new byte[fileInputStream.available()];
            fileInputStream.read(poem);
            String[] words = new String(poem).split(" ");
            String temp = words[0];
            words[0] = words[words.length - 1];
            words[words.length - 1] = temp;
            StringBuilder output = new StringBuilder();
            for (String word : words) {
                output.append(word).append(" ");
            }
            String outputString = String.valueOf(output);
            byte[] outputByte = outputString.getBytes();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(outputByte);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
