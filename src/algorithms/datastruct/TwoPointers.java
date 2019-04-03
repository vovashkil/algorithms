package algorithms.datastruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoPointers {
    private final static int[] data = {56,1,2,3,4,7,4,3,1,2,3,1,2,3,4};
    private final static Map<List<Integer>, Integer> counter = new HashMap<>();

    private static void inc(List<Integer> key) {
        if (counter.containsKey(key)) {
            Integer val = counter.get(key);
            val++;
            counter.put(key, val);
        } else {
            counter.put(key, 1);
        }
    }

    private static List<Integer> seq(int idx1, int len) {
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = idx1; i < idx1+len; i++) {
            a.add(data[i]);
        }
        return a;
    }

    private static boolean compare(List<Integer> seq1, List<Integer> seq2) {
        return seq1.equals(seq2);
    }

    public static void main(String[] args) {
        final int len = data.length;
        for (int l = 3; l < len - 3; l++) { // pattern length
            for (int left = 0; left < len - l; left++) { // pattern position
                List<Integer> pattern = seq(left, l);
                System.out.println(pattern.toString());
                for (int pos = 0; pos < len - l + 1; pos++) {
                    List<Integer> seq = seq(pos, l);
                    if (pos != left) {
                        if (compare(pattern, seq)) {
                            inc(pattern);
                        }
                    }
                }
            }
        }
        counter.entrySet()
                .stream()
                .filter(e -> e.getValue()>1)
                .forEach(e -> System.out.printf("pattern:%s, count:%d\n", e.getKey().toString(), e.getValue()));
    }
}
