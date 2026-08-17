import java.util.Random;

public class Batalla {
    private final Random random;

    public Batalla() {
        this.random = new Random();
    }

    public ResultadoRonda jugarRonda(
            Entrenador entrenador1,
            Digimon digimon1,
            boolean usarHabilidad1,
            Entrenador entrenador2,
            Digimon digimon2,
            boolean usarHabilidad2) {

        int lanzamiento1 = -1;
        int lanzamiento2 = -1;

        boolean habilidadActivada1 = false;
        boolean habilidadActivada2 = false;

        if (usarHabilidad1) {
            Digievolucion habilidad1 =
                    digimon1.getDigievolucion();

            lanzamiento1 =
                    habilidad1.lanzarActivacion(random);

            habilidadActivada1 =
                    habilidad1.seActiva(lanzamiento1);

            if (habilidadActivada1) {
                entrenador1.aplicarDigievolucion(
                        habilidad1);
            }
        }

        if (usarHabilidad2) {
            Digievolucion habilidad2 =
                    digimon2.getDigievolucion();

            lanzamiento2 =
                    habilidad2.lanzarActivacion(random);

            habilidadActivada2 =
                    habilidad2.seActiva(lanzamiento2);

            if (habilidadActivada2) {
                entrenador2.aplicarDigievolucion(
                        habilidad2);
            }
        }

        int modificadorTipo1 = digimon1.getTipo()
                .calcularModificadorContra(
                        digimon2.getTipo());

        int modificadorTipo2 = digimon2.getTipo()
                .calcularModificadorContra(
                        digimon1.getTipo());

        int ataqueTotal1 = calcularAtaqueTotal(
                entrenador1,
                digimon1,
                modificadorTipo1,
                entrenador2,
                digimon2);

        int ataqueTotal2 = calcularAtaqueTotal(
                entrenador2,
                digimon2,
                modificadorTipo2,
                entrenador1,
                digimon1);

        int ganador = 0;

        if (ataqueTotal1 > ataqueTotal2) {
            ganador = 1;
            entrenador1.sumarRondaGanada();

        } else if (ataqueTotal2 > ataqueTotal1) {
            ganador = 2;
            entrenador2.sumarRondaGanada();
        }

        ResultadoRonda resultado =
                new ResultadoRonda(
                        ataqueTotal1,
                        ataqueTotal2,
                        modificadorTipo1,
                        modificadorTipo2,
                        lanzamiento1,
                        lanzamiento2,
                        habilidadActivada1,
                        habilidadActivada2,
                        ganador);

        entrenador1.avanzarRonda();
        entrenador2.avanzarRonda();

        return resultado;
    }

    private int calcularAtaqueTotal(
            Entrenador atacante,
            Digimon digimonAtacante,
            int modificadorTipo,
            Entrenador defensor,
            Digimon digimonDefensor) {

        int ataque =
                digimonAtacante.getAtaque()
                + atacante.getAtaqueActual()
                + modificadorTipo;

        int defensa =
                digimonDefensor.getDefensa()
                + defensor.getDefensaActual();

        int ataqueTotal =
                ataque
                - defensa
                - defensor.getDanioActual();

        return Math.max(0, ataqueTotal);
    }
}