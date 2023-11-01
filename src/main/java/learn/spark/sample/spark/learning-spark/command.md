### BasicAvg
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicAvg \
--name "BasicAvg" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar
```

### BasicAvgMapPartitions
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicAvgMapPartitions \
--name "BasicAvgMapPartitions" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar \
"hdfs://master:9000/data/sample.txt"
```

### BasicAvgWithKryo
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicAvgWithKryo \
--name "BasicAvgWithKryo" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar
```

### BasicFlatMap
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicFlatMap \
--name "BasicFlatMap" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar \
"hdfs://master:9000/data/sample.txt"
```

### BasicJoinCsv
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicJoinCsv \
--name "BasicJoinCsv" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar \
"hdfs://master:9000/data/int_string.csv" \
"hdfs://master:9000/data/int_string.csv"
```

### BasicLoadJson
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicLoadJson \
--name "BasicLoadJson" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar \
"hdfs://master:9000/data/pandainfo.json" \
"hdfs://master:9000/data/result_pandainfo.json"
```

### BasicMap
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicMap \
--name "BasicMap" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar
```

### BasicMapPartitions
```shell
$SPARK_HOME/bin/spark-submit --master yarn \
--deploy-mode cluster \
--class nttdata.sample.spark.BasicMapPartitions \
--name "BasicMapPartitions" \
--executor-memory 512m \
--jars sample/target/sample-1.0-SNAPSHOT.jar \
sample/target/sample-1.0-SNAPSHOT.jar
```
