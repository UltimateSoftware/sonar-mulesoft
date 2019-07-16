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
package org.sonar.plugins.mulesoft;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import javax.xml.stream.XMLStreamReader;

public class JsonParser {
    private final Path jsonReportPath;

    public JsonParser(Path jsonReportPath) {
        this.jsonReportPath = jsonReportPath;
    }

    public List<SourceFile> parse() {
        List<SourceFile> sourceFiles = new ArrayList<>();
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(this.jsonReportPath.toString()));

            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //convert json string to object
            MunitReport mur = objectMapper.readValue(jsonData, MunitReport.class);

            for (MunitReport.MunitFile file : mur.getFiles()) {
                SourceFile sourceFile = new SourceFile(null, file.getName());
                for(int i = 0; i < file.getFlows().size(); i++) {
                    MunitReport.Flow flow = file.getFlows().get(i);
                    sourceFile.lines().add(
                        new Line(
                            i,
                            flow.getMessageProcessorCount() - flow.getCoveredProcessorCount(),
                            flow.getCoveredProcessorCount(),
                            0,
                            0
                            ));
                }

               sourceFiles.add(sourceFile);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return sourceFiles;
    }

    private static String getStringAttr(XMLStreamReader parser, String name, Supplier<String> errorContext) {
        String value = parser.getAttributeValue(null, name);
        if (value == null) {
            throw new IllegalStateException("Invalid report: couldn't find the attribute '" + name + "' " + errorContext.get());
        }
        return value;
    }

    private static int getOptionalIntAttr(XMLStreamReader parser, String name, Supplier<String> errorContext) {
        String value = parser.getAttributeValue(null, name);
        if (value == null) {
            return 0;
        }
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid report: failed to parse integer from the attribute '" + name + "' " + errorContext.get());
        }
    }

    private static int getIntAttr(XMLStreamReader parser, String name, Supplier<String> errorContext) {
        String value = getStringAttr(parser, name, errorContext);
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            throw new IllegalStateException("Invalid report: failed to parse integer from the attribute '" + name + "' " + errorContext.get());
        }
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
