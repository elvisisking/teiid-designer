/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.designer.query.sql.lang;

import org.teiid.designer.query.sql.ILanguageVisitor;

/**
 * @param <LV> 
 *
 */
public interface ILanguageObject<LV extends ILanguageVisitor> {

    /**
     * Clone this language object
     * 
     * @return a copy of this language object
     */
    ILanguageObject clone();

    /**
     * @param visitor
     */
    void acceptVisitor(LV visitor);

}
