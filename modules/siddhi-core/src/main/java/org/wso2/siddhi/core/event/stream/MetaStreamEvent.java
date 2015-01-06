/*
*  Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
*  WSO2 Inc. licenses this file to you under the Apache License,
*  Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License.
*  You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
*/
package org.wso2.siddhi.core.event.stream;

import org.wso2.siddhi.core.event.MetaComplexEvent;
import org.wso2.siddhi.query.api.definition.AbstractDefinition;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.definition.StreamDefinition;

import java.util.ArrayList;
import java.util.List;


/**
 * Class to hold meta info about StreamEvent.
 * This is also used to update variable positions on executors
 */
public class MetaStreamEvent implements MetaComplexEvent {
    private List<Attribute> beforeWindowData = new ArrayList<Attribute>();
    private List<Attribute> onAfterWindowData = null;
    private List<Attribute> outputData = null;
    private AbstractDefinition inputDefinition;
    private int initialAttributeSize;
    private String inputReferenceId;
    private StreamDefinition outputStreamDefinition;

    public List<Attribute> getBeforeWindowData() {
        return beforeWindowData;
    }

    public List<Attribute> getOnAfterWindowData() {
        if (onAfterWindowData != null) {
            return onAfterWindowData;
        } else {
            return new ArrayList<Attribute>();  //return empty arraylist to avoid NPE
        }
    }

    public List<Attribute> getOutputData() {
        if (outputData != null) {
            return outputData;
        } else {
            return new ArrayList<Attribute>();  //return empty arraylist to avoid NPE
        }
    }

    public void initializeAfterWindowData() {
        if (onAfterWindowData == null) {
            onAfterWindowData = new ArrayList<Attribute>();
        }
    }

    /**
     * Universal method to add data to MetaStream event.
     * Will make sure event will be added to corresponding array by
     * initializing them accordingly.
     *
     * @param attribute
     */
    public void addData(Attribute attribute) {
        if (onAfterWindowData != null) {
            if (!onAfterWindowData.contains(attribute)) {
                onAfterWindowData.add(attribute);
            }
        } else {
            if (!beforeWindowData.contains(attribute)) {
                beforeWindowData.add(attribute);
            }
        }
    }

    public void addOutputData(Attribute attribute) {
        if (outputData == null) {
            outputData = new ArrayList<Attribute>();
        }
        outputData.add(attribute);
    }

    public AbstractDefinition getInputDefinition() {
        return inputDefinition;
    }

    public void setInputDefinition(AbstractDefinition inputDefinition) {
        this.inputDefinition = inputDefinition;
    }

    public String getInputReferenceId() {
        return inputReferenceId;
    }

    public void setInputReferenceId(String inputReferenceId) {
        this.inputReferenceId = inputReferenceId;
    }

    public void setInitialAttributeSize(int initialAttributeSize) {
        this.initialAttributeSize = initialAttributeSize;
    }

    public int getInitialAttributeSize() {
        return initialAttributeSize;
    }

    @Override
    public void setOutputDefinition(StreamDefinition streamDefinition) {
        outputStreamDefinition = streamDefinition;
    }

    @Override
    public StreamDefinition getOutputStreamDefinition() {
        return outputStreamDefinition;
    }

}