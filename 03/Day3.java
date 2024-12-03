import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day3 {
    static String input;
    public static int level1() {
        Pattern p = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher m = p.matcher(input);
        int out = 0;
        while (m.find()) {
            out += getResultFromMul(m.group());
        }
        return out;
    }

    public static int level2() {
        boolean currentlyEnabled = true;
        Pattern p = Pattern.compile("don't\\(\\)|do\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher m = p.matcher(input);
        int out = 0;
        while (m.find()) {
            if (m.group().equals("do()"))
                currentlyEnabled = true;
            
            else if (m.group().equals("don't()"))
                currentlyEnabled = false;
            
            else if (currentlyEnabled) {
                out += getResultFromMul(m.group());
            }
        }
        return out;
    }

    private static int getResultFromMul(String mul){
        String[] nums = mul.substring(4, mul.length() - 1).split(",");
        return Integer.parseInt(nums[0]) * Integer.parseInt(nums[1]);
    }

    static {
        try { input = Files.readString(Paths.get("03/input.txt")); }
        catch (Exception e) { System.out.println("you are stupid put the input file in the right place"); }
    }
}
