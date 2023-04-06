package sorting.sortAlgorithm;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeSort<T> implements SortAlghoritm<T> {
    Comparator<T> comparator;

    public MergeSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public List<T> sort(List<T> list) {
        return Arrays.asList(splitAndMerge((T[])list.toArray(),0,list.size()-1));
    }


    /**
     * Разделение массива на 2 части, рекурсивный вызов функции для каждой из частей и
     * затем объединение результатов с сортировкой
     * @param totalArr исходный массив
     * @param startIndex начало подмассива
     * @param endIndex конец подмассива
     * @return отсортированный новый массив
     */
    T[] splitAndMerge(final T[] totalArr, int startIndex, int endIndex) {
        if (startIndex != endIndex) {
            int middle = (startIndex + endIndex) / 2;
            return mergeSorted(
                    splitAndMerge(totalArr,startIndex,middle),
                    splitAndMerge(totalArr,middle+1, endIndex)
            );
        } else {
            @SuppressWarnings("unchecked")
            T[] sorted = (T[]) Array.newInstance(Object.class, 1);
            sorted[0] = totalArr[startIndex];
            return sorted;
        }
    }

    /**
     * Объединение двух отсортированных массивов с сортировкой
     *
     * @param left  первый отсортированный массив
     * @param right второй отсортированный массив
     * @return объединённый отсортированный массив
     */
    T[] mergeSorted(T[] left, T[] right) {
        @SuppressWarnings("unchecked")
        T[] sorted = (T[]) Array.newInstance(Object.class, left.length + right.length);

        for (int l = 0, r = 0, s = 0; s < sorted.length; s++) {
            if (l == left.length) // левый закончился берём из правого
                sorted[s] = right[r++];
            else if (r == right.length) // правый закончился берём из левого
                sorted[s] = left[l++];
            else if (comparator.compare(left[l], right[r]) <= 0)
                sorted[s] = left[l++]; // левый <= правого
            else sorted[s] = right[r++];
        }
        return sorted;
    }

}
