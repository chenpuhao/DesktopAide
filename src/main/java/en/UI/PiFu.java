package en.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class PiFu extends JDialog {
    public PiFu(MainUI UI) {
        super(UI, "Modify the skin", true);
        FlatLaf.setup(new FlatIntelliJLaf());
        FlatDarkFlatIJTheme.setup();
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        this.setType(Type.UTILITY);
        this.setLayout(new BorderLayout());
        this.setBounds((ds.width - 500) / 2, (ds.height - 300) / 2, 500, 300);
        File file = new File("icon/pictures");
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdir();
        }
        JLabel info = new JLabel("Please select an image:");
        this.add(info,BorderLayout.NORTH);
        JScrollPane jsp = new JScrollPane();
        JPanel jp = new JPanel();
        ButtonGroup bg = new ButtonGroup();
        String[] list = file.list();
        assert list != null;
        for (String s : list) {
            ImageIcon img = new ImageIcon(file + "\\" + s);
            File file2 = new File(file + "\\" + s);
            //noinspection StatementWithEmptyBody
            if (file2.isDirectory()) {
            } else {
                JButton get = new JButton(s);
                get.setIcon(img);
                get.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        String name = get.getText();
                        MainUI.MainUI.remove(MainUI.BodyLabel);
                        ImageIcon i = new ImageIcon("icon/pictures/" + name);
                        MainUI.BodyLabel = new JLabel(i);
                        MainUI.MainUI.add(MainUI.BodyLabel, BorderLayout.CENTER);
                        MainUI.MainUI.revalidate();
                        MainUI.MainUI.repaint();
                        PiFu.this.setVisible(false);
                    }
                });
                bg.add(get);
                jp.setLayout(new GridLayout(list.length, 1, 1, 1));
                jsp.setViewportView(jp);
                jp.add(get);
            }
            this.add(jsp, BorderLayout.CENTER);
        }
        JPanel button = new JPanel();
        button.setLayout(new GridLayout(1,3,1,1));
        JButton mr =new JButton("Modify the default image");
        mr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Compress your image to 173x220 and rename it to Body.png then replace the original image in the directory", "note", JOptionPane.INFORMATION_MESSAGE);
                File file =new File("icon");
                try {
                    Desktop.getDesktop().open(new File(file.toURI()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton ml = new JButton("Open the table of contents");
        ml.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                File file =new File("icon/pictures");
                try {
                    Desktop.getDesktop().open(new File(file.toURI()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        JButton no = new JButton("Cancel");
        no.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PiFu.this.setVisible(false);
            }
        });
        button.add(mr);
        button.add(ml);
        button.add(no);
        this.add(button,BorderLayout.SOUTH);
    }
}

