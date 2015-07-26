package mvc.raiz;

import java.util.HashSet;
import java.util.Iterator;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import mvc.Vista;
import mvc.utilidades.ClaseEvento;
import mvc.utilidades.Evento;
import pais.VistaPais;

public final class VistaRaiz extends Vista implements ClaseEvento<ModeloRaiz,ControladorRaiz>
{
 //Singleton de la Raiz y sus ramas
 private static final VistaRaiz instancia = new VistaRaiz();

 //Constructores
 private VistaRaiz()
 {  
  super("Raiz");
 }
 
 //Modelo
 public void mostrar() 
 {   
  //La ventana de la aplicación
  JFrame ventana = new JFrame();
  ((JFrame)ventana).setTitle("InformaT");
  ((JFrame)ventana).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
  //El panel de tabs que contendrá todas las vistas
  JTabbedPane panel = new JTabbedPane();

  javax.swing.GroupLayout layout = new javax.swing.GroupLayout(ventana.getContentPane());
  ventana.getContentPane().setLayout(layout);        
  layout.setHorizontalGroup
  (
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
  );
  layout.setVerticalGroup
  (
   layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
   .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
  );
  
  ventana.pack();
  
  //Identificador del objeto y del elemento para el controlador
  ventana.setName("Raiz_ventana");
  panel.setName("Raiz_base");
  
  //Agregar los componentes
  componentes.add(ventana);
  componentes.add(panel);
  
  //Preparar el panel para ser controlado
  controlados.add(panel);
  
  enviarEvento(ControladorRaiz.obtenerInstancia());
  
  ventana.setVisible(true);
 }
 protected void actualizar(HashSet<String> modificados) 
 {
  //TODO: Recibido el array de modificados, esa vista oportuna determina si se
  //trata de actualizar una vista existente o se trata de crear y mostrar una 
  //nueva vista. Eso se distinge debido a que, si entre el array de modificados
  //hay un datos con la marca <>, son una petición de 
  //crear una subvista dentro de la del identificador que hace de marco. Si
  //hay datos simples, es una petición de actualización de la 
  //vista con ese identificador. Si una nueva vista es creada, tiene que enviar
  //un evento al controlador una vez se muestre para que pueda recojer las acciones
  //sobre la nueva.
  if(componentes.isEmpty())
  {
   mostrar();
  }

  //Se comprueba si hay datos complejos y en función de ello se crea las subvistas
  Iterator<String> i = modificados.iterator();
  while(i.hasNext())
  {
   String siguiente = i.next();
   if(siguiente.contains("Pais<>"))
   {
    elementos.add(new VistaPais(siguiente.split("<>")[1],this));
   }
   //Y sucesivamente para los demás tipos de componente que pueda haber.
  }
  
  //TODO: Actualizar los componentes de la vista en concreto
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
