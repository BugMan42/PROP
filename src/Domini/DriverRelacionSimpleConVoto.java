package Domini;


import java.util.Scanner;

public class DriverRelacionSimpleConVoto {

    private static RelacionSimpleConVoto R;

    private static void print(String str) {
        System.out.println(str);
    }

    private static void PresentaMenu() {
        print("\nDRIVER Relacion Simple Con Voto");
        print("0 RelacionSimpleConVoto(Congresista a, Evento e, Voto v)");
        print("1 modRelacion(Congresista C1, Evento Ev)");
        print("2 modRelacion(Congresista C1)");
        print("3 modRelacion(Evento Ev)");
        print("4 modVoto(Voto v)");
        print("5 obtCongresista():Congresista");
        print("6 obtEvento():Evento");
        print("7 tieneVoto():boolean");
        print("8 obtVoto():Voto");
        print("9 esSimple():boolean");
        print("10 toString():String");
        print("11 Salir");
        print("Escribe la opcion");

    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        Congresista C1 = new Congresista(new Dni("00000000A"),"Edsger","Dijkstra",72,"Rotterdam","NT","Pirata");
        Evento E1 = new Profesional("Golf","12/12/2012",10);
        Voto V1 = new Blanco();
        R = new RelacionSimpleConVoto(C1,E1,V1);

        boolean imprimir = true;
        if (args.length > 0) {
            if (args[0].equals("0")) imprimir = false;
        }
        Scanner userInput = new Scanner(System.in);
        if (imprimir) PresentaMenu();
        do {
            try {
                ProcesarLinea(LeerLinea(userInput),imprimir);
            } catch (Exception a) {
                print(a.getMessage());
            }
            if (imprimir) PresentaMenu();
        } while(userInput.hasNextLine());
    }

    private static void ProcesarLinea(String str, boolean imprimir) throws Exception {
        Congresista C1 = new Congresista(new Dni("00000000A"),"Edsger","Dijkstra",72,"Rotterdam","NT","Pirata");
        Congresista C2 = new Congresista(new Dni("00000000B"),"Gordon","Moore",86,"San Francisco","CA","Berkeley");
        Congresista C3 = new Congresista(new Dni("00000000C"),"Richard","Hamming",82,"Monterey","CA","Pirata");
        Evento E1 = new Profesional("Golf","12/12/2012",10);
        Evento E2 = new Profesional("Golf2","12/12/2012",10);
        Evento E3 = new Votacion("votos","12/12/2012",11);
        Voto V1 = new Positivo();
        Voto V2 = new Negativo();
        if (!imprimir) print("E: "+str);
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                R = new RelacionSimpleConVoto(C1,E1,V1);
                break;
            case 1:
                R.modRelacion(C2, E2);
                break;
            case 2:
                R.modRelacion(C3);
                break;
            case 3:
                R.modRelacion(E3);
                break;
            case 4:
                R.modVoto(V2);
                break;
            case 5:
                print("S: "+R.obtCongresista());
                break;
            case 6:
                print("S: " + R.obtEvento());
                break;
            case 7:
                print("S: "+R.tieneVoto());
                break;
            case 8:
                print("S: "+R.obtVoto());
                break;
            case 9:
                print("S: "+R.esSimple());
                break;
            case 10:
                print("S: "+R.toString());
                break;
            case 11:
                System.exit(0);
                break;

            default:
                print("Fuera de rango");
                break;
        }
    }

}
