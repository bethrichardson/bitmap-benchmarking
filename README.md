# Benchmarking RoaringBitmaps vs Set Implementations

This project produces benchmarks to evaluate the performance of the RoaringBitmap implementation from Daniel Lemire 
and our own implementation of the bitmap used for chunking in RoaringBitmap. 

We compared the performance of Lemire’s and our implementation to a selection of non-abstract set 
classes in the Java standard library including ConcurrentSkipListSet, CopyOnWriteArraySet, 
EnumSet, HashSet, LinkedHashSet, and TreeSet. We compared the performance using varying set 
cardinalities and the AND and OR operations implemented in Java Sets as `addAll` and `retainAll` 
and in RoaringBitmap and our own implementation of BitSet as `and` and `or`.

## RoaringBitmap
For more information on RoaringBitmaps, see [http://roaringbitmap.org/](http://roaringbitmap.org/).

## Our Presentation
View our presentation on this topic[here](https://docs.google.com/presentation/d/18YLjPI3XuSxi5RH0wECcyxcxbBoSzEDgBoJcUyeDTeQ/edit?usp=sharing).

## Our Benchmarks 
Our latest benchmark values are available [here](https://microbenchmarks.appspot.com/runs/9381e632-5f18-44e7-a7bc-4c0877164d90)

# To run:
`mvn clean install`

`mvn exec:java  -Dexec.mainClass="com.google.caliper.runner.CaliperMain" -Dexec.args="ut.algos.rb.RoaringBitmapBenchmark"`
