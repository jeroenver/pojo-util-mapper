package pojo.util.transform.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * User: verelje
 * Date: 3/03/11
 */
public class ComplexPojo {
    private SimplePojo simplePojo;
    private Date dateField;
    private Collection<SimplePojo> myCollection;
    private ArrayList<SimplePojo> myArrayList;

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

    public ArrayList<SimplePojo> getMyArrayList() {
        return myArrayList;
    }

    public void setMyArrayList(final ArrayList<SimplePojo> myArrayList) {
        this.myArrayList = myArrayList;
    }

    public Collection<SimplePojo> getMyCollection() {
        return myCollection;
    }

    public void setMyCollection(Collection<SimplePojo> myCollection) {
        this.myCollection = myCollection;
    }
}
