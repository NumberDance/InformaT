package mvc.utilidades;

import mvc.utilidades.ClaseEvento;

public final class Evento<Emisor extends ClaseEvento,Receptor extends ClaseEvento>
{
 //Tipos genéricos sobre el emisor del evento y su receptor
 private Emisor emisor = null;
 private Receptor receptor = null;
 
 //Constructores
 public Evento(Emisor emisor, Receptor receptor)
 {
  this.emisor = emisor;
  this.receptor = receptor;
 }
 
 //Conexión
 public void enviar()
 { 
  receptor.recibirEvento(this); 
 }
 
 //Getters
 public Emisor obtenerEmisor()
 { return emisor; }
}