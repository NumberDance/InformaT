package informat;

/* 
  INICIO
  
  El modelo raiz carga los datos de la raiz, y los envía a la vista raiz 
  para que los actualice a través de un evento. Cuando la vista reciba ese evento,
  se mostrará, y luego el evento la actualizará con el array de datos recibido.
  
  CASO DE USO
     
  Cada vez que el usuario seleccione un identificador de un contenido en 
  la vista correspondiente a su contexto, el contexto la busca en sus datos y, 
  en caso de coincidir con alguno de los identificadores de su contenido, mediante 
  una consulta a la base de datos creará el modelo correspondiente. 
     
  Una vez creado, ese modelo envía un evento a la vista raiz con su identificador. 
  La vista lo crea, buscando y creándolo en la vista de su contexto en caso de ser 
  un modelo compuesto, y dentro de la vista principal si se trata de un modelo
  que es contenido directo de la raiz.
*/

import mvc.raiz.ModeloRaiz;

public class InformaT 
{
 public static void main(String[] args) 
 {
  ModeloRaiz.obtenerInstancia().cargar();
 }  
}
