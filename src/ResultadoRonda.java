public class ResultadoRonda {
    private final int ataqueTotal1;
    private final int ataqueTotal2;

    private final int modificadorTipo1;
    private final int modificadorTipo2;

    private final int lanzamiento1;
    private final int lanzamiento2;

    private final boolean habilidadActivada1;
    private final boolean habilidadActivada2;

    private final int ganador;

    public ResultadoRonda(
            int ataqueTotal1,
            int ataqueTotal2,
            int modificadorTipo1,
            int modificadorTipo2,
            int lanzamiento1,
            int lanzamiento2,
            boolean habilidadActivada1,
            boolean habilidadActivada2,
            int ganador) {

        this.ataqueTotal1 = ataqueTotal1;
        this.ataqueTotal2 = ataqueTotal2;
        this.modificadorTipo1 = modificadorTipo1;
        this.modificadorTipo2 = modificadorTipo2;
        this.lanzamiento1 = lanzamiento1;
        this.lanzamiento2 = lanzamiento2;
        this.habilidadActivada1 = habilidadActivada1;
        this.habilidadActivada2 = habilidadActivada2;
        this.ganador = ganador;
    }

    public int getAtaqueTotal1() {
        return ataqueTotal1;
    }

    public int getAtaqueTotal2() {
        return ataqueTotal2;
    }

    public int getModificadorTipo1() {
        return modificadorTipo1;
    }

    public int getModificadorTipo2() {
        return modificadorTipo2;
    }

    public int getLanzamiento1() {
        return lanzamiento1;
    }

    public int getLanzamiento2() {
        return lanzamiento2;
    }

    public boolean isHabilidadActivada1() {
        return habilidadActivada1;
    }

    public boolean isHabilidadActivada2() {
        return habilidadActivada2;
    }

    public int getGanador() {
        return ganador;
    }
}