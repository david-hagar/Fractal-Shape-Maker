/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 * @author
 * @version 1.0
 */
package fsm.gui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;

import util.deepCloneUtil;

import fsm.FractalShapeMaker;
import fsm.model.FractalShape;
import fsm.model.FractalShapeLibrary;
import fsm.model.TransformationSequence;
import fsm.shape.ElementShape;
import fsm.shape.SEllipse2D;
import fsm.shape.SGeneralPath;
import fsm.transformation.Transformation;
import fsm.transformation.TransformationRotate;
import fsm.transformation.TransformationScale;
import fsm.transformation.TransformationScaleXY;
import fsm.transformation.TransformationTranslate;

import java.awt.print.*;

public class ToolsFrame extends JFrame
{
	public FractalShapeViewerFrame viewerFrame = new FractalShapeViewerFrame();
	FractalShapeViewer viewer = viewerFrame.viewer;
	FractalShapeLibrary library = new FractalShapeLibrary();
	public FractalShape currentFractalShape = null;
	ElementShape currentElementShape = null;
	FractalShapeViewer currentLibraryItem = null;
	TransformationSequence currentTransform = null;
	Transformation currentTransformItem = null;
	ToolTList TFPanel = new ToolTList();
	ArrayList<FractalShapeViewer> libraryViewerList = null;
	File currentLibraryFile = null;

	JPanel contentPane;
	JMenuBar menuBar1 = new JMenuBar();
	JMenu libraryMenu = new JMenu();
	JMenuItem menuFileExit = new JMenuItem();
	JMenu menuHelp = new JMenu();
	JMenuItem menuHelpAbout = new JMenuItem();
	BorderLayout borderLayout1 = new BorderLayout();
	JScrollPane jScrollPane1 = new JScrollPane();
	JPanel libraryPanel = new JPanel();
	JPanel TransformationPanel = new JPanel();
	JPanel jPanel8 = new JPanel();
	JPanel librarySubPanel = new JPanel();
	JPanel jPanel6 = new JPanel();
	JPanel jPanel3 = new JPanel();
	JPanel jPanel2 = new JPanel();
	JPanel jPanel1 = new JPanel();
	GridLayout gridLayout4 = new GridLayout();
	BorderLayout borderLayout9 = new BorderLayout();
	BorderLayout borderLayout8 = new BorderLayout();
	BorderLayout borderLayout6 = new BorderLayout();
	BorderLayout borderLayout5 = new BorderLayout();
	BorderLayout borderLayout3 = new BorderLayout();
	BorderLayout borderLayout2 = new BorderLayout();
	BorderLayout borderLayout7 = new BorderLayout();
	JLabel jLabel1 = new JLabel();
	JMenuItem menuLoad = new JMenuItem();
	JMenuItem menuSave = new JMenuItem();
	TitledBorder titledBorder1;
	JPanel jPanel16 = new JPanel();
	JPanel shapePanel = new JPanel();
	BorderLayout borderLayout4 = new BorderLayout();
	JPanel jPanel14 = new JPanel();
	JPanel jPanel13 = new JPanel();
	JPanel jPanel12 = new JPanel();
	JPanel jPanel11 = new JPanel();
	JButton minusButton = new JButton();
	JButton infButton = new JButton();
	JPanel optionsPanel = new JPanel();
	JButton plusButton = new JButton();
	JButton oneButton = new JButton();
	FlowLayout flowLayout3 = new FlowLayout();
	BorderLayout borderLayout11 = new BorderLayout();
	BorderLayout borderLayout10 = new BorderLayout();
	GridLayout gridLayout5 = new GridLayout();
	JTextField levelTextField = new JTextField();
	JLabel jLabel2 = new JLabel();
	BorderLayout borderLayout12 = new BorderLayout();
	BorderLayout borderLayout13 = new BorderLayout();
	JPanel jPanel17 = new JPanel();
	BorderLayout borderLayout14 = new BorderLayout();
	JLabel jLabel4 = new JLabel();
	JMenu shapeMenu = new JMenu();
	JMenu transformationMenu = new JMenu();
	JMenuItem deleteSItem = new JMenuItem();
	JMenuItem duplicateSItem = new JMenuItem();
	JMenuItem moveUpSItem = new JMenuItem();
	JMenuItem moveDownSItem = new JMenuItem();
	JMenuItem newTransformation = new JMenuItem();
	JMenuItem deleteTransformation = new JMenuItem();
	JMenuItem dupTransformation = new JMenuItem();
	JPanel jPanel4 = new JPanel();
	JLabel jLabel5 = new JLabel();
	BorderLayout borderLayout15 = new BorderLayout();
	JPanel jPanel5 = new JPanel();
	BorderLayout borderLayout16 = new BorderLayout();
	JLabel jLabel6 = new JLabel();
	JPanel jPanel9 = new JPanel();
	JButton setBaseTransformButton = new JButton();
	GridLayout gridLayout1 = new GridLayout();
	JScrollPane jScrollPane2 = new JScrollPane();
	JScrollPane jScrollPane3 = new JScrollPane();
	JScrollPane jScrollPane4 = new JScrollPane();
	JPanel jPanel10 = new JPanel();
	ToolsShapeList shapeList = new ToolsShapeList();
	BorderLayout borderLayout17 = new BorderLayout();
	JPanel jPanel15 = new JPanel();
	BorderLayout borderLayout18 = new BorderLayout();
	JButton setInitialTransformButton = new JButton();
	JPanel jPanel18 = new JPanel();
	ToolTItemList TFIPanel = new ToolTItemList();
	BorderLayout borderLayout19 = new BorderLayout();
	JCheckBox lastOnlyToggle = new JCheckBox();
	JMenuItem TMoveUpMenu = new JMenuItem();
	JMenuItem TMoveDownMenu = new JMenuItem();
	JMenu jMenu1 = new JMenu();
	JMenuItem newSquareMenu = new JMenuItem();
	JMenuItem newCircleMenu = new JMenuItem();
	JMenuItem newTriangleMenu = new JMenuItem();
	JMenuItem TSetNameMenuItem = new JMenuItem();
	JPanel jPanel19 = new JPanel();
	JLabel backgroundLabel = new JLabel();
	BorderLayout borderLayout20 = new BorderLayout();
	JButton backgroundColorButton = new JButton();
	JButton newItemButton = new JButton();
	TitledBorder titledBorder2;
	JMenu addTItemsSubMenu = new JMenu();
	JMenuItem itemMoveDownMenu = new JMenuItem();
	JMenuItem translateMenu = new JMenuItem();
	JMenuItem scaleXYmenu = new JMenuItem();
	JMenuItem scaleMenu = new JMenuItem();
	JMenuItem itemDeleteMenu = new JMenuItem();
	JMenuItem rotateMenu = new JMenuItem();
	JMenuItem itemMoveUpMenu = new JMenuItem();
	JMenuItem menuItemAppendFile = new JMenuItem();
	JMenuItem menuSaveAs = new JMenuItem();
	JMenu itemsSubMenu = new JMenu();
	JMenuItem deleteAllButMenuItem = new JMenuItem();
	JMenuItem newItem = new JMenuItem();
	JMenuItem deleteItem = new JMenuItem();
	JMenuItem printShapeMenu = new JMenuItem();


