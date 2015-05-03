package Domini;

import java.util.*;


public class Relaciones {
    private class NodeC {
        ArrayList<Relacion> eventos;
        NodeC(Relacion r) {
            eventos = new ArrayList<Relacion>();
            eventos.add(0,r);
        }
        public Congresista obtCongresista() {
            return eventos.get(0).obtCongresista();
        }
        public String obtenerIDCongresista() {
            return eventos.get(0).obtCongresista().ID();
        }
        public void agregarRelacion(Relacion r) throws Exception {
            int i;
            for (i = 0; i < eventos.size(); ++i) {
                if (eventos.get(i).obtEvento().ID().equals(r.obtEvento())) {
                    throw new Exception("Ya existe la Relacion");
                }
            }
            eventos.add(i,r);
        }
        public void eliminarRelacion(Relacion r) throws Exception {
            for (int i = 0; i < eventos.size(); ++i) {
                if (eventos.get(i).obtEvento().ID().equals(r.obtEvento())) {
                    eventos.remove(i);
                    return;
                }
            }
            throw new Exception("No existe Relacion");
        }
        //cuidado no es deep copy
        public ArrayList<Relacion> obtenerRelaciones() {
            return eventos;
        }
        public ArrayList<Evento> obtenerEventos() {
            ArrayList<Evento> aux = new ArrayList<Evento>();
            for (int i = 0; i < eventos.size(); ++i) {
                aux.add(i,eventos.get(i).obtEvento());
            }
            return aux;
        }
    }
    private class NodeE {
        ArrayList<Relacion> congresistas;
        public NodeE(Relacion r) {
            congresistas = new ArrayList<Relacion>();
            congresistas.add(0,r);
        }
        public void agregarRelacion(Relacion r) throws Exception {
            int i;
            for (i = 0; i < congresistas.size(); ++i) {
                if (congresistas.get(i).obtEvento().ID().equals(r.obtEvento())) {
                    throw new Exception("Ya existe la Relacion");
                }
            }
            congresistas.add(i,r);
        }
        public void eliminarRelacion(Relacion r) throws Exception {
            for (int i = 0; i < eventos.size(); ++i) {
                if (congresistas.get(i).obtEvento().ID().equals(r.obtEvento())) {
                    congresistas.remove(i);
                    return;
                }
            }
            throw new Exception("No existe Relacion");
        }
        //cuidado no es deep copy
        public ArrayList<Relacion> obtenerRelaciones() {
            return congresistas;
        }
        public ArrayList<Congresista> obtenerCongresistas() {
            ArrayList<Congresista> aux = new ArrayList<Congresista>();
            for (int i = 0; i < congresistas.size(); ++i) {
                aux.add(i,congresistas.get(i).obtCongresista());
            }
            return aux;
        }
    }
    private TST<NodeC> congresistas;
    private TST<NodeE> eventos;

    public Relaciones() {
        congresistas = new TST<NodeC>();
        eventos = new TST<NodeE>();
    }
    public void agregarRelacion(Relacion r) throws Exception {
        NodeC aux = new NodeC(r);
        if (!congresistas.existe(r.obtCongresista().ID())) {
            congresistas.insertar(r.obtCongresista().ID(),aux);
        }
        else {
            aux = congresistas.obtener(r.obtCongresista().ID());
            aux.agregarRelacion(r);
        }
        NodeE aux2 = new NodeE(r);
        if (!eventos.existe(r.obtEvento().ID())) {
            eventos.insertar(r.obtEvento().ID(),aux2);
        }
        else {
            aux2 = eventos.obtener(r.obtEvento().ID());
            aux2.agregarRelacion(r);
        }
    }
    public void eliminarRelacion(Relacion r) throws Exception {
        NodeC aux = congresistas.obtener(r.obtCongresista().ID());
        aux.eliminarRelacion(r);
        NodeE aux2 = eventos.obtener(r.obtEvento().ID());
        aux2.eliminarRelacion(r);
    }
    public ArrayList<Evento> obtEventos(Congresista c) throws Exception {
        return congresistas.obtener(c.ID()).obtenerEventos();
    }
    public ArrayList<Congresista> obtCongresistas(Evento e) throws Exception {
        return eventos.obtener(e.ID()).obtenerCongresistas();
    }
    public ArrayList<Relacion> obtRelaciones(Congresista c) throws Exception {
        return congresistas.obtener(c.ID()).obtenerRelaciones();
    }
    public ArrayList<Relacion> obtRelaciones(Evento e) throws Exception {
        return eventos.obtener(e.ID()).obtenerRelaciones();
    }
    public ArrayList<Congresista> obtCongresistas() {
        List<NodeC> array = congresistas.consultarObjetos();
        ArrayList<Congresista> aux = new ArrayList<Congresista>();
        ListIterator<NodeC> it = array.listIterator();
        while (it.hasNext()) {
            aux.add(it.next().obtCongresista());
        }
        return aux;
    }
    public ArrayList<Relacion> obtTodasLasRelaciones() throws Exception {
        ArrayList<Congresista> aux = obtCongresistas();
        ArrayList<Relacion> array = new ArrayList<Relacion>();
        for (int i = 0; i < aux.size(); ++i) {
            array.addAll(congresistas.obtener(aux.get(i).ID()).obtenerRelaciones());
        }
        return array;
    }


}
