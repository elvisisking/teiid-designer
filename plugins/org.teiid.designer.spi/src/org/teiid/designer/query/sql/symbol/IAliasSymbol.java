/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.designer.query.sql.symbol;

import org.teiid.designer.query.sql.ILanguageVisitor;
import org.teiid.designer.query.sql.lang.IExpression;

/**
 *
 */
public interface IAliasSymbol<E extends IExpression, LV extends ILanguageVisitor>
    extends ISymbol<LV>, IExpression<LV> {

    /**
     * Get the underlying symbol
     * 
     * @return Underlying symbol
     */
    E getSymbol();

    /**
     * Set the underlying symbol
     * 
     * @param symbol New symbol
     */
    void setSymbol(E symbol);

}
