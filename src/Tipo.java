public enum Tipo {
    FUEGO,
    AGUA,
    PLANTA,
    ELECTRICO;

    public int calcularModificadorContra(Tipo defensor) {
        boolean esEfectivo =
                (this == FUEGO && defensor == PLANTA)
                || (this == PLANTA && defensor == AGUA)
                || (this == AGUA && defensor == FUEGO)
                || (this == ELECTRICO && defensor == AGUA);

        boolean esDebil =
                (this == PLANTA && defensor == FUEGO)
                || (this == AGUA && defensor == PLANTA)
                || (this == FUEGO && defensor == AGUA)
                || (this == AGUA && defensor == ELECTRICO);

        if (esEfectivo) {
            return 20;
        }

        if (esDebil) {
            return -10;
        }

        return 0;
    }

    @Override
    public String toString() {
        if (this == ELECTRICO) {
            return "Eléctrico";
        }

        String nombre = name().toLowerCase();

        return Character.toUpperCase(nombre.charAt(0))
                + nombre.substring(1);
    }
}