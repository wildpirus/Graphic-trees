/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.nario;

import javax.swing.JPanel;

/**
 *
 * @author wildg
 */
public class Arbol_Nario {
    private Nodo_Nario raiz;
    private static Nodo_Nario buscado;
    
    public Arbol_Nario() {
        this.raiz = null;
        this.buscado = null;
    }

    public void insertarNodo(String nuevo, String padre) {
        buscado = null;
        buscarNodo(raiz, nuevo);
        if (buscado != null) {
            System.out.println("El nodo ya existe");
        } else if (raiz == null) {
            raiz = new Nodo_Nario(nuevo);
        } else {
            buscado = null;
            buscarNodo(raiz, padre);
            if (buscado == null) {
                System.out.println("No existe el padre");
            } else {
                buscado.addHijo(nuevo);
            }
        }
    }

    private static void buscarNodo(Nodo_Nario n, String valor) {
        if (n == null) {
            buscado = null;
        } else {
            if (valor.equals(n.getInfo())) {
                buscado = n;
            }
            for (Nodo_Nario temp : n.getHijos()) {
                if (buscado == null) {
                    buscarNodo(temp, valor);
                }
            }
        }

    }

    public void eliminarNodo(String valor) {

        if (raiz.getInfo().equals(valor)) {
            if (raiz.getHijos().isEmpty()) {
                raiz = null;
            } else {
                raiz.setInfo(masIzq(raiz).getInfo());
            }
        } else {
            buscado = null;
            buscarNodo(raiz, valor);
            if (buscado == null) {
                System.out.println("Nodo no existe");
            } else {
                Nodo_Nario izquierda = masIzq(buscado), padre;
                buscado = null;
                buscarpadre(raiz, valor);
                padre = buscado;

                int i;
                for (i = 0; i < padre.getHijos().size(); i++) {
                    if (padre.getHijos().get(i).getInfo().equals(valor)) {
                        if (izquierda == null) {
                            padre.getHijos().remove(i);
                        } else {
                            padre.getHijos().get(i).setInfo(izquierda.getInfo());
                        }
                    }
                }
            }
        }
    }

    private Nodo_Nario masIzq(Nodo_Nario n) {
        Nodo_Nario anterior = n;
        while (!n.getHijos().isEmpty()) {
            anterior = n;
            n = n.getHijos().getFirst();
        }
        if (anterior == n)return null;
        anterior.getHijos().remove(n);
        return n;
    }
    
    private void buscarpadre(Nodo_Nario padre, String valor) {
        if (buscado == null) {
            for (Nodo_Nario hijo : padre.getHijos()) {
                if (hijo.getInfo().equals(valor)) {
                    buscado = padre;
                }
            }
            if (buscado == null) {
                for (Nodo_Nario hijo : padre.getHijos()) {
                    buscarpadre(hijo, valor);
                }
            }
        }
    }

    public Nodo_Nario getRaiz() {
        return raiz;
    }

    public static int altura(Nodo_Nario ptr){
        if(ptr==null)return 0;
        int profundidadMax = 0;
        for (Nodo_Nario hijo : ptr.getHijos()) {
            profundidadMax = Math.max(profundidadMax,altura(hijo));
        }
        return profundidadMax+1;
    }
    
    public JPanel getDibujo() {
        return new Drawer(this);
    }
}
