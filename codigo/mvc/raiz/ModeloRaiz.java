package mvc.raiz;

import java.util.HashSet;
import mvc.Modelo;
import mvc.raiz.evento.ClaseEvento;
import mvc.raiz.evento.Evento;
import pais.partido.Partido;

public final class ModeloRaiz extends Modelo implements ClaseEvento<ControladorRaiz,VistaRaiz>  
{
 //Singleton de la Raiz y sus ramas
 private static final ModeloRaiz instancia = new ModeloRaiz();
 private HashSet<Partido> partidos = null;
 
 //Constructores
 private ModeloRaiz()
 { 
  super("Raiz"); 
 }
 
 //Vista
 public void cargar() 
 {
  //TODO: Una vez definida la base de datos, extraer los elementos y hacer una relación.
  //<xsd:simpleType>
  //[Nodo]->[Valor]
  //Los atributos compuestos para crear submodelos serán los únicos nodos con un atributo.
  //<xsd:complexType>
  //[Nodo]<>[Atributo == Identificador]
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