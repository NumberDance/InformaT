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
   XPathFactory xPathfactory = XPathFactory.newInstance();
   XPath xpath = xPathfactory.newXPath();
   XPathExpression expr = xpath.compile(consulta);
   
   String resultado = expr.evaluate(tabla,XPathConstants.STRING).toString().trim();
   
   return resultado;
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
