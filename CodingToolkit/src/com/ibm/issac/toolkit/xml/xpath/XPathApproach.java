package com.ibm.issac.toolkit.xml.xpath;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ibm.issac.toolkit.DevLog;

/**
 * Approach to XML files with XPath.
 * 
 * @author issac
 * 
 */
public class XPathApproach extends AbstractApproach {
	private Document doc;

	public XPathApproach(String filename) {
		this.loadXML(filename);
	}

	/**
	 * Load xml for later usage.
	 * 
	 * @param filename
	 */
	private void loadXML(String filename) {
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			builderFactory.setNamespaceAware(true);
			DevLog.debug("Loading " + filename);
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			this.doc = builder.parse(filename);
			DevLog.debug("XML Loaded successfully.");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * get node list by xpath.
	 * 
	 * @param xpath
	 * @return
	 */
	public NodeList getNodeList(String xpath) {
		try {
			return (NodeList) XPathFactory.newInstance().newXPath().compile(xpath).evaluate(this.doc, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
