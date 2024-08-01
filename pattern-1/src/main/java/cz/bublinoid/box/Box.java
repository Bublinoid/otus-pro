package cz.bublinoid.box;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Box implements Iterable<String> {
    private final List<String> list1 = new ArrayList<>();
    private final List<String> list2 = new ArrayList<>();
    private final List<String> list3 = new ArrayList<>();
    private final List<String> list4 = new ArrayList<>();

    public void addToList1(String item) {
        list1.add(item);
    }

    public void addToList2(String item) {
        list2.add(item);
    }

    public void addToList3(String item) {
        list3.add(item);
    }

    public void addToList4(String item) {
        list4.add(item);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int listIndex = 1;
            private int itemIndex = 0;
            private final List<List<String>> lists = List.of(list1, list2, list3, list4);

            @Override
            public boolean hasNext() {
                while (listIndex <= lists.size()) {
                    if (itemIndex < lists.get(listIndex - 1).size()) {
                        return true;
                    } else {
                        listIndex++;
                        itemIndex = 0;
                    }
                }
                return false;
            }

            @Override
            public String next() {
                return lists.get(listIndex - 1).get(itemIndex++);
            }
        };
    }
}
