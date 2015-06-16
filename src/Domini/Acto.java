package Domini;

public abstract class Acto extends Evento {

    public Acto(String nombre, Fecha fecha, int importancia) throws Exception{
        super(nombre, fecha, importancia);
    }
}
