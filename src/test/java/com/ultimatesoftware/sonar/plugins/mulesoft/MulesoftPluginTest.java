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

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.sonar.api.Plugin;
import org.sonar.api.config.PropertyDefinition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class MulesoftPluginTest {
  private MulesoftPlugin plugin = new MulesoftPlugin();
  private Plugin.Context ctx = mock(Plugin.Context.class);
  @Test
  public void should_add_sensor() {
    plugin.define(ctx);

    ArgumentCaptor<Object> arg = ArgumentCaptor.forClass(Object.class);
    verify(ctx, times(3)).addExtension(arg.capture());
    verifyNoMoreInteractions(ctx);

    assertThat(arg.getAllValues().get(0)).isEqualTo(MulesoftSensor.class);
    assertThat(arg.getAllValues().get(1)).isInstanceOf(PropertyDefinition.class);
    PropertyDefinition propertyDefinition = (PropertyDefinition) arg.getAllValues().get(1);
    assertThat(propertyDefinition.key()).isEqualTo("sonar.coverage.mulesoft.jsonReportPaths");
    assertThat(propertyDefinition.category()).isEqualTo("MuleSoft");
  }
}
