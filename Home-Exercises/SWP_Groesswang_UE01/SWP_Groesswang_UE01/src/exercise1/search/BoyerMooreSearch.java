package exercise1.search;

import exercise1.utils.DataCollector;

public class BoyerMooreSearch {

    // -------------- private --------------


    static Integer[] getShiftTable(final String pattern, final DataCollector dataCollector) {
        //should be an Integer array as its easier for generic Collection usage
        Integer[] result = dataCollector.assignment(new Integer[256]);

        int n = dataCollector.assignment(pattern.length());

        for (int i = dataCollector.assignment(0);
             dataCollector.compareTerm(i < 256);
             i = dataCollector.assignment(dataCollector.add(i, 1))
        ) {
            dataCollector.setIndexOfCollection(result, i, n);
        }

        for (int i = dataCollector.assignment(0);
             dataCollector.compareTerm(i < dataCollector.add(n, -1));
             i = dataCollector.assignment(dataCollector.add(i, 1))
        ) {
            dataCollector.setIndexOfCollection(
                    result,
                    dataCollector.getIndex(pattern, i),
                    dataCollector.add(n, dataCollector.add(-1, -i))
            );
        }

        return result;
    }

    // -------------- public --------------
    public static int search(final String text, final String pattern, final DataCollector dataCollector) {
        return search(text, pattern, 0, dataCollector);
    }

    public static int search(final String text, final String pattern, int startPos, final DataCollector dataCollector) {

        if (dataCollector.compareTerm(text.length() < pattern.length()) || dataCollector.compareTerm(startPos < 0) || dataCollector.compareTerm(startPos > text.length())) {
            return -1;
        }

        Integer[] shiftTable = dataCollector.assignment(getShiftTable(pattern, dataCollector));

        dataCollector.assignment(startPos);
        int k;
        int n = dataCollector.assignment(pattern.length());
        int m = dataCollector.assignment(text.length());

        do {
            k = dataCollector.assignment(n);

            do {
                k = dataCollector.assignment(dataCollector.add(k, -1));
            } while (
                    dataCollector.compareTerm(k >= 0) &&
                            dataCollector.countedEqual(
                                dataCollector.getIndex(text, dataCollector.add(startPos, k)
                                ),
                                dataCollector.getIndex(pattern, k)
                            )
            );

            if (dataCollector.compareTerm(k >= 0)) {
                char bad_char = dataCollector.assignment(
                        dataCollector.getIndex(
                                text,
                                dataCollector.add(dataCollector.add(startPos, n), -1))
                );

                startPos = dataCollector.assignment(
                        dataCollector.add(
                                startPos, dataCollector.getIndex(shiftTable, bad_char)
                        )
                );
            }
        } while (
                dataCollector.compareTerm(k >= 0) && dataCollector.compareTerm(startPos <= dataCollector.add(m, -n))
        );

        return dataCollector.compareTerm(k < 0) ? startPos : -1;
    }
}
