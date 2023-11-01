/**
 * Illustrates a simple map in Java
 */
package learn.spark.sample.spark;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SparkSession;

public class BasicMap {
  public static void main(String[] args) throws Exception {
    SparkConf sparkConf = new SparkConf().setAppName("BasicMap");
    JavaSparkContext sc = new JavaSparkContext(sparkConf);
    SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
    JavaRDD<Integer> rdd = sc.parallelize(Arrays.asList(1, 2, 3, 4));
    JavaRDD<Integer> result = rdd.map(
        new Function<Integer, Integer>() {
          public Integer call(Integer x) {
            return x * x;
          }
        });
    System.out.println(StringUtils.join(result.collect(), ","));
  }
}
