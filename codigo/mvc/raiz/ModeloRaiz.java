package mvc.raiz;

import java.util.HashSet;
import java.util.Iterator;
import mvc.Modelo;
import mvc.utilidades.ClaseEvento;
import mvc.utilidades.Evento;
import mvc.utilidades.BaseDatos;
import pais.Pais;

public final class ModeloRaiz extends Modelo implements ClaseEvento<ControladorRaiz,VistaRaiz>  
{
 //Singleton de la Raiz y sus ramas
 private static final ModeloRaiz instancia = new ModeloRaiz();
 
 //Constructores
 private ModeloRaiz()
 { 
  super("Raiz"); 
 }
 
 //Vista
 public void cargar() 
 { 
  // Se debe conocer en todo momento si se están extrayendo datos simples o complejos
  // con @identificador: dato complejo
  // con text(): dato simple
  // BaseDatos se encarga de añadirles tanto el tipo de dato como el nombre del 
  // dato para ayudar a la Vista a distingir el tipo de petición.
  
  // NOTA: Para cargar todos los datos de un objeto a partir del identificador
  // /paises/Pais[@identificador='España']/*/text()
  HashSet<String> resultado = BaseDatos.leerTabla("base_datos/paises.xml","/paises/Pais[@identificador='España']/@identificador");
  Iterator<String> i = resultado.iterator();
  while(i.hasNext())
  {
   String siguiente = i.next();
   
   contenido.add(new Pais(siguiente.split("<>")[1],this));
   modificados.add(siguiente);
  }
  
  enviarEvento(VistaRaiz.obtenerInstancia());
  
  //Se limpian los modificados, ya que son en realidad los datos iniciales.
  modificados.clear();
 }
 protected void modificar(String accion) 
 {
  //TODO: Una vez el controlador entrege la petición de la vista al modelo del
  //contexto, el modelo de contexto tendrá que distinguir por el código de la 
  //acción si se vuelve a abrir un modelo ya existente o guardado, o se hace la 
  //petición de cargar uno nuevo. En el segundo caso, el modelo buscará entre
  //los datos por aquellos que tengan la relación <> de submodelo y lo creará
  //con dicho identificador. Luego se manda una señal para crear su respectiva
  //vista.
 }
    
 //Eventos
 public void enviarEvento(VistaRaiz destinatario) 
 {
  Evento<ModeloRaiz,VistaRaiz> evento = new Evento<ModeloRaiz,VistaRaiz>(this,destinatario);
  evento.enviar();
 }
 public void recibirEvento(Evento<ControladorRaiz,ClaseEvento> evento) 
 {
  ControladorRaiz emisor = evento.obtenerEmisor();
  buscarModelo(emisor.obtenerIdentificador(),emisor.obtenerAccion());
 }
 
 //Getters
 public static ModeloRaiz obtenerInstancia()
 { return instancia; }
}