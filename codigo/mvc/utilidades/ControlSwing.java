package mvc.utilidades;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import mvc.raiz.ControladorRaiz;

public final class ControlSwing implements KeyListener,MouseListener
{
 private static final ControlSwing instancia = new ControlSwing();
    
 //Eventos de ratón
 public void mouseClicked(MouseEvent evento_click)
 { 
  ControladorRaiz.obtenerInstancia().ratonPulsado(evento_click); 
 }
 public void mouseEntered(MouseEvent evento_enfocado){}
 public void mouseExited(MouseEvent evento_desenfocado){}
 public void mousePressed(MouseEvent evento_mantenido){}
 public void mouseReleased(MouseEvent evento_liberado){}

 //Eventos de teclado
 public void keyTyped(KeyEvent evento_pulsado)
 {
  ControladorRaiz.obtenerInstancia().teclaPulsada(evento_pulsado);
 }
 public void keyPressed(KeyEvent evento_mantenido){}
 public void keyReleased(KeyEvent evento_liberado){}
 
 //Getters
 public static ControlSwing obtenerInstancia()
 { return instancia; }
}