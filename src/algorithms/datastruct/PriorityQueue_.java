package algorithms.datastruct;

public class PriorityQueue_ {

    private int[] data;
    private int count;

    public PriorityQueue_(int size) {
        this.data = new int[size];
    }

    private void resize() {
        int newLength = this.data.length << 1 | 1;
        int[] new_data = new int[newLength];
        for (int i = 0; i < this.data.length; i++) {
            new_data[i] = this.data[i];
        }
        this.data = new_data;
    }

    private int poll() {
        return data[0];
    }

    private void shift(int pos) {
        for (int i = count-1; i >= pos; i--) {
            data[i+1] = data[i];
        }
        count++;
    }

    private void add(int newVal) {
        if (count >= this.data.length) {
            resize();
        }
        int pos = find_pos(newVal);
        shift(pos);
        insert(pos, newVal);
    }

    void print() {
        StringBuilder sb = new StringBuilder("[");
        boolean first = true;
        for (int i = 0; i < count; i++) {
            if (first) {
                first = false;
            } else {
                sb.append(",");
            }
            sb.append(data[i]);
        }
        System.out.println(sb.append("]"));
    }

    void insert(int pos, int val) {
        this.data[pos] = val;
    }

    int find_pos2(int val) {
        // this.data[0..count-1]
        for (int i = 0; i < count ; i++) {
            if (data[i] >= val) {
                return i;
            }
        }
        return count;
    }

    int find_pos(int val) {
        int left = 0;
        int right = count-1;
        while (left <= right) {
            int middle = (right + left) / 2;
            if (val > data[middle]) {
                left = middle + 1;
            } else if (val < data[middle]) {
                right = middle -1;
            } else {
                return middle;
            }
        }
        return left;

    }

    boolean contains(int val) {
        int left = 0;
        int right = count - 1;
        while (left <= right) {
            int middle = (left + right)/2;
            if (val < data[middle]) {
                right = middle - 1;
            } else if (val > data[middle]) {
                left = middle + 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        PriorityQueue_ pq = new PriorityQueue_(40);
        for (int i = 1; i <= 20; i++) {
            int val = (int) (Math.random()*99);
            pq.add(val);
            pq.print();
        }
        pq.add(42);
        pq.add(122);
        pq.add(133);
        pq.add(42);
        pq.print();
        System.out.println(pq.contains(101)); // false
        System.out.println(pq.contains(42)); // true
    }
}
