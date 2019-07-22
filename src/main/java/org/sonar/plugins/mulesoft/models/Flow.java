package org.sonar.plugins.mulesoft.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Flow {
    private String name;
    private String type;
    private double coverage;
    private int messageProcessorCount;
    private int coveredProcessorCount;

    public Flow(String name, String type, double coverage, int messageProcessorCount, int coveredProcessorCount) {
        this.name = name;
        this.type = type;
        this.coverage = coverage;
        this.messageProcessorCount = messageProcessorCount;
        this.coveredProcessorCount = coveredProcessorCount;
    }

    public Flow() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public int getMessageProcessorCount() {
        return messageProcessorCount;
    }

    public void setMessageProcessorCount(int messageProcessorCount) {
        this.messageProcessorCount = messageProcessorCount;
    }

    public int getCoveredProcessorCount() {
        return coveredProcessorCount;
    }

    public void setCoveredProcessorCount(int coveredProcessorCount) {
        this.coveredProcessorCount = coveredProcessorCount;
    }
}
