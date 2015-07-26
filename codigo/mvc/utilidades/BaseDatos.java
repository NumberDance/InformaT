package mvc.utilidades;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import mvc.Modelo;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class BaseDatos 
{
 //Apertura de la tabla, sólo disponible localmente.
 private static Document abrirTabla(String ruta) throws IOException, ParserConfigurationException, SAXException 
 {
  DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
  DocumentBuilder constructor = fabrica.newDocumentBuilder();
   
  return constructor.parse(ruta);
 } 
 
 
 //Lectura de la tabla.
 public static HashSet<String> leerTabla(String ruta_tabla,String consulta)
 {   
  try 
  {   
   Document tabla = abrirTabla(ruta_tabla);
      
   XPathExpression expresion = XPathFactory.newInstance().newXPath().compile(consulta);
   NodeList resultado = (NodeList) expresion.evaluate(tabla,XPathConstants.NODESET);
   
   HashSet<String> datos = new HashSet<String>();
   for(int i = 0; i < resultado.getLength(); i++)
   {
    Node padre = resultado.item(i).getParentNode();
     
    //Si es un nodo de datos se añade con ->, y si no tiene nodo padre entonces
    //es un atributo, por lo cual es una referencia a un sub-objeto, entonces
    //se añade <> y se obtiene el nodo propietario del atributo, de manera que
    //la vista pueda los distinguir.
    
    //AVISO: Aún no se soporta la extracción de nodos enteros.
    if(padre == null)
    {
     String propietario = ((Attr)resultado.item(i)).getOwnerElement().getNodeName();
     String dato_complejo = propietario + "<>" + resultado.item(i).getTextContent();
     
     datos.add(dato_complejo.replaceAll("\n","").replace(" ",""));
    }
    else
    {
     String contenido = resultado.item(i).getTextContent();
     String dato_simple = padre.getNodeName() + "->" + contenido;
     
     datos.add(dato_simple.replaceAll("\n","").replace(" ",""));
    }
   }
   
   return datos;
  } 
  catch(IOException | ParserConfigurationException | SAXException e)
  {
   System.out.println("ERROR: No se ha podido abrir el DOM.");
   System.exit(1);
   return null;
  }
  catch (XPathExpressionException e) 
  { 
   System.out.println("ERROR: No se ha podido ejecutar el XPath.");
   System.exit(1);
   return null; 
  }
 }
 //TODO: Escritura de la tabla.
 public static void escribirTabla4(String ruta_tabla, Modelo modelo)
 {
  try
  {
   Document tabla = abrirTabla(ruta_tabla);
   
   XPathExpression expresion = XPathFactory.newInstance().newXPath().compile("//");
   NodeList resultado = (NodeList) expresion.evaluate(tabla,XPathConstants.NODESET);
   
   Iterator<String> i = modelo.obtenerModificados().iterator();
   while(i.hasNext())
   {
    
   }
  }
  catch(IOException | ParserConfigurationException | SAXException e)
  {
   System.out.println("ERROR: No se ha podido abrir el DOM.");
   System.exit(1);
  }
  catch (XPathExpressionException e) 
  { 
   System.out.println("ERROR: No se ha podido ejecutar el XPath.");
   System.exit(1); 
  }
 }
}
