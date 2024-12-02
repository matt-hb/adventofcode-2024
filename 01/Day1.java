import java.io.*;
import java.util.*;

public class Day1 {
    static List<Integer> list1;
    static List<Integer> list2;

    public static int level1() throws IOException {
        readInput();
        Collections.sort(list1);
        Collections.sort(list2);
        int outp = 0;
        for (int i = 0; i < list1.size(); i++) {
            int diff = Math.abs(list1.get(i) - list2.get(i));
            outp += diff;
        }
        return outp;
    }

    public static int level2() throws IOException {
        readInput();
        int out = 0;
        for (int current : list1) {
            int count = 0;
            for (int i = 0; i < list2.size(); i++) {
                if (current == list2.get(i)) {
                    count++;
                }
            }
            out += current * count;
        }
        return out;
    }

    private static void readInput() throws IOException {
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("01/input.txt")));
        String line = br.readLine();
        while (line != null) {
            String[] parts = line.split(" {3}");
            list1.add(Integer.parseInt(parts[0]));
            list2.add(Integer.parseInt(parts[1]));
            line = br.readLine();
        }
        br.close();
    }
}
