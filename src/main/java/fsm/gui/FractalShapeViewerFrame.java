/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 *
 * @author
 * @version 1.0
 */
package fsm.gui;

import fsm.FractalShapeMaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;


public class FractalShapeViewerFrame extends JFrame {

    public FractalShapeViewer viewer = new FractalShapeViewer();


    public FractalShapeViewerFrame() {

        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void redraw() {
        viewer.repaint();
        FractalShapeMaker.toolsFrame.currentLibraryItem.repaintImage();
    }


    private void jbInit() throws Exception {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setTitle("View Window");
        this.getContentPane().add(viewer, BorderLayout.CENTER);
    }


    protected void processWindowEvent(WindowEvent e) {
        super.processWindowEvent(e);
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            System.exit(0);
        }
    }


}