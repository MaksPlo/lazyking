package main.java;

import java.util.*;

import static java.lang.System.*;
import static java.util.Arrays.asList;

class UnluckyVassal {

    public void printReportForKing(List<String> pollResults) {
        Map<String, String[]> map = new TreeMap<>();
        Set<String> vasals = new TreeSet<>();
        Set<String> bosses = new TreeSet<>();


        for (String str : pollResults) {
            String[] split = str.split(": ");
            if (split.length > 1) {
                map.put(split[0], split[1].split(", "));
                bosses.add(split[0]);
                vasals.addAll(asList(split[1].split(", ")));
            } else {
                map.put(split[0], new String[0]);
                bosses.add(split[0]);
            }
        }

        bosses.removeIf(vasals::contains);
        out.println("Король");
        print(map, bosses, "   ");
    }


    private void print(Map<String, String[]> map, Set<String> bosses, String space) {
        for (String boss : bosses) {
            out.println(space + boss);
            String[] newBosses = map.get(boss);
            space = space.concat("   ");
            if (newBosses != null) {
                print(map, new TreeSet<>(asList(newBosses)), space);
            }
            space = space.substring(0, space.length()-3);
        }
    }
}