	// Construct the frame
	public ToolsFrame()
	{
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try
		{
			jbInit();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// Rectangle2D.Double rect = new Rectangle2D.Double( -0.5, -0.5, 1.0, 1.0 );
		// SGeneralPath b = new SGeneralPath();
		// b.moveTo(-0.5f, -0.5f);
		// b.lineTo(-0.5f, +0.5f);
		// b.lineTo(+0.5f, +0.5f);
		// b.lineTo(+0.5f, -0.5f);
		// b.closePath();

		// ElementShape es = new ElementShape(b);
		// es.color = Color.green;

		currentFractalShape = new FractalShape();
		// currentFractalShape.add(es);

		library.add(currentFractalShape);
		viewer.setFractalShape(currentFractalShape);

		TransformationSequence ts = new TransformationSequence();
		ts.recurse = true;
		TransformationTranslate trans = new TransformationTranslate();
		trans.setTranslate(-0.5, 1.0);
		ts.add(trans);
		TransformationScale sc = new TransformationScale();
		sc.setScale(0.5);
		ts.add(sc);
		TransformationRotate r4 = new TransformationRotate();
		r4.setRotate(120.0);
		ts.add(r4);
		ts.hueDelta = 0.1f;
		// ts.color = Color.green;


		TransformationSequence ts2 = new TransformationSequence();
		ts2.recurse = true;
		TransformationTranslate trans2 = new TransformationTranslate();
		trans2.setTranslate(0.9, 1.0);
		ts2.add(trans2);
		TransformationScale sc2 = new TransformationScale();
		sc2.setScale(0.8);
		ts2.add(sc2);
		TransformationRotate r2 = new TransformationRotate();
		r2.setRotate(-30.0);
		ts2.add(r2);
		ts2.hueDelta = -0.1f;

		// ts2.color = Color.blue;

		currentFractalShape.add(ts2);
		currentFractalShape.add(ts);

		SEllipse2D e = new SEllipse2D(-0.5, 0.5, 1.0, 1.0);
		ElementShape es2 = new ElementShape(e);
		// es2.color = Color.yellow;
		currentFractalShape.add(es2);

		TransformationSequence tsi = new TransformationSequence();
		TransformationRotate r3 = new TransformationRotate();
		r3.setRotate(45.0);
		tsi.add(r3);
		tsi.color = Color.green; // initial color
		currentFractalShape.setInitialTF(tsi);
		currentFractalShape.add(tsi);
		// currentFractalShape.hueIncrement = 0.1f;

		Dimension size = new Dimension(20, 20);
		viewer.calcFractalBounds(size);
		// System.out.println( "bounds = " + bounds );
		// System.out.println( "right = " + (bounds.x + bounds.width) + " " +
		// (bounds.y + bounds.height));

		// shapeListModel.setFractalShape(currentFractalShape);
		int i = 0;
		if (i == 0)
		{
			// shapeList.setModel(shapeListModel) ;
			// shapeList.setCellRenderer( shapeCellRenderer);
		}

		shapeList.setToolsFrame(this);
		TFPanel.setToolsFrame(this);

		FractalShape s = (FractalShape) deepCloneUtil
				.deepClone(currentFractalShape);
		library.add(s);
		s = (FractalShape) deepCloneUtil.deepClone(currentFractalShape);
		library.add(s);

		updateLibrary();
		update();


	}


	// Component initialization
	private void jbInit() throws Exception
	{
		contentPane = (JPanel) this.getContentPane();
		titledBorder1 = new TitledBorder("");
		titledBorder2 = new TitledBorder("");
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(500, 600));
		this.setTitle("Tools");
		libraryMenu.setText("Library");
		menuFileExit.setText("Exit");
		menuFileExit.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				fileExit_actionPerformed(e);
			}
		});
		menuHelp.setText("Help");
		menuHelpAbout.setText("About");
		menuHelpAbout.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				helpAbout_actionPerformed(e);
			}
		});
		libraryPanel.setLayout(borderLayout3);
		TransformationPanel.setLayout(borderLayout5);
		jPanel8.setLayout(borderLayout9);
		librarySubPanel.setLayout(gridLayout4);
		jPanel6.setLayout(borderLayout8);
		jPanel3.setLayout(borderLayout6);
		jPanel2.setLayout(borderLayout2);
		jPanel1.setLayout(borderLayout7);
		gridLayout4.setColumns(1);
		gridLayout4.setRows(0);
		jLabel1.setHorizontalAlignment(SwingConstants.LEFT);
		jLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
		jLabel1.setText("  Library");
		menuLoad.setText("Load ...");
		menuLoad.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				menuLoad_actionPerformed(e);
			}
		});
		menuSave.setText("Save");
		menuSave.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				menuSave_actionPerformed(e);
			}
		});

		borderLayout2.setHgap(5);
		borderLayout7.setHgap(10);
		shapePanel.setLayout(borderLayout4);
		jPanel14.setLayout(flowLayout3);
		jPanel13.setLayout(gridLayout5);
		jPanel12.setLayout(borderLayout10);
		jPanel11.setLayout(borderLayout11);
		minusButton.setText("-");
		minusButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				minusButton_actionPerformed(e);
			}
		});
		infButton.setFont(new java.awt.Font("Dialog", 0, 10));
		infButton.setText("Inf");
		infButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				infButton_actionPerformed(e);
			}
		});
		optionsPanel.setLayout(borderLayout13);
		plusButton.setFont(new java.awt.Font("Dialog", 0, 10));
		plusButton.setText("+");
		plusButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				plusButton_actionPerformed(e);
			}
		});
		oneButton.setFont(new java.awt.Font("Dialog", 0, 10));
		oneButton.setText("1");
		oneButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				oneButton_actionPerformed(e);
			}
		});
		gridLayout5.setRows(2);
		levelTextField.setMinimumSize(new Dimension(20, 21));
		levelTextField.setPreferredSize(new Dimension(30, 21));
		levelTextField.setText("10");
		levelTextField.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				levelTextField_actionPerformed(e);
			}
		});
		jLabel2.setText("Level");
		jPanel16.setLayout(borderLayout12);
		borderLayout13.setVgap(5);
		jPanel17.setLayout(borderLayout14);
		jLabel4.setText("Shapes");
		borderLayout12.setVgap(5);
		shapeMenu.setText("Shape");
		transformationMenu.setText("Transformation");
		deleteSItem.setText("Delete");
		deleteSItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				deleteSItem_actionPerformed(e);
			}
		});
		duplicateSItem.setText("Duplicate");
		duplicateSItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				duplicateSItem_actionPerformed(e);
			}
		});
		moveUpSItem.setText("Move Up");
		moveUpSItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				moveUpSItem_actionPerformed(e);
			}
		});
		moveDownSItem.setText("Move Down");
		moveDownSItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				moveDownSItem_actionPerformed(e);
			}
		});
		newTransformation.setText("New");
		newTransformation.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				newTransformation_actionPerformed(e);
			}
		});
		deleteTransformation.setText("Delete");
		deleteTransformation.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				deleteTransformation_actionPerformed(e);
			}
		});
		dupTransformation.setText("Duplicate");
		dupTransformation.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				dupTransformation_actionPerformed(e);
			}
		});
		jLabel5.setText("Transformations");
		jPanel4.setLayout(borderLayout15);
		borderLayout5.setVgap(5);
		jPanel5.setLayout(borderLayout16);
		jLabel6.setText("Items");
		setBaseTransformButton.setFont(new java.awt.Font("Dialog", 0, 12));
		setBaseTransformButton.setText("Set Shape TF");
		setBaseTransformButton
				.addActionListener(new java.awt.event.ActionListener()
				{

					public void actionPerformed(ActionEvent e)
					{
						setBaseTransformButton_actionPerformed(e);
					}
				});
		jPanel9.setLayout(gridLayout1);
		gridLayout1.setColumns(1);
		gridLayout1.setRows(0);
		borderLayout14.setVgap(1);
		jScrollPane2.setPreferredSize(new Dimension(100, 131));
		jScrollPane3.setPreferredSize(new Dimension(100, 131));
		jScrollPane4.setPreferredSize(new Dimension(100, 131));
		jPanel10.setLayout(borderLayout17);
		jPanel15.setLayout(borderLayout18);
		TransformationPanel.setPreferredSize(new Dimension(150, 301));
		setInitialTransformButton
				.addActionListener(new java.awt.event.ActionListener()
				{

					public void actionPerformed(ActionEvent e)
					{
						setInitialTransformButton_actionPerformed(e);
					}
				});
		setInitialTransformButton.setText("Set Initial TF");
		setInitialTransformButton.setFont(new java.awt.Font("Dialog", 0, 12));
		jPanel18.setLayout(borderLayout19);
		lastOnlyToggle.setText("Last Level Only");
		lastOnlyToggle.setFont(new java.awt.Font("Dialog", 0, 12));
		lastOnlyToggle.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				autoBoundsToggle_actionPerformed(e);
			}
		});
		TMoveUpMenu.setText("Move Up");
		TMoveUpMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				TMoveUpMenu_actionPerformed(e);
			}
		});
		TMoveDownMenu.setText("Move Down");
		TMoveDownMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				TMoveDownMenu_actionPerformed(e);
			}
		});
		jMenu1.setText("New");
		newSquareMenu.setText("Square");
		newSquareMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				newSquareMenu_actionPerformed(e);
			}
		});
		newCircleMenu.setText("Circle");
		newCircleMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				newCircleMenu_actionPerformed(e);
			}
		});
		newTriangleMenu.setText("Triangle");
		newTriangleMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				newTriangleMenu_actionPerformed(e);
			}
		});
		TSetNameMenuItem.setText("Set Name");
		TSetNameMenuItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				TSetNameMenuItem_actionPerformed(e);
			}
		});
		backgroundLabel.setText("Background");
		jPanel19.setLayout(borderLayout20);
		borderLayout20.setHgap(4);
		backgroundColorButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				backgroundColorButton_actionPerformed(e);
			}
		});
        backgroundColorButton.setOpaque(true);
        backgroundColorButton.setBorder(new LineBorder(Color.black, 1));
        backgroundColorButton.setPreferredSize(new Dimension(50,20));

		newItemButton.setFont(new java.awt.Font("Dialog", 1, 10));
		newItemButton.setMargin(new Insets(2, 5, 2, 5));
		newItemButton.setText("Dup Item");
		newItemButton.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				newItemButton_actionPerformed(e);
			}
		});
		borderLayout6.setHgap(10);
		jPanel8.setBackground(Color.black);
		addTItemsSubMenu.setText("TF Items");
		itemMoveDownMenu.setText("Move Down");
		itemMoveDownMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				itemMoveDownMenu_actionPerformed(e);
			}
		});
		translateMenu.setText("Add Translate");
		translateMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				translateMenu_actionPerformed(e);
			}
		});
		scaleXYmenu.setText("Add Scale x, y");
		scaleXYmenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				scaleXYmenu_actionPerformed(e);
			}
		});
		scaleMenu.setText("Add Scale");
		scaleMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				scaleMenu_actionPerformed(e);
			}
		});
		itemDeleteMenu.setText("Delete");
		itemDeleteMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				itemDeleteMenu_actionPerformed(e);
			}
		});
		rotateMenu.setText("Add Rotate");
		rotateMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				rotateMenu_actionPerformed(e);
			}
		});
		itemMoveUpMenu.setText("Move Up");
		itemMoveUpMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				itemMoveUpMenu_actionPerformed(e);
			}
		});
		menuItemAppendFile.setText("Append File ...");
		menuItemAppendFile.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				menuItemAppendFile_actionPerformed(e);
			}
		});
		menuSaveAs.setText("Save As ...");
		menuSaveAs.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				menuSaveAs_actionPerformed(e);
			}
		});
		itemsSubMenu.setText("Fractal");
		deleteAllButMenuItem.setText("Delete All But Selected");
		deleteAllButMenuItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				deleteAllButMenuItem_actionPerformed(e);
			}
		});
		newItem.setText("Duplicate");
		newItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				newItemButton_actionPerformed(e);
			}
		});
		deleteItem.setText("Delete");
		deleteItem.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				deleteItem_actionPerformed(e);
			}
		});
		printShapeMenu.setText("Print");
		printShapeMenu.addActionListener(new java.awt.event.ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				printShapeMenu_actionPerformed(e);
			}
		});
		libraryMenu.add(menuLoad);
		libraryMenu.add(menuSave);
		libraryMenu.add(menuSaveAs);
		libraryMenu.add(menuItemAppendFile);
		libraryMenu.add(menuFileExit);
		menuHelp.add(menuHelpAbout);
		menuBar1.add(libraryMenu);
		menuBar1.add(itemsSubMenu);
		menuBar1.add(shapeMenu);
		menuBar1.add(transformationMenu);
		menuBar1.add(addTItemsSubMenu);
		menuBar1.add(menuHelp);
		this.setJMenuBar(menuBar1);
		contentPane.add(jPanel1, BorderLayout.CENTER);
		jPanel1.add(jPanel2, BorderLayout.EAST);
		jPanel2.add(TransformationPanel, BorderLayout.EAST);
		TransformationPanel.add(jPanel4, BorderLayout.NORTH);
		jPanel4.add(jLabel5, BorderLayout.NORTH);
		jPanel4.add(jScrollPane3, BorderLayout.SOUTH);
		jScrollPane3.getViewport().add(jPanel15, null);
		jPanel15.add(TFPanel, BorderLayout.NORTH);
		TransformationPanel.add(jPanel5, BorderLayout.CENTER);
		jPanel5.add(jLabel6, BorderLayout.NORTH);
		jPanel5.add(jScrollPane4, BorderLayout.CENTER);
		jScrollPane4.getViewport().add(jPanel18, null);
		jPanel18.add(TFIPanel, BorderLayout.NORTH);
		jPanel2.add(jPanel16, BorderLayout.WEST);
		jPanel16.add(optionsPanel, BorderLayout.NORTH);
		optionsPanel.add(jPanel12, BorderLayout.NORTH);
		jPanel12.add(jPanel14, BorderLayout.NORTH);
		jPanel14.add(jLabel2, null);
		jPanel14.add(levelTextField, null);
		jPanel12.add(jPanel13, BorderLayout.SOUTH);
		jPanel13.add(plusButton, null);
		jPanel13.add(minusButton, null);
		jPanel13.add(oneButton, null);
		jPanel13.add(infButton, null);
		optionsPanel.add(jPanel11, BorderLayout.SOUTH);
		jPanel11.add(lastOnlyToggle, BorderLayout.NORTH);
		jPanel11.add(jPanel19, BorderLayout.SOUTH);
		jPanel19.add(backgroundLabel, BorderLayout.WEST);
		jPanel19.add(backgroundColorButton, BorderLayout.EAST);
		jPanel16.add(shapePanel, BorderLayout.CENTER);
		shapePanel.add(jPanel17, BorderLayout.CENTER);
		jPanel17.add(jPanel9, BorderLayout.SOUTH);
		jPanel9.add(setBaseTransformButton, null);
		jPanel9.add(setInitialTransformButton, null);
		jPanel17.add(jScrollPane2, BorderLayout.CENTER);
		jScrollPane2.getViewport().add(jPanel10, null);
		jPanel10.add(shapeList, BorderLayout.NORTH);
		shapePanel.add(jLabel4, BorderLayout.NORTH);
		jPanel1.add(libraryPanel, BorderLayout.CENTER);
		libraryPanel.add(jPanel3, BorderLayout.NORTH);
		jPanel3.add(jLabel1, BorderLayout.CENTER);
		jPanel3.add(newItemButton, BorderLayout.EAST);
		libraryPanel.add(jScrollPane1, BorderLayout.CENTER);
		jScrollPane1.getViewport().add(jPanel6, null);
		jPanel6.add(jPanel8, BorderLayout.NORTH);
		jPanel8.add(librarySubPanel, BorderLayout.WEST);
		shapeMenu.add(jMenu1);
		shapeMenu.add(deleteSItem);
		shapeMenu.add(duplicateSItem);
		shapeMenu.add(moveUpSItem);
		shapeMenu.add(moveDownSItem);
		transformationMenu.add(newTransformation);
		transformationMenu.add(deleteTransformation);
		transformationMenu.add(dupTransformation);
		transformationMenu.add(TMoveUpMenu);
		transformationMenu.add(TMoveDownMenu);
		transformationMenu.add(TSetNameMenuItem);
		jMenu1.add(newSquareMenu);
		jMenu1.add(newCircleMenu);
		jMenu1.add(newTriangleMenu);
		addTItemsSubMenu.add(rotateMenu);
		addTItemsSubMenu.add(translateMenu);
		addTItemsSubMenu.add(scaleMenu);
		addTItemsSubMenu.add(scaleXYmenu);
		addTItemsSubMenu.add(itemMoveUpMenu);
		addTItemsSubMenu.add(itemMoveDownMenu);
		addTItemsSubMenu.add(itemDeleteMenu);
		itemsSubMenu.add(newItem);
		itemsSubMenu.add(deleteItem);
		itemsSubMenu.add(deleteAllButMenuItem);
		itemsSubMenu.add(printShapeMenu);
	}


	// File | Exit action performed
	public void fileExit_actionPerformed(ActionEvent e)
	{
		System.exit(0);
	}


	// Help | About action performed
	public void helpAbout_actionPerformed(ActionEvent e)
	{
		ToolsFrame_AboutBox dlg = new ToolsFrame_AboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.setVisible(true);
	}


	// Overridden so we can exit when window is closed
	protected void processWindowEvent(WindowEvent e)
	{
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING)
		{
			fileExit_actionPerformed(null);
		}
	}


	void update()
	{
		levelTextField.setText(Integer.toString(currentFractalShape.level));
		// hueIncrementText.setText( Float.toString(currentFractalShape.hueIncrement
		// ) );
		lastOnlyToggle.setSelected(currentFractalShape.getLastOnly());
		backgroundColorButton.setBackground(currentFractalShape.background);

		updateShapes();
		updateTransforms();
		updateTransformItems();
	}


	void updateLibrary()
	{
		librarySubPanel.removeAll();
		// librarySubPanel.add(new JButton("hdjfh"));
		// librarySubPanel.add(new JButton("hdjfh"));

		int size = library.fractals.size();
		libraryViewerList = new ArrayList<FractalShapeViewer>(size);
		for (int i = 0; i < size; i++)
		{
			FractalShape s = (FractalShape) library.fractals.get(i);
			FractalShapeViewer view = new FractalShapeViewer(s, true);
			libraryViewerList.add(view);
			view.setSize(80, 80);
			view.setMinimumSize(new Dimension(80, 80));
			view.setPreferredSize(new Dimension(80, 80));
			view.addMouseListener(new java.awt.event.MouseAdapter()
			{
				public void mousePressed(MouseEvent e)
				{
					librarySubPanel_mousePressed(e);
				}
			});
			JPanel p = new JPanel();

			if (s == currentFractalShape)
			{
				p.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
				currentLibraryItem = view;
				System.out.println("added ");
			} else
				p.setBorder(BorderFactory.createLineBorder(Color.black, 2));

			p.setLayout(new BorderLayout());
			p.add(view, BorderLayout.CENTER);
			librarySubPanel.add(p);
		}

		// librarySubPanel.add(new JButton("hdjfh"));
		// librarySubPanel.add(new JButton("hdjfh"));
		// librarySubPanel.add(new JButton("hdjfh"));

		libraryPanel.validate();


		/*
		 * //ProgressFrame progress = new ProgressFrame( "message", 0, size);
		 * //progress.go(); size = libraryViewerList.size(); for( int i=0; i<size;
		 * i++ ) { FractalShapeViewer view = (FractalShapeViewer)
		 * libraryViewerList.get(i) ; view.makeThumbnail(); //progress.setValue(i); }
		 * //progress.close();
		 */
	}


	void updateShapes()
	{
		shapeList.update();
		jScrollPane2.validate();
	}


	void updateTransforms()
	{
		TFPanel.update();
		jScrollPane3.validate();
	}


	void updateTransformItems()
	{
		TFIPanel.update();
		jScrollPane4.validate();
	}


	public ElementShape getCurrentElementShape()
	{
		return currentElementShape;
	}


	public void setCurrentElementShape(ElementShape shape)
	{
		currentElementShape = shape;
		updateShapes();
	}


	public TransformationSequence getCurrentTransform()
	{
		return currentTransform;
	}


	public void setCurrentTransform(TransformationSequence tf)
	{
		currentTransform = tf;
		setCurrentTransformItem(null);
		TFIPanel.setTS(tf);
		updateTransforms();
		jScrollPane4.validate();
	}


	public Transformation getCurrentTransformItem()
	{
		return currentTransformItem;
	}


	public void setCurrentTransformItem(Transformation tf)
	{
		currentTransformItem = tf;
		updateTransformItems();
	}


	void menuLoad_actionPerformed(ActionEvent e)
	{
		JFileChooser fc = new JFileChooser((currentLibraryFile == null) ? "."
				: currentLibraryFile.getAbsolutePath());
		fc.setPreferredSize(new Dimension(500, 700));
		int ret = fc.showOpenDialog(FractalShapeMaker.viewer);
		if (ret == JFileChooser.CANCEL_OPTION)
			return;

		if (ret == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("load file = " + fc.getSelectedFile());
			try
			{
				library.load(fc.getSelectedFile());
			} catch (Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(FractalShapeMaker.viewer,
						"Error on load\nException = " + ex);
			}

			setLibraryFile(fc.getSelectedFile());

			// System.out.println("loaded file = " + fc.getSelectedFile() );
			updateLibrary();
			// System.out.println("updated library");
			selectLibraryItem(libraryViewerList.get(0));
			// System.out.println("selected" + fc.getSelectedFile() );
		}

	}


	void menuSaveAs_actionPerformed(ActionEvent e)
	{
		JFileChooser fc = new JFileChooser((currentLibraryFile == null) ? "."
				: currentLibraryFile.getAbsolutePath());
		fc.setPreferredSize(new Dimension(500, 700));
		int ret = fc.showSaveDialog(FractalShapeMaker.viewer);
		if (ret == JFileChooser.CANCEL_OPTION)
			return;

		if (ret == JFileChooser.APPROVE_OPTION)
		{
			System.out.println("save file = " + fc.getSelectedFile());
			try
			{
				library.save(fc.getSelectedFile());
			} catch (Exception ex)
			{
				ex.printStackTrace();
				JOptionPane.showMessageDialog(FractalShapeMaker.viewer,
						"Error on save\nException = " + ex);
			}

			setLibraryFile(fc.getSelectedFile());
		}


	}


	void infButton_actionPerformed(ActionEvent e)
	{
		currentFractalShape.level = 99;
		levelTextField.setText(Integer.toString(currentFractalShape.level));
		FractalShapeMaker.viewer.redraw();
	}


	void setBaseTransformButton_actionPerformed(ActionEvent e)
	{
		if (currentElementShape == null || currentTransform == null)
			return;

		currentElementShape.setBaseTF(currentTransform);
		updateShapes();

		FractalShapeMaker.viewer.redraw();
	}


	void setInitialTransformButton_actionPerformed(ActionEvent e)
	{
		currentFractalShape.setInitialTF(currentTransform);
		FractalShapeMaker.viewer.redraw();
	}


	void levelTextField_actionPerformed(ActionEvent e)
	{
		currentFractalShape.level = Integer.parseInt(((JTextField) e.getSource())
				.getText());
		levelTextField.setText(Integer.toString(currentFractalShape.level));
		FractalShapeMaker.viewer.redraw();
	}


	void plusButton_actionPerformed(ActionEvent e)
	{
		currentFractalShape.level++;
		levelTextField.setText(Integer.toString(currentFractalShape.level));
		FractalShapeMaker.viewer.redraw();
	}


	void minusButton_actionPerformed(ActionEvent e)
	{
		if (--currentFractalShape.level == 0)
			currentFractalShape.level = 1;
		levelTextField.setText(Integer.toString(currentFractalShape.level));
		FractalShapeMaker.viewer.redraw();
	}


	void oneButton_actionPerformed(ActionEvent e)
	{
		currentFractalShape.level = 1;
		levelTextField.setText(Integer.toString(currentFractalShape.level));
		FractalShapeMaker.viewer.redraw();
	}


	void autoBoundsToggle_actionPerformed(ActionEvent e)
	{
		currentFractalShape.setLastOnly(lastOnlyToggle.isSelected());

		FractalShapeMaker.viewer.redraw();

	}


	void newTransformation_actionPerformed(ActionEvent e)
	{
		TransformationSequence ts = new TransformationSequence();
		if (currentFractalShape != null)
			currentFractalShape.add(ts);
		setCurrentTransform(ts);
	}


	void deleteTransformation_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		int index = currentFractalShape.transforms.indexOf(currentTransform);
		currentFractalShape.transforms.remove(index);
		setCurrentTransform(null);
		FractalShapeMaker.viewer.redraw();
	}


	void dupTransformation_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		TransformationSequence ts = (TransformationSequence) deepCloneUtil
				.deepClone(currentTransform);
		if (currentFractalShape != null)
			currentFractalShape.add(ts);
		setCurrentTransform(ts);
	}


	void TMoveUpMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		int index = currentFractalShape.transforms.indexOf(currentTransform);
		if (index == 0)
			return;

		currentFractalShape.transforms.remove(index);
		currentFractalShape.transforms.add(index - 1, currentTransform);
		updateTransforms();
		FractalShapeMaker.viewer.redraw();
	}


	void TMoveDownMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		int index = currentFractalShape.transforms.indexOf(currentTransform);
		if (index == currentFractalShape.transforms.size() - 1)
			return;

		currentFractalShape.transforms.remove(index);
		currentFractalShape.transforms.add(index + 1, currentTransform);
		updateTransforms();
		FractalShapeMaker.viewer.redraw();
	}


	void TSetNameMenuItem_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		String ans = (String) JOptionPane.showInputDialog(this, "Enter New Name",
				"Change Transformation Name", JOptionPane.PLAIN_MESSAGE, (Icon) null,
				(Object[]) null, currentTransform.name);
		if (ans == null || ans.equals(""))
			return;
		currentTransform.name = ans;
		updateTransforms();
	}


	void rotateMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		TransformationRotate r = new TransformationRotate();
		currentTransform.add(r);
		updateTransformItems();
	}


	void translateMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		TransformationTranslate r = new TransformationTranslate();
		currentTransform.add(r);
		updateTransformItems();
	}


	void scaleMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		TransformationScale r = new TransformationScale();
		currentTransform.add(r);
		updateTransformItems();
	}


	void scaleXYmenu_actionPerformed(ActionEvent e)
	{
		if (currentTransform == null)
			return;
		TransformationScaleXY r = new TransformationScaleXY();
		currentTransform.add(r);
		updateTransformItems();
	}


	void itemMoveUpMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransformItem == null || currentTransform == null)
			return;

		ArrayList<Transformation> list = currentTransform.transforms;

		int index = list.indexOf(currentTransformItem);
		if (index <= 0)
			return;

		list.remove(index);
		list.add(index - 1, currentTransformItem);
		currentTransform.recalcTotal();

		updateTransformItems();
		FractalShapeMaker.viewer.redraw();
	}


	void itemMoveDownMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransformItem == null || currentTransform == null)
			return;

		ArrayList<Transformation> list = currentTransform.transforms;

		int index = list.indexOf(currentTransformItem);
		if (index == -1 || index == list.size() - 1)
			return;

		list.remove(index);
		list.add(index + 1, currentTransformItem);
		currentTransform.recalcTotal();

		updateTransformItems();
		FractalShapeMaker.viewer.redraw();

	}


	void itemDeleteMenu_actionPerformed(ActionEvent e)
	{
		if (currentTransformItem == null || currentTransform == null)
			return;

		ArrayList<Transformation> list = currentTransform.transforms;

		int index = list.indexOf(currentTransformItem);
		if (index == -1)
			return;

		list.remove(index);
		currentTransform.recalcTotal();

		updateTransformItems();
		FractalShapeMaker.viewer.redraw();

	}


	void newSquareMenu_actionPerformed(ActionEvent e)
	{
		if (currentFractalShape == null)
			return;
		SGeneralPath b = new SGeneralPath();
		b.moveTo(-0.5f, -0.5f);
		b.lineTo(-0.5f, +0.5f);
		b.lineTo(+0.5f, +0.5f);
		b.lineTo(+0.5f, -0.5f);
		b.closePath();

		ElementShape es = new ElementShape(b);

		currentFractalShape.add(es);
		updateShapes();
		FractalShapeMaker.viewer.redraw();
	}


	void newCircleMenu_actionPerformed(ActionEvent ae)
	{
		SEllipse2D e = new SEllipse2D(-0.5, -0.5, 1.0, 1.0);
		ElementShape es2 = new ElementShape(e);
		currentFractalShape.add(es2);
		updateShapes();
		FractalShapeMaker.viewer.redraw();
	}


	SGeneralPath makeRegularPoly(int sides)
	{
		SGeneralPath b = new SGeneralPath();

		for (int i = 0; i < sides; i++)
		{
			double angle = i * 2.0 * Math.PI / sides;
			b.addPoint((float) (0.5 * Math.sin(angle)), (float) (0.5 * Math
					.cos(angle)));
		}

		b.closePath();
		return b;
	}


	void newTriangleMenu_actionPerformed(ActionEvent e)
	{
		if (currentFractalShape == null)
			return;

		SGeneralPath b = makeRegularPoly(3);

		ElementShape es = new ElementShape(b);

		currentFractalShape.add(es);
		updateShapes();
		FractalShapeMaker.viewer.redraw();
	}


	void deleteSItem_actionPerformed(ActionEvent e)
	{
		if (currentElementShape == null || currentFractalShape == null)
			return;

		ArrayList list = currentFractalShape.elementShapes;

		int index = list.indexOf(currentElementShape);
		if (index == -1)
			return;

		list.remove(index);

		updateShapes();
		FractalShapeMaker.viewer.redraw();
	}


	void duplicateSItem_actionPerformed(ActionEvent e)
	{
		if (currentElementShape == null || currentFractalShape == null)
			return;

		currentFractalShape.elementShapes.add((ElementShape) currentElementShape
				.clone());

		updateShapes();
		FractalShapeMaker.viewer.redraw();
	}


	void moveDownSItem_actionPerformed(ActionEvent e)
	{
		if (currentElementShape == null || currentFractalShape == null)
			return;

		ArrayList<ElementShape> list = currentFractalShape.elementShapes;

		int index = list.indexOf(currentElementShape);
		if (index == -1 || index == list.size() - 1)
			return;

		list.remove(index);
		list.add(index + 1, currentElementShape);

		updateShapes();
		FractalShapeMaker.viewer.redraw();
	}


	void moveUpSItem_actionPerformed(ActionEvent e)
	{
		if (currentElementShape == null || currentFractalShape == null)
			return;

		ArrayList<ElementShape> list = currentFractalShape.elementShapes;

		int index = list.indexOf(currentElementShape);
		if (index == -1 || index == 0)
			return;

		list.remove(index);
		list.add(index - 1, currentElementShape);

		updateShapes();
		FractalShapeMaker.viewer.redraw();
	}


	void backgroundColorButton_actionPerformed(ActionEvent e)
	{

		Color c = JColorChooser.showDialog(this,
				"Pick Color (Cancel sets no color)", currentFractalShape.background);
		if (c == null)
			return;
		currentFractalShape.background = c;
		backgroundColorButton.setBackground(c);
		repaint();
		FractalShapeMaker.viewer.redraw();
	}


	void newItemButton_actionPerformed(ActionEvent e)
	{

		library.duplicate(currentFractalShape);
		updateLibrary();
	}


	void librarySubPanel_mousePressed(MouseEvent e)
	{
		// System.out.println( "mousePressed ");

		FractalShapeViewer p = (FractalShapeViewer) e.getSource();

		selectLibraryItem(p);
	}


	void selectLibraryItem(FractalShapeViewer p)
	{

		JPanel parent = (JPanel) p.getParent();

		if (currentLibraryItem != null)
			((JPanel) currentLibraryItem.getParent()).setBorder(BorderFactory
					.createLineBorder(Color.black, 2));

		parent.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
		currentLibraryItem = p;
		if (p != null)
			currentFractalShape = p.getFractalShape();
		else
			currentFractalShape = null;
		currentElementShape = null;
		setCurrentTransform(null);
		currentTransformItem = null;
		FractalShapeMaker.viewer.viewer.setFractalShape(currentFractalShape);
		update();
		FractalShapeMaker.viewer.redraw();

	}


	public void loadDefault()
	{
		String current = System.getProperty("user.dir");
		File f = new File(current, "default.fsm");
		if (!f.canRead())
			return;

		System.out.println("loading default");

		try
		{
			library.load(f);
		} catch (Exception ex)
		{
			System.out.println("Did not load default:" + f);
			return;
		}

		updateLibrary();
		selectLibraryItem(libraryViewerList.get(0));
	}


	void printShapeMenu_actionPerformed_old(ActionEvent e)
	{

		try
		{
			PrinterJob printJob = PrinterJob.getPrinterJob();

			boolean pDialogState = printJob.printDialog();
			Book book = new Book();
			PageFormat pageFormat = printJob.defaultPage();

			Paper paper = new Paper();
			paper.setImageableArea(0.0, 0.0, 8 * 72, 10.3 * 72);

			pageFormat.setPaper(paper);
			book.append(FractalShapeMaker.viewer.viewer, pageFormat);

			// if (pageFormat.getOrientation() == pageFormat.LANDSCAPE)
			// System.out.println("pageFormat.LANDSCAPE");
			// if (pageFormat.getOrientation() == pageFormat.PORTRAIT)
			// System.out.println("pageFormat.PORTRAIT");

			printJob.setPageable(book);


			if (pDialogState)
				printJob.print();

		} catch (java.security.AccessControlException ace)
		{
			String errmsg = "Applet access control exception; to allow "
					+ "access to printer, run policytool and set\n"
					+ "permission for \"queuePrintJob\" in " + "RuntimePermission.";
			JOptionPane.showMessageDialog(this, errmsg, "Printer Access Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

	}


	void deleteItem_actionPerformed(ActionEvent e)
	{

		int index = library.fractals.indexOf(currentFractalShape);

		if (index == -1)
		{
			JOptionPane.showMessageDialog(this, "Item not selected", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		library.fractals.remove(index);
		updateLibrary();
		selectLibraryItem(libraryViewerList.get(0));

	}


	void printShapeMenu_actionPerformed(ActionEvent e)
	{
		print();
	}


	private void print()
	{

		try
		{
			final PrinterJob printJob = PrinterJob.getPrinterJob();

			boolean pDialogState = printJob.printDialog();
			if (!pDialogState)
				return;
			
			Book book = new Book();

			PageFormat pageFormat = printJob.pageDialog(printJob.defaultPage());

			book.append(FractalShapeMaker.viewer.viewer, pageFormat);

			printJob.setPageable(book);
			printJob.setJobName("Fractal Shape Maker");

			Thread t = new Thread()
			{
				public void run() {
					try{
						printJob.print();											
					}
					catch( Exception e)
					{
						e.printStackTrace();
					}
				};
			};
			t.setName("Print thread");
			t.start();

		} catch (java.security.AccessControlException ace)
		{
			String errmsg = "Applet access control exception; to allow "
					+ "access to printer, run policytool and set\n"
					+ "permission for \"queuePrintJob\" in " + "RuntimePermission.";
			JOptionPane.showMessageDialog(this, errmsg, "Printer Access Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex)
		{
			showException("Error while printing", ex);
		}


	}


	private void showException(String message, Exception ex)
	{
		ex.printStackTrace();
		JOptionPane.showMessageDialog(FractalShapeMaker.viewer, message + ":" + ex);
	}


	void menuSave_actionPerformed(ActionEvent e)
	{
		if (currentLibraryFile == null)
		{
			menuSaveAs_actionPerformed(e);
			return;
		}

		System.out.println("save file = " + currentLibraryFile);
		try
		{
			library.save(currentLibraryFile);
		} catch (Exception ex)
		{
			ex.printStackTrace();
			JOptionPane.showMessageDialog(FractalShapeMaker.viewer,
					"Error on save\nException = " + ex);
		}
	}


	void menuItemAppendFile_actionPerformed(ActionEvent e)
	{

		JFileChooser fc = new JFileChooser((currentLibraryFile == null) ? "."
				: currentLibraryFile.getAbsolutePath());
		fc.setPreferredSize(new Dimension(500, 700));
		int ret = fc.showOpenDialog(FractalShapeMaker.viewer);
		if (ret != JFileChooser.APPROVE_OPTION)
			return;

		FractalShapeLibrary newLibrary = new FractalShapeLibrary();
		System.out.println("load file = " + fc.getSelectedFile());
		try
		{
			newLibrary.load(fc.getSelectedFile());
		} catch (Exception ex)
		{
			JOptionPane.showMessageDialog(FractalShapeMaker.viewer,
					"Error on load\nException = " + ex);
		}

		System.out.println("loaded file = " + fc.getSelectedFile());

		library.fractals.addAll(newLibrary.fractals);

		updateLibrary();
		System.out.println("updated library");
		selectLibraryItem(libraryViewerList.get(0));
		System.out.println("selected" + fc.getSelectedFile());

	}


	void deleteAllButMenuItem_actionPerformed(ActionEvent e)
	{

		library.fractals = new ArrayList<FractalShape>();
		library.fractals.add(currentFractalShape);

		updateLibrary();
		selectLibraryItem(libraryViewerList.get(0));
		setLibraryFile(null);
	}


	void setLibraryFile(File file)
	{
		currentLibraryFile = file;
		String title = (file == null) ? "" : file.getAbsolutePath();
		this.setTitle("Tools   " + title);
	}


	void newItem_actionPerformed(ActionEvent e)
	{

	}


}