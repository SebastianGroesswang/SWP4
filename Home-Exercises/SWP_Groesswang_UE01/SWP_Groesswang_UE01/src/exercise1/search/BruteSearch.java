package exercise1.search;

import exercise1.utils.DataCollector;

public class BruteSearch {
    /// creating an overloaded method for search, which will call the main search method with start index 0
    public static int search(String text, String pattern, DataCollector dataCollector) {
        return search(text, pattern, 0, dataCollector);
    }

    public static int search(String text, String pattern, int startPos, DataCollector dc) {
        if(dc.compareTerm(startPos < 0) || dc.compareTerm(startPos > text.length()) || dc.compareTerm(pattern.length() > text.length()))
            return -1;

        for (int i = dc.assignment(startPos);
             dc.compareTerm(
                     i <= dc.add(
                             text.length(),
                             - pattern.length()
                     )
             );
             i = dc.assignment(dc.add(i,1))
        ) {
            int k = dc.assignment(0);
            while (dc.compareTerm(k < pattern.length()) && //&& is a logical operator
                    dc.countedEqual(
                            dc.getIndex(text, dc.add(i, k)),
                            dc.getIndex(pattern, k)
                    )
            ) {

                k = dc.assignment(dc.add(k,1));
            }
            if (dc.compareTerm(k == pattern.length())) {
                return i;
            }
        }

        return -1;
    }
}
