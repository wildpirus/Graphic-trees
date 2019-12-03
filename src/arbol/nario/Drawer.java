package arbol.nario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;


public class Drawer extends JPanel {
    private final Arbol_Nario arbol;
    
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    
    private boolean dirty = true;
    
    private final int padreahijo = 40;
    private final int hijoahijo = 40;
    
    private final Dimension vacio = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    /**
     * Constructor de la clase ArbolExpresionGrafico.
     * El constructor permite inicializar los atributos de la clase ArbolExpresionGrafico
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     * @param arbol dato de tipo Arbol_Nario que contiene el Arbol a dibujar.
     */
    public Drawer(Arbol_Nario arbol) {
        this.arbol = arbol;
        this.setBackground(Color.WHITE);
        posicionNodos = new HashMap();
        subtreeSizes = new HashMap();
        dirty = true;
        repaint();      
    }


    /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void calcularPosiciones() {
        posicionNodos.clear();
        subtreeSizes.clear();
        Nodo_Nario raiz = this.arbol.getRaiz();
        if (raiz != null) {
            calcularTamañoSubarbol(raiz);
        }
    }
    
    /**
     * Calcula el tamaño de cada subárbol y lo agrega al objeto subtreeSizes de la clase
     * de tipo HashMap que va a contener la coleccion de todos los 
     * subárboles que contiene un arbol.
     * @param n:Objeto de la clase NodoB <T> que se utiliza como
     * referencia calcular el tamaño de cada subárbol.
     * @return Dimension con el tamaño de cada subárbol.
     */
    private Dimension calcularTamañoSubarbol(Nodo_Nario n) {
        if (n.getHijos().isEmpty()) {
            //subtreeSizes.put(n, new Dimension(hijoahijo ,fm.getHeight() + padreahijo));
            return new Dimension(hijoahijo ,fm.getHeight() + padreahijo);
        }
        
        ArrayList<Dimension> ds = new ArrayList();
        for (Nodo_Nario hijo : n.getHijos()) {
            ds.add(calcularTamañoSubarbol(hijo));
        }

        int maxHeight = 0, w = 0;
        for (Dimension d : ds) {
            maxHeight = Math.max(d.height,maxHeight);
            w += d.width;
        }
        w += (hijoahijo*(n.getHijos().size()-1));
        int h = fm.getHeight() + padreahijo + maxHeight;

        Dimension d = new Dimension(w, h);
        subtreeSizes.put(n, d);

        return d;
    }
    
    /**
     * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los 
     * subárboles calculadas anteriormente.
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el Nodo.
     * @param n: Objeto de la clase NodoB <T> que se utiliza como referencia para dibujar el árbol.
     * @param puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void dibujarArbol(Graphics g ,Nodo_Nario n, int x, int y){
        if (n == null) return;
        /*Dimension dd = (Dimension) subtreeSizes.get(n);
        if (dd == null)  dd = vacio;
        g.setColor(Color.RED);
        g.drawRect(x-(dd.width/2), y-14, dd.width, dd.height);*/
        
        int width = fm.stringWidth(n.getInfo()+"");
        g.setColor(Color.yellow);
        g.fillOval(x-((6+width)/2), y-14, 6+width, 20);
        g.setColor(Color.BLACK);
        g.drawString(n.getInfo(), x+1-(width/2), y);
        
        //g.drawRect(x, y-14, 0, 0);
        
        ArrayList<Dimension> ds = new ArrayList();
        for (Nodo_Nario hijo : n.getHijos()) {
            Dimension d = (Dimension) subtreeSizes.get(hijo);
            if (d == null)  d = vacio;
            ds.add(d);
        }
        
        int dh = (hijoahijo)*(n.getHijos().size()-1);
        int s=0;
        for (Dimension d : ds) {
            s += d.width;
        }
        int xoffset= (x-((dh+s)/2));
        for (Nodo_Nario hijo : n.getHijos()) {
            Dimension dm = (Dimension) subtreeSizes.get(hijo); 
            if (dm == null)  dm = vacio;
            int xx = xoffset+(dm.width/2);
            int w1 = fm.stringWidth(hijo.getInfo()+"");
            g.setColor(Color.BLACK);
            g.drawLine(x, y+6, xx, y + padreahijo+2);
            dibujarArbol(g,hijo,xx,y + fm.getHeight() + padreahijo);
            xoffset += dm.width+hijoahijo;
        }
    }
    

   /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        fm = g.getFontMetrics();

        if (dirty) {
          calcularPosiciones();
          dirty = false;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(getWidth()/2, 0);
        dibujarArbol(g, this.arbol.getRaiz(), 0, 20);
        fm = null;
    }
}




