import java.io.*;
import java.util.*;

public class Day2 {
    static List<List<Integer>> list;

    public static int level1() throws IOException {
        readInput();
        int safeCount = 0;
        for (List<Integer> l : list) {
            if (isSafe(l)) {
                safeCount++;
            }
        }
        return safeCount;
    }

    public static int level2() throws IOException {
        readInput();
        int safeCount = 0;
        for (List<Integer> l : list) {
            if (isSafe(l)) {
                safeCount++;
            }
            // Only for level 2
            else {
                for(int i = 0; i < l.size(); i++){
                    List<Integer> copy = new ArrayList<>(l);
                    copy.remove(i);
                    if (isSafe(copy)){
                        safeCount++;
                        break;
                    }
                }
            }
            // End of Only for level 2
        }
        return safeCount;
    }

    private static void readInput() throws IOException {
        list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("02/input.txt")));
        String line = br.readLine();
        while (line != null) {
            List<Integer> current = new ArrayList<>();
            String[] parts = line.split(" ");
            for (String part : parts) {
                current.add(Integer.parseInt(part));
            }
            list.add(current);
            line = br.readLine();
        }
        br.close();
    }

    private static boolean isSafe(List<Integer> list){
        List<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy);
        List<Integer> copy2 = new ArrayList<>(list);
        Collections.sort(copy2);
        Collections.reverse(copy2);
        if (!list.equals(copy) && !list.equals(copy2)) {
            return false;
        }
        for (int i = 1; i<list.size(); i++){
            if (Math.abs(list.get(i) - list.get(i-1)) > 3 || list.get(i).equals(list.get(i-1))){
                return false;
            }
        }
        return true;
    }
}
