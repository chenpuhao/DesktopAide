package EN.Function.Puppet;

import javax.swing.*;
import java.awt.*;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
public class Puppet extends JFrame {
    public Puppet(){
        this.setSize(250,180);
        ImageIcon puppetImage = new ImageIcon("Icon/Function/Puppet/1.png");
        JLabel puppet = new JLabel(puppetImage);
        this.add(puppet);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setBackground(new Color(0, 0, 0, 0));
        this.setType(Type.UTILITY);
        this.setAlwaysOnTop(true);
    }
    }
