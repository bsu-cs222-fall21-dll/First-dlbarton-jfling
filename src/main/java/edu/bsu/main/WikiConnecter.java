package edu.bsu.main;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class WikiConnecter {
    public static void main(String[] args)
            throws IOException, ParserConfigurationException, SAXException, TransformerException {
        URLConnection connection = connectToWikipedia();
        Document document = readXmlDocumentFrom(connection);
        printDocument(document);
    }

    private static URLConnection connectToWikipedia() throws IOException {
        URL url = new URL(
                "https://en.wikipedia.org/w/api.php?action=query&prop=revisions&format=xml&rvprop=timestamp%7Cuser&rvlimit=4&titles=frank%20zappa&redirects=");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "CS222FirstProject/0.1 (dllargent@bsu.edu)");
        connection.connect();
        return connection;
    }

    private static Document readXmlDocumentFrom(URLConnection connection) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        return (Document) documentBuilder.parse(connection.getInputStream());
    }

    private static void printDocument(Document doc) throws TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(new DOMSource((Node) doc), new StreamResult(new OutputStreamWriter(System.out, StandardCharsets.UTF_8)));
    }

}
