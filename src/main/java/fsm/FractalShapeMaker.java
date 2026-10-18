/**
 * Title:        <p>
 * Description:  <p>
 * Copyright:    Copyright (c) <p>
 * Company:      <p>
 *
 * @author
 * @version 1.0
 */
package fsm;

import com.formdev.flatlaf.FlatDarkLaf;
import fsm.gui.FractalShapeViewerFrame;
import fsm.gui.ToolsFrame;

import javax.swing.*;
import java.awt.*;

public class FractalShapeMaker {
    public static ToolsFrame toolsFrame;
    public static FractalShapeViewerFrame viewer;
    boolean packFrame = false;


    // Construct the application
    public FractalShapeMaker() {
        toolsFrame = new ToolsFrame();
        viewer = toolsFrame.viewerFrame;
        // Validate frames that have preset sizes
        // Pack frames that have useful preferred size info, e.g. from their layout
        if (packFrame) {
            toolsFrame.pack();
            viewer.pack();
        } else {
            toolsFrame.validate();
            viewer.validate();
        }
        // Center the window

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int menuHeight = 20;

        Dimension toolsSize = toolsFrame.getSize();
        int width = screenSize.width - toolsSize.width;
        int height = screenSize.height - menuHeight;
        //int top = (screenSize.height - width) / 2;

        viewer.setLocation(10, menuHeight);
        viewer.setSize(new Dimension(width - 20, height));
        toolsFrame.setLocation(width, menuHeight);
        toolsFrame.setSize(new Dimension(toolsSize.width, height - 100));

        viewer.setVisible(true);
        toolsFrame.setVisible(true);

        toolsFrame.loadDefault();
    }


    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                    //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                    //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    UIManager.setLookAndFeel(new FlatDarkLaf());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                new FractalShapeMaker();
            }
        });

    }
}