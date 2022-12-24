package cn.Function;

import cn.UI.MainUI;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class CollationUI extends JDialog {
    public CollationUI(MainUI UI) throws IOException {
        super(UI,"编辑",true);
        FlatLaf.setup(new FlatIntelliJLaf());
        FlatDarkFlatIJTheme.setup();
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        this.setResizable(false);
        this.setType(JFrame.Type.UTILITY);
        this.setLayout(new BorderLayout());
        this.setBounds((ds.width-500)/2,(ds.height-300)/2,500,300);
        JTextArea JTA = new JTextArea();
        JScrollPane JSP = new JScrollPane();
     //   JSP.setBounds(0,0,500,300);
        JSP.setViewportView(JTA);
        File collationUI = new File("data/collation/collationUI.data");
        if (collationUI.exists()){
            String content;
            StringBuilder builder = new StringBuilder();
            InputStreamReader streamReader = new InputStreamReader(new FileInputStream(collationUI), StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(streamReader);

            while ((content = bufferedReader.readLine()) != null)
                builder.append(content);
            JTA.setText(builder.toString());
        }
        this.add(JSP,BorderLayout.CENTER);
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(1,2,1,1));
        JButton qd = new JButton("确定");
        JButton qx = new JButton("取消");
        qx.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CollationUI.this.setVisible(false);
            }
        });
        qd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String filePath = "data/collation";
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File checkFile = new File(filePath + "/collationUI.data");
                FileWriter writer = null;
                try {
                    if (!checkFile.exists()) {
                        checkFile.createNewFile();
                    }
                    String nr = JTA.getText();
                    writer = new FileWriter(checkFile, false);
                    writer.append(nr);
                    writer.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                } finally {
                    if (null != writer) {
                        try {
                            writer.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                CollationUI.this.setVisible(false);
            }

        });
        jp.add(qd);
        jp.add(qx);
        this.add(jp,BorderLayout.SOUTH);
    }
}
