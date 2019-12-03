package arbol.binario;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wildg
 */
public class Nodo {

    private int dato, altura;
    private Nodo Hizq, Hder;
    //Constructores

    Nodo(int Elem) {
        dato = Elem;
        altura = 1;
        Nodo Hizq, Hder = null;
    }

    Nodo() {
        altura = 1;
        Nodo Hizq, Hder = null;
    }

    public Nodo getHder() {
        return Hder;
    }

    public Nodo getHizq() {
        return Hizq;
    }

    public int getDato() {
        return dato;
    }

    public int getAltura() {
        return altura;
    }
    
    public void setHder(Nodo Hder) {
        this.Hder = Hder;
    }

    public void setHizq(Nodo Hizq) {
        this.Hizq = Hizq;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
    
    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    /*
    public void insertar(int Elem) {
        if (Elem < dato) {
            if (Hizq == null) {
                Hizq = new NodoB(Elem);
            } else {
                Hizq.insertar(Elem);
            }
        } else {
            if (Elem > dato) {
                if (Hder == null) {
                    Hder = new NodoB(Elem);
                } else {
                    Hder.insertar(Elem);
                }
            } else {
                System.out.println("Nodo ya existe ");
            }
        }
    }*/
}
