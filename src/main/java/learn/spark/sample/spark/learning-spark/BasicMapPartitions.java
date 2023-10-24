/**
 * Illustrates a simple map in Java
 */
package learn.spark.sample.spark;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;

import org.eclipse.jetty.client.ContentExchange;
import org.eclipse.jetty.client.HttpClient;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.SparkSession;

public class BasicMapPartitions {
  public static void main(String[] args) throws Exception {

    SparkConf sparkConf = new SparkConf().setAppName("BasicMapPartitions");
    JavaSparkContext sc = new JavaSparkContext(sparkConf);
    SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
    JavaRDD<String> rdd = sc.parallelize(Arrays.asList("KK6JKQ", "Ve3UoW", "kk6jlk", "W6BB"));
    JavaRDD<String> result = rdd.mapPartitions(
      new FlatMapFunction<Iterator<String>, String>() {
        public Iterator<String> call(Iterator<String> input) {
          ArrayList<String> content = new ArrayList<String>();
          ArrayList<ContentExchange> cea = new ArrayList<ContentExchange>();
          HttpClient client = new HttpClient();
          try {
            client.start();
            while (input.hasNext()) {
              ContentExchange exchange = new ContentExchange(true);
              exchange.setURL("http://qrzcq.com/call/" + input.next());
              client.send(exchange);
              cea.add(exchange);
            }
            for (ContentExchange exchange : cea) {
              exchange.waitForDone();
              content.add(exchange.getResponseContent());
            }
          } catch (Exception e) {
          }
          return content.iterator();
        }});
    System.out.println(StringUtils.join(result.collect(), ","));
  }
}
