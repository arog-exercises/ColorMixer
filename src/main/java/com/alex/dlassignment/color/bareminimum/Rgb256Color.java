package com.alex.dlassignment.color.bareminimum;

public class Rgb256Color extends Color<Short> {
    private static final short MAX = 255;

    public Rgb256Color(int red, int green, int blue) throws ColorException { // see this: https://stackoverflow.com/questions/6086334/is-it-good-practice-to-make-the-constructor-throw-an-exception
        super((short)red, (short)green, (short)blue);
    }

    @Override
    public Color mix(Color color) throws ColorException {
        if (!(color instanceof Rgb256Color)) {
            throw new ColorException("Can only mix another Rgb255Color");
        }

        Short[] thisComponents = getComponents();
        Short[] thatComponents = ((Rgb256Color)color).getComponents();
        Short[] result = new Short[thisComponents.length];

        for (int i = 0; i < result.length; i++) {
            result[i] = (short)(thisComponents[i] + thatComponents[i]);
            if (result[i] > MAX) {
                result[i] = MAX;
            }
        }

        return new Rgb256Color(result[0], result[1], result[2]);
    }

    @Override
    protected void validate() throws ColorException {
        int i = 0;
        for (Short component : getComponents()) {
            if (component < 0 && component > MAX) {
                throw new ColorException("Invalid color " + i + ": " + component);
            }
        }
    }
}
