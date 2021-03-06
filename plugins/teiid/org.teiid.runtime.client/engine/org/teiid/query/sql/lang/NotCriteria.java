/* Generated By:JJTree: Do not edit this line. NotCriteria.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package org.teiid.query.sql.lang;

import org.teiid.designer.query.sql.lang.INotCriteria;
import org.teiid.query.parser.LanguageVisitor;
import org.teiid.query.parser.TeiidParser;

/**
 *
 */
public class NotCriteria extends Criteria implements INotCriteria<LanguageVisitor> {

    /** The single sub criteria */
    private Criteria criteria;

    /**
     * @param p
     * @param id
     */
    public NotCriteria(TeiidParser p, int id) {
        super(p, id);
    }

    /**
     * Get sub criteria
     * @return Sub criteria
     */
    @Override
    public Criteria getCriteria() {
        return criteria;
    }

    /**
     * Set sub criteria
     * @param criteria Sub criteria
     */
    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((this.criteria == null) ? 0 : this.criteria.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!super.equals(obj)) return false;
        if (getClass() != obj.getClass()) return false;
        NotCriteria other = (NotCriteria)obj;
        if (this.criteria == null) {
            if (other.criteria != null) return false;
        } else if (!this.criteria.equals(other.criteria)) return false;
        return true;
    }

    /** Accept the visitor. **/
    @Override
    public void acceptVisitor(LanguageVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public NotCriteria clone() {
        NotCriteria clone = new NotCriteria(this.parser, this.id);

        if(getCriteria() != null)
            clone.setCriteria(getCriteria().clone());

        return clone;
    }

}
/* JavaCC - OriginalChecksum=b6a937b5a3dc9ccacee90ecad74cfcf6 (do not edit this line) */
