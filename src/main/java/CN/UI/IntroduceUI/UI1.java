package CN.UI.IntroduceUI;

import javax.swing.*;
import java.awt.*;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
public class UI1 extends JFrame {
    public UI1(){

        Dimension getDesktopSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((getDesktopSize.width-300)/2,(getDesktopSize.height-500)/2,300,500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setType(JFrame.Type.UTILITY);
        this.setTitle("详细说明--四叶草");
        this.setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon("Icon/MainUI/plant/success/1.png");
        JLabel label = new JLabel(img);
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.add(label);
        JPanel panel = new JPanel();
        JLabel introduce = new JLabel("象征着幸运的四叶草，或许，今天会有好运出现？");
        panel.add(introduce);
        this.add(imgPanel,BorderLayout.NORTH);
        this.add(panel);
    }
}

