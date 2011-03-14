package pojo.util.transform.pojo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static pojo.util.transform.PojoSelector.select;
import static pojo.util.transform.PojoSelector.valueOf;
import static org.testng.Assert.*;

/**
 * User: verelje
 * Date: 2/03/11
 */
public final class PojoSelectorTest {
    private SimplePojo simplePojo;
    private ComplexPojo complexPojo;
    private List<ComplexPojo> pojoList;

    @BeforeMethod
    public void initPojos() {
        complexPojo = new ComplexPojo();
        simplePojo = new SimplePojo();
        simplePojo.setField1("test1");
        simplePojo.setField2("test2");
        complexPojo.setSimplePojo(simplePojo);
        complexPojo.setDateField(new Date());
        pojoList = new LinkedList<ComplexPojo>();
        pojoList.add(complexPojo);
        pojoList.add(new ComplexPojo());
    }

    @Test
    public void testSimpleGetter() throws Exception {
        String field1 = valueOf(select(simplePojo).getField1());
        assertEquals(field1, "test1");
    }

    @Test
    public void testMoreComplexGetter() throws Exception {
        String field1 = valueOf(select(complexPojo).getSimplePojo().getField1());
        assertEquals(field1, "test1");
    }

    @Test
    public void testGetObject() throws Exception {
        SimplePojo simplePojoReflect = valueOf(select(complexPojo).getSimplePojo());
        assertEquals(simplePojoReflect, simplePojo);
    }

    @Test
    public void testWorkOnDate() throws Exception {
        long time = valueOf(select(complexPojo).getDateField().getTime());
        assertTrue(time > 0);
    }

    @Test
    public void testGetNullField() throws Exception {
        String field3 = valueOf(select(simplePojo).getField3());
        assertNull(field3);
    }

    @Test
    public void testGetNullObject() throws Exception {
        complexPojo.setSimplePojo(null);
        String field1 = valueOf(select(complexPojo).getSimplePojo().getField1());
        assertNull(field1);
    }

    @Test
    public void testGetDefaultValue() throws Exception {
        String field3 = valueOf(select(simplePojo).getField3(), "default");
        assertEquals(field3, "default");
    }

    @Test
    public void testSimpleQuery() throws Exception {
        //query(pojoList).lookingFor();

    }
}
