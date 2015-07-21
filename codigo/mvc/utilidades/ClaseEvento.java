package mvc.utilidades;

public abstract interface ClaseEvento<Mensajero extends ClaseEvento, Destinatario extends ClaseEvento>
{
 //Métodos de envío y recepción de eventos
 public void enviarEvento(Destinatario destinatario); 
 public void recibirEvento(Evento<Mensajero,ClaseEvento> evento);
}