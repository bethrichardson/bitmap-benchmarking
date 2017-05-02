package ut.algos.rb;


public class BitSet {
    char set[];

    public BitSet(int max_size) {
        set = new char[max_size];
    }

    public void add(int x) {
        int bit = x % 8;
        int idx = x / 8;
        set[idx] |= (0x01 << bit);
    }

    public boolean inSet(int x) {
        int bit = x % 8;
        int idx = x / 8;
        int ret =  (set[idx] & (0x01 << bit));
        return ret != 0;
    }

    public static void main(String []args) {
        BitSet b = new BitSet(100);
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0)
                b.add(i);
        }
        for (int i = 0; i < 100; i++) {
            System.out.println(b.inSet(i));
        }
    }
}
