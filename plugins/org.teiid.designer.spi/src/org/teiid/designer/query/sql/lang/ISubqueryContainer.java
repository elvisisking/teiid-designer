/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.designer.query.sql.lang;



/**
 * This interface defines a common interface for all SQL objects 
 * that contain subqueries. 
 * 
 * @param <C> 
 * @param <LV> 
 */
public interface ISubqueryContainer<C extends ICommand> {

    /**
     * Returns the subquery Command object
     * @return the subquery Command object
     */
    C getCommand();
    
    /**
     * Sets the subquery Command object
     * @param command the subquery Command object
     */
    void setCommand(C command);
}
