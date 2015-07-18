package mvc.raiz;

import java.util.HashSet;
import java.util.Iterator;
import mvc.Modelo;
import mvc.raiz.evento.ClaseEvento;
import mvc.raiz.evento.Evento;
import mvc.utilidades.BaseDatos;
import org.w3c.dom.Document;
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
  //Se conoce en todo momento si se están extrayendo datos simples o complejos
  // con @identificador: dato complejo
  // con text(): dato simple
  // BaseDatos se encarga de añadirles tanto el tipo de dato como el nombre del 
  // dato para ayudar a la Vista a distingir el tipo de petición.
  Document tabla = BaseDatos.abrirTabla("base_datos/paises.xml");
  
  HashSet<String> resultado = BaseDatos.consultarTabla(tabla,"/paises/Pais/@identificador");
  Iterator<String> i = resultado.iterator();
  while(i.hasNext())
  {
   String siguiente = i.next();
   
   contenido.add(new Pais(siguiente.split("<>")[1],this));
   modificados.add(siguiente);
  }
     
  enviarEvento(VistaRaiz.obtenerInstancia());
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
  System.out.println("Recibido");
 }
    
 //Eventos
 public void enviarEvento(VistaRaiz destinatario) 
 {
  Evento<ModeloRaiz,VistaRaiz> evento = new Evento<ModeloRaiz,VistaRaiz>(this,destinatario);
  evento.enviar();
  
  modificados.clear();
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