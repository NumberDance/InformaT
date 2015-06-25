package mvc.raiz;

import java.util.HashSet;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;
import mvc.Vista;
import mvc.raiz.evento.ClaseEvento;
import mvc.raiz.evento.Evento;
import pais.VistaPais;

public final class VistaRaiz extends Vista implements ClaseEvento<ModeloRaiz,ControladorRaiz>
{
 //Singleton de la Raiz y sus ramas
 private static final VistaRaiz instancia = new VistaRaiz();
 private HashSet<VistaPais> vistas_paises = null;

 //Constructores
 private VistaRaiz()
 {  
  super("Raiz");
 }
 
 //Modelo
 public void mostrar() 
 {   
  JFrame ventana = new JFrame();
  ((JFrame)ventana).setName("ventana");
  ((JFrame)ventana).setTitle("InformaT");
  ((JFrame)ventana).setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     
  JTabbedPane panel = new JTabbedPane();
  panel.setName("base");

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
  
  componentes.add(ventana);
  componentes.add(panel);
  
  controlados.add(panel);
  
  enviarEvento(ControladorRaiz.obtenerInstancia());
  
  ventana.setVisible(true);
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
     
  if(modificados.isEmpty())
  {
   mostrar();
  }
 }
 
 //Eventos
 public void enviarEvento(ControladorRaiz destinatario) 
 {
  Evento<VistaRaiz,ControladorRaiz> evento = new Evento<VistaRaiz,ControladorRaiz>(this,destinatario);
  evento.enviar();
  
  //Los controlados nunca se deben borrar.
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
