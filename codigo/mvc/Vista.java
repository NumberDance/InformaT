package mvc;

import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JComponent;

public abstract class Vista 
{
 //ID
 protected String identificador = null;
 
 //Relaciones de composición
 protected Vista marco = null;
 protected HashSet<Vista> elementos = null;
 
 //Colección de componentes gráficos de Swing de la vista y una subcolección para leer desde eventos
 protected HashSet<JComponent> componentes = null;
 protected HashSet<JComponent> controlados = null;
 
 //Constructores
 protected Vista(String identificador)
 { 
  this.identificador = identificador; 
 }
 protected Vista(String identificador,Vista marco)
 {
  this.identificador = identificador;
  this.marco = marco;
 }
 
 //Métodos generales de la vista
 protected abstract void mostrar();
 protected void buscarVista(String identificador, HashSet<String> modificados)
 {
  if(this.identificador.equals(identificador))
  { 
   actualizar(modificados); 
  }
  else if(elementos != null)
  {
   Iterator<Vista> i = elementos.iterator();
   while(i.hasNext())
   { 
    i.next().buscarVista(identificador, modificados); 
   }
  }
 }
 protected abstract void actualizar(HashSet<String> modificados);
 protected void ocultar()
 {
  if(componentes != null)
  {
   Iterator<JComponent> i = componentes.iterator();
   while(i.hasNext())
   { 
    i.next().setVisible(false); 
   }
  }
     
  if(elementos != null)
  {
   Iterator<Vista> i = elementos.iterator();
   while(i.hasNext())
   { 
    i.next().ocultar(); 
   }
  }
 }
 
 //Getters
 public final String obtenerIdentificador()
 { return identificador; }
 public final HashSet<JComponent> obtenerControlados()
 { return controlados; }
}