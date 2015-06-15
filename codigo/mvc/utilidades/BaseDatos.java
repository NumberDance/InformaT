package mvc.utilidades;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
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
 
 public static String consultarTabla(Document tabla,String consulta)
 {
  try 
  {
   XPathFactory fabrica = XPathFactory.newInstance();
   XPath compilador = fabrica.newXPath();
   XPathExpression expresion = compilador.compile(consulta);
   NodeList extraccion = (NodeList) expresion.evaluate(tabla,XPathConstants.NODESET);
   
   String resultado = "";
   for(int i = 0; i < extraccion.getLength(); i++)
   {
    String tag = extraccion.item(i).getParentNode().getNodeName();
    String contenido = extraccion.item(i).getTextContent();
       
    resultado = resultado + tag + "->" + contenido + "|";
   }
   
   return resultado.replaceAll("\n","").replace(" ","");
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
