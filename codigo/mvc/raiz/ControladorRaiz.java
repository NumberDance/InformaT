package mvc.raiz;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Iterator;
import mvc.Controlador;
import mvc.utilidades.ClaseEvento;
import mvc.utilidades.Evento;
import mvc.utilidades.ControlSwing;

public final class ControladorRaiz extends Controlador implements ClaseEvento<VistaRaiz,ModeloRaiz>
{   
 //Singleton del Controlador
 private static final ControladorRaiz instancia = new ControladorRaiz();
    
 //Control de Swing
 public void ratonPulsado(MouseEvent evento) 
 {
  identificador = ((Container)evento.getSource()).getName().split("_")[0];
  accion = ((Container)evento.getSource()).getName().split("_")[1] + "_Click_" + evento.getButton();
 
  enviarEvento(ModeloRaiz.obtenerInstancia());
 }
 public void teclaPulsada(KeyEvent evento) 
 {
  identificador = ((Container)evento.getSource()).getName().split("_")[0];
  accion = ((Container)evento.getSource()).getName().split("_")[1] + "_Tecla_" + evento.getKeyChar();
 
  enviarEvento(ModeloRaiz.obtenerInstancia());
 }
 
 //Eventos
 public void enviarEvento(ModeloRaiz destinatario) 
 {
  Evento<ControladorRaiz,ModeloRaiz> evento = new Evento<ControladorRaiz,ModeloRaiz>(this,destinatario);
  evento.enviar();
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
