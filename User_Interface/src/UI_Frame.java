
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class UI_Frame {

    JFrame uiFrame;
    JPanel uiPanel;

    public void ShowFrame() {

        uiFrame = new JFrame();
        uiPanel = new JPanel();

        // get user's screen resolution
        ScreenResolution sr = getScreenResolution();

        //set default exit process
        uiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        uiFrame.setTitle("IEC61499 Debug");
        uiFrame.setSize(sr.getWidth(), sr.getHeight());
        uiFrame.setMinimumSize(new Dimension(1000, 600));
        uiFrame.setLayout(new BorderLayout());

        //Test Component, remove it before final product
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBorder(BorderFactory.createTitledBorder("Panel 1"));
        uiFrame.add(panel, BorderLayout.WEST);
        
//        JButton button = new JButton("");
//        button.setBackground(Color.red);
//        button.setBorderPainted(false);
//        button.setOpaque(true);
//        button.setLocation(302, 320);
//        button.setSize(10, 10);

        DisplayCanvas cvs = new DisplayCanvas();
        cvs.setBackground(Color.WHITE);
        cvs.setAutoscrolls(true);
        cvs.setLayout(null);
        cvs.setPreferredSize(new Dimension(3000, 3000));
        cvs.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                System.out.println(event.getX());
                System.out.println(event.getY());
            }
        });
        //uiFrame.add(cvs,BorderLayout.CENTER);
//        cvs.add(button);

        JScrollPane scroll = new JScrollPane(cvs, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scroll.setBorder(BorderFactory.createTitledBorder("Panel 2"));

        uiFrame.add(scroll, BorderLayout.CENTER);

        // set the frame always show at the center point of the screen.
        uiFrame.setLocationRelativeTo(null);
        uiFrame.setVisible(true);
    }

    public ScreenResolution getScreenResolution() {

        // the default screenresolution value would be 1000 * 1000;
        ScreenResolution sr = new ScreenResolution();

        try {
            // get multi-monitor configuration (default one)
            GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

            sr.setWidth(gd.getDisplayMode().getWidth());
            sr.setHeight(gd.getDisplayMode().getHeight());

        } catch (Error e) {
            System.err.println(e.getLocalizedMessage());
            System.err.println("Could Not Found Monitor, Default Size will be used.");
        }

        return sr;
    }

}
