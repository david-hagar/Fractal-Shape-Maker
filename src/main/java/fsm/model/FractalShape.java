/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.model;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fsm.shape.ElementShape;

import util.XMLUtils;


public class FractalShape implements Serializable
{

	static final long serialVersionUID = 3557274722018507994L;

	public ArrayList<ElementShape> elementShapes = new ArrayList<ElementShape>();
	public ArrayList<TransformationSequence> transforms = new ArrayList<TransformationSequence>();
	public int level = 99;
	public Color background = Color.black;
	transient public Rectangle2D.Double bounds = new Rectangle2D.Double(-2.0,
			-2.0, 4.0, 4.0);
	private TransformationSequence initialTF = null;
	public boolean autoBounds = true;


	// public float hueIncrement = 0.0f;

	public FractalShape()
	{
	}


	public FractalShape(Node fsNode) throws Exception
	{
		NamedNodeMap attr = fsNode.getAttributes();
		level = Integer.parseInt(attr.getNamedItem("level").getNodeValue());
		background = XMLUtils.fromXMLString(attr.getNamedItem("background")
				.getNodeValue());

		System.out.println(level + " " + background);

		NodeList nodeList = fsNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("ElementShapes"))
			{
				loadElementShapes(node);
			} else if (node.getNodeName().equals("Transforms"))
			{
				loadTransforms(node);
			} else
				System.out.println("unused node " + node);
		}

		int initialTFIndex = Integer.parseInt(attr.getNamedItem("initialTFIndex")
				.getNodeValue());
		initialTF = transforms.get(initialTFIndex);

	}


	private void loadTransforms(Node tNode)
	{
		NodeList nodeList = tNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("TransformationSequence"))
			{
				transforms.add(new TransformationSequence(node));
			} else
				System.out.println("unused node " + node);
		}
	}


	private void loadElementShapes(Node eNode)
	{
		NodeList nodeList = eNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("ElementShape"))
			{
				elementShapes.add(new ElementShape(node, transforms));
			} else
				System.out.println("unused node " + node);
		}
	}


	public void add(ElementShape shape)
	{
		elementShapes.add(shape);
	}


	public void add(TransformationSequence tf)
	{
		transforms.add(tf);
	}


	public Rectangle2D.Double getShapeBounds()
	{
		Rectangle2D.Double rUnion = null;
		Iterator i = elementShapes.iterator();
		if (i.hasNext())
			rUnion = ((ElementShape) i.next()).getBounds();
		while (i.hasNext())
		{
			Rectangle2D r = ((ElementShape) i.next()).getBounds();
			Rectangle2D.union(rUnion, r, rUnion);
		}

		return rUnion;
	}


	public Object clone()
	{
		try
		{
			ElementShape obj = (ElementShape) super.clone();
			// obj. = .clone();
			return obj;
		} catch (CloneNotSupportedException e)
		{
			System.out.println("clone failed");
		}

		return null;
	}


	public void setInitialTF(TransformationSequence tf)
	{
		initialTF = tf;
		// if( tf.color != null)
		// background = tf.color;
	}


	public TransformationSequence getInitialTF()
	{
		return initialTF;
	}


	private void addTransformationsIfNeeded()
	{
		{
			initialTF.recurse = false;
			initialTF.name = "Inital Transformation";
			int index = transforms.indexOf(initialTF);
			if (index == -1)
			{
				transforms.add(initialTF);
			}
		}

		for (ElementShape elementShape : elementShapes)
		{
			TransformationSequence s = elementShape.baseTransform;

			s.recurse = false;
			s.name = "Shape Transformation";
			int index = transforms.indexOf(s);
			if (index == -1)
			{
				transforms.add(s);
			}
		}


	}


	public Element toXML(Document document) throws Exception
	{
		addTransformationsIfNeeded();
		Element fractalShapeNode = document.createElement("FractalShape");

		fractalShapeNode.setAttribute("level", Integer.toString(level));
		fractalShapeNode.setAttribute("background", XMLUtils
				.toXMLString(background));

		Element transformsNode = document.createElement("Transforms");
		fractalShapeNode.appendChild(transformsNode);

		for (TransformationSequence ts : transforms)
		{
			Element e = ts.toXML(document);
			transformsNode.appendChild(e);
		}


		Element elementShapesNode = document.createElement("ElementShapes");
		fractalShapeNode.appendChild(elementShapesNode);

		for (ElementShape elementShape : elementShapes)
		{
			Element e = elementShape.toXML(document, transforms);
			elementShapesNode.appendChild(e);
		}


		int index = transforms.indexOf(initialTF);
		if (index == -1)
			throw new Exception("Could not find initial shape transformation");

		fractalShapeNode.setAttribute("initialTFIndex", Integer.toString(index));

		return fractalShapeNode;
	}


	public boolean getLastOnly()
	{
		if (elementShapes.isEmpty())
			return false;

		return elementShapes.get(0).onlyDrawLast;
	}


	public void setLastOnly(boolean lastOnly)
	{
		for (ElementShape es : elementShapes)
		{
			es.onlyDrawLast = lastOnly;
		}
	}


}