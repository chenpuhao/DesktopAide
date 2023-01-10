package cn.UI;

import cn.Function.Communicate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

@SuppressWarnings({"InfiniteLoopStatement", "BusyWait"})
public class MainUI extends JFrame {
    static String data;
    static double X ;
    static double Y ;
public static final JPanel Function = new JPanel();
    public static final JFrame MainUI = new JFrame();
   public static final ImageIcon Body = new ImageIcon("icon/Body.png");
   public  static JLabel BodyLabel = new JLabel(Body);


    static void readAppointedLineNumber(File sourceFile, int lineNumber)
            throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = "";
        if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {
            JOptionPane.showMessageDialog (null, "请选择桌面路径", "出现了一个错误", JOptionPane.ERROR_MESSAGE);
        }
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
            if((lines - lineNumber) == 0) {
                data = s;
            }
        }
        reader.close();
        in.close();
    }
    static int getTotalLines(File file) throws IOException {
        FileReader in = new FileReader(file);
        LineNumberReader reader = new LineNumberReader(in);
        String s = reader.readLine();
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
        }
        reader.close();
        in.close();
        return lines;
    }

    public static void main(String[] args) throws InterruptedException {
        MainUI UI = new MainUI();
        Dimension Screen = Toolkit.getDefaultToolkit().getScreenSize();
        MainUI.setLayout(new BorderLayout());
        File path = new File("data/MainUI/MainUI.data");
        if(path.exists()){
            int lineNumber = 1;
            File sourceFile = new File(path.toURI());
            try {
                readAppointedLineNumber(sourceFile, lineNumber);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int X= Integer.parseInt(data);
            int lineNumber2 = 2;
            try {
                readAppointedLineNumber(sourceFile, lineNumber2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            int Y = Integer.parseInt(data);
            MainUI.setBounds(X,Y,193,240);
        }else {
            MainUI.setBounds(Screen.width / 3, Screen.height / 2, 193, 240);
        }
        MainUI.setUndecorated(true);
        MainUI.setBackground(new Color(0,0,0, 0));
        MainUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        MainUI.setType(JFrame.Type.UTILITY);
        //Body


        //设置拖动
        MainUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    X = e.getPoint().getX();
                    Y = e.getPoint().getY();

            }
        });
        MainUI.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                    MainUI.setLocation((int) (e.getXOnScreen() - X), (int) (e.getYOnScreen() - Y));

            }
        });
        ImageIcon IconCollation = new ImageIcon("icon/Function/Collation.png");
        JButton Collation = new JButton(IconCollation);
        Collation.setContentAreaFilled(false);
        Collation.setBorderPainted(false);
        ImageIcon IconCommunicate = new ImageIcon("icon/Function/Communicate.png");
        JButton Communicate = new JButton(IconCommunicate);
        Communicate.setContentAreaFilled(false);
        Communicate.setBorderPainted(false);
        ImageIcon IconMore = new ImageIcon("icon/Function/More.png");
        JButton More = new JButton(IconMore);
        More.setContentAreaFilled(false);
        More.setBorderPainted(false);
        ImageIcon IconCancel = new ImageIcon("icon/Function/cancel.png");
        JButton Cancel = new JButton(IconCancel);
        Cancel.setContentAreaFilled(false);
        Cancel.setBorderPainted(false);

        Function.setPreferredSize(new Dimension(20, 240));
        Function.setBackground(new Color(0,0,0,0));
        Function.setLayout(new BorderLayout());
        JPanel Function2 = new JPanel();
        Function2.setBackground(new Color(0,0,0,0));
        Function2.setLayout(new GridLayout(4,1,1,1));
        Function2.add(Collation);
        Function2.add(Communicate);
        Function2.add(More);
        Function2.add(Cancel);
        Collation.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Collation collation = new Collation(UI);
                collation.setVisible(true);
            }
        });
        Communicate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cn.Function.Communicate communicate = new Communicate();
                communicate.run();
            }
        });
        More.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cn.UI.More more = new More(UI);
                more.setVisible(true);
            }
        });
        Cancel.addMouseListener(new MouseAdapter() {
            @SuppressWarnings("ResultOfMethodCallIgnored")
            @Override
            public void mouseClicked(MouseEvent e) {
                int X = (int) e.getLocationOnScreen().getX()-183;
                int Y = (int) e.getLocationOnScreen().getY()-220;
                String X1 = String.valueOf(X);
                String Y1 = String.valueOf(Y);
                String filePath = "data/MainUI";
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File checkFile = new File(filePath + "/MainUI.data");
                FileWriter writer = null;
                try {
                    if (!checkFile.exists()) {
                        checkFile.createNewFile();
                    }
                    writer = new FileWriter(checkFile, false);
                    writer.append(X1);
                    writer.append("\n");
                    writer.append(Y1);
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    if (null != writer) {
                        try {
                            writer.close();
                        } catch (IOException ex) {
                            //noinspection ThrowFromFinallyBlock
                            throw new RuntimeException(ex);
                        }
                    }
                }
                System.exit(0);
            }
        });
        Function.add(Function2,BorderLayout.SOUTH);
        MainUI.add(Function,BorderLayout.EAST);
        MainUI.add(BodyLabel,BorderLayout.CENTER);
        MainUI.setVisible(true);
        while (true) {
            File sourceFile = new File("data/collation/collation.data");
            if (sourceFile.exists()) {
                int lineNumber = 2;
                try {
                    readAppointedLineNumber(sourceFile, lineNumber);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                if (data.equals("true")) {
                    cn.Function.Collation collation = new cn.Function.Collation();
                    collation.Collation();
                    Thread.sleep(500);
                }
            }
        }
    }
}
