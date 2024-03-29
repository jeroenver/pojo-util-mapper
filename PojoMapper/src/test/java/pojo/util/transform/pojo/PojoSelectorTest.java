package pojo.util.transform.pojo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

import static java.math.BigDecimal.ONE;
import static org.testng.Assert.*;
import static pojo.util.transform.PojoSelector.select;
import static pojo.util.transform.PojoSelector.valueOf;

/**
 * User: verelje
 * Date: 2/03/11
 */
public final class PojoSelectorTest {
    private SimplePojo simplePojo;
    private ComplexPojo complexPojo;
    private List<SimplePojo> pojoList;
    private ArrayList<SimplePojo> pojoArrayList;

    @BeforeMethod
    public void initPojos() {
        complexPojo = new ComplexPojo();
        simplePojo = new SimplePojo();
        simplePojo.setField1("test1");
        simplePojo.setField2("test2");
        complexPojo.setSimplePojo(simplePojo);
        complexPojo.setDateField(new Date());
        complexPojo.setBigDecimal(ONE);
        pojoList = new LinkedList<SimplePojo>();
        complexPojo.setMyCollection(pojoList);
        pojoList.add(simplePojo);
        pojoList.add(new SimplePojo());
    }

    @Test
    public void testSimpleGetter() throws Exception {
        String field1 = valueOf(select(simplePojo, SimplePojo.class).getField1());
        assertEquals(field1, "test1");
    }

    @Test
    public void testSimpleGetterNoDefaultConstructor() throws Exception {
        BigDecimal result = valueOf(select(complexPojo, ComplexPojo.class).getBigDecimal());
        assertEquals(result, ONE);
    }

    @Test
    public void testMoreComplexGetter() throws Exception {
        String field1 = valueOf(select(complexPojo, ComplexPojo.class).getSimplePojo().getField1());
        assertEquals(field1, "test1");
    }

    @Test
    public void testCollectionGetter() throws Exception {
        final Collection<SimplePojo> result = valueOf(select(complexPojo, ComplexPojo.class).getMyCollection());
        assertEquals(result, pojoList);
    }

    @Test
    public void testConcreteCollectionGetter() throws Exception {
        final Collection<SimplePojo> result = valueOf(select(complexPojo, ComplexPojo.class).getMyArrayList());
        assertEquals(result, pojoArrayList);
    }

    @Test
    public void testNullCollectionGetter() throws Exception {
        ComplexPojo complexPojo2 = new ComplexPojo();
        complexPojo2.setMyCollection(null);
        final Collection<SimplePojo> result = valueOf(select(complexPojo2, ComplexPojo.class).getMyCollection());
        assertNull(result);
    }

    @Test
    public void testGetObject() throws Exception {
        SimplePojo simplePojoReflect = valueOf(select(complexPojo, ComplexPojo.class).getSimplePojo());
        assertEquals(simplePojoReflect, simplePojo);
    }

    @Test
    public void testWorkOnDate() throws Exception {
        long time = valueOf(select(complexPojo, ComplexPojo.class).getDateField().getTime());
        assertTrue(time > 0);
    }

    @Test
    public void testGetNullField() throws Exception {
        String field3 = valueOf(select(simplePojo, SimplePojo.class).getField3());
        assertNull(field3);
    }

    @Test
    public void testGetNullObject() throws Exception {
        complexPojo.setSimplePojo(null);
        String field1 = valueOf(select(complexPojo, ComplexPojo.class).getSimplePojo().getField1());
        assertNull(field1);
    }

    @Test
    public void testGetNull() throws Exception {
        final ComplexPojo complexPojo = null;
        String field1 = valueOf(select(complexPojo, ComplexPojo.class).getSimplePojo().getField1());
        assertNull(field1);
    }

    @Test
    public void testGetDefaultValue() throws Exception {
        String field3 = valueOf(select(simplePojo, SimplePojo.class).getField3(), "default");
        assertEquals(field3, "default");
    }
}