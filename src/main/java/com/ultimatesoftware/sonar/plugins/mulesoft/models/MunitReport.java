package org.sonar.plugins.mulesoft.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MunitReport {
    private double coverage;
    private int floatCount;
    private int processorCount;
    private int coveredProcessorCount;
    private List<MunitFile> files;

    public MunitReport(double coverage, int floatCount, int processorCount, int coveredPRocessorCount, List<MunitFile> files) {
        this.coverage = coverage;
        this.floatCount = floatCount;
        this.processorCount = processorCount;
        this.coveredProcessorCount = coveredPRocessorCount;
        this.files = files;
    }

    public MunitReport() {
    }

    public double getCoverage() {
        return coverage;
    }

    public int getFloatCount() {
        return floatCount;
    }

    public int getProcessorCount() {
        return processorCount;
    }

    public int getCoveredProcessorCount() {
        return coveredProcessorCount;
    }

    public List<MunitFile> getFiles() {
        return files;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public void setFloatCount(int floatCount) {
        this.floatCount = floatCount;
    }

    public void setProcessorCount(int processorCount) {
        this.processorCount = processorCount;
    }

    public void setCoveredProcessorCount(int coveredProcessorCount) {
        this.coveredProcessorCount = coveredProcessorCount;
    }

    public void setFiles(List<MunitFile> files) {
        this.files = files;
    }
}
