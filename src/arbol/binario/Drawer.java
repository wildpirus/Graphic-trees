/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbol.binario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author wildg
 */
public class Drawer extends JPanel{
    
    private AB Arbol;
    private boolean dirty = true;
    private int parent2child = 20, child2child = 30;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;

    public Drawer(AB arbol) {
        this.Arbol = arbol;
        this.setBackground(Color.WHITE);
        dirty = true;
        repaint();
    }
    
    private void actualizarArbol(Graphics g, int puntox, int puntoy, int yoffs){
        Nodo root = Arbol.getRaiz();
        if (root != null){
            dibujarNodo(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0, g, puntox, puntoy, yoffs);
        }
    }
    
    private void dibujarNodo(Nodo n, int left, int right, int top,Graphics g, int puntox, int puntoy, int yoffs){
        if (n == null) return;
        Dimension ld = (Dimension) calcularTamañoSubarbol(n.getHizq());
        if (ld == null) ld = empty;

        Dimension rd = (Dimension) calcularTamañoSubarbol(n.getHder());
        if (rd == null)  rd = empty;

        int center = 0;

        if (right != Integer.MAX_VALUE)
            center = right - rd.width - child2child/2;
        else if (left != Integer.MAX_VALUE)
            center = left + ld.width + child2child/2;
        int width = fm.stringWidth(n.getDato()+"");

        Rectangle r = (Rectangle) new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight());
        
        java.awt.Font currentFont =  new java.awt.Font ("Tahoma", 0,9);
        g.setFont(currentFont);
        //g.drawString(Integer.toString(Arbol.), r.x+r.width, r.y+(r.height/2));
        currentFont =  new java.awt.Font ("Tahoma", 0,14);
        g.setFont(currentFont);
        
        g.setColor(Color.yellow);
        g.fillOval(r.x, r.y, r.width, r.height);
        g.setColor(Color.BLACK);
        g.drawString(n.getDato()+"", r.x + 3, r.y + yoffs);
        if (puntox != Integer.MAX_VALUE)
        g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
        
        dibujarNodo(n.getHizq(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child,g , (int)(r.x + r.width/2), r.y + r.height, yoffs);
        dibujarNodo(n.getHder(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child,g , (int)(r.x + r.width/2), r.y + r.height, yoffs);
    }
    
    private Dimension calcularTamañoSubarbol(Nodo n){
        if (n == null) return new Dimension(0,0);
        Dimension ld = calcularTamañoSubarbol(n.getHizq());
        Dimension rd = calcularTamañoSubarbol(n.getHder());
        int h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
        int w = ld.width + child2child + rd.width;
        Dimension d = new Dimension(w, h);
        return d;
    }
    
    /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        fm = g.getFontMetrics();
        if (dirty){
           dirty = false;
         }
        g.translate(getWidth() / 2, parent2child);
        actualizarArbol(g, Integer.MAX_VALUE, Integer.MAX_VALUE, fm.getLeading() + fm.getAscent());
        fm = null;
   }    
}
