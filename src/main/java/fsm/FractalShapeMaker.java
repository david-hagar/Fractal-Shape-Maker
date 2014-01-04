/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm;

import javax.swing.UIManager;

import fsm.gui.FractalShapeViewerFrame;
import fsm.gui.ToolsFrame;

import java.awt.*;

public class FractalShapeMaker
{
	boolean packFrame = false;
	public static ToolsFrame toolsFrame = new ToolsFrame();
	public static FractalShapeViewerFrame viewer = toolsFrame.viewerFrame;


	// Construct the application
	public FractalShapeMaker()
	{

		// Validate frames that have preset sizes
		// Pack frames that have useful preferred size info, e.g. from their layout
		if (packFrame)
		{
			toolsFrame.pack();
			viewer.pack();
		} else
		{
			toolsFrame.validate();
			viewer.validate();
		}
		// Center the window

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		Dimension toolsSize = toolsFrame.getSize();
		int width = screenSize.width - toolsSize.width;
		int top = (screenSize.height - width) / 2;

		viewer.setLocation(10, top);
		viewer.setSize(new Dimension(width - 20, width));
		toolsFrame.setLocation(width, top);

		viewer.setVisible(true);
		toolsFrame.setVisible(true);

		toolsFrame.loadDefault();
	}


	// Main method
	public static void main(String[] args)
	{
		try
		{
			//UIManager
			//		.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		new FractalShapeMaker();
	}
}