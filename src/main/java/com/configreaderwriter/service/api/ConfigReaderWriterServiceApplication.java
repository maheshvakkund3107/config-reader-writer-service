package com.configreaderwriter.service.api;


import com.configreaderwriter.service.api.exceptions.ReaderWriterException;
import com.configreaderwriter.service.api.model.ConfigReaderWriterModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;



@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class ConfigReaderWriterServiceApplication  extends SpringBootServletInitializer {

    @PostMapping("/processrequest")
    public String register(@RequestBody ConfigReaderWriterModel user) throws ReaderWriterException {
        String scriptContent = "spark-submit \\\n" +
                "--name DataCopier_MYSQL  \\\n" +
                "--class com.clairvoyant.data.DataCopyApplication \\\n" +
                "--master local \\\n" +
                "--num-executors 8 \\\n" +
                "--driver-memory 8g \\\n" +
                "--executor-memory 8g \\\n" +
                "--executor-cores 8 \\\n" +
                "--files /home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/application.properties,/home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/gcp_credentials.txt,/home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/gcp_encrypted_credentials.json \\\n" +
                "--jars=NA \\\n" +
                "/home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/data-copier-1.0.0-SNAPSHOT.jar /home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/application.properties " + user.getConfigReader() + " " + user.getConfigWriter() + " " + "NA NA NA NA NA\n";
        try {
            Writer output = new BufferedWriter(new FileWriter("/home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/write/reader-writer.sh"));
            output.write(scriptContent);
            Runtime.getRuntime().exec("chmod u+x /home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/write/reader-writer.sh");
            output.flush();
            output.close();
            Runtime.getRuntime().exec("/home/mahesh/Documents/Deployment/apache-tomcat-9.0.64/webapps/datacopier_resources/write/reader-writer.sh");
        } catch (Exception e) {
            throw new ReaderWriterException("Error While Running : " + e);
        }
        return "Hi  your Request process successfully completed";

    }

    public static void main(String[] args) {
        SpringApplication.run(ConfigReaderWriterServiceApplication.class, args);
    }
}


