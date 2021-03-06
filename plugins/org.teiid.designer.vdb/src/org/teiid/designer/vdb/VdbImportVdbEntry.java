/*
 * JBoss, Home of Professional Open Source.
*
* See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
*
* See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
*/
package org.teiid.designer.vdb;

import java.util.concurrent.atomic.AtomicReference;
import org.teiid.designer.vdb.Vdb.Event;
import org.teiid.designer.vdb.manifest.ImportVdbElement;

/**
*
*
* @since 8.0
*/
public class VdbImportVdbEntry extends VdbUnit {
	
	final AtomicReference<Integer> version = new AtomicReference<Integer>();
	
	final AtomicReference<Boolean> importDataPolicies = new AtomicReference<Boolean>();
	
	/**
	 * @param vdb
	 * @param importVdbName
	 */
	public VdbImportVdbEntry(Vdb vdb, String importVdbName) {
		setVdb(vdb);
        setName(importVdbName);
        this.version.set(1);
        this.importDataPolicies.set(false);
	}
	
	/**
	 * @param vdb
	 *	@param element
	 */
	public VdbImportVdbEntry(Vdb vdb, ImportVdbElement element) {
		this(vdb, element.getName());
        this.version.set(element.getVersion());    
        this.importDataPolicies.set(element.isImportDataPolicies());
	}

	/**
	 * @return return the version
	 */
	public int getVersion() {
		return version.get();
	}
	
	/**
	 * Modify the version attribute
	 * 
	 * @param version
	 */
	public void setVersion(int version) {
		final int oldVersion = this.version.get();
        if (version == oldVersion) return;
        this.version.set(version);
		setModified(this, Event.IMPORT_VDB_ENTRY_VERSION, oldVersion, version);
	}

	/**
	 * @return the import data policies flag
	 */
	public boolean isImportDataPolicies() {
		return importDataPolicies.get();
	}
	
	/**
	 * Modify the import data policies flag
	 * 
	 * @param importDataPolicies
	 */
	public void setImportDataPolicies(boolean importDataPolicies) {
		final boolean oldImportDataPolicies = this.importDataPolicies.get();
        if (importDataPolicies == oldImportDataPolicies) return;
        this.importDataPolicies.set(importDataPolicies);
		setModified(this, Event.IMPORT_VDB_ENTRY_DATA_POLICY, oldImportDataPolicies, importDataPolicies);
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.importDataPolicies == null) ? 0 : this.importDataPolicies.hashCode());
        result = prime * result + ((this.version == null) ? 0 : this.version.hashCode());
        return result;
    }

	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        VdbImportVdbEntry other = (VdbImportVdbEntry)obj;
        if (this.importDataPolicies == null) {
            if (other.importDataPolicies != null)
                return false;
        } else if (!this.importDataPolicies.equals(other.importDataPolicies))
            return false;
        if (this.version == null) {
            if (other.version != null)
                return false;
        } else if (!this.version.equals(other.version))
            return false;
        return true;
    }

    @Override
    public VdbImportVdbEntry clone() {
        VdbImportVdbEntry clone = new VdbImportVdbEntry(getVdb(), getName());
        cloneVdbObject(clone);
        clone.setImportDataPolicies(isImportDataPolicies());
        clone.setVersion(getVersion());
        return clone;
    }
}
