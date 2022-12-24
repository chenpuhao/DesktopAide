package cn.UI;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Setting extends JDialog {
    public Setting(MainUI UI){
        super(UI,"设置",true);
        FlatLaf.setup(new FlatIntelliJLaf());
        FlatDarkFlatIJTheme.setup();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screen.width-600)/2,(screen.height-500)/2,600,500);
        this.setResizable(false);
        this.setLayout(new GridLayout(5,3,10,10));
        JButton YHXY = new JButton("用户协议");
        YHXY.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                YHXY yhxy = new YHXY(null);
                yhxy.setVisible(true);
            }
        });
        this.add(YHXY);
    }
}
