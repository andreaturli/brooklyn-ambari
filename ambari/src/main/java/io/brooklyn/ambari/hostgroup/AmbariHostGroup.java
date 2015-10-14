/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.brooklyn.ambari.hostgroup;

import brooklyn.config.ConfigKey;
import brooklyn.entity.basic.ConfigKeys;
import brooklyn.entity.basic.SoftwareProcess;
import brooklyn.entity.group.DynamicCluster;
import brooklyn.entity.java.UsesJava;
import brooklyn.entity.proxying.EntitySpec;
import brooklyn.entity.proxying.ImplementedBy;
import brooklyn.util.flags.SetFromFlag;
import com.google.common.reflect.TypeToken;

import javax.annotation.Nullable;
import java.util.List;

@ImplementedBy(AmbariHostGroupImpl.class)
public interface AmbariHostGroup extends DynamicCluster {

    @SetFromFlag("components")
    ConfigKey<List<String>> HADOOP_COMPONENTS = ConfigKeys.newConfigKey(new TypeToken<List<String>>() {
    }, "components", "List of components to deploy to host group");

    @SetFromFlag("siblingSpec")
    ConfigKey<EntitySpec<?>> SIBLING_SPEC = ConfigKeys.newConfigKey(
            new TypeToken<EntitySpec<?>>(){},
            "ambari.sibling.spec", "Spec for  extra entity to be installed on each of nodes in cluster", null);

    List<String> getHostFQDNs();

    /**
     * Returns the list of {@link io.brooklyn.ambari.agent.AmbariAgent#COMPONENTS} that will be / are installed on all
     * node within this host group.
     *
     * @return a list of string that represents the components to be installed.
     */
    @Nullable
    List<String> getComponents();
}
