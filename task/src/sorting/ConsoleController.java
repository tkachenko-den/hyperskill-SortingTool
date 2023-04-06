package sorting;

import sorting.sortType.Sort;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleController {
    Map<String, String> args;
    final InputStream in;
    final PrintStream out;
    final PrintStream err;

    public ConsoleController(Map<String, String> args) throws FileNotFoundException {
        this.args = args;
        in = args.containsKey("-inputFile") ?
                new FileInputStream(args.get("-inputFile")) :
                System.in;
        out = args.containsKey("-outputFile") ?
                new PrintStream(args.get("-outputFile")) :
                System.out;
        err = System.err;
    }

    public void run() throws IOException {
        if (checkArgs()) {
            DataType dataType = DataType.valueOf(args.getOrDefault("-dataType","line").toUpperCase());
            out.println(
                    Sort.getInstance(args.getOrDefault("-sortingType", "natural"))
                            .sortAndOutput(getInputElements(dataType), dataType)
            );
        }
    }

    List<String> getInputElements(DataType dt) throws IOException {
        List<String> incorrectInput = new ArrayList<>();
        try {
            return new Scanner(in)
                    .useDelimiter(dt.getRegexDelimiter())
                    .tokens()
                    .filter(e -> {
                        if (!dt.isValueValid(e)) {
                            incorrectInput.add(e);
                            return false;
                        } else
                            return true;
                    })
                    .toList();
        } finally {
            incorrectInput.forEach(e -> err.println("\"" + e + "\" is not a long. It will be skipped."));
            in.close();
        }
    }

    boolean checkArgs() {
        List<String> knownArgs = List.of("-sortingType", "-dataType","-inputFile","-outputFile");

        try {
            if (args.containsKey("-sortingType") && args.get("-sortingType").isEmpty())
                throw new Exception("No sorting type defined!");
            if (args.containsKey("-dataType") && args.get("-dataType").isEmpty())
                throw new Exception("No data type defined!");
            args.keySet().stream()
                    .filter(k -> !knownArgs.contains(k))
                    .map(k -> "\"" + k + "\" is not a valid parameter. It will be skipped.")
                    .forEach(err::println);
        } catch (Exception e) {
            err.println(e.getMessage());
            return false;
        }
        return true;
    }

}
