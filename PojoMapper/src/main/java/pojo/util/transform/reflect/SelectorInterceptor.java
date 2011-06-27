package pojo.util.transform.reflect;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import static pojo.util.transform.PojoSelector.select;

/**
 * User: verelje
 * Date: 3/03/11
 */
public final class SelectorInterceptor implements MethodInterceptor {
    private final Logger logger = LoggerFactory.getLogger(SelectorInterceptor.class);
    private final Object source;
    private final Class sourceClass;

    public SelectorInterceptor(final Object source, final Class sourceClass) {
        this.source = source;
        this.sourceClass = sourceClass;
    }

    public Object intercept(final Object o, final Method method, final Object[] objects, final MethodProxy methodProxy) throws Throwable {
        logger.trace("Intercept: method " + method.toString());
        Object result;
        if (method.getDeclaringClass().isAssignableFrom(SourceProxy.class)) {
            result = interceptSourceProxyMethods(SourceProxy.class.cast(o), method, objects, methodProxy);
        } else {
            result = interceptOtherMethods(o, method, objects, methodProxy);
        }
        return result;
    }

    private Object interceptOtherMethods(final Object o, final Method method, final Object[] objects, final MethodProxy methodProxy) throws Throwable {
        final Class resultType = method.getReturnType();
        logger.trace("InterceptOtherMethods: Need new proxy with returnType " + resultType);
        Object result;
        try {
            result = method.invoke(source, objects);
        } catch (NullPointerException e) {
            logger.trace("returning null for method call " + method.toString());
            result = null;
        }
        return select(result, resultType);
    }

    private Object interceptSourceProxyMethods(final SourceProxy sourceProxy, final Method method, final Object[] objects, final MethodProxy methodProxy) throws Throwable {
        logger.trace("InterceptSourceProxy: returning source of type " + sourceClass.getName());
        return source;
    }
}
