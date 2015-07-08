package pais;

import mvc.Modelo;

public class Pais extends Modelo
{
 public Pais(String identificador,Modelo contexto)
 {
  super(identificador,contexto);
  System.out.println("Pais creado: " + identificador);
 }
    
 protected void cargar() 
 {}
 protected void modificar(String accion) 
 {}
}
