package mvc.utilidades;

import java.io.IOException;
import java.util.HashSet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public abstract class BaseDatos 
{
 public static Document abrirTabla(String ruta)
 {
  try
  {
   DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
   DocumentBuilder constructor = fabrica.newDocumentBuilder();
   
   return constructor.parse(ruta);
  }
  catch(IOException | ParserConfigurationException | SAXException e)
  { 
   return null;
  }
 }
 
 public static HashSet<String> consultarTabla(Document tabla,String consulta)
 {
  try 
  {
   XPathExpression expresion = XPathFactory.newInstance().newXPath().compile(consulta);
   NodeList resultado = (NodeList) expresion.evaluate(tabla,XPathConstants.NODESET);
   
   HashSet<String> datos = new HashSet<String>();
   for(int i = 0; i < resultado.getLength(); i++)
   {
    Node padre = resultado.item(i).getParentNode();
     
    //Si es un nodo de datos se añade con ->, y si no tiene nodo padre entonces
    //es un atributo, por lo cual es el una referencia a un sub-objeto, entonces
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
  catch (XPathExpressionException ex) 
  { 
   return null; 
  }
 }
    
 //TODO: Averiguar cómo, mediante comandos, modificar la base de datos extraída
 //para luego sobreescribir esa parte en concreto de la base datos sin tener
 //que reemplazar el documento entero.
}
