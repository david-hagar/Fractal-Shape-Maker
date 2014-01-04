/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author 
 * @version 1.0
 */
package fsm.gui.transformation;

import javax.swing.*;

import fsm.FractalShapeMaker;
import fsm.transformation.TransformationScale;

import java.awt.*;
import java.awt.event.*;


public class ToolTIScale extends JPanel
{

	TransformationScale t;
	JTextField scaleTextField = new JTextField();
	JLabel jLabel1 = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();


	public ToolTIScale(TransformationScale t)
	{
		this.t = t;

		scaleTextField.setText(Double.toString(t.scale));
		if (t == FractalShapeMaker.toolsFrame.getCurrentTransformItem())
		{
			setBackground(Color.darkGray);
			jLabel1.setForeground(Color.white);
		}

		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	private void jbInit() throws Exception
	{
		jLabel1.setText("Scale");
		jLabel1.addMouseListener(new java.awt.event.MouseAdapter()
		{

			public void mousePressed(MouseEvent e)
			{
				jLabel1_mousePressed(e);
			}
		});
		this.setLayout(borderLayout1);
		scaleTextField.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				scaleTextField_actionPerformed(e);
			}
		});
		this.add(jLabel1, BorderLayout.CENTER);
		this.add(scaleTextField, BorderLayout.SOUTH);
	}


	void scaleTextField_actionPerformed(ActionEvent e)
	{
		t.setScale(Double.parseDouble(((JTextField) e.getSource()).getText()));
		FractalShapeMaker.viewer.redraw();
	}


	void jLabel1_mousePressed(MouseEvent e)
	{
		FractalShapeMaker.toolsFrame.setCurrentTransformItem(t);
	}
}