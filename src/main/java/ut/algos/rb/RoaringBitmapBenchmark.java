package ut.algos.rb;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.VmOptions;
import org.roaringbitmap.RoaringBitmap;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

@VmOptions("-XX:-TieredCompilation") // Workaround (java 8)
public class RoaringBitmapBenchmark {
  @Param({
    "5",
    "1000",
    "9999999"
  }) int adds;
  private Random r = new Random();

  /*
   * Cheap trick: All benchmarks return a bitmap or a modified
   * object to try to prevent clever JVM optimizations.
   */

  @Benchmark
  public RoaringBitmap addToSetRoaring(int adds) {
    RoaringBitmap bitmap = new RoaringBitmap();
    for (int i = 0; i < adds; i++) {
      bitmap.add(i);
    }
    return bitmap;
  }

  @Benchmark
  public Set addToSetHashSet(int adds) {
    Set set = new HashSet<Integer>();
    return fillUpSet(set, adds);
  }

  /**
   * Use integers instead of shorts for benchmark purposes.
   * Actual implementation would wrap BitSet for chunk purposes
   * **/
  @Benchmark
  public IntegerBitSet addToSetBitSet(int adds) {
    IntegerBitSet set = new IntegerBitSet(adds);
    for (int i = 0; i < adds; i++) {
      set.add(i);
    }
    return set;
  }

  @Benchmark
  public Set addToSetLinkedHashSet(int adds) {
    Set set = new LinkedHashSet<Integer>();
    return fillUpSet(set, adds);
  }

  @Benchmark
  public Set addToSetTreeSet(int adds) {
    Set set = new TreeSet<Integer>();
    return fillUpSet(set, adds);
  }

  @Benchmark
  public Set addToSetCopyOnWriteArraySet(int adds) {
    Set set = new CopyOnWriteArraySet<Integer>();
    return fillUpSet(set, adds);
  }

  private static final Map<Integer, NUMBERS> intToNumberMap = new HashMap<>();
  static {
    for (NUMBERS type : NUMBERS.values()) {
      intToNumberMap.put(type.value, type);
    }
  }

  /**
   * Not a perfect comparison as there are only ten possible values to add
   * to this set.
   * **/
  @Benchmark
  public Set addToSetEnumSetSet(int adds) {
    Set set = EnumSet.noneOf(NUMBERS.class);
    for (int i = 0; i < adds; i++) {
      set.add(intToNumberMap.get(i % 10));
    }
    return set;
  }

  private enum NUMBERS {
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9);

    private final int value;

    NUMBERS(int value) {
      this.value = value;
    }
  }

  @Benchmark
  public Set addToSetConcurrentSkipListSet(int adds) {
    Set set = new ConcurrentSkipListSet<Integer>();
    return fillUpSet(set, adds);
  }

  private Set fillUpSet(Set set, int adds) {
    for (int i = 0; i < adds; i++) {
      set.add(i);
    }
    return set;
  }

  @Benchmark
  public RoaringBitmap unionGaussianRoaring(int adds) {
    RoaringBitmap bitmap1 = new RoaringBitmap();
    RoaringBitmap bitmap2 = new RoaringBitmap();
    for (int i = 0; i < adds; i++) {
      bitmap1.add(getRandomInteger());
      bitmap2.add(getRandomInteger());
    }

    bitmap1.or(bitmap2);
    return bitmap1;
  }

  @Benchmark
  public Set unionGaussianHashSet(int adds) {
    HashSet set1 = new HashSet();
    HashSet set2 = new HashSet();
    for (int i = 0; i < adds; i++) {
      set1.add(getRandomInteger());
      set2.add(getRandomInteger());
    }

    set1.addAll(set2);
    return set1;
  }

  /**
   * Benchmarks against two distinct sets
   * **/
  @Benchmark
  public IntegerBitSet unionBitSet(int adds) {
    IntegerBitSet set1 = new IntegerBitSet(adds);
    IntegerBitSet set2 = new IntegerBitSet(adds);
    for (int i = 0; i < adds; i++) {
      if (i % 2 == 0)
        set1.add(i);
      else
        set2.add(i);
    }

    set1.or(set2);
    return set1;
  }

  @Benchmark
  public LinkedHashSet unionGaussianLinkedHashSet(int adds) {
    LinkedHashSet set1 = new LinkedHashSet();
    LinkedHashSet set2 = new LinkedHashSet();
    for (int i = 0; i < adds; i++) {
      set1.add(getRandomInteger());
      set2.add(getRandomInteger());
    }

    set1.addAll(set2);
    return set1;
  }

  @Benchmark
  public RoaringBitmap intersectionGaussianRoaring(int adds) {
    RoaringBitmap bitmap1 = new RoaringBitmap();
    RoaringBitmap bitmap2 = new RoaringBitmap();
    for (int i = 0; i < adds; i++) {
      bitmap1.add(getRandomInteger());
      bitmap2.add(getRandomInteger());
    }

    bitmap1.and(bitmap2);
    return bitmap1;
  }

  @Benchmark
  public Set intersectionGaussianHashSet(int adds) {
    HashSet set1 = new HashSet();
    HashSet set2 = new HashSet();
    for (int i = 0; i < adds; i++) {
      set1.add(getRandomInteger());
      set2.add(getRandomInteger());
    }

    set1.retainAll(set2);
    return set1;
  }

  /**
   * Benchmarks against two distinct sets
   * **/
  @Benchmark
  public IntegerBitSet intersectionBitSet(int adds) {
    IntegerBitSet set1 = new IntegerBitSet(adds);
    IntegerBitSet set2 = new IntegerBitSet(adds);
    for (int i = 0; i < adds; i++) {
      if (i % 2 == 0)
        set1.add(i);
      else
        set2.add(i);
    }

    set1.and(set2);
    return set1;
  }

  @Benchmark
  public LinkedHashSet intersectionGaussianLinkedHashSet(int adds) {
    LinkedHashSet set1 = new LinkedHashSet();
    LinkedHashSet set2 = new LinkedHashSet();
    for (int i = 0; i < adds; i++) {
      set1.add(getRandomInteger());
      set2.add(getRandomInteger());
    }

    set1.retainAll(set2);
    return set1;
  }

  private int getRandomInteger() {
    return (int) (r.nextGaussian() * 9999);
  }
}