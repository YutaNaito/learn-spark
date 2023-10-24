package learn.spark.sample.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class Knock1 {

  public static void main(String[] args) {
    SparkConf sparkConf = new SparkConf().s.setAppName("CSV Reader with RDD");
    JavaSparkContext sc = new JavaSparkContext(sparkConf);
    SparkSession sparkSession =
        SparkSession.builder().config(sparkConf).getOrCreate();

    // RDDを使用してCSVファイルを読み込む
    JavaRDD<String> dataRdd =
        sc.textFile("hdfs:/data/22_shizuoka_all_20210331.csv");

    // 最初の5行を取得して表示する
    for (String line : dataRdd.take(5)) {
      System.out.println(line);
    }

    sc.close();
  }
}