package com.alex.dlassignment.color.robust;

import java.util.Objects;

/**
 * Base color component for additive, subtractive, and otehr (black adjustment, etc.) colors
 *
 * @param <T> numeric type like Short, Integer, or Long to store the value
 */
public abstract class ColorComponent<T extends Number> {
    private T value;

    /**
     * Base constructor
     *
     * @param value typically Short, Integer, or Long
     */
    protected ColorComponent(T value) {
        this.value = value;

    }

    /**
     * Component value
     *
     * @return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * Mixing API
     *
     * @param another another component
     * @return the resulting component
     */
    public ColorComponent<T> mix(ColorComponent<T> another) {
        return valueOf(mix(another.value));
    }

    /**
     * Evaluation API for adjustment colors
     *
     * @param color the overall color with its components
     * @return the new component
     */
    public ColorComponent<T> evaluate(Color color) {
        T newVal = eval(color);
        return valueOf(newVal == null ? getValue() : newVal);
    }

    /**
     * Creates a copy initialized with specific value
     *
     * @param value numeric value
     * @return a copy of this component
     */
    protected abstract ColorComponent<T> valueOf(T value);

    /**
     * Mixing two colors according to teh additive or another logic. Can be empty/default for adjustment colors like black.
     *
     * @param another another color to mix
     * @return the mix of this and anotehr colors
     */
    protected T mix(T another) {
        return null;
    }

    /**
     * Evaluate an adjustment component like black. Can be empty for normal e.g. R/G/B colors
     *
     * @param color the overall color with its components
     * @return the new value to set or null
     */
    protected T eval(Color color) {
        return null;
    }

    public abstract String getName();

    public abstract boolean isValid();

    @Override // generated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ColorComponent<?> that = (ColorComponent<?>)o;
        return Objects.equals(value, that.value);
    }

    @Override // generated
    public int hashCode() {
        return Objects.hash(value);
    }
}
