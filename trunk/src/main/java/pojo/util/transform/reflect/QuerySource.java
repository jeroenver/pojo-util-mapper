package pojo.util.transform.reflect;

/**
 * User: verelje
 * Date: 3/03/11
 */
public interface QuerySource <T> {

    public <T> T lookingFor(final T source);
}
