/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.gui;

import javax.swing.*;

import fsm.gui.transformation.ToolTIRotate;
import fsm.gui.transformation.ToolTIScale;
import fsm.gui.transformation.ToolTIScaleXY;
import fsm.gui.transformation.ToolTITranslate;
import fsm.model.TransformationSequence;
import fsm.transformation.Transformation;
import fsm.transformation.TransformationRotate;
import fsm.transformation.TransformationScale;
import fsm.transformation.TransformationScaleXY;
import fsm.transformation.TransformationTranslate;

import java.util.*;
import java.awt.*;

public class ToolTItemList extends JPanel
{

	TransformationSequence ts = null;
	GridLayout gridLayout1 = new GridLayout();


	public ToolTItemList()
	{
		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public void setTS(TransformationSequence ts)
	{
		this.ts = ts;
		update();
	}


	public void update()
	{
		removeAll();
		if (ts == null)
			return;
		JPanel prev_w = null;
		Iterator i = ts.transforms.iterator();
		while (i.hasNext())
		{
			Transformation t = (Transformation) i.next();
			JPanel p = null;
			if (t instanceof TransformationRotate)
				p = new ToolTIRotate((TransformationRotate) t);
			else if (t instanceof TransformationScale)
				p = new ToolTIScale((TransformationScale) t);
			else if (t instanceof TransformationScaleXY)
				p = new ToolTIScaleXY((TransformationScaleXY) t);
			else if (t instanceof TransformationTranslate)
				p = new ToolTITranslate((TransformationTranslate) t);
			else
			{
				System.out.println("Error: could not find transformation subclass");
				continue;
			}

			JPanel w = new JPanel();
			w.setLayout(new BorderLayout());
			w.add(p, BorderLayout.NORTH);
			if (prev_w == null)
				add(w);
			else
			{
				prev_w.add(w, BorderLayout.CENTER);
			}

			prev_w = w;
		}

		validate();

	}


	private void jbInit() throws Exception
	{
		gridLayout1.setColumns(1);
		gridLayout1.setRows(0);
		this.setLayout(gridLayout1);
	}


}