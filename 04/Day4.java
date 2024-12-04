import java.io.*;
import java.util.*;

public class Day4 {
    static final char[] WORD_TO_SEARCH = "XMAS".toCharArray();
    static final char[] WORD_TO_X = "MAS".toCharArray(); // jesus christ
    static List<String> lines;
    
    public static int level1() {
        int out = 0;
        for (int y = 0; y < lines.size(); y++){
            for (int x = 0; x < lines.get(y).length(); x++){
                out += countWord(x, y);
            }
        }
        return out;
    }

    public static int level2() {
        int out = 0;
        for (int y = 0; y < lines.size(); y++){
            for (int x = 0; x < lines.get(y).length(); x++){
                out += wordInX(x, y) ? 1 : 0;
            }
        }
        return out;
    }

    private static int countWord(int x, int y){
        int count = 0;
        for (int dx = -1; dx <= 1; dx++){
            for (int dy = -1; dy <= 1; dy++){
                if (dx == 0 && dy == 0){
                    continue;
                }
                if (wordInDirection(x, y, dx, dy)){
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean wordInDirection(int originx, int originy, int dx, int dy){
        int currentx = originx;
        int currenty = originy;
        for (char c : WORD_TO_SEARCH){
            if (currentx < 0 || currentx >= lines.get(0).length() || currenty < 0 || currenty >= lines.size()){
                return false;
            }
            if (lines.get(currenty).charAt(currentx) != c){
                return false;
            }
            currentx += dx;
            currenty += dy;
        }
        return true;
    }

    private static boolean wordInX(int x, int y){
        if (lines.get(y).charAt(x) != WORD_TO_X[1]){
            return false;
        }
        if (x - 1 < 0 || x + 1 >= lines.get(0).length() || y - 1 < 0 || y + 1 >= lines.size()){
            return false;
        }
        return 
            (
            (lines.get(y-1).charAt(x-1) == WORD_TO_X[0] && lines.get(y+1).charAt(x+1) == WORD_TO_X[2]) ||
            (lines.get(y-1).charAt(x-1) == WORD_TO_X[2] && lines.get(y+1).charAt(x+1) == WORD_TO_X[0])
            ) && (
            (lines.get(y-1).charAt(x+1) == WORD_TO_X[0] && lines.get(y+1).charAt(x-1) == WORD_TO_X[2]) ||
            (lines.get(y-1).charAt(x+1) == WORD_TO_X[2] && lines.get(y+1).charAt(x-1) == WORD_TO_X[0])
            ); // big bool
    }

    static {
        try {
            lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(new File("04/input.txt")));
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) { /* ignored :~) */ }
    }
}
