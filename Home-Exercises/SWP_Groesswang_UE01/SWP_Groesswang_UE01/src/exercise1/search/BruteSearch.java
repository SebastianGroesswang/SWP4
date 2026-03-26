package exercise1.search;

import exercise1.utils.DataCollector;

public class BruteSearch {
    /// creating an overloaded method for search, which will call the main search method with start index 0
    public static int search(String text, String pattern, DataCollector dataCollector) {
        return search(text, pattern, 0, dataCollector);
    }

    public static int search(String text, String pattern, int start, DataCollector dataCollector) {

        for (int i = dataCollector.assignment(start);
             dataCollector.compareTerm(
                     i < dataCollector.add(
                             text.length(),
                             - pattern.length()
                     )
             );
             dataCollector.assignment(dataCollector.add(i,1))
        ) {
            int k = dataCollector.assignment(0);
            while (dataCollector.compareTerm(k < pattern.length()) && //&& is a logical operator
                    dataCollector.countedEqual(
                            dataCollector.getIndex(text, dataCollector.add(i, k)),
                            dataCollector.getIndex(pattern, k)
                    )
            ) {

                k = dataCollector.assignment(dataCollector.add(k,1));
            }
            if (dataCollector.compareTerm(k == pattern.length())) {
                return i;
            }
        }

        return -1;
    }
}
