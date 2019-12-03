/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.nario;

import java.util.LinkedList;

/**
 *
 * @author wildg
 */
public class Nodo_Nario {
    private String info;
    private LinkedList<Nodo_Nario> hijos=null;

    public Nodo_Nario(String info) {
        this.info = info;
        hijos = new LinkedList();
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public LinkedList<Nodo_Nario> getHijos() {
        return hijos;
    }
     
    public void addHijo(String info){
        hijos.add(new Nodo_Nario(info));
    }
}
