package src.com.br.cp.recursion.dp.knapsack.problems.uva;


import src.com.br.cp.io.JIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * https://onlinejudge.org/index.php?option=onlinejudge&page=show_problem&problem=1071
 */
public class P10130 {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private interface Converter<T> {
        T convert(String data);
    }


    private static <T> List<T> readValues(String del, Converter<T> converter) throws IOException {
        String[] data = reader.readLine().split(del);
        List<T> values = new ArrayList<>();
        for (String value : data) {
            values.add(converter.convert(value));
        }
        return values;
    }

    private static <T> T readValue(Converter<T> converter) throws IOException {
        return converter.convert(reader.readLine());
    }

    interface TestCase {
        void run();
    }

    private static void testCases(int cases, TestCase testCase) throws IOException {
        while (cases > 0) {
            testCase.run();
            cases -= 1;
        }
    }


    private static <T> void runWhileIsNotNull(Converter<T> converter) throws IOException {
        String enter;
        while ((enter = reader.readLine()) != null) {
            converter.convert(enter);
        }
    }

    public static void main(String[] args) {

    }
}
