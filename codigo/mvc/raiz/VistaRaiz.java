package mvc.raiz;

import java.util.HashSet;
import mvc.Vista;
import mvc.raiz.evento.ClaseEvento;
import mvc.raiz.evento.Evento;
import pais.partido.VistaPartido;

public final class VistaRaiz extends Vista implements ClaseEvento<ModeloRaiz,ControladorRaiz>
{
 //Singleton de la Raiz y sus ramas
 private static final VistaRaiz instancia = new VistaRaiz();
 private HashSet<VistaPartido> vista_partidos = null;

 //Constructores
 private VistaRaiz()
 {  
  super("Raiz");
 }
 
 //Modelo
 public void mostrar() 
 {
  //TODO: Aquí, usando las librerías de swing, se crearían los objetos de la 
  //vista y se añadirían posteriormente a la colección de componentes. Hecho 
  //esto, se manda un mensaje al controlador indicando los componentes que
  //interesa controlar desde allí. Para poner el código de acción, se usa el
  //método setName() de cada componente swing.
 }
 protected void actualizar(HashSet<String> modificados) 
 {
  //TODO: Recibido el array de modificados, esa vista oportuna determina si se
  //trata de actualizar una vista existente o se trata de crear y mostrar una 
  //nueva vista. Eso se distinge debido a que, si entre el array de modificados
  //hay un único dato con la marca <>, significa que es una petición de 
  //crear una subvista dentro de la del identificador que hace de marco. Si no 
  //hay ninguna y sólo datos simples, es una petición de actualización de la 
  //vista con ese identificador. Si una nueva vista es creada, tiene que enviar
  //un evento al controlador una vez se muestre para que pueda recojer las acciones
  //sobre la nueva.
 }
 
 //Eventos
 public void enviarEvento(ControladorRaiz destinatario) 
 {
  Evento<VistaRaiz,ControladorRaiz> evento = new Evento<VistaRaiz,ControladorRaiz>(this,destinatario);
  evento.enviar();
 }
 public void recibirEvento(Evento<ModeloRaiz,ClaseEvento> evento) 
 {
  ModeloRaiz emisor = evento.obtenerEmisor();
  buscarVista(emisor.obtenerIdentificador(),emisor.obtenerModificados());
 }   
 
 //Getters
 public static VistaRaiz obtenerInstancia()
 { return instancia; }
}
