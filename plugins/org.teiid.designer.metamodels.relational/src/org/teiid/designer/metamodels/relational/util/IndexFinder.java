/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.relational.util;

import org.eclipse.emf.ecore.EObject;
import org.teiid.designer.metamodels.relational.Catalog;
import org.teiid.designer.metamodels.relational.Index;
import org.teiid.designer.metamodels.relational.Schema;

/**
 * IndexFinder
 *
 * @since 8.0
 */
public class IndexFinder extends RelationalEntityFinder {

    /**
     * Construct an instance of UniqueKeyFinder.
     */
    public IndexFinder() {
        super();
    }

    /**
     * This method accumulates the {@link Index} instances. The implementation takes as many shortcuts as possible to prevent
     * unnecessarily visiting unrelated objects.
     * 
     * @see org.teiid.designer.core.util.ModelVisitor#visit(org.eclipse.emf.ecore.EObject)
     */
    @Override
	public boolean visit( final EObject object ) {
        // Indexes are contained by Catalogs, Schemas and Resources
        if (object instanceof Index) {
            // Must have been a root object in a Resource ...
            found((Index)object);
            return false;
        }
        if (object instanceof Catalog) {
            return true; // may be schemas below the catalog
        }
        if (object instanceof Schema) {
            // schemas contain indexes
            return true;
        }
        return false;
    }

}
