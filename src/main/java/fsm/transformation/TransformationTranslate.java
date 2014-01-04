/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.transformation;

import java.io.Serializable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


public class TransformationTranslate extends Transformation implements
		Serializable
{
	static final long serialVersionUID = -6344198539216959341L;

	public double transX = 0.0;
	public double transY = 0.0;


	public TransformationTranslate()
	{
	}


	public TransformationTranslate(Node node)
	{
		NamedNodeMap attr = node.getAttributes();
		transX = Float.parseFloat(attr.getNamedItem("transX").getNodeValue());
		transY = Float.parseFloat(attr.getNamedItem("transY").getNodeValue());
		transform.setToTranslation(transX, transY);
	}


	public String getDisplayString()
	{
		return "Transl " + transX + "," + transY;
	}


	public void setTranslate(double transX, double transY)
	{
		this.transX = transX;
		this.transY = transY;
		transform.setToTranslation(transX, transY);
	}


	public void setTranslateX(double transX)
	{
		this.transX = transX;
		transform.setToTranslation(transX, transY);
	}


	public void setTranslateY(double transY)
	{
		this.transY = transY;
		transform.setToTranslation(transX, transY);
	}


	@Override
	public Node toXML(Document document)
	{
		Element e = document.createElement("TransformationTranslate");
		e.setAttribute("transX", Double.toString(transX));
		e.setAttribute("transY", Double.toString(transY));
		return e;
	}

}