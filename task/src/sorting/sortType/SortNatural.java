package sorting.sortType;

import sorting.DataType;
import sorting.sortAlgorithm.MergeSort;

import java.util.Comparator;
import java.util.List;


public class SortNatural implements Sort {
    //Comparator<String> comparator = String::compareTo;

    @Override
    public String sortAndOutput(List<String> elements, DataType dt) {
        List<String> sortedList = new MergeSort<>(dt.getComparator()).sort(elements);

        StringBuilder sb = new StringBuilder();
        sb.append("Total ").append(dt.getPluralName()).append(": ").append(sortedList.size()).append("\nSorted data:");
        sortedList.forEach(e -> sb.append(dt == DataType.LINE ? "\n" : " ").append(e));

        return sb.toString();
    }
}
