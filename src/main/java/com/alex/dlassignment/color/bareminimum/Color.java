package com.alex.dlassignment.color.bareminimum;

import java.util.Arrays;

public abstract class Color<T extends Number> {
    private T[] components;

    public static class ColorException extends Exception {
        public ColorException(String message) {
            super(message);
        }
    }

    protected Color(T... components) throws ColorException {
        this.components = components;
        if (components.length == 0) {
            throw new ColorException("No color components");
        }
        validate();
    }

    protected abstract void validate() throws ColorException;

    public T[] getComponents() {
        return components;
    }

    public abstract Color mix(Color color) throws ColorException;

    @Override // generated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Color<?> color = (Color<?>)o;
        return Arrays.equals(components, color.components);
    }

    @Override // generated
    public int hashCode() {
        return Arrays.hashCode(components);
    }
}