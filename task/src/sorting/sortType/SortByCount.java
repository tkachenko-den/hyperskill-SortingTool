package sorting.sortType;

import sorting.DataType;
import sorting.sortAlgorithm.MergeSort;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortByCount implements Sort {

    static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(Map.Entry<K, V> mapEntry) {
            this.key = mapEntry.getKey();
            this.value = mapEntry.getValue();
        }
    }

    @Override
    public String sortAndOutput(List<String> elements, DataType dt) {
        Map<String, Integer> map = elements.stream().collect(Collectors.toMap(
                Function.identity(),
                t -> 1,
                Integer::sum));
        List<Pair<String, Integer>> sortedList =
                new MergeSort<>(
                        (Pair<String, Integer> t1, Pair<String, Integer> t2) ->
                                Objects.equals(t1.value, t2.value) ?
                                        dt.getComparator().compare(t1.key, t2.key) :
                                        t1.value.compareTo(t2.value)
                ).sort(map.entrySet().stream().map(Pair::new).toList());

        int totalCount = sortedList.stream().mapToInt(t -> t.value).sum();

        StringBuilder sb = new StringBuilder();
        sb.append("Total ").append(dt.getPluralName()).append(": ").append(totalCount).append("\n");
        sortedList.forEach(e -> sb.append(
                String.format(
                        "%s: %d time(s), %d%%\n",
                        e.key,
                        e.value,
                        e.value * 100 / totalCount))
        );
        return sb.toString();
    }
}
