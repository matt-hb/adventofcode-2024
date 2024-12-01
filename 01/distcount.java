import java.io.*;
import java.util.*;

public class distcount {
    public static void main(String[] args) throws Exception {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String line = br.readLine();
        while (line != null) {
            String[] parts = line.split(" {3}");
            list1.add(Integer.parseInt(parts[0]));
            list2.add(Integer.parseInt(parts[1]));
            line = br.readLine();
        }
        br.close();
        Collections.sort(list1);
        Collections.sort(list2);
        int outp = 0;
        for (int i = 0; i < list1.size(); i++) {
            int diff = Math.abs(list1.get(i) - list2.get(i));
            System.out.println(list1.get(i) + " " + list2.get(i) + " diff: " + diff);
            outp += diff;
        }
        System.out.println(outp);
    }
}