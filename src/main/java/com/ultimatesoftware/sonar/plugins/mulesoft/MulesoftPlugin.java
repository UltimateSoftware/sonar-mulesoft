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

import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;

public class MulesoftPlugin implements Plugin {
    @Override
    public void define(Context context) {
        context.addExtension(MulesoftSensor.class);
        context.addExtension(PropertyDefinition.builder(ReportPathsProvider.REPORT_PATHS_PROPERTY_KEY)
                .multiValues(true)
                .category("MuleSoft")
                .description("Paths to MuleSoft JSON coverage report files. Each path can be either absolute or relative to the project base directory.")
                .build());
        context.addExtension(PropertyDefinition.builder(ReportPathsProvider.SOURCE_PATHS_PROPERTY_KEY)
                .multiValues(true)
                .category("MuleSoft")
                .description("Paths to MuleSoft XML source files. Each path can be either absolute or relative to the project base directory.")
                .build());
    }
}
