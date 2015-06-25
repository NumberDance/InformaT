package mvc.raiz;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import mvc.Controlador;
import mvc.raiz.evento.ClaseEvento;
import mvc.raiz.evento.Evento;
import mvc.utilidades.ControlSwing;

public final class ControladorRaiz extends Controlador implements ClaseEvento<VistaRaiz,ModeloRaiz>
{   
 //Singleton del Controlador
 private static final ControladorRaiz instancia = new ControladorRaiz();
    
 //Control de Swing
 public void ratonPulsado(MouseEvent evento) 
 {}
 public void teclaPulsada(KeyEvent evento) 
 {}
 
 //Eventos
 public void enviarEvento(ModeloRaiz destinatario) 
 {
  Evento<ControladorRaiz,ModeloRaiz> evento = new Evento<ControladorRaiz,ModeloRaiz>(this,destinatario);
  evento.enviar();
  
  identificador = null;
  accion = null;
 }
 public void recibirEvento(Evento<VistaRaiz,ClaseEvento> evento) 
 {
  HashSet<Container> controlados = evento.obtenerEmisor().obtenerControlados();
  
  Iterator<Container> i = controlados.iterator();
  while(i.hasNext())
  {
   Container controlado = i.next();
   
   controlado.addMouseListener(ControlSwing.obtenerInstancia());
   controlado.addKeyListener(ControlSwing.obtenerInstancia());
  }
 }
 
 //Getters
 public static ControladorRaiz obtenerInstancia()
 { return instancia; }
}
