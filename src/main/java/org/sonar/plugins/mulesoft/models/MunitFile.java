package org.sonar.plugins.mulesoft.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MunitFile {
    private String name;
    private int flowCount;
    private double coverage;
    private int processorCount;
    private double weight;
    private List<Flow> flows;

    public MunitFile(String name, double coverage, int flowCount, int processorCount, double weight, List<Flow> flows) {
        this.name = name;
        this.coverage = coverage;
        this.flowCount = flowCount;
        this.processorCount = processorCount;
        this.weight = weight;
        this.flows = flows;
    }

    public MunitFile() {
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlowCount() {
        return flowCount;
    }

    public void setFlowCount(int flowCount) {
        this.flowCount = flowCount;
    }

    public int getProcessorCount() {
        return processorCount;
    }

    public void setProcessorCount(int processorCount) {
        this.processorCount = processorCount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
