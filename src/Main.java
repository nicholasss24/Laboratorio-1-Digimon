import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER =
            new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println(
                "====================================");

        System.out.println(
                "     BATALLA DE DIGIMON - UVG");

        System.out.println(
                "====================================");

        String nombre1 =
                leerTexto("Nombre del entrenador 1: ");

        String nombre2 =
                leerTexto("Nombre del entrenador 2: ");

        Entrenador entrenador1 =
                new Entrenador(nombre1);

        Entrenador entrenador2 =
                new Entrenador(nombre2);

        seleccionarEquipo(
                entrenador1,
                crearCatalogo());

        seleccionarEquipo(
                entrenador2,
                crearCatalogo());

        Batalla batalla = new Batalla();

        for (int ronda = 1; ronda <= 4; ronda++) {
            System.out.println(
                    "\n========== RONDA "
                    + ronda
                    + " ==========");

            Digimon digimon1 =
                    seleccionarParaRonda(entrenador1);

            boolean usarHabilidad1 =
                    seleccionarAccion(
                            entrenador1,
                            digimon1);

            Digimon digimon2 =
                    seleccionarParaRonda(entrenador2);

            boolean usarHabilidad2 =
                    seleccionarAccion(
                            entrenador2,
                            digimon2);

            ResultadoRonda resultado =
                    batalla.jugarRonda(
                            entrenador1,
                            digimon1,
                            usarHabilidad1,
                            entrenador2,
                            digimon2,
                            usarHabilidad2);

            mostrarResultado(
                    entrenador1,
                    digimon1,
                    usarHabilidad1,
                    entrenador2,
                    digimon2,
                    usarHabilidad2,
                    resultado);
        }

        mostrarGanadorFinal(
                entrenador1,
                entrenador2);

        SCANNER.close();
    }

    private static ArrayList<Digimon> crearCatalogo() {
        ArrayList<Digimon> catalogo =
                new ArrayList<>();

        catalogo.add(new Digimon(
                "Agumon",
                Tipo.FUEGO,
                70,
                25,
                new Digievolucion(
                        "Adult",
                        TipoEfecto.AUMENTAR_ATAQUE,
                        15,
                        30)));

        catalogo.add(new Digimon(
                "Gabumon",
                Tipo.AGUA,
                66,
                30,
                new Digievolucion(
                        "Mega",
                        TipoEfecto.AUMENTAR_DEFENSA,
                        20,
                        35)));

        catalogo.add(new Digimon(
                "Palmon",
                Tipo.PLANTA,
                64,
                32,
                new Digievolucion(
                        "Ultimate",
                        TipoEfecto.DANIO_DIRECTO,
                        10,
                        25)));

        catalogo.add(new Digimon(
                "Tentomon",
                Tipo.ELECTRICO,
                68,
                28,
                new Digievolucion(
                        "Adult",
                        TipoEfecto.AUMENTAR_ATAQUE,
                        15,
                        30)));

        catalogo.add(new Digimon(
                "Biyomon",
                Tipo.FUEGO,
                65,
                31,
                new Digievolucion(
                        "Mega",
                        TipoEfecto.AUMENTAR_DEFENSA,
                        20,
                        35)));

        catalogo.add(new Digimon(
                "Gomamon",
                Tipo.AGUA,
                69,
                26,
                new Digievolucion(
                        "Ultimate",
                        TipoEfecto.DANIO_DIRECTO,
                        10,
                        25)));

        catalogo.add(new Digimon(
                "Floramon",
                Tipo.PLANTA,
                62,
                34,
                new Digievolucion(
                        "Adult",
                        TipoEfecto.AUMENTAR_ATAQUE,
                        15,
                        30)));

        catalogo.add(new Digimon(
                "Elecmon",
                Tipo.ELECTRICO,
                67,
                29,
                new Digievolucion(
                        "Mega",
                        TipoEfecto.AUMENTAR_DEFENSA,
                        20,
                        35)));

        return catalogo;
    }

    private static void seleccionarEquipo(
            Entrenador entrenador,
            ArrayList<Digimon> catalogo) {

        System.out.println(
                "\n"
                + entrenador.getNombre()
                + ", selecciona 4 Digimon distintos.");

        while (entrenador.getCantidadEquipo() < 4) {
            mostrarCatalogo(catalogo);

            int opcion = leerEnteroEnRango(
                    "Selecciona el Digimon "
                    + (entrenador.getCantidadEquipo() + 1)
                    + ": ",
                    1,
                    catalogo.size());

            boolean agregado =
                    entrenador.agregarDigimon(
                            catalogo.get(opcion - 1));

            if (agregado) {
                System.out.println(
                        "Digimon agregado correctamente.");
            } else {
                System.out.println(
                        "Ese Digimon ya fue seleccionado. "
                        + "Elige otro.");
            }
        }
    }

    private static void mostrarCatalogo(
            List<Digimon> catalogo) {

        System.out.println("\nCatálogo disponible:");

        for (int i = 0; i < catalogo.size(); i++) {
            Digimon digimon = catalogo.get(i);

            Digievolucion habilidad =
                    digimon.getDigievolucion();

            System.out.println(
                    (i + 1)
                    + ". "
                    + digimon.getNombre()
                    + " | "
                    + digimon.getTipo()
                    + " | ATQ: "
                    + digimon.getAtaque()
                    + " | DEF: "
                    + digimon.getDefensa()
                    + " | "
                    + habilidad.getNombre()
                    + " ("
                    + habilidad.describirEfecto()
                    + ", "
                    + habilidad.getProbabilidadActivacion()
                    + "%)");
        }
    }

    private static Digimon seleccionarParaRonda(
            Entrenador entrenador) {

        List<Digimon> disponibles =
                entrenador.getDigimonesDisponibles();

        System.out.println(
                "\nTurno de "
                + entrenador.getNombre());

        for (int i = 0; i < disponibles.size(); i++) {
            Digimon digimon = disponibles.get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + digimon.getNombre()
                    + " | "
                    + digimon.getTipo()
                    + " | ATQ: "
                    + digimon.getAtaque()
                    + " | DEF: "
                    + digimon.getDefensa());
        }

        int opcion = leerEnteroEnRango(
                "Selecciona un Digimon: ",
                1,
                disponibles.size());

        return entrenador.usarDigimon(opcion - 1);
    }

    private static boolean seleccionarAccion(
            Entrenador entrenador,
            Digimon digimon) {

        Digievolucion habilidad =
                digimon.getDigievolucion();

        System.out.println(
                "Acción de "
                + entrenador.getNombre()
                + " con "
                + digimon.getNombre()
                + ":");

        System.out.println(
                "1. Atacar normalmente");

        System.out.println(
                "2. Usar "
                + habilidad.getNombre()
                + " ("
                + habilidad.describirEfecto()
                + ")");

        int opcion = leerEnteroEnRango(
                "Selecciona una acción: ",
                1,
                2);

        return opcion == 2;
    }

    private static void mostrarResultado(
            Entrenador entrenador1,
            Digimon digimon1,
            boolean usoHabilidad1,
            Entrenador entrenador2,
            Digimon digimon2,
            boolean usoHabilidad2,
            ResultadoRonda resultado) {

        System.out.println(
                "\n--- Resultado de la ronda ---");

        mostrarIntentoHabilidad(
                digimon1,
                usoHabilidad1,
                resultado.getLanzamiento1(),
                resultado.isHabilidadActivada1());

        mostrarIntentoHabilidad(
                digimon2,
                usoHabilidad2,
                resultado.getLanzamiento2(),
                resultado.isHabilidadActivada2());

        System.out.println(
                entrenador1.getNombre()
                + " - "
                + digimon1.getNombre()
                + ": ataque total = "
                + resultado.getAtaqueTotal1()
                + " (tipo "
                + formatoConSigno(
                        resultado.getModificadorTipo1())
                + ")");

        System.out.println(
                entrenador2.getNombre()
                + " - "
                + digimon2.getNombre()
                + ": ataque total = "
                + resultado.getAtaqueTotal2()
                + " (tipo "
                + formatoConSigno(
                        resultado.getModificadorTipo2())
                + ")");

        if (resultado.getGanador() == 1) {
            System.out.println(
                    "Ganador de la ronda: "
                    + entrenador1.getNombre());

        } else if (resultado.getGanador() == 2) {
            System.out.println(
                    "Ganador de la ronda: "
                    + entrenador2.getNombre());

        } else {
            System.out.println(
                    "La ronda terminó en empate.");
        }

        System.out.println(
                "Marcador: "
                + entrenador1.getNombre()
                + " "
                + entrenador1.getRondasGanadas()
                + " - "
                + entrenador2.getRondasGanadas()
                + " "
                + entrenador2.getNombre());
    }

    private static void mostrarIntentoHabilidad(
            Digimon digimon,
            boolean usoHabilidad,
            int lanzamiento,
            boolean activada) {

        if (usoHabilidad) {
            System.out.println(
                    digimon.getNombre()
                    + " intentó "
                    + digimon.getDigievolucion()
                            .getNombre()
                    + ". Lanzamiento: "
                    + lanzamiento
                    + ".");

            if (activada) {
                System.out.println(
                        "La habilidad se activó durante "
                        + "esta ronda y la siguiente.");
            } else {
                System.out.println(
                        "La habilidad no se activó.");
            }
        }
    }

    private static void mostrarGanadorFinal(
            Entrenador entrenador1,
            Entrenador entrenador2) {

        System.out.println(
                "\n====================================");

        System.out.println(
                "          RESULTADO FINAL");

        System.out.println(
                "====================================");

        System.out.println(
                entrenador1.getNombre()
                + ": "
                + entrenador1.getRondasGanadas()
                + " rondas ganadas");

        System.out.println(
                entrenador2.getNombre()
                + ": "
                + entrenador2.getRondasGanadas()
                + " rondas ganadas");

        if (entrenador1.getRondasGanadas()
                > entrenador2.getRondasGanadas()) {

            System.out.println(
                    "Ganador de la batalla: "
                    + entrenador1.getNombre());

        } else if (entrenador2.getRondasGanadas()
                > entrenador1.getRondasGanadas()) {

            System.out.println(
                    "Ganador de la batalla: "
                    + entrenador2.getNombre());

        } else {
            System.out.println(
                    "La batalla terminó en empate.");
        }
    }

    private static String formatoConSigno(int valor) {
        if (valor > 0) {
            return "+" + valor;
        }

        return String.valueOf(valor);
    }

    private static String leerTexto(String mensaje) {
        String texto = "";

        while (texto.isBlank()) {
            System.out.print(mensaje);
            texto = SCANNER.nextLine().trim();

            if (texto.isBlank()) {
                System.out.println(
                        "El texto no puede quedar vacío.");
            }
        }

        return texto;
    }

    private static int leerEnteroEnRango(
            String mensaje,
            int minimo,
            int maximo) {

        int valor = minimo - 1;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);

            String entrada =
                    SCANNER.nextLine().trim();

            try {
                valor = Integer.parseInt(entrada);

                if (valor >= minimo && valor <= maximo) {
                    valido = true;
                } else {
                    System.out.println(
                            "Ingresa un número entre "
                            + minimo
                            + " y "
                            + maximo
                            + ".");
                }

            } catch (NumberFormatException error) {
                System.out.println(
                        "Entrada inválida. "
                        + "Debes ingresar un número entero.");
            }
        }

        return valor;
    }
}