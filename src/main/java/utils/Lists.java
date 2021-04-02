package utils;

import java.util.ArrayList;
import java.util.Collections;

public class Lists {
    public static <E> ArrayList<E> newArrayList(E... elements) {
        if (elements == null) return new ArrayList<>();
        ArrayList<E> list = new ArrayList<>(elements.length);
        Collections.addAll(list, elements);
        return list;
    }
}
