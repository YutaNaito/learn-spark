/**
 * Illustrates a simple flatMap in Java to extract the words
 */
package nttdata.sample.spark;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.SparkSession;

public class BasicFlatMap {
  public static void main(String[] args) throws Exception {

		if (args.length != 2) {
      throw new Exception("Usage BasicFlatMap sparkMaster inputFile");
		}

    SparkConf sparkConf = new SparkConf().setAppName("BasicFlatMap");
    JavaSparkContext sc = new JavaSparkContext(sparkConf);
    SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
    JavaRDD<String> rdd = sc.textFile(args[1]);
    JavaRDD<String> words = rdd.flatMap(new FlatMapFunction<String, String>() { 
        @Override 
        public Iterator<String> call(String x) { 
          return Arrays.asList(x.split(" ")).iterator(); 
        }
    });
    Map<String, Long> result = words.countByValue();
    for (Entry<String, Long> entry: result.entrySet()) {
      System.out.println(entry.getKey() + ":" + entry.getValue());
    }
  }
}
