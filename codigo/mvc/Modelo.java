package mvc;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Modelo 
{
 //ID
 protected String identificador = null;
 
 //Relaciones de composición
 protected Modelo contexto = null;
 protected final HashSet<Modelo> contenido = new HashSet<Modelo>();
 
 //Coleción de datos extraídos de la base de datos XML y una subcolección para leer desde eventos
 protected final HashSet<String> datos = new HashSet<String>();
 protected final HashSet<String> modificados = new HashSet<String>();
 
 //Constructores
 protected Modelo(String identificador)
 { 
  this.identificador = identificador; 
 }
 protected Modelo(String identificador,Modelo contexto)
 {
  this.identificador = identificador;
  this.contexto = contexto;
 }
 
 //Métodos generales del modelo
 protected abstract void cargar();
 protected void buscarModelo(String identificador, String accion)
 {
  if(this.identificador.equals(identificador))
  { 
   modificar(accion); 
  }
  else if(contenido != null)
  {
   Iterator<Modelo> i = contenido.iterator();
   while(i.hasNext())
   { 
    i.next().buscarModelo(identificador, accion); 
   }
  }
 }
 protected abstract void modificar(String accion);
 
 //Getters
 public final String obtenerIdentificador()
 { return identificador; }
 public final HashSet<String> obtenerModificados()
 { return modificados; }
}