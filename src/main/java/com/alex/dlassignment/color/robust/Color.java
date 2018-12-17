package com.alex.dlassignment.color.robust;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Main color abstraction
 */
public class Color {
    private List<ColorComponent> components;

    private enum Type {
        Rgb256(true, false);

        private boolean mixing;
        private boolean adjusting;

        Type(boolean mixing, boolean adjusting) {
            this.mixing = mixing;
            this.adjusting = adjusting;
        }
    }

    private Type type;

    public static class ColorException extends Exception {
        public ColorException(String message) {
            super(message);
        }
    }

    private Color(Type type) {
        this.type = type;
        components = new ArrayList<>();
    }

    private Color(Type type, ColorComponent... components) throws ColorException {
        this.type = type;
        this.components = Arrays.asList(components);
        for (ColorComponent comp : this.components)
            if (!comp.isValid()) {
                throw new ColorException("Invalid color component " + comp.getName() + ": " + comp.getValue());
            }
    }

    /**
     * Additive 256-bit RGB color
     *
     * @param red   red value (0-255)
     * @param green green value (0-255)
     * @param blue  blue value (0-255)
     * @return the new instance
     * @throws ColorException validation exception
     */
    public static Color rgb256(int red, int green, int blue) throws ColorException {
        return new Color(
            Type.Rgb256,
            new Rgb256ColorComponent(Rgb256ColorComponent.Type.Red, (short)red),
            new Rgb256ColorComponent(Rgb256ColorComponent.Type.Green, (short)green),
            new Rgb256ColorComponent(Rgb256ColorComponent.Type.Blue, (short)blue));
    }

    /**
     * Mixing API
     *
     * @param color another color to mix
     * @return the mixed color (copy)
     * @throws ColorException validation error
     */
    public Color mix(Color color) throws ColorException {
        if (!type.equals(color.type)) {
            throw new ColorException("Can only mix same-type colors");
        }

        Color result = new Color(type);

        // Mixing
        if (type.mixing) {
            int i = 0;
            for (ColorComponent comp1 : components) {
                //noinspection unchecked
                result.components.add(comp1.mix(color.components.get(i++))); // The type check is ensured by the validation above
            }
        }

        // Adjustments (adding black, etc.)
        if (type.adjusting) {
            int i = 0;
            for (ColorComponent comp : components) {
                result.components.set(i++, comp.evaluate(this));
            }
        }

        return result;
    }

    /**
     * Get components (a copy).
     *
     * @return the components
     */
    public List<ColorComponent> getComponents() {
        return Collections.unmodifiableList(components);
    }

    @Override
    public String toString() {
        return "{ " + components.stream().map(Object::toString).collect(Collectors.joining(", ")) + " }";
    }

    @Override // generated
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Color color = (Color)o;
        return Objects.equals(components, color.components) && type == color.type;
    }

    @Override // generated
    public int hashCode() {
        return Objects.hash(components, type);
    }
}
