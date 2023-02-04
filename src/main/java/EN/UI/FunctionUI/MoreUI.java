package EN.UI.FunctionUI;

import EN.UI.FunctionPanel.CollationPanel;
import EN.UI.FunctionPanel.FindPanel;
import EN.UI.FunctionPanel.puppetPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
public class MoreUI extends JFrame {
   public MoreUI(){
       //获取桌面大小
       Dimension getScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
       //基本设置
       this.setBounds((getScreenSize.width-600)/2,(getScreenSize.height-500)/2,600,500);
       this.setDefaultCloseOperation(HIDE_ON_CLOSE);
       this.setResizable(false);
       JPanel moreUIPanel = new JPanel();
       moreUIPanel.setLayout(new BorderLayout());
       ImageIcon title = new ImageIcon("Icon/MoreUI/favicon.png");
       this.setIconImage(title.getImage());
       JScrollPane jsp = new JScrollPane();
       JPanel leftSetting = new JPanel();
       jsp.setViewportView(leftSetting);
       leftSetting.setLayout(new GridLayout(3,1));
       //添加整理的设置
       ImageIcon collationImage = new ImageIcon("Icon/MoreUI/collation.png");
       JButton collation = new JButton("Collate file Settings");
       collation.setIcon(collationImage);
       collation.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               moreUIPanel.removeAll();
               moreUIPanel.setLayout(new BorderLayout());
               moreUIPanel.add(leftSetting,BorderLayout.WEST);
               CollationPanel collationPanel;
               try {
                   collationPanel = new CollationPanel();
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               }
               moreUIPanel.add(collationPanel,BorderLayout.CENTER);
               MoreUI.this.revalidate();
               MoreUI.this.repaint();
           }
       });
       leftSetting.add(collation);
       //添加QQ机器人设置
       ImageIcon qqImage = new ImageIcon("Icon/MoreUI/hand.png");
       JButton qq = new JButton("Mouse puppet");
       qq.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               moreUIPanel.removeAll();
               moreUIPanel.setLayout(new BorderLayout());
               moreUIPanel.add(leftSetting,BorderLayout.WEST);
               puppetPanel handPanel;
               try {
                   handPanel = new puppetPanel();
               } catch (IOException ex) {
                   throw new RuntimeException(ex);
               }
               moreUIPanel.add(handPanel,BorderLayout.CENTER);
               MoreUI.this.revalidate();
               MoreUI.this.repaint();
           }
       });
       qq.setIcon(qqImage);
       leftSetting.add(qq);
       //查找文件
       ImageIcon searchImage = new ImageIcon("Icon/MoreUI/search.png");
       JButton search = new JButton("Find file");
       search.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               moreUIPanel.removeAll();
               moreUIPanel.setLayout(new BorderLayout());
               moreUIPanel.add(leftSetting,BorderLayout.WEST);
               FindPanel findPanel;
               findPanel = new FindPanel();
               moreUIPanel.add(findPanel,BorderLayout.CENTER);
               MoreUI.this.revalidate();
               MoreUI.this.repaint();
           }
       });
       search.setIcon(searchImage);
       leftSetting.add(search);
       moreUIPanel.add(jsp,BorderLayout.WEST);
       this.add(moreUIPanel);
   }
}
