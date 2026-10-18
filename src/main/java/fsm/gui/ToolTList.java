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

import fsm.model.TransformationSequence;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class ToolTList extends JPanel {

    int currentSelectionIndex = -1;
    GridLayout gridLayout1 = new GridLayout();
    private ToolsFrame toolsFrame = null;


    public ToolTList() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void update() {
        if (toolsFrame.currentFractalShape == null)
            return;

        removeAll();

        Iterator i = toolsFrame.currentFractalShape.transforms.iterator();
        while (i.hasNext()) {
            ToolTRow row = new ToolTRow((TransformationSequence) i.next());
            add(row);
        }
        validate();

        // System.out.println("update");

    }


    public void setToolsFrame(ToolsFrame toolsFrame) {
        this.toolsFrame = toolsFrame;
        update();
    }


    public void remove() {
        toolsFrame.currentFractalShape.elementShapes.remove(currentSelectionIndex);
    }


    private void jbInit() throws Exception {
        gridLayout1.setColumns(1);
        gridLayout1.setRows(0);
        this.setLayout(gridLayout1);
    }


}