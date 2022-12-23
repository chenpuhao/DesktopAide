package Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CollationNewUI extends JDialog {
    static double X;
    static double Y;
    public static void main(String[] args) {

        JFrame jf = new JFrame();
        Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setBounds(sc.width-600, sc.height-400,600,400);
        jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jf.setUndecorated(true);
        jf.setBackground(new Color(61, 255, 0, 118));
        jf.setResizable(false);
        jf.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                X = e.getPoint().getX();
                Y = e.getPoint().getY();

            }
        });
        jf.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                jf.setLocation((int) (e.getXOnScreen() - X), (int) (e.getYOnScreen() - Y));

            }
        });
        jf.setLayout(new BorderLayout());
        //时间
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        JLabel time = new JLabel(formatter.format(date),JLabel.CENTER);
        time.setFont(new Font("宋体", Font.PLAIN, 20));
        jf.add(time,BorderLayout.NORTH);
        jf.setVisible(true);
        while(true){
            date = new Date(System.currentTimeMillis());
            time.setText(formatter.format(date));
        }
    }
}
