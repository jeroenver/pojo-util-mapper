package pojo.util.transform;

import pojo.util.transform.reflect.QuerySource;
import pojo.util.transform.reflect.SelectorInterceptor;
import pojo.util.transform.reflect.SourceProxy;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * User: verelje
 * Date: 2/03/11
 */
public final class PojoSelector {
    private static final Logger logger = LoggerFactory.getLogger(PojoSelector.class);

    private PojoSelector() {
    }

    public static <T> T select(final T source) {
        return select(source, (Class<T>) source.getClass());
    }

    public static <T> T select(final T source, final Class<T> sourceClass) {
        Object result = source;
        if (!Modifier.isFinal(sourceClass.getModifiers())) {
            final Callback callback = new SelectorInterceptor(source);
            final Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(sourceClass);
            enhancer.setCallback(callback);
            enhancer.setInterfaces(new Class[]{SourceProxy.class});
            logger.trace("Select: returning proxy for class " + sourceClass.getName());
            result = enhancer.create();
        } else {
            logger.trace("Select: not returning proxy for final class " + sourceClass.getName());
        }
        return (T) result;
    }

    public static <T> T valueOf(final T source, final T defaultValue) {
        Object result = source;
        if (SourceProxy.class.isInstance(source)) {
            logger.trace("ValueOf called on proxy of type " + source.getClass().getName());
            result = SourceProxy.class.cast(source).getValue(source);
        }
        if (result == null) {
            logger.trace("ValueOf: returning default value.");
            result = defaultValue;
        }
        return (T) result;
    }

    public static <T> T valueOf(final T source) {
        return valueOf(source, null);
    }

    public static <T> QuerySource<T> query(final List<T> sourceList){

        return null;
    }
}
