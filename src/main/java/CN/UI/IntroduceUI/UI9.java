package CN.UI.IntroduceUI;

import javax.swing.*;
import java.awt.*;
/**
 * @author chenpuhao
 * @Date 2023/2/4
 */
public class UI9 extends JFrame {
    public UI9(){

        Dimension getDesktopSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((getDesktopSize.width-300)/2,(getDesktopSize.height-500)/2,300,500);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setType(JFrame.Type.UTILITY);
        this.setTitle("详细说明--西红柿");
        this.setLayout(new BorderLayout());
        ImageIcon img = new ImageIcon("Icon/MainUI/plant/success/9.png");
        JLabel label = new JLabel(img);
        JPanel imgPanel = new JPanel();
        imgPanel.setLayout(new BorderLayout());
        imgPanel.add(label);
        JPanel panel = new JPanel();
        JLabel introduce = new JLabel("西红柿炒蛋？鸡蛋呢？期待后续~");
        panel.add(introduce);
        this.add(imgPanel,BorderLayout.NORTH);
        this.add(panel);
    }
}
