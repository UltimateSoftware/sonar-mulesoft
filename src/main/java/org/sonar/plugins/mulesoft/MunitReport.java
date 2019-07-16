package org.sonar.plugins.mulesoft;

import java.util.List;

public class MunitReport {
    private double coverage;
    private int floatCount;
    private int processorCount;
    private int coveredPRocessorCount;
    private List<MunitFile> files;

    public MunitReport(double coverage, int floatCount, int processorCount, int coveredPRocessorCount, List<MunitFile> files) {
        this.coverage = coverage;
        this.floatCount = floatCount;
        this.processorCount = processorCount;
        this.coveredPRocessorCount = coveredPRocessorCount;
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

    public int getCoveredPRocessorCount() {
        return coveredPRocessorCount;
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

    public void setCoveredPRocessorCount(int coveredPRocessorCount) {
        this.coveredPRocessorCount = coveredPRocessorCount;
    }

    public void setFiles(List<MunitFile> files) {
        this.files = files;
    }

    public class MunitFile {
        private String name;
        private int flowCount;
        private int processorCount;
        private double weight;
        private List<Flow> flows;

        public MunitFile(String name, int flowCount, int processorCount, double weight, List<Flow> flows) {
            this.name = name;
            this.flowCount = flowCount;
            this.processorCount = processorCount;
            this.weight = weight;
            this.flows = flows;
        }

        public MunitFile() {
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
}
