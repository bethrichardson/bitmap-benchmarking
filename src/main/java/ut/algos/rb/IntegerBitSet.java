package ut.algos.rb;


public class IntegerBitSet {
    protected long set[];
    private int cardinality;

    public IntegerBitSet(int max_size) {
        set = new long[max_size];
        cardinality = 0;
    }

    public boolean contains(int x) {
        /*
          Paper mentions that modulo and division are performed in a single
          instruction here.
         */
        return (set[x / Long.SIZE] & (1L << (x % Long.SIZE))) != 0;
    }


    public int getCardinality() {
        return cardinality;
    }

    public void add(int x) {
        if (!contains(x)) {
            set[x / Long.SIZE] |= (1L << (x % Long.SIZE));
            cardinality++;
        }
    }

    public void remove(int x) {
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

    public void and(IntegerBitSet b) {
        int smallest = Math.min(set.length, b.set.length);
        for (int i = 0; i < smallest; i++) {
            set[i] &= b.set[i];
        }
        recomputeCardinality();
    }

    public void or(IntegerBitSet b) {
        int smallest = Math.min(set.length, b.set.length);
        for (int i = 0; i< smallest; i++) {
            set[i] |= b.set[i];
        }
        recomputeCardinality();
    }

    public static void main(String []args) {
        IntegerBitSet b = new IntegerBitSet(100);
        for (int x = 0; x < 19; x++) {
            if (x % 2 != 0)
                b.add(x);
        }

        System.out.println(b.getCardinality());
    }
}
