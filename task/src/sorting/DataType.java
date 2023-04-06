package sorting;

import java.util.Comparator;

public enum DataType {
    LONG("\\s+",
            Comparator.comparing(Integer::valueOf),
            "numbers"),
    LINE("\\r?\\n|\\r",
            Comparator.naturalOrder(),
            "words"),
    WORD("\\s+",
            Comparator.naturalOrder(),
            "lines");


    private final String regexDelimiter;
    private final Comparator<String> comparator;
    private final String pluralName;

    DataType(String regexDelimiter, Comparator<String> comparator, String pluralName) {
        this.regexDelimiter = regexDelimiter;
        this.comparator = comparator;
        this.pluralName = pluralName;
    }

    public String getRegexDelimiter() {
        return regexDelimiter;
    }

    public Comparator<String> getComparator() {
        return comparator;
    }

    public String getPluralName() {
        return pluralName;
    }

    public boolean isValueValid(String value) {
        return this == LONG ?
                value.matches("^-?\\d+$") :
                true;
    }
}
