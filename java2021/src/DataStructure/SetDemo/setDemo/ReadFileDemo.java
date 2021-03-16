package DataStructure.SetDemo.setDemo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 统计一篇英文中共有多少表单词（使用简单的分词）
 */
public class ReadFileDemo {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        boolean result = readFile("a-tale-of-two-cities.txt", words);
        System.out.println(words.size());
    }

    public static boolean readFile(String fileName, List<String> words) {
        if (fileName == null || fileName.isEmpty() || words == null) {
            System.out.println("fileName or words is null!!");
            return false;
        }
        File file = new File(fileName);
        BufferedReader bReader = null;
        try {
            bReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String text;
            while ((text = bReader.readLine()) != null) {
                matchWords(text, words);
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bReader != null) {
                try {
                    bReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void matchWords(String text, List<String> list) {
        Pattern pattern = Pattern.compile("[A-Za-z']+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            list.add(matcher.group());
        }
    }
}
