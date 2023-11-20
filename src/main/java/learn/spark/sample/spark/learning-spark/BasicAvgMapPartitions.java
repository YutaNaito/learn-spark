/**
 * Illustrates a simple map partitions in Java to compute the average
 */
package learn.spark.sample.spark;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.sql.SparkSession;
import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;

public final class BasicAvgMapPartitions implements Serializable {
  class AvgCount implements Serializable {
    public AvgCount() {
      total_ = 0;
      num_ = 0;
    }

    public AvgCount(Integer total, Integer num) {
      total_ = total;
      num_ = num;
    }

    public AvgCount merge(Iterable<Integer> input) {
      for (Integer elem : input) {
        num_ += 1;
        total_ += elem;
      }
      return this;
    }

    public Integer total_;
    public Integer num_;

    public float avg() {
      return total_ / (float) num_;
    }
  }

  public static void main(String[] args) throws Exception {
    BasicAvgMapPartitions bamp = new BasicAvgMapPartitions();
    bamp.run();
  }

  public void run() {
    SparkConf sparkConf = new SparkConf().setAppName("BasicAvgMapPartitions");
    JavaSparkContext sc = new JavaSparkContext(sparkConf);
    SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
    JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4, 5));
    FlatMapFunction<Iterator<Integer>, AvgCount> setup = new FlatMapFunction<Iterator<Integer>, AvgCount>() {
      @Override
      public Iterator<AvgCount> call(Iterator<Integer> input) {
        AvgCount a = new AvgCount(0, 0);
        while (input.hasNext()) {
          a.total_ += input.next();
          a.num_ += 1;
        }
        ArrayList<AvgCount> ret = new ArrayList<AvgCount>();
        ret.add(a);
        return ret.iterator();
      }
    };
    Function2<AvgCount, AvgCount, AvgCount> combine = new Function2<AvgCount, AvgCount, AvgCount>() {
      @Override
      public AvgCount call(AvgCount a, AvgCount b) {
        a.total_ += b.total_;
        a.num_ += b.num_;
        return a;
      }
    };

    JavaRDD<AvgCount> resultRdd = rdd.mapPartitions(setup);
    for (AvgCount avg : resultRdd.collect()) {
      System.out.println("RDD >> count : " + avg.num_ + ", sum : " + avg.total_ + ", average :" + avg.avg());
    }

    AvgCount result = resultRdd.reduce(combine);
    System.out.println("count : " + result.num_ + ", sum : " + result.total_ + ", average :" + result.avg());

    sc.close();
  }
}
