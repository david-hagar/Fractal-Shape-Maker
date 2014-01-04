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
import javax.swing.border.LineBorder;

import fsm.FractalShapeMaker;
import fsm.model.TransformationSequence;

import java.awt.*;
import java.awt.event.*;


public class ToolTRow extends JPanel
{

	TransformationSequence ts;
	JLabel label = new JLabel();
	BorderLayout borderLayout1 = new BorderLayout();
	Color noColor = getBackground();
	// Color background = label.getBackground();
	// Color forground = label.getForeground();
	JPanel jPanel1 = new JPanel();
	JToggleButton recurseButton = new JToggleButton();
	JButton colorButton = new JButton();
	BorderLayout borderLayout2 = new BorderLayout();
	JButton hueDeltaButton = new JButton();


	public ToolTRow(TransformationSequence ts)
	{

		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		this.ts = ts;
		label.setText(ts.name);
		if (FractalShapeMaker.toolsFrame != null
				&& ts == FractalShapeMaker.toolsFrame.getCurrentTransform())
		{
			label.setForeground(Color.white);
			setBackground(Color.darkGray);
		}

		if (ts.color == null)
			setColorButtonColor(noColor);
		else
			setColorButtonColor(ts.color);

		recurseButton.setSelected(ts.recurse);

	}


	private void jbInit() throws Exception
	{
		this.setLayout(borderLayout1);
		label.addMouseListener(new java.awt.event.MouseAdapter()
		{

			public void mousePressed(MouseEvent e)
			{
				label_mousePressed(e);
			}
		});
		recurseButton.setMargin(new Insets(2, 5, 2, 5));
		recurseButton.setText("R");
        recurseButton.setPreferredSize(new Dimension(20, 25));
		recurseButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				recurseButton_actionPerformed(e);
			}
		});
		colorButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				colorButton_actionPerformed(e);
			}
		});
		colorButton.setPreferredSize(new Dimension(20, 20));
		colorButton.setMargin(new Insets(1, 1, 1, 1));
        colorButton.setOpaque(true);
        colorButton.setBorder(new LineBorder(Color.black, 1));

		jPanel1.setLayout(borderLayout2);
		hueDeltaButton.setMargin(new Insets(2, 5, 2, 5));
		hueDeltaButton.setText("Δ");
        hueDeltaButton.setPreferredSize(new Dimension(20, 25));
		hueDeltaButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				hueDeltaButton_actionPerformed(e);
			}
		});
		borderLayout1.setHgap(6);
		this.add(label, BorderLayout.CENTER);
		this.add(jPanel1, BorderLayout.WEST);
		jPanel1.add(recurseButton, BorderLayout.EAST);
		jPanel1.add(colorButton, BorderLayout.WEST);
		jPanel1.add(hueDeltaButton, BorderLayout.CENTER);

        borderLayout2.setHgap(2);
	}


	void colorButton_actionPerformed(ActionEvent e)
	{

		Color colorToUse = ts.color == null ? Color.blue : ts.color;

		Color c = JColorChooser.showDialog(this,
				"Pick Color (Cancel sets no color)", colorToUse);


		if (c == null)
		{
			ts.color = null;
		} else
		{

			String ans = (String) JOptionPane.showInputDialog(this,
					"Enter tranparency ( 0.0 to 1.0 ) ", "Tranparency",
					JOptionPane.PLAIN_MESSAGE, (Icon) null, (Object[]) null, new Float(
							colorToUse.getAlpha() / 255.0f));
			if (ans == null || ans.equals(""))
				return;

			int transparency = Math.round(Float.parseFloat(ans) * 255);

			System.out.println("transparency=" + transparency);


			ts.color = new Color(c.getRed(),c.getGreen(),c.getBlue(),transparency);

		}


		if (ts.color == null)
			setColorButtonColor(noColor);
		else
		{
			setColorButtonColor(ts.color);			
		}
		repaint();
		FractalShapeMaker.viewer.redraw();
	}


	private void setColorButtonColor(Color c)
	{
		Color c2 = new Color(c.getRed(), c.getGreen(), c.getBlue());
        colorButton.setBackground(c2);
	}


	void label_mousePressed(MouseEvent e)
	{
		FractalShapeMaker.toolsFrame.setCurrentTransform(ts);
		// System.out.println(e.getClickCount());

	}


	void recurseButton_actionPerformed(ActionEvent e)
	{
		ts.recurse = ((JToggleButton) e.getSource()).isSelected();
		FractalShapeMaker.viewer.redraw();
	}


	void hueDeltaButton_actionPerformed(ActionEvent e)
	{

		String ans = (String) JOptionPane.showInputDialog(this,
				"Enter New Hue Delta ( -1.0 to 1.0 ) ", "Change Hue Delta",
				JOptionPane.PLAIN_MESSAGE, (Icon) null, (Object[]) null, new Float(
						ts.hueDelta));
		if (ans == null || ans.equals(""))
			return;

		ts.hueDelta = Float.parseFloat(ans);

		FractalShapeMaker.viewer.redraw();
	}


}