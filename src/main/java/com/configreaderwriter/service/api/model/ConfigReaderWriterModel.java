package com.configreaderwriter.service.api.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfigReaderWriterModel {
    private String configReader;
    private String configWriter;
    private String configPath;

    public String getConfigReader() {
        return configReader;
    }

    public void setConfigReader(String configReader) {
        this.configReader = configReader;
    }

    public String getConfigWriter() {
        return configWriter;
    }

    public void setConfigWriter(String configWriter) {
        this.configWriter = configWriter;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
}
