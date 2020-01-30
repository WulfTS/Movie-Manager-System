import java.util.ArrayList;

public class OrderedArrayList<T extends Comparable<T>> extends ArrayList<T> {

        // Constructors
        public OrderedArrayList() { super(); }  // Default constructor
        public OrderedArrayList(OrderedArrayList<T> other) { super(other); }  // Copy constructor

        //Methods
        @Override
        public boolean add(T item) {  // Add a new item in order.
            if (size() == 0 || item.compareTo(get(size() - 1)) >= 0) {
                super.add(item);
            }
            for (int i = 0; i < size(); i++) {  // Linear search
                if (get(i).compareTo(item) > 0) {
                    super.add(i, item);
                    break;
                }
            }
            return false;
        }
}

