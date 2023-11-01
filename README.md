# learn-spark

### Requirements
- Apache Maven: 3.9.5
- OpenJDK: 1.8.0_382
- Apache Spark: 3.5.0
- Apache Hadoop: 3.3.6

### Usage
How to execute spark application with yran cluster
```shell
$SPARK_HOME/bin/spark-submit --master yarn --deploy-mode cluster --class nttdata.sample.spark.BasicJoinCsv --name "BasicJoinCsv" --executor-memory 512m --jars sample/target/sample-1.0-SNAPSHOT.jar sample/target/sample-1.0-SNAPSHOT.jar
```

### reference
- https://github.com/databricks/learning-spark
- https://github.com/The-Japan-DataScientist-Society/100knocks-preprocess
