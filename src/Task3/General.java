package Task3;

import java.io.*;

public class General {
    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir"), "task3.txt");
        try (BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(file))) {
            byte[] text = new byte[fileInputStream.available()];
            fileInputStream.read(text);
            String[] textString = new String(text).split(" ");
            String surname = null;
            int indexOfSurname = 0;
            int sum = 0;
            int countOfMarks = 0;
            for (int i = 0; i < textString.length; i++) {
                if (isNumber(textString[i]) && surname != null) {
                    sum += Integer.parseInt(textString[i]);
                    countOfMarks++;
                }
                if (surname == null) {
                    surname = textString[i];
                    indexOfSurname = i;
                    continue;
                }
                if (!isNumber(textString[i]) || i == textString.length - 1) {
                    if (sum / countOfMarks >= 7) {
                        textString[indexOfSurname] = textString[indexOfSurname].toUpperCase();
                    }
                    surname = null;
                    countOfMarks = 0;
                    sum = 0;
                    i--;
                }
            }
            FileOutputStream bufferedOutputStream = new FileOutputStream(file);
            bufferedOutputStream.write(getBytes(textString));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getBytes(String[] textString) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < textString.length; i++) {
            if (i == textString.length - 1) {
                stringBuilder.append(textString[i]);
                break;
            }
            stringBuilder.append(textString[i]).append(" ");
        }
        return String.valueOf(stringBuilder).getBytes();
    }

    static boolean isNumber (String word) {
        try {
            Integer.parseInt(word);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
