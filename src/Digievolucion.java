import java.util.Random;

public class Digievolucion {
    private final String nombre;
    private final TipoEfecto tipoEfecto;
    private final int cantidad;
    private final int probabilidadActivacion;

    public Digievolucion(
            String nombre,
            TipoEfecto tipoEfecto,
            int cantidad,
            int probabilidadActivacion) {

        this.nombre = nombre;
        this.tipoEfecto = tipoEfecto;
        this.cantidad = cantidad;
        this.probabilidadActivacion = probabilidadActivacion;
    }

    public int lanzarActivacion(Random random) {
        return random.nextInt(101);
    }

    public boolean seActiva(int lanzamiento) {
        return lanzamiento <= probabilidadActivacion;
    }

    public String describirEfecto() {
        if (tipoEfecto == TipoEfecto.AUMENTAR_ATAQUE) {
            return "+" + cantidad + " ataque";
        }

        if (tipoEfecto == TipoEfecto.AUMENTAR_DEFENSA) {
            return "+" + cantidad + " defensa";
        }

        return "-" + cantidad + " ataque total al rival";
    }

    public String getNombre() {
        return nombre;
    }

    public TipoEfecto getTipoEfecto() {
        return tipoEfecto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public int getProbabilidadActivacion() {
        return probabilidadActivacion;
    }
}