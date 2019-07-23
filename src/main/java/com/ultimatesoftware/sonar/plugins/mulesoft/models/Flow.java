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
