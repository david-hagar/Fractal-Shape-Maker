/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import util.XMLUtils;
import util.deepCloneUtil;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class FractalShapeLibrary implements Serializable
{
	static final long serialVersionUID = -6217395552210469089L;

	public ArrayList<FractalShape> fractals = new ArrayList<FractalShape>();


	public FractalShapeLibrary()
	{
	}


	public void add(FractalShape s)
	{
		fractals.add(s);
	}


	public void duplicate(FractalShape s)
	{
		int i = fractals.indexOf(s);
		if (i == -1)
		{
			System.out.println("duplicate failed");
			return;
		}
		FractalShape dup = (FractalShape) deepCloneUtil.deepClone(s);
		if (dup == null)
		{
			System.out.println("duplicate failed (clone)");
			return;
		}
		fractals.add(i, dup);
	}


	private boolean isXML(File file)
	{
		return file.toString().endsWith(".xml");
	}

	private boolean isFSM(File file)
	{
		return file.toString().endsWith(".fsm");
	}


	public void load(File file) throws Exception
	{
//		if (isXML(file))
			loadXML(file);
//		else
//			loadSerialized(file);

	}


	public void save(File file) throws Exception
	{
//		if (isXML(file))
			saveXML(file);
//		else
//			saveSerialized(file);
	}


	private void loadSerialized(File file) throws Exception
	{
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		FractalShapeLibrary newLibrary = (FractalShapeLibrary) ois.readObject();

		fractals = newLibrary.fractals;

		fis.close();
		ois.close();

	}


	private void saveSerialized(File file) throws Exception
	{

		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);

		fos.close();
		oos.close();


	}


	void loadXML(File file) throws Exception
	{

		ArrayList<FractalShape> newFractals = new ArrayList<FractalShape>();

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// factory.setValidating(true);
		// factory.setNamespaceAware(true);

		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);

		Node libraryNode = null;
		NodeList nodeList = document.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("FractalShapeLibrary"))
			{
				libraryNode = node;
				break;
			} else
				System.out.println("unused node " + node);
		}

		if (libraryNode == null)
			throw new Exception("could not FractalShapeLibrary find  node");

		nodeList = libraryNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("FractalShape"))
				newFractals.add(new FractalShape(node));
			else
				System.out.println("unused node " + node);
		}

		fractals = newFractals;

	}


	void saveXML(File file) throws Exception
	{

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// factory.setValidating(true);
		// factory.setNamespaceAware(true);

		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.newDocument();

		Element root = document.createElement("FractalShapeLibrary");
		document.appendChild(root);

		// root.appendChild(document.createTextNode("Some"));
		// root.appendChild(document.createTextNode(" "));
		// root.appendChild(document.createTextNode("text"));

		for (FractalShape fs : fractals)
		{
			Element e = fs.toXML(document);
			root.appendChild(e);
		}


		FileOutputStream out = new FileOutputStream(file);
		OutputFormat format = new OutputFormat(document);
		format.setIndenting(true);
		//format.setLineSeparator("\r\n");
		XMLSerializer output = new XMLSerializer(out, format);
		output.serialize(document);
		out.flush();
		out.close();

	}


	/*
	 * public void verify() {
	 * 
	 * int size = fractals.size(); for(int i=0;i<size;i++) { FractalShape s =
	 * (FractalShape) fractals.get(i); if( s== null) { fractals.remove(i--);
	 * System.out.println( "null fractal found" ); } } }
	 */
}