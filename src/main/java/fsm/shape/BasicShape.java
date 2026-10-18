package fsm.shape;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

public interface BasicShape extends Shape {

    Element toXML(Document document);


}
