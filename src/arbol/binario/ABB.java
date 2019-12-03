package arbol.binario;


import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author wildg
 */
public class ABB extends AB{

    public ABB() {
        super();
    }

    //Insercion de un elemento en el arbol
    @Override
    public void insertarNodo(int Elem) {
        // This method mainly calls insert() 
        Raiz = insert(Raiz, Elem);
    }
    
    private Nodo insert(Nodo root, int key) { 
        /* If the tree is empty, return a new node */
        if (root == null) { 
            root = new Nodo(key); 
            return root; 
        }
        if (key < root.getDato()) 
            root.setHizq(insert(root.getHizq(), key)); 
        else if (key > root.getDato()) 
            root.setHder(insert(root.getHder(), key)); 
        /* return the (unchanged) node pointer */
        return root; 
    } 

    @Override
    public void borrarClave(int key) { 
        Raiz = deleteRec(Raiz, key); 
    } 
    
    private int minValue(Nodo root) { 
        int minv = root.getDato(); 
        while (root.getHizq() != null) 
        { 
            minv = root.getHizq().getDato(); 
            root = root.getHizq(); 
        } 
        return minv; 
    } 
  
    private Nodo deleteRec(Nodo root, int key) { 
        if (root == null)  return root; 
        if (key < root.getDato()) 
            root.setHizq(deleteRec(root.getHizq(), key)); 
        else if (key > root.getDato()) 
            root.setHder(deleteRec(root.getHder(), key)); 
        else { 
            if (root.getHizq() == null) 
                return root.getHder(); 
            else if (root.getHder() == null) 
                return root.getHizq(); 
            root.setDato(minValue(root.getHder())); 
            root.setHder(deleteRec(root.getHder(), root.getDato())); 
        } 
        return root; 
    }   
}
