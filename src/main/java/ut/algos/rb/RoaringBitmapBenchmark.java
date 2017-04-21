package ut.algos.rb;


import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.google.caliper.Benchmark;
import com.google.caliper.Param;
import com.google.caliper.api.VmOptions;
import org.roaringbitmap.RoaringBitmap;


@VmOptions("-XX:-TieredCompilation") // Workaround (java 8)
public class RoaringBitmapBenchmark {
  @Param({
    "5",
    "1000",
    "9999999"
  }) int adds;

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
  public Set addToSetBitSet(int adds) {
    Set set = new HashSet<Integer>();
    for (int i = 0; i < adds; i++) {
      set.add(i);
    }
    return set;
  }

  @Benchmark
  public RoaringBitmap unionGaussianRoaring(int adds) {
    Random r = new Random();
    RoaringBitmap bitmap1 = new RoaringBitmap();
    RoaringBitmap bitmap2 = new RoaringBitmap();
    int g, h;
    for (int i = 0; i < adds; i++) {
      g = (int) (r.nextGaussian() * 9999);
      h = (int) (r.nextGaussian() * 9999);
      bitmap1.add(g);
      bitmap2.add(h);
    }

    bitmap1.or(bitmap2);
    return bitmap1;
  }

  @Benchmark
  public Set unionGaussianBitSet(int adds) {
    Random r = new Random();
    HashSet set1 = new HashSet();
    HashSet set2 = new HashSet();
    int g, h;
    for (int i = 0; i < adds; i++) {
      g = (int) (r.nextGaussian() * 9999);
      h = (int) (r.nextGaussian() * 9999);
      set1.add(h);
      set2.add(g);
    }

    set1.addAll(set2);
    return set1;
  }

}