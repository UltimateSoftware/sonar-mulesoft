/* Copyright 2019 Ultimate Software

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.ultimatesoftware.sonar.plugins.mulesoft.models;

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
