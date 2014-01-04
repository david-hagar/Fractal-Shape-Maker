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


public class TransformationRotate extends Transformation implements
		Serializable
{
	static final long serialVersionUID = -915414269689575040L;

	public double angle = 0.0;


	public TransformationRotate()
	{
	}


	public TransformationRotate(Node node)
	{
		NamedNodeMap attr = node.getAttributes();
		angle = Float.parseFloat(attr.getNamedItem("angle").getNodeValue());

		transform.setToRotation(Math.toRadians(angle));
	}


	public String getDisplayString()
	{
		return "Rotate " + angle;
	}


	public void setRotate(double angle)
	{
		this.angle = angle;
		transform.setToRotation(Math.toRadians(angle));
	}


	@Override
	public Node toXML(Document document)
	{
		Element e = document.createElement("TransformationRotate");
		e.setAttribute("angle", Double.toString(angle));
		return e;
	}

}