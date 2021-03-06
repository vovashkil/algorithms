package algorithms.datastruct;

public class XHashMap implements IMap<Integer, String> {

    class Entry {
        Integer key;
        String value;
        Entry next;

        public Entry(Integer key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("E:{k:%d, v:'%s', n:%s}", key, value, next);
        }
    }

    private Entry[] entries;
    private final static int LEN = 16;

    public XHashMap() {
        this(LEN);
    }

    public XHashMap(int len) {
        this.entries = new Entry[len];
    }

    private int hash(int object) {
        return object % LEN;
    }

    @Override
    public void put(Integer key, String val) {
        int index = hash(key);
        Entry ent = new Entry(key, val);

        Entry curr = this.entries[index];
        if (curr == null) {
            this.entries[index] = ent;
        } else {
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = ent;
        }
    }

    @Override
    public String get(Integer key) {
        int index = hash(key);
        Entry curr = this.entries[index];
        if (curr == null) {
            throw new IllegalArgumentException(String.format("element with key %d not found (1st)", key));
        }
        while (curr != null) {
            if (curr.key == key) {
                return curr.value;
            }
            curr = curr.next;
        }
        throw new IllegalArgumentException(String.format("element with key %d not found(tail)", key));
    }

    public static void main1(String[] args) {
        XHashMap map = new XHashMap();
        map.put(1, "Dima");
        map.put(2, "Lena");
        System.out.println(map.get(1));
        System.out.println(map.get(2));
        System.out.println(map.get(3));
    }

    public static void main(String[] args) {
        XHashMap map = new XHashMap(100);
        map.put(1, "Dima");
        map.put(17, "Lena");
        map.put(33, "Ira");
        System.out.println(map.get(1));
        System.out.println(map.get(17));
        System.out.println(map.get(33));
//        System.out.println(map.get(65)); // element with key 65 not found(tail)
//        System.out.println(map.get(66)); // element with key 66 not found (1st)
    }
}
