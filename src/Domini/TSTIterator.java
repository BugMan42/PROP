package Domini;

import java.util.Stack;

public class TSTIterator<X> extends TST{
    Stack<TSTNodoChar> stack;
    int n;

    //public TSTIterator(TSTIterator aux) {
       //for (int i = 0; i < )
    //}
    public TSTIterator(TST tst) {
        stack = new Stack<TSTNodoChar>();
        n = tst.size();
        TSTNodoChar aux = tst.root;
        boolean seguir = aux != null;
        while (seguir) {
            stack.push(aux);
            if (aux.left == null) aux = (TSTNodoChar)aux.middle;
            else aux = (TSTNodoChar) aux.left;
            if (aux.valor == fin) {
                seguir = false;
                stack.push(aux);
            }
        }
        //print("stack iniiiii");
        //printstack();
        //print(" ");
    }

    public boolean hasNext() {
        return n > 0;
    }
    private void print(String str) {
        System.out.println(str);
    }
    public X next() {
        if (n != 0) {
            //printstack();
            --n;
            TSTNodoChar nodeIni = stack.pop();
            TSTNodoFinal valor = (TSTNodoFinal) nodeIni.middle;
            X x = (X)valor.valor;
            if (n != 0) {
                if (nodeIni.right != null) {
                    //print("wtf");
                    stack.push(nodeIni);
                    obtenerNext((TSTNodoChar) nodeIni.right);
                }
                else {
                    //printstack();
                    boolean found = false;
                    TSTNodoChar auxP,auxF;
                    auxP = nodeIni;
                    while (!found) {
                        auxF = auxP;
                        auxP = stack.pop();
                        //print("P: "+auxP.valor);
                        //print("F: "+auxF.valor);
                        if (auxP.left == auxF) {
                            if (auxP.middle != null) {
                                stack.push(auxP);
                                obtenerNext((TSTNodoChar)auxP.middle);
                                found = true;
                            }
                            else if (auxP.right != null) {
                                stack.push(auxP);
                                obtenerNext((TSTNodoChar)auxP.right);
                                found = true;
                            }

                        }
                        else if (auxP.middle == auxF) {
                            if (auxP.right != null) {
                                stack.push(auxP);
                                obtenerNext((TSTNodoChar)auxP.right);
                                found = true;
                            }
                        }
                        else {
                            //if (auxP.right == auxF) //seguir
                        }
                    }
                }
            }

            return x;
        }
        else return null;
    }

    /*public X next() {
        --n;
        //printstack();
        TSTNodoChar node = stack.pop();
        TSTNodoFinal aux = (TSTNodoFinal)node.middle;
        X x = (X)aux.valor;
        //print("Stack: "+stack.get(0).valor);
       // print("Debug: node.valor: "+node.valor);
        if (n != 0) {
            if (node.right != null) {
                stack.push(node);
                //TSTNodoChar l = (TSTNodoChar) node.right;
                //print("It's something: ");
                obtenerNext((TSTNodoChar) node.right);
            } else {
                TSTNodoChar aux1 = stack.pop();
                if (aux1.right != null) {
                    stack.push(aux1);
                    //print("It's something2: "+aux1.valor);
                    obtenerNext((TSTNodoChar)aux1.right);
                }
                else {
                    //printstack();
                    aux1 = stack.pop();
                    TSTNodoChar aux2 = stack.pop();
                    //aux2 = stack.pop();
                    //boolean seguir = true;
                    //print("valor "+aux2.valor);
                    while (aux2 != null) {
                        //print("dins while");
                        if (aux2.left != aux1 && aux2.left != null) {
                            //print("left");
                            obtenerNext((TSTNodoChar) aux2.left);
                            break;
                        }
                        if (aux2.middle != null && aux2.middle != aux1) {
                           // print("middle");
                            obtenerNext((TSTNodoChar) aux2.middle);
                            break;
                        }
                        if (aux2.right != null && aux2.right != aux1) {
                            //print("right");
                            obtenerNext((TSTNodoChar) aux2.right);
                            break;
                        }
                        aux1 = aux2;
                        if (!stack.isEmpty()) aux2 = stack.pop();
                        else aux2 = null;
                    }
                }
            }
        }
        return x;
    }*/
    private void obtenerNext(TSTNodoChar t) {
        if (t != null) {
            //print("dins obtener: " + t.valor);
            //printstack();
            //print("");
            stack.push(t);
            if (t.valor != fin) {
                TSTNodoChar l = (TSTNodoChar)t.left;
                TSTNodoChar m = (TSTNodoChar)t.middle;
                TSTNodoChar r = (TSTNodoChar)t.right;
                obtenerNext((TSTNodoChar) t.left);
                if (l != null && stack.peek().valor == fin) {

                }
                else {
                    obtenerNext((TSTNodoChar)t.middle);
                    if (m != null && stack.peek().valor == fin) {

                    }
                    else {
                        obtenerNext((TSTNodoChar)t.right);

                    }
                }
            }
        }
    }
    void printstack() {
        //Stack<TSTNodoChar> aux = stack;
        for (int i = 0; i < stack.size(); ++i) {
            print("i:"+i+" "+stack.get(i).valor);
        }
    }
}
