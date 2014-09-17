/*
 * Copyright (c) 2005 - 2014, WSO2 Inc. (http://www.wso2.org)
 * All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
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

package org.wso2.siddhi.core;

import org.wso2.siddhi.core.config.SiddhiContext;
import org.wso2.siddhi.core.util.parser.ExecutionPlanParser;
import org.wso2.siddhi.query.api.ExecutionPlan;
import org.wso2.siddhi.query.compiler.SiddhiCompiler;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class SiddhiManager {
    private SiddhiContext siddhiContext;
    private ConcurrentMap<String, ExecutionPlanRuntime> executionPlanRuntimeMap = new ConcurrentHashMap<String, ExecutionPlanRuntime>();

    public SiddhiManager() {
        siddhiContext = new SiddhiContext();
    }

    /**
     * add stream definitions, partitions and queries of an execution plan
     * @param executionPlan  executionPlan which contains stream definitions,queries and partitions
     * @return executionPlanRuntime corresponding to the given executionPlan
     * @
     */
    public ExecutionPlanRuntime addExecutionPlan(ExecutionPlan executionPlan) {
        validateExecutionPlan(executionPlan);
        ExecutionPlanRuntime executionPlanRuntime = ExecutionPlanParser.parse(executionPlan);
        executionPlanRuntimeMap.put((executionPlan.getName()!= null) ? executionPlan.getName(): UUID.randomUUID().toString(), executionPlanRuntime);
        return executionPlanRuntime;
    }

    public ExecutionPlanRuntime addExecutionPlan(String executionPlan) {
        return addExecutionPlan(SiddhiCompiler.parse(executionPlan));
    }

    public ExecutionPlanRuntime getExecutionPlanRuntime(String executionPlanName) {
        return executionPlanRuntimeMap.get(executionPlanName);
    }

    //Todo remove the execution plan from SiddhiManager
    public SiddhiContext getSiddhiContext() {
        return siddhiContext;
    }

    private void validateExecutionPlan(ExecutionPlan executionPlan) {
        ExecutionPlanParser.parse(executionPlan);
    }

}
