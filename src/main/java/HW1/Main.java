
package HW1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main{

    public Main(String fileName, int topN) throws IOException {

        List<String> words = new ArrayList<>();
        File file = new File(fileName);


        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            for (String word : st.split(" ")) {
                if (word.trim().length() > 0)
                    words.add(lowerCaseConverter(word));
            }

        }
        computeAvgLengthByFirstChar(words, topN);

    }


    private void computeAvgLengthByFirstChar(List<String> words, int topN) {
        Map<Character, List<String>> map = new HashMap<>();
        for (String word : words) {
            if (!map.containsKey(word.charAt(0))){
                map.put(word.charAt(0), new ArrayList<>());
            }
            map.get(word.charAt(0)).add(word);
        }

        List<CharAverage> resultList = new ArrayList<>();
        for (Character ch : map.keySet()) {
            List<String> list = map.get(ch);
            double sum = 0;
            for (String word : list) {
                sum += word.length();
            }
            double average = sum / list.size();
            resultList.add(new CharAverage(ch, average));

        }

        System.out.println("InitialCharacter AverageLength");
        for (CharAverage ca : resultList) {
            System.out.println(ca.ch + "\t\t\t\t\t" + ca.average);
        }
        System.out.println();
        System.out.println();

        Set<Pair> set = calculateMinPairDistance(words);

        int printSize = Math.min(topN, set.size());

        Pair[] array = set.toArray(new Pair[0]);
        Arrays.sort(array);
        for (int i = 0; i < printSize; i++) {
            System.out.println(array[i]);
        }
    }

    static class CharAverage {
        char ch;
        double average;

        public CharAverage(char ch, double average) {
            this.ch = ch;
            this.average = average;
        }
    }

    private Set<Pair> calculateMinPairDistance(List<String> words) {
        Set<Pair> set = new HashSet<>();

        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                String temp1 = words.get(i);
                String temp2 = words.get(j);

                if (temp1.equals(temp2)) {
                    continue;
                }
                double distance = calculatesTotalDistance(words, temp1, temp2);
                set.add(new Pair(temp1, temp2, distance));
            }
        }


        return set;
    }

    private double calculatesTotalDistance(List<String> words, String token1, String token2) {
        int temp1 = 0;
        int temp2 = 0;
        for (String word : words) {
            if (word.equals(token1))
                temp1++;
            if (word.equals(token2))
                temp2++;
        }
        int sum = 0;
        for (int i = 0; i < words.size(); i++) {
            if (!words.get(i).equals(token1))
                continue;
            for (int j = i; j < words.size(); j++) {
                if (!words.get(j).equals(token2))
                    continue;
                sum = sum + j - i;
                break;
            }
        }

        return (temp1 * temp2) / (1 + Math.log(sum));
    }
    private String lowerCaseConverter (String word) {

        word = word.toLowerCase();
        String temp = "";

        for (int i = 0; i < word.length(); i++) {
            if(Character.isLetterOrDigit(word.charAt(i))) {
                temp += word.charAt(i);
            }
        }
        return temp;

    }


    public static void main(String[] args) throws IOException {
        new Main(args[0],Integer.parseInt(args[1]));

    }


}