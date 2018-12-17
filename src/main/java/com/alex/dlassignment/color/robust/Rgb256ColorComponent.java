package com.alex.dlassignment.color.robust;

/**
 * RGB 256 additive color component
 */
public class Rgb256ColorComponent extends ColorComponent<Short> {
    private static final short MAX = 255;

    public enum Type {Red, Green, Blue}

    private Type type;

    public Rgb256ColorComponent(Type type, short value) {
        super(value);
        this.type = type;
    }

    @Override
    protected ColorComponent<Short> valueOf(Short value) {
        return new Rgb256ColorComponent(type, value);
    }

    @Override
    protected Short mix(Short another) {
        short result = (short)(getValue() + another);
        if (result > MAX) {
            result = MAX;
        }
        return result;
    }

    @Override
    public String getName() {
        return type.toString();
    }

    @Override
    public boolean isValid() {
        long value = getValue();
        return value >= 0 && value <= MAX;
    }

    @Override
    public String toString() {
        return type + ": " + getValue();
    }
}
