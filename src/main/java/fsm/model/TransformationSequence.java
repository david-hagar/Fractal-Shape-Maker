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
import java.awt.geom.AffineTransform;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import fsm.transformation.Transformation;
import fsm.transformation.TransformationRotate;
import fsm.transformation.TransformationScale;
import fsm.transformation.TransformationScaleXY;
import fsm.transformation.TransformationTranslate;

import util.XMLUtils;


public class TransformationSequence implements Serializable
{
	static final long serialVersionUID = 8624414620523641092L;

	public AffineTransform totalTransform = new AffineTransform();
	public ArrayList<Transformation> transforms = new ArrayList<Transformation>();
	public Color color = null;
	public float hueDelta = 0.0f;
	public String name = "No Name";
	public boolean recurse = false;


	public TransformationSequence()
	{
	}


	public TransformationSequence(Node tsNode)
	{
		NamedNodeMap attr = tsNode.getAttributes();
		color = XMLUtils.fromXMLString(attr.getNamedItem("color").getNodeValue());
		hueDelta = Float.parseFloat(attr.getNamedItem("hueDelta").getNodeValue());
		name = attr.getNamedItem("name").getNodeValue();
		recurse = Boolean.parseBoolean(attr.getNamedItem("recurse").getNodeValue());

		System.out.println(color + " " + hueDelta + " " + name + " " + recurse);


		NodeList nodeList = tsNode.getChildNodes();
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			XMLUtils.printType(node);

			if (node.getNodeName().equals("TransformationRotate"))
			{
				transforms.add(new TransformationRotate(node));
			} else if (node.getNodeName().equals("TransformationScale"))
			{
				transforms.add(new TransformationScale(node));
			} else if (node.getNodeName().equals("TransformationScaleXY"))
			{
				transforms.add(new TransformationScaleXY(node));
			} else if (node.getNodeName().equals("TransformationTranslate"))
			{
				transforms.add(new TransformationTranslate(node));
			} else
				System.out.println("unused node " + node);
		}
	}


	public void recalcTotal()
	{
		totalTransform = new AffineTransform();
		Iterator i = transforms.iterator();
		while (i.hasNext())
			totalTransform.concatenate(((Transformation) i.next()).transform);

		// System.out.println("mat = " + totalTransform);
	}


	public void add(Transformation t)
	{
		transforms.add(t);
	}


	public Element toXML(Document document)
	{
		Element e = document.createElement("TransformationSequence");
		e.setAttribute("color", XMLUtils.toXMLString(color));
		e.setAttribute("hueDelta", Float.toString(hueDelta));
		e.setAttribute("name", name);
		e.setAttribute("recurse", Boolean.toString(recurse));

		for (Transformation t : transforms)
		{
			e.appendChild(t.toXML(document));
		}

		return e;
	}


}