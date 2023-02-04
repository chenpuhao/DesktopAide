package EN.UI.IntroduceUI;

import javax.swing.*;
import java.awt.*;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
public class UI6 extends JFrame {
    public UI6(){

        Dimension getDesktopSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((getDesktopSize.width-300)/2,(getDesktopSize.height-500)/2,300,500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setType(Type.UTILITY);
        this.setTitle("Details -- cabbage");
        this.setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon("Icon/MainUI/plant/success/6.png");
        JLabel label = new JLabel(img);
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.add(label);
        JPanel panel = new JPanel();
        JLabel introduce = new JLabel("Plump cabbage, what can be in it?");
        panel.add(introduce);
        this.add(imgPanel,BorderLayout.NORTH);
        this.add(panel);
    }
}
