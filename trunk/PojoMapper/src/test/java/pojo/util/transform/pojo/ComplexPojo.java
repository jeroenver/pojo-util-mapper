package pojo.util.transform.pojo;

import java.util.Collection;
import java.util.Date;

/**
 * User: verelje
 * Date: 3/03/11
 */
class ComplexPojo {
    private SimplePojo simplePojo;
    private Date dateField;
    private Collection<SimplePojo> myCollection;

    public SimplePojo getSimplePojo() {
        return simplePojo;
    }

    public void setSimplePojo(SimplePojo simplePojo) {
        this.simplePojo = simplePojo;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }

    public Collection<SimplePojo> getMyCollection() {
        return myCollection;
    }

    public void setMyCollection(Collection<SimplePojo> myCollection) {
        this.myCollection = myCollection;
    }
}
