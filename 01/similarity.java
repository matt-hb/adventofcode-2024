import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class similarity {
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
        int out = 0;
        for (int current : list1) {
            int count = 0;
            for (int i = 0; i < list2.size(); i++) {
                if (current == list2.get(i)) {
                    count++;
                }
            }
            System.out.println(current + " found " + count + " times");
            out += current * count;
        }
        System.out.println(out);
    }
}
