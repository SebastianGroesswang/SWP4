package exercise1.search;

import exercise1.utils.DataCollector;

import java.util.ArrayList;

public class KnuthMorrisPrattSearch {

    private static ArrayList<Integer> computePrefixFunction(final String pattern, final DataCollector dc) {
        ArrayList<Integer> prefixFunction = dc.assignment(new ArrayList<>());
        int n = dc.assignment(pattern.length());
        int k = dc.assignment(0);
        int s = dc.assignment(-1);

        prefixFunction.add(s);

        while (dc.compareTerm(k < n)) {
            while (dc.compareTerm(s >= 0) && dc.compareTerm(dc.getIndex(pattern, s) != dc.getIndex(pattern, k))) {
                s = dc.assignment(dc.getIndex(prefixFunction, s));
            }

            s = dc.assignment(dc.add(s, 1));
            prefixFunction.add(s);
            k = dc.assignment(dc.add(k, 1));
        }

        return prefixFunction;
    }

    public static int search(String text, String pattern, DataCollector dc) {
        return search(text, pattern, 0, dc);
    }

    public static int search(String text, String pattern, int startPos, DataCollector dc) {
        if (dc.compareTerm(startPos < 0) ||
                dc.compareTerm(startPos > text.length()) ||
                dc.compareTerm(pattern.length() > text.length()) ||
                dc.compareTerm(text.isEmpty()))
            return -1;

        ArrayList<Integer> prefixFunction = dc.assignment(computePrefixFunction(pattern, dc));
        int m = dc.assignment(text.length());
        int n = dc.assignment(pattern.length());
        int c = dc.assignment(0);
        int k = dc.assignment(0);

        while (dc.compareTerm(c < m) && dc.compareTerm(k < n)) {
            while (dc.compareTerm(k >= 0) && !dc.countedEqual(dc.getIndex(text, c), dc.getIndex(pattern, k))) {
                k = dc.assignment(dc.getIndex(prefixFunction, k));
            }
            c = dc.assignment(dc.add(c, 1));
            k = dc.assignment(dc.add(k, 1));
        }

        return dc.compareTerm(k == n) ? dc.add(c, -n) : -1;
    }
}
