package fsm.shape;

import java.awt.Shape;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public interface BasicShape extends Shape
{

	public Element toXML(Document document);


}
