package org.fluentlenium.core.inject;

import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.components.ComponentException;
import org.fluentlenium.utils.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class DefaultContainerInstanciator implements ContainerInstanciator {
    private final FluentControl fluentControl;

    public DefaultContainerInstanciator(FluentControl fluentControl) {
        this.fluentControl = fluentControl;
    }

    @Override
    public <T> T newInstance(Class<T> cls) {
        try {
            return ReflectionUtils.newInstanceOptionalArgs(cls, fluentControl);
        } catch (NoSuchMethodException e) {
            throw new FluentInjectException(cls.getName() + " is not a valid component class.", e);
        } catch (IllegalAccessException e) {
            throw new FluentInjectException(cls.getName() + " can't be instantiated.", e);
        } catch (InvocationTargetException e) {
            throw new FluentInjectException(cls.getName() + " can't be instantiated.", e);
        } catch (InstantiationException e) {
            throw new FluentInjectException(cls.getName() + " can't be instantiated.", e);
        }
    }
}
