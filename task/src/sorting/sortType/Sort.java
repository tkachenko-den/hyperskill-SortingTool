package sorting.sortType;

import sorting.DataType;

import java.util.List;

public interface Sort {
    String sortAndOutput(List<String> elements, DataType dt);

    static Sort getInstance(String sortingType) {
        return switch(sortingType) {
            case "byCount" -> new SortByCount();
            case "natural" -> new SortNatural();
            default -> null;
        };
    }
}
