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
