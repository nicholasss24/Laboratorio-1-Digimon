import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entrenador {
    private final String nombre;
    private final ArrayList<Digimon> equipo;
    private final ArrayList<Digimon> disponibles;

    private int rondasGanadas;

    private int ataqueActual;
    private int ataqueSiguiente;

    private int defensaActual;
    private int defensaSiguiente;

    private int danioActual;
    private int danioSiguiente;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.equipo = new ArrayList<>();
        this.disponibles = new ArrayList<>();
        this.rondasGanadas = 0;
    }

    public boolean agregarDigimon(Digimon digimon) {
        boolean repetido = false;

        for (Digimon integrante : equipo) {
            if (integrante.getNombre()
                    .equalsIgnoreCase(digimon.getNombre())) {

                repetido = true;
            }
        }

        if (!repetido && equipo.size() < 4) {
            equipo.add(digimon);
            disponibles.add(digimon);
            return true;
        }

        return false;
    }

    public Digimon usarDigimon(int indice) {
        return disponibles.remove(indice);
    }

    public void aplicarDigievolucion(
            Digievolucion digievolucion) {

        int cantidad = digievolucion.getCantidad();

        if (digievolucion.getTipoEfecto()
                == TipoEfecto.AUMENTAR_ATAQUE) {

            ataqueActual += cantidad;
            ataqueSiguiente += cantidad;

        } else if (digievolucion.getTipoEfecto()
                == TipoEfecto.AUMENTAR_DEFENSA) {

            defensaActual += cantidad;
            defensaSiguiente += cantidad;

        } else {
            danioActual += cantidad;
            danioSiguiente += cantidad;
        }
    }

    public void avanzarRonda() {
        ataqueActual = ataqueSiguiente;
        defensaActual = defensaSiguiente;
        danioActual = danioSiguiente;

        ataqueSiguiente = 0;
        defensaSiguiente = 0;
        danioSiguiente = 0;
    }

    public void sumarRondaGanada() {
        rondasGanadas++;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Digimon> getEquipo() {
        return Collections.unmodifiableList(equipo);
    }

    public List<Digimon> getDigimonesDisponibles() {
        return Collections.unmodifiableList(disponibles);
    }

    public int getCantidadEquipo() {
        return equipo.size();
    }

    public int getRondasGanadas() {
        return rondasGanadas;
    }

    public int getAtaqueActual() {
        return ataqueActual;
    }

    public int getDefensaActual() {
        return defensaActual;
    }

    public int getDanioActual() {
        return danioActual;
    }
}