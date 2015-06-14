package mvc;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Controlador
{
 //ID de la vista que realiza la acción y el código junto con el posible contenido de la misma 
 protected String identificador = null;
 protected String accion = null;
 
 //Métodos para recoger controlar eventos Swing de las vistas
 protected abstract void ratonPulsado(MouseEvent evento);
 protected abstract void teclaPulsada(KeyEvent evento);
 
 //Getters
 public final String obtenerIdentificador()
 { return identificador; }
 public final String obtenerAccion()
 { return accion; }
}