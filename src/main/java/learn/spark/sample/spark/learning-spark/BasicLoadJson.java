/**
 * Illustrates loading a json file and finding out if people like pandas
 */
package learn.spark.sample.spark;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.lang.Iterable;

import scala.Tuple2;

import org.apache.commons.lang.StringUtils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.SparkSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class BasicLoadJson {

  public static class Person implements java.io.Serializable {
    public String name;
    public Boolean lovesPandas;
  }

  public static class ParseJson implements FlatMapFunction<Iterator<String>, Person> {
    public Iterator<Person> call(Iterator<String> lines) throws Exception {
      ArrayList<Person> people = new ArrayList<Person>();
      ObjectMapper mapper = new ObjectMapper();
      while (lines.hasNext()) {
        String line = lines.next();
        try {
          people.add(mapper.readValue(line, Person.class));
        } catch (Exception e) {
          // Skip invalid input
        }
      }
      return people.iterator();
    }
  }

  public static class LikesPandas implements Function<Person, Boolean> {
    public Boolean call(Person person) {
      return person.lovesPandas;
    }
  }

  public static class WriteJson implements FlatMapFunction<Iterator<Person>, String> {
    public Iterator<String> call(Iterator<Person> people) throws Exception {
      ArrayList<String> text = new ArrayList<String>();
      ObjectMapper mapper = new ObjectMapper();
      while (people.hasNext()) {
        Person person = people.next();
        text.add(mapper.writeValueAsString(person));
      }
      return text.iterator();
    }
  }

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      throw new Exception("Usage BasicLoadJson [sparkMaster] [jsoninput] [jsonoutput]");
    }
    String fileName = args[0];
    String outfile = args[1];

    SparkConf sparkConf = new SparkConf().setAppName("BasicLoadJson");
    JavaSparkContext sc = new JavaSparkContext(sparkConf);
    SparkSession sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();
    JavaRDD<String> input = sc.textFile(fileName);
    JavaRDD<Person> result = input.mapPartitions(new ParseJson()).filter(new LikesPandas());
    JavaRDD<String> formatted = result.mapPartitions(new WriteJson());
    formatted.saveAsTextFile(outfile);
  }
}
