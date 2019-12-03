/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.binario;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author wildg
 */
public abstract class AB {
    
    protected Nodo Raiz;

    public AB() {
        Raiz = null;
    }
    
    public Nodo getRaiz() {
        return Raiz;
    }
    
    //cantidad de niveles que posee el arbol
    public int alturaArbol(Nodo nodo) {
        if (nodo == null) {
            return -1;
        } else {
            return 1 + Math.max(alturaArbol(nodo.getHizq()), alturaArbol(nodo.getHder()));
        }
    }
    
    //cantidad de nodos que posee el arbol	
    public int tamañoArbol(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else {
            return 1 + tamañoArbol(nodo.getHizq()) + tamañoArbol(nodo.getHder());
        }
    }

    //cantidad de nodos hojas que posee el arbol	
    public int cantHojas(Nodo nodo) {
        if (nodo == null) {
            return 0;
        } else if (nodo.getHizq() == null && nodo.getHder() == null) {
            return 1;
        } else {
            return cantHojas(nodo.getHizq()) + cantHojas(nodo.getHder());
        }
    }

    //cantidad de nodos completos que posee el arbol	
    public int cantCompletos(Nodo nodo) {
        int cont = 0;
        if (nodo != null) {
            cont = cantCompletos(nodo.getHizq()) + cantCompletos(nodo.getHder());
            if (nodo.getHizq() != null && nodo.getHder() != null) {
                cont = cont + 1;
            }
        }
        return cont;
    }

    //Busca un elemento en el arbol
    public void buscarNodo(int Elem, Nodo A) {
        if (A == null) {
            System.out.println("no");
        } else if (A.getDato() == Elem) {
            System.out.println("encon");
        } else if (Elem > A.getDato()) {
            buscarNodo(Elem, A.getHder());
        } else {
            buscarNodo(Elem, A.getHizq());
        } 
    }
    
    
    public Nodo maximo(Nodo raiz) {
        if (raiz.getHder() == null) {
            return raiz;
        } else {
            return maximo(raiz.getHder());
        }
    }
    
    public void borrarArbol(){
        this.Raiz=null;
    }
    
    //Preorden Recursivo del arbol
    public void preorden(Nodo nodo, ArrayList salida) {
        if (nodo == null) {
            return;
        } else {
            salida.add(nodo.getDato());
            preorden(nodo.getHizq(),salida);
            preorden(nodo.getHder(),salida);
        }
    }

    //PostOrden recursivo del arbol
    public void postOrden(Nodo nodo, ArrayList salida) {
        if (nodo == null) {
            return;
        } else {
            postOrden(nodo.getHizq(),salida);
            postOrden(nodo.getHder(),salida);
            salida.add(nodo.getDato());
        }
    }

    //Inorden Recursivo del arbol
    public void inorden(Nodo nodo, ArrayList salida) {
        if (nodo == null) {
            return ;
        } else {
            inorden(nodo.getHizq(),salida);
            salida.add(nodo.getDato());
            inorden(nodo.getHder(),salida);
        }
    }
    
    public void escNiveles(ArrayList salida){ 
        int h = height(Raiz); 
        int i; 
        for (i=1; i<=h; i++) printGivenLevel(Raiz, i,salida); 
    }
    
    private int height(Nodo root){ 
        if (root == null) 
           return 0; 
        else
        { 
            /* compute  height of each subtree */
            int lheight = height(root.getHizq()); 
            int rheight = height(root.getHder()); 
              
            /* use the larger one */
            if (lheight > rheight) 
                return(lheight+1); 
            else return(rheight+1);  
        } 
    }
    
    private void printGivenLevel (Nodo root ,int level,ArrayList salida){ 
        if (root == null) 
            return; 
        if (level == 1) 
            salida.add(root.getDato());
        else if (level > 1) 
        { 
            printGivenLevel(root.getHizq(), level-1,salida); 
            printGivenLevel(root.getHder(), level-1,salida); 
        } 
    }
    
    public JPanel getdibujo() {
        return new Drawer(this);
    }
    
    public abstract void insertarNodo(int elem);
    public abstract void borrarClave(int elem);
}
