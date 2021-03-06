/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.query.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.teiid.runtime.client.Messages;
import org.teiid.runtime.client.TeiidClientException;

public class VariableContext {

    // map between variables and their values
    private Map variableMap = new LinkedHashMap();

    // reference to the parent variable context
    private VariableContext parentContext;
    private boolean delegateSets;

    /**
     * Constructor for VariableContext.
     */
    public VariableContext() {
        this(false);
    }

    public VariableContext(boolean delegateSets) {
        this.delegateSets = delegateSets;
    }

    public void setGlobalValue(String variable, Object value) {
        if (this.parentContext != null) {
            this.parentContext.setGlobalValue(variable, value);
        } else {
            variableMap.put(variable, value);
        }
    }

    public Object getGlobalValue(String variable) throws TeiidClientException {
        if (this.parentContext != null) {
            return this.parentContext.getGlobalValue(variable);
        }
        Object value = variableMap.get(variable);
        if (value == null && !variableMap.containsKey(variable)) {
            throw new TeiidClientException(Messages.gs(Messages.TEIID.TEIID30328,
                                                                  variable,
                                                                  "No value was available")); //$NON-NLS-1$
        }
        return value;
    }

    /**
     * Set the value for the given, if the variable already exits replaces its value
     * with the given value else adds a new variable to the map.
     * @param variable The <code>ElementSymbol</code> to be added as a variable.
     * @param value The value to be set for the given variable.
     */
    public Object setValue(Object variable, Object value) {
        if (delegateSets && parentContext != null && parentContext.containsVariable(variable)) {
            return parentContext.setValue(variable, value);
        }
        return variableMap.put(variable, value);
    }

    /**
     * Get the value for the given variable, if the variable exits in the current
     * context just return the value of the variable else lookup the parent context
     * and return the value of the variable.
     * @param variable The <code>ElementSymbol</code> whose value needs to be returned.
     * @return The value of the given variable
     */
    public Object getValue(Object variable) {
        if (variableMap.containsKey(variable)) {
            return variableMap.get(variable);
        }
        // if the variable is not present in the current variablecontext
        // look up the parent context
        if (this.parentContext != null) {
            return this.parentContext.getValue(variable);
        }
        return null;
    }

    /**
     * Set the parent variable context for this variable context, when looking up
     * the variable's value, if the variable is not present in the current context,
     * the parent context is lookedup.
     * @param parent The parent <code>VariableContext</code>.
     */
    public void setParentContext(VariableContext parent) {
        this.parentContext = parent;
    }

    /**
     * Get the parent context for this variable context. When looking up
     * the variable's value, if the variable is not present in the current context,
     * the parent context is lookedup.
     * @return The parent <code>VariableContext</code>.
     */
    public VariableContext getParentContext() {
        return this.parentContext;
    }

    /** Helper Methods  */

    public void getFlattenedContextMap(Map values) {
        if (this.parentContext != null) {
            this.parentContext.getFlattenedContextMap(values);
        }
        values.putAll(this.variableMap);
    }

    /**
     * Check if this context or any of it's parent contexts contain this variable
     * @param variable The variable which may be present on this context
     * @return A boolean value indiating if the given variable is present on this context
     * or any of it's parent contexts.
     */
    public boolean containsVariable(Object variable) {
        if (!variableMap.containsKey(variable)) {
            if (this.parentContext != null) {
                return this.parentContext.containsVariable(variable);
            }
            return false;
        }
        return true;
    }

    /**
     * Check if the current context and its parents contain any variables
     * @return A boolean bollean value indicating if this context is empty
     */
    public boolean isEmpty() {
        if (variableMap.isEmpty()) {
            if (this.parentContext != null) {
                return this.parentContext.isEmpty();
            }
            return true;
        }
        return false;
    }

    public Object remove(Object symbol) {
        if (!this.variableMap.containsKey(symbol)) {
            if (this.parentContext != null) {
                return this.parentContext.remove(symbol);
            }
            return null;
        }
        return this.variableMap.remove(symbol);
    }

    @Override
    public String toString() {
        return this.variableMap.toString();
    }

    public void clear() {
        this.variableMap.clear();
    }

    public void putAll(VariableContext other) {
        this.variableMap.putAll(other.variableMap);
    }

    public List<Object> getLocalValues() {
        return new ArrayList<Object>(this.variableMap.values());
    }

    public Map<Object, Object> getVariableMap() {
        return variableMap;
    }

}
