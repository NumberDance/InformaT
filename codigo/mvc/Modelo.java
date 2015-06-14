package mvc;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Modelo 
{
 //ID
 protected String identificador = null;
 
 //Relaciones de composición
 protected Modelo contexto = null;
 protected HashSet<Modelo> contenido = null;
 
 //Coleción de datos extraídos de la base de datos XML y una subcolección para leer desde eventos
 protected HashSet<String> datos = null;
 protected HashSet<String> modificados = null;
 
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
 protected void guardar()
 {
  //TODO: Hacer una consulta para modificar los datos en la copia y reemplazar
  //el fichero actual por la base de datos modificada. Si no se han hecho cambios,
  //se comprueba y no se guarda.
     
  if(contenido != null)
  {
   Iterator<Modelo> i = contenido.iterator();
   while(i.hasNext())
   { 
    i.next().guardar(); 
   }
  }
 }
 
 //Getters
 public final String obtenerIdentificador()
 { return identificador; }
 public final HashSet<String> obtenerModificados()
 { return modificados; }
}