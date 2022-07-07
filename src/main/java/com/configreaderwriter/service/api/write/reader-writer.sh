spark-submit \
--name DataCopier_MYSQL  \
--class com.clairvoyant.data.DataCopyApplication \
--master local \
--num-executors 8 \
--driver-memory 8g \
--executor-memory 8g \
--executor-cores 8 \
--files ./src/main/java/com/configreaderwriter/service/api/datacopier_resources/application.properties,./src/main/java/com/configreaderwriter/service/api/datacopier_resources/gcp_credentials.txt,./src/main/java/com/configreaderwriter/service/api/datacopier_resources/gcp_encrypted_credentials.json \
--jars=NA \
./src/main/java/com/configreaderwriter/service/api/datacopier_resources/data-copier-1.0.0-SNAPSHOT.jar ./src/main/java/com/configreaderwriter/service/api/datacopier_resources/application.properties CSV LOCAL_CSV NA NA NA NA NA
