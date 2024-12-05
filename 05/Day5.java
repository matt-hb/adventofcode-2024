import java.io.*;
import java.util.*;

public class Day5 {
    // key is the page number on the right, value is a list of pages that must precede it
    static Map<Integer, Set<Integer>> rules;
    // the proposed changes as a list of page numbers
    static List<List<Integer>> changes;

    public static int level1(){
        int count = 0;
        for (List<Integer> change : changes){
            if (changeIsValid(change)){
                count += middleValueOf(change);
            }
        }
        return count;
    }

    public static int level2(){
        int count = 0;
        for (List<Integer> change : changes){
            if (!changeIsValid(change)){
                count += middleValueOf(fixOrdering(change));
            }
        }
        return count;
    }

    private static List<Integer> fixOrdering(List<Integer> invalid){
        List<Integer> fixed = new ArrayList<>(invalid);
        // i know this is fucking ass but it's faster than bogosort and i should be studying for a midterm instead of this shit
        // should have done part 1 differently by finding errors instead of general check, but as we say "ilyen az élet"
        while(!changeIsValid(fixed)){
            fixBrokenRule(fixed);
        }
        return fixed;
    }

    private static boolean changeIsValid(List<Integer> change){
        return change.stream()
                    .noneMatch(c -> earlierThanAnyInList(c, change));
    }

    private static void fixBrokenRule(List<Integer> change){
        for (int i = 0; i < change.size(); i++){
            int page = change.get(i);
            if (earlierThanAnyInList(page, change)){
                int later = change.indexOf(
                    change.subList(i, change.size())
                        .stream()
                        .filter(c -> rules.get(page).contains(c))
                        .findFirst()
                        .get()
                );
                Collections.swap(change, i, later);
                return;
            }
        }
    }
    
    private static boolean earlierThanAnyInList(int page, List<Integer> change){
        if (!rules.containsKey(page)){
            return false;
        }
        Set<Integer> checks = rules.get(page);
        return checks.stream()
                    .anyMatch(check -> change.contains(check) && change.indexOf(page) < change.indexOf(check));
    }

    private static int middleValueOf(List<Integer> list){
        // assumes odd length, returns the 2nd middle value if length is even
        return list.get(list.size() / 2);
    }

    static {
        try {
            changes = new ArrayList<>();
            rules = new HashMap<>();
            BufferedReader br = new BufferedReader(new FileReader(new File("05/input.txt")));
            boolean readingChanges = false;
            String line = br.readLine();
            while (line != null) {
                if (!readingChanges && line.equals("")){
                    readingChanges = true;
                } else if (readingChanges){
                    List<Integer> change = new ArrayList<>();
                    for (String s : line.split(",")){
                        change.add(Integer.parseInt(s));
                    }
                    changes.add(change);
                } else {
                    String[] split = line.split("[|]");
                    int later = Integer.parseInt(split[1]);
                    int earlier = Integer.parseInt(split[0]);
                    if (rules.containsKey(later)){
                        rules.get(later).add(earlier);
                    } else {
                        Set<Integer> set = new HashSet<>();
                        set.add(earlier);
                        rules.put(later, set);
                    }
                }
                line = br.readLine();
            }
            br.close();
        } catch (Exception e) { /* ignored :~) */ }
    }
}