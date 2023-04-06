package sorting;

import sorting.sortType.Sort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    /**
     * Ищет ключи или ключ-значение в параметрах командной строки. Ключи должны иметь дефис вначале
     *
     * @param args массив строк
     * @return карта ключ-значение
     */
    private static Map<String, String> getArgsAsMap(final String[] args) {
        return new Scanner(String.join(" ", args))
                .findAll("(-\\w+)\\s*(\\S*)")
                .collect(Collectors.toMap(mr -> mr.group(1), mr -> mr.group(2)));
    }

    public static void main(final String[] args) throws IOException {
        new ConsoleController(getArgsAsMap(args)).run();
    }
}
