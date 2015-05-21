package Domini;


import java.util.Scanner;

public class DriverRelacionCompuestaOr {

    private static RelacionCompuestaOr R;

    private static void print(String str) {
        System.out.println(str);
    }

    private static void PresentaMenu() {
        print("\nDRIVER Relacion Compuesta Or");
        print("0 RelacionCompuestaOr(Relacion a)");
        print("1 modRelacionA(Relacion a)");
        print("2 modRelacionB(Relacion b)");
        print("3 obtRelacionA():Relacion");
        print("4 obtRelacionB():Relacion");
        print("5 esOr():boolean");
        print("6 esNot():boolean");
        print("7 esSimple():boolean");
        print("8 toString():String");
        print("9 Salir");
        print("Escribe la opcion");

    }
    private static String LeerLinea(Scanner Input) {
        return Input.nextLine();
    }
    public static void main(String[] args) throws Exception {
        Congresista C1 = new Congresista(new Dni("00000000A"),"Edsger","Dijkstra",72,"Rotterdam","NT","Pirata");
        Congresista C2 = new Congresista(new Dni("00000000B"),"Gordon","Moore",86,"San Francisco","CA","Berkeley");
        Reunion E1 = new Profesional("Golf","12/12/2012",10);
        Reunion E2 = new Profesional("Golf2","12/12/2012",10);
        RelacionSimpleSinVoto R1 = new RelacionSimpleSinVoto(C1,E1);
        RelacionSimpleSinVoto R2 = new RelacionSimpleSinVoto(C2,E2);
        R = new RelacionCompuestaOr(R1,R2);

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
        Reunion E1 = new Profesional("Golf","12/12/2012",10);
        Reunion E2 = new Profesional("Golf2","12/12/2012",10);
        Acto E3 = new Oficial("votos","12/12/2012",11);
        Voto V1 = new Positivo();
        Voto V2 = new Negativo();
        RelacionSimpleSinVoto R1 = new RelacionSimpleSinVoto(C1,E1);
        RelacionSimpleSinVoto R2 = new RelacionSimpleSinVoto(C2,E2);
        RelacionSimpleSinVoto R3 = new RelacionSimpleSinVoto(C3,E3);


        if (!imprimir) print("E: "+str);
        String aux[] = str.split("\\s");
        if (str.length() == 0) throw new ArgumentosInsuficientes();
        switch (Integer.parseInt(aux[0])) {
            case 0:
                R = new RelacionCompuestaOr(R3,R3);
                break;
            case 1:
                R.modRelacionA(R1);
                break;
            case 2:
                R.modRelacionB(R2);
                break;
            case 3:
                print("S: "+R.obtRelacionA());
                break;
            case 4:
                print("S: "+R.obtRelacionB());
                break;
            case 5:
                print("S: "+R.esOr());
                break;
            case 6:
                print("S: " + R.esNot());
                break;
            case 7:
                print("S: "+R.esSimple());
                break;
            case 8:
                print("S: "+R.toString());
                break;
            case 9:
                System.exit(0);
                break;
            default:
                print("Fuera de rango");
                break;
        }
    }

}

