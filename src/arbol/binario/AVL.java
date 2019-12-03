/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.binario;


/**
 *
 * @author wildg
 */
public class AVL extends AB{

    public AVL() {
        super();
    }
    
    @Override
    public void insertarNodo(int elem) {
        Raiz = insertarAVL(Raiz,elem);
    }
    
    @Override
    public void borrarClave(int elem) {
        Raiz = borrarAVL(Raiz,elem);
    }
    
    public  Nodo insertarAVL(Nodo raiz, int dato) {
        if (raiz == null) {
            return new Nodo(dato);
        } else if (dato > raiz.getDato()) {
            raiz.setHder(insertarAVL(raiz.getHder(), dato));
        } else if (dato < raiz.getDato()) {
            raiz.setHizq(insertarAVL(raiz.getHizq(), dato));
        }
        
        int factorBalance = factorBalance(raiz);

        if (factorBalance == -2 && factorBalance(raiz.getHizq()) != 1) {
            return rotacionSimpleIzquierda(raiz);
        }
        if (factorBalance == 2 && factorBalance(raiz.getHder()) != -1) {
            return rotacionSimpleDerecha(raiz);
        }
        if (factorBalance == -2 && factorBalance(raiz.getHizq()) == 1) {
            return rotacionDobleIzquierda(raiz);
        }
        if (factorBalance == 2 && factorBalance(raiz.getHder()) == -1) {
            return rotacionDobleDerecha(raiz);
        }

        return raiz;

    }
    
    public  int factorBalance(Nodo raiz) {
        return alturaArbol(raiz.getHder()) - alturaArbol(raiz.getHizq());
    }
    
    public Nodo rotacionSimpleIzquierda(Nodo raiz) {
        Nodo hijo = raiz.getHizq();
        raiz.setHizq(hijo.getHder());
        hijo.setHder(raiz);
        return hijo;
    }

    public Nodo rotacionSimpleDerecha(Nodo raiz) {
        Nodo hijo = raiz.getHder();
        raiz.setHder(hijo.getHizq());
        hijo.setHizq(raiz);
        return hijo;
    }

    public Nodo rotacionDobleIzquierda(Nodo raiz) {
        raiz.setHizq(rotacionSimpleDerecha(raiz.getHizq()));
        return rotacionSimpleIzquierda(raiz);
    }

    public Nodo rotacionDobleDerecha(Nodo raiz) {
        raiz.setHder(rotacionSimpleIzquierda(raiz.getHder()));
        return rotacionSimpleDerecha(raiz);
    }
    
    public  Nodo buscarAVL(Nodo raiz, int dato) {
        if (raiz != null) {
            if (raiz.getDato() == dato) {
                return raiz;
            }
            if (dato > raiz.getDato()) {
                return buscarAVL(raiz.getHder(), dato);
            } else {
                return buscarAVL(raiz.getHizq(), dato);
            }
        }
        return null;
    }
    
    public Nodo borrarAVL(Nodo raiz, int dato) {
        if (raiz == null) {
            return raiz;
        }
        if (dato < raiz.getDato()) {
            raiz.setHizq(borrarAVL(raiz.getHizq(), dato));
        } else if (dato > raiz.getDato()) {
            raiz.setHder(borrarAVL(raiz.getHder(), dato));
        } else if ((raiz.getHizq() == null) || (raiz.getHder() == null)) {
            Nodo temp = null;
            if (temp == raiz.getHizq()) {
                temp = raiz.getHder();
            } else {
                temp = raiz.getHizq();
            }
            if (temp == null) {
                temp = raiz;
                raiz = null;
            } else {
                raiz = temp;
            }
        } else {

            Nodo temp = maximo(raiz.getHizq());

            raiz.setDato(temp.getDato());

            raiz.setHizq(borrarAVL(raiz.getHizq(), temp.getDato()));
        }

        if (raiz == null) {
            return raiz;
        }

        int factorBalance = factorBalance(raiz);

        if (factorBalance == -2 && factorBalance(raiz.getHizq()) != 1) {
            return rotacionSimpleIzquierda(raiz);
        }
        if (factorBalance == 2 && factorBalance(raiz.getHder()) != -1) {
            return rotacionSimpleDerecha(raiz);
        }
        if (factorBalance == -2 && factorBalance(raiz.getHizq()) == 1) {
            return rotacionDobleIzquierda(raiz);
        }
        if (factorBalance == 2 && factorBalance(raiz.getHder()) == -1) {
            return rotacionDobleDerecha(raiz);
        }

        return raiz;
    }

    public int obtenerNivel(Nodo raiz, int key, int nivel) {
        if (raiz == null) {
            return 0;
        }
        if (raiz.getDato() == key) {
            return nivel;
        }

        int result = obtenerNivel(raiz.getHizq(), key, nivel + 1);
        if (result != 0) {
            // If found in left subtree , return 
            return result;
        }
        result = obtenerNivel(raiz.getHder(), key, nivel + 1);

        return result;
    }
}
