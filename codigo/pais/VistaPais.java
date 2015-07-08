package pais;

import java.util.HashSet;
import mvc.Vista;

public class VistaPais extends Vista
{
 public VistaPais(String identificador,Vista marco)
 {
  super(identificador,marco);
  System.out.println("VistaPais creada: " + identificador);
 }
    
 protected void mostrar() 
 {}
 protected void actualizar(HashSet<String> modificados) 
 {}
}
