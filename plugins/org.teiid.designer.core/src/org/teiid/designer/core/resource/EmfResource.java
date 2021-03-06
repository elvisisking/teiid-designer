/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.resource;

import org.teiid.designer.core.container.Container;
import org.teiid.designer.core.util.ModelContents;


/**
 * MTK implementation of a Resource
 *
 * @since 8.0
 */
public interface EmfResource extends MMXmiResource {
       
    /**
     * Returns the {@link Container} instance associated with
     * this resource set.
     * @return Container
     */
    Container getContainer();
    
    /**
     * Obtain the helper for the model contents.
     * @return the content helper; may be null if this resource is not loaded
     */
    ModelContents getModelContents();

 
}
