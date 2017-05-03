package ut.algos.rb;


public class BitSet {
    protected long set[];
    private int bitsPerItem = Short.SIZE;
    private int cardinality;

    public BitSet(int max_size) {
        set = new long[max_size];
        cardinality = 0;
    }

    public boolean contains(short x) {
        /*
          Paper mentions that modulo and division are performed in a single
          instruction here.
         */
        return (set[x / Long.SIZE] & (1L << (x % Long.SIZE))) != 0;
    }


    public int getCardinality() {
        return cardinality;
    }

    public void add(short x) {
        if (!contains(x)) {
            set[x / Long.SIZE] |= (1L << (x % Long.SIZE));
            cardinality++;
        }
    }

    public void remove(short x) {
        if (contains(x)) {
            set[x / Long.SIZE] &= ~(1L << (x % Long.SIZE));
            cardinality--;
        }
    }

    public void recomputeCardinality() {
        cardinality = 0;
        for (int i = 0; i < set.length; i++) {
            cardinality += Long.bitCount(set[i]);
        }
    }

    public void and(BitSet b) {
        int smallest = Math.min(set.length, b.set.length);
        for (int i = 0; i < smallest; i++) {
            set[i] &= b.set[i];
        }
        recomputeCardinality();
    }

    public void or(BitSet b) {
        int smallest = Math.min(set.length, b.set.length);
        for (int i = 0; i< smallest; i++) {
            set[i] |= b.set[i];
        }
        recomputeCardinality();
    }

    public static void main(String []args) {
        BitSet b = new BitSet(100);
        for (short x = 0; x < 19; x++) {
            if (x % 2 != 0)
                b.add(x);
        }

        System.out.println(b.getCardinality());
    }
}
