/*
 * Copyright (c) 2005 - 2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.siddhi.core.executor.math.multiply;


import org.wso2.siddhi.core.event.ComplexEvent;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.query.api.definition.Attribute;

public class MultiplyExpressionExecutorLong implements ExpressionExecutor {
    private ExpressionExecutor leftExpressionExecutor;
    private ExpressionExecutor rightExpressionExecutor;


    public MultiplyExpressionExecutorLong(ExpressionExecutor leftExpressionExecutor,
                                          ExpressionExecutor rightExpressionExecutor) {
        this.leftExpressionExecutor = leftExpressionExecutor;
        this.rightExpressionExecutor = rightExpressionExecutor;
    }

    @Override
    public Object execute(ComplexEvent event) {
        return ((Number) leftExpressionExecutor.execute(event)).longValue() * ((Number) rightExpressionExecutor.execute(event)).longValue();

    }

    public Attribute.Type getReturnType() {
        return Attribute.Type.LONG;
    }

    @Override
    public ExpressionExecutor cloneExecutor() {
        return new MultiplyExpressionExecutorLong(leftExpressionExecutor.cloneExecutor(), rightExpressionExecutor.cloneExecutor());
    }

}