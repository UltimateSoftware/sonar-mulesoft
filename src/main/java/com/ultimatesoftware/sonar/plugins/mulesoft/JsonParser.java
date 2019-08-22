/*
 * SonarQube JaCoCo Plugin
 * Copyright (C) 2018-2019 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.ultimatesoftware.sonar.plugins.mulesoft;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultimatesoftware.sonar.plugins.mulesoft.models.Flow;
import com.ultimatesoftware.sonar.plugins.mulesoft.models.MunitFile;
import com.ultimatesoftware.sonar.plugins.mulesoft.models.MunitReport;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    private final Path jsonReportPath;

    public JsonParser(Path jsonReportPath) {
        this.jsonReportPath = jsonReportPath;
    }

    public List<SourceFile> parse() throws IOException {
        List<SourceFile> sourceFiles = new ArrayList<>();
        byte[] jsonData = Files.readAllBytes(Paths.get(this.jsonReportPath.toString()));

        //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        MunitReport mur = objectMapper.readValue(jsonData, MunitReport.class);

        for (MunitFile file : mur.getFiles()) {
            SourceFile sourceFile = new SourceFile("", file.getName());
            int numOfLines = 0;
            int covered = 0;
            for (Flow flow : file.getFlows()) {
                numOfLines += flow.getMessageProcessorCount();
                covered += flow.getCoveredProcessorCount();
            }
            for (int i = 1; i <= numOfLines; i++) {
                sourceFile.lines().add(
                        new Line(
                                i,
                                i > covered ? 1 : 0,
                                i > covered ? 0 : 1,
                                0,
                                0
                        ));
            }

            sourceFiles.add(sourceFile);
        }

        return sourceFiles;
    }

    static class SourceFile {
        private String name;
        private String packageName;
        private List<Line> lines = new ArrayList<>();

        SourceFile(String packageName, String name) {
            this.name = name;
            this.packageName = packageName;
        }

        public String name() {
            return name;
        }

        public String packageName() {
            return packageName;
        }

        public List<Line> lines() {
            return lines;
        }
    }

    static class Line {
        private int number;
        private int missedInstrs;
        private int coveredInstrs;
        private int missedBranches;
        private int coveredBranches;

        Line(int number, int missedInstrs, int coveredInstrs, int missedBranches, int coveredBranches) {
            this.number = number;
            this.missedInstrs = missedInstrs;
            this.coveredInstrs = coveredInstrs;
            this.missedBranches = missedBranches;
            this.coveredBranches = coveredBranches;
        }

        public int number() {
            return number;
        }

        public int missedInstrs() {
            return missedInstrs;
        }

        public int coveredInstrs() {
            return coveredInstrs;
        }

        public int missedBranches() {
            return missedBranches;
        }

        public int coveredBranches() {
            return coveredBranches;
        }
    }

}
