package Domini;

import java.util.List;

/**
 * Created by usuario on 26/04/2015.
 */
public class CjtEvento {
    private TST<Evento> cjt;

    private void Valido(String nombre, String fecha) throws NoValido {
        if (nombre.equals("")) throw new NoValido("Nombre", 0);
        if (!Fecha.valido(fecha)) throw new NoValido("Fecha", 0);
    }

    //////////////////////////////////////CREADORA////////////////////////

    public CjtEvento() {
        cjt = new TST<Evento>();
    }

    /////////////////////////////////////ELIMINADORA/////////////////////
    public void EliminarCjtEvento() {
        cjt.vaciar();
    }

    /////////////////////////////////////MODIFICADORAS////////////////////////

    public void EliminarEvento(String nombre, String fecha) throws Exception{
        Valido(nombre, fecha);
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro
        // de quitarlos porque sino no se encontrara el objeto
        //Siempre convierto el nombre a mayusculas para evitar errores de no
        // encontrar el elemento
        String name = nombre.toUpperCase();
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        cjt.borrar(name + fecha);
    }

    public void AgregarEvento(Evento e) throws Exception{
        if (e == null) throw new NoValido("Evento", 0);
        cjt.insertar(e.ID(), e);
    }

    public void ModificarNombreEvento(String nomViejo, String fecha, String nomNuevo) throws Exception{
        Valido(nomViejo, fecha);
        if (!nomNuevo.equals("") && !nomNuevo.equals(nomViejo)) {
            //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
            String oldname = nomViejo.toUpperCase();
            //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque
            // sino no se encontrara el objeto
            String s[] = fecha.split("/");
            fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
            //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el
            // conjunto de acuerdo a su nueva clave
            Evento aux = cjt.obtener(oldname + fecha);
            String newname = nomNuevo.toUpperCase();
            aux.ModNombre(newname);
            cjt.modificar(oldname+fecha, newname+fecha, aux);
        }
        else throw new NoValido("NombreNuevo", 0);
    }

    public void ModificarFechaEvento(String nombre, String fechaVieja, String fechaNueva) throws Exception {
        Valido(nombre, fechaVieja);
        if (!fechaVieja.equals(fechaNueva) && Fecha.valido(fechaNueva)) {
            //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
            String name = nombre.toUpperCase();
            //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque
            // sino no se encontrara el objeto
            String f[] = fechaVieja.split("/");
            fechaVieja = Integer.toString(Integer.parseInt(f[0]))+Integer.toString(Integer.parseInt(f[1]))+Integer.toString(Integer.parseInt(f[2]));
            //Como obtener pasa la referencia al objeto lo modifico y lo pongo correctamente en el
            // conjunto de acuerdo a su nueva clave
            Evento aux = cjt.obtener(name + fechaVieja);
            aux.ModFecha(fechaNueva);
            String s[] = fechaNueva.split("/");
            fechaNueva = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
            cjt.modificar(name+fechaVieja, name+fechaNueva, aux);
        }
        else throw new NoValido("FechaNueva", 0);
    }

    public void ModificarImpEvento(String nombre, String fecha, int importance) throws Exception {
        Valido(nombre, fecha);
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
        String name = nombre.toUpperCase();
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque
        // sino no se encontrara el objeto
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        //Como el tst devuelve la refencia al objeto directamente puedo cambiarle los atributos
        cjt.obtener(name+fecha).ModImportancia(importance);
    }

    public void AgregarEventoRandom() throws Exception {
        //Creo un nombre random concatenando letras de
        // la A-Z. 90 y 65 corresponde al codigo
        // ascii de la Z y la A respectivamente
        String nombre = "";
        for (int i = 0; i < 8; ++i) {
            int valorEntero = (int)Math.floor(Math.random()*(90-65+1)+65);
            char c = (char)valorEntero;
            nombre = nombre + c;
        }
        //Uso numeros random del [1,27] para el dia,
        //[1,12] para mes y [1,3000] para el año
        String data = "";
        int valorEntero = (int)Math.floor(Math.random()*(27-1+1)+1);
        data = data+Integer.toString(valorEntero)+"/";
        valorEntero = (int)Math.floor(Math.random()*(12-1+1)+1);
        data = data+Integer.toString(valorEntero)+"/";
        valorEntero = (int)Math.floor(Math.random()*(3000-1+1)+1);
        data = data+Integer.toString(valorEntero);
        //En el remoto caso de que el random genere un evento que ya exista, se aborta.
        if (cjt.existe(nombre+data)) throw new Exception("El evento ya existe vuelve a probar");
        //Uso un numero random [1,10] para importancia
        valorEntero =(int)Math.floor(Math.random()*(10-1+1)+1);
        //Uso un numero random [1,5] para seleccionar el tipo de evento
        int tipo = (int)Math.floor(Math.random()*(5-1+1)+1);
        Evento e;
        switch (tipo) {
            case 1:
                e = new Votacion(nombre, data, valorEntero);
                cjt.insertar(e.ID(), e);
                break;
            case 2:
                e = new Personal(nombre, data, valorEntero);
                cjt.insertar(e.ID(), e);
                break;
            case 3:
                e = new Profesional(nombre, data, valorEntero);
                cjt.insertar(e.ID(), e);
                break;
            case 4:
                e = new Oficial(nombre, data, valorEntero);
                cjt.insertar(e.ID(), e);
                break;
            case 5:
                e =  new NoOficial(nombre, data, valorEntero);
                cjt.insertar(e.ID(), e);
                break;
            default:
        }
    }

    //Consultoras

    public List<Evento> ConsultarTodosEventos() {
        return cjt.consultarObjetos();
    }

    public Evento ConsultarEvento(String nombre, String fecha) throws Exception {
        Valido(nombre, fecha);
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
        String name = nombre.toUpperCase();
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        return cjt.obtener(name+fecha);
    }

    public boolean ExisteEvento(String nombre, String fecha) throws NoValido {
        Valido(nombre, fecha);
        //Siempre convierto el nombre a mayusculas para evitar errores de no encontrar el elemento
        String name = nombre.toUpperCase();
        //En caso de que la fecha tenga numeros que empiecen por 0 me aseguro de quitarlos porque sino no se encontrara el objeto
        String s[] = fecha.split("/");
        fecha = Integer.toString(Integer.parseInt(s[0]))+Integer.toString(Integer.parseInt(s[1]))+Integer.toString(Integer.parseInt(s[2]));
        return cjt.existe(name+fecha);
    }

    public int size() {
        return cjt.size();
    }
}
