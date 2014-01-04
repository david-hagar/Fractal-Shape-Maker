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

import fsm.shape.ElementShape;

import java.util.*;
import java.awt.*;


public class ToolsShapeList extends JPanel
{

	private ToolsFrame toolsFrame = null;
	int currentSelectionIndex = -1;
	GridLayout gridLayout1 = new GridLayout();


	public ToolsShapeList()
	{
		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	public void update()
	{

		if (toolsFrame.currentFractalShape == null)
			return;

		removeAll();

		Iterator i = toolsFrame.currentFractalShape.elementShapes.iterator();
		while (i.hasNext())
		{
			ToolsShapeRowPanel row = new ToolsShapeRowPanel((ElementShape) i.next(),
					toolsFrame.currentFractalShape);
			add(row);
			// row.setVisible(true);
			// row.show();
		}
		validate();

	}


	public void setToolsFrame(ToolsFrame toolsFrame)
	{
		this.toolsFrame = toolsFrame;
		update();
	}


	public void remove()
	{
		toolsFrame.currentFractalShape.elementShapes.remove(currentSelectionIndex);
	}


	private void jbInit() throws Exception
	{
		gridLayout1.setColumns(1);
		gridLayout1.setRows(0);
		this.setLayout(gridLayout1);
	}


}