// from http://osherove.com/tdd-kata-1/

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = ",";
    private static final String DELIMITER_SINGLE_CHAR_REGEX = "//(.)\n";
    private static final String DELIMITER_MULTI_CHAR_REGEX = "//\\[(.+)\\]\n";

    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter;
        Pattern pattern = Pattern.compile(DELIMITER_SINGLE_CHAR_REGEX);
        Matcher matcher = pattern.matcher(numbers);
        if (matcher.find()) {
            delimiter = matcher.group(1);
            numbers = numbers.replaceFirst(DELIMITER_SINGLE_CHAR_REGEX, "");
        } else {
            Pattern p2 = Pattern.compile(DELIMITER_MULTI_CHAR_REGEX);
            Matcher m2 = p2.matcher(numbers);

            if (m2.find()) {
                delimiter = m2.group(1);
                numbers = numbers.replaceFirst(DELIMITER_MULTI_CHAR_REGEX, "");
            } else {
                delimiter = DEFAULT_DELIMITER;
            }
        }

//        System.out.println(delimiter);

        if (numbers.contains(delimiter) || numbers.contains("\n")) {
            String[] parts = numbers.split(delimiter + "|" + "\n");

            List<Integer> negatives = new LinkedList<>();
            int result = 0;
            for (String s : parts) {
                int i = Integer.parseInt(s);

                if (i < 0) {
                    negatives.add(i);
                } else {
                    if (i <= 1000) {
                        result += i;
                    }
                }
            }

            if (negatives.size() > 0) {
                String s = "Negative numbers not allowed: ";

                boolean processedFirst = false;
                for (Integer i : negatives) {
                    if (processedFirst) {
                        s += ", " + i;
                    } else {
                        s += i;
                        processedFirst = true;
                    }
                }

                throw new RuntimeException(s);
            } else {
                return result;
            }
        } else {
            return Integer.parseInt(numbers);
        }
    }
}
