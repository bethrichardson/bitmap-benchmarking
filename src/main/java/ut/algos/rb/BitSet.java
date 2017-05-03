package ut.algos.rb;


public class BitSet {
    protected int set[];
    private int cardinality;

    public BitSet(int max_size) {
        set = new int[max_size];
        cardinality = 0;
    }

    public int getCardinality() {
        int ret = 0;
        for (int i: set) {
            ret += Integer.bitCount(i);
        }
        return ret;
    }

    public void add(int x) {
        int bit = x % 32;
        int idx = x / 32;
        int mask = 0x01 << bit;
        set[idx] |= (0x01 << bit);
    }

    public void remove(int x) {
        int bit = x % 32;
        int idx = x / 32;
        int mask = (0x01 << bit);
        set[idx] &= ~mask;
    }

    public boolean inSet(int x) {
        int bit = x % 32;
        int idx = x / 32;
        int ret =  (set[idx] & (0x01 << bit));
        return ret != 0;
    }

    public void and(BitSet b) {
        int smallest = Math.min(set.length, b.set.length);
        for (int i = 0; i < smallest; i++) {
            set[i] &= b.set[i];
        }
    }

    public void or(BitSet b) {
        int smallest = Math.min(set.length, b.set.length);
        for (int i = 0; i< smallest; i++) {
            set[i] |= b.set[i];
        }
    }

    public static void main(String []args) {
        BitSet b = new BitSet(100);
        b.add(0);
        b.add(1);
        b.inSet(0);
        b.remove(0);
        b.remove(1);
    }
}
