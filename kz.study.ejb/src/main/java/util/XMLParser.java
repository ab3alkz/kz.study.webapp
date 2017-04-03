/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Bekzhan
 */
public class XMLParser {

    public static Node getNode(String tagName, NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                return node;
            }
        }

        return null;
    }

    public static String getNodeValue(Node node) {
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node data = childNodes.item(i);
            if (data.getNodeType() == Node.TEXT_NODE) {
                return data.getNodeValue();
            }
        }
        return "";
    }

    public static String getNodeValue(String tagName, NodeList nodes) {
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node.getNodeName().equalsIgnoreCase(tagName)) {
                NodeList childNodes = node.getChildNodes();
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node data = childNodes.item(y);
                    if (data.getNodeType() == Node.TEXT_NODE) {
                        return data.getNodeValue();
                    }
                }
            }
        }
        return "";
    }
}
