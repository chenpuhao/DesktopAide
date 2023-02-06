package CN.UI.FunctionPanel;

import CN.Function.Puppet.Puppet;
import CN.Function.Puppet.PuppetRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/**
 * @author chenpuhao
 * @Date 2023/2/4
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class puppetPanel extends JPanel {
    public static JRadioButton yes = new JRadioButton("开启");
    static String result;
    public static PuppetRun puppetRun ;

    static void readAppointedLineNumber(File sourceFile, int lineNumber)
            throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = "";
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
            if((lines - lineNumber) == 0) {
                result = s;
            }
        }
        reader.close();
        in.close();
    }
    public static Puppet puppet = new Puppet();
    public puppetPanel() throws IOException {
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        File folderPath = new File("C:\\Users\\"+userName+"\\.DesktopAide\\puppet");
        File filePath = new File(folderPath+"\\puppet.da");
        if(!folderPath.exists()) {
            folderPath.mkdirs();
        }
        if(!filePath.exists()){
            filePath.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write("false".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        //定义组件
        JLabel introduce = new JLabel("打开或关闭鼠标傀儡功能");
        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton no = new JRadioButton("关闭");
        buttonGroup.add(yes);
        buttonGroup.add(no);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        buttonPanel.add(yes);
        buttonPanel.add(no);
        JButton save = new JButton("保存");

        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if(yes.isSelected()){
                    PuppetRun.exit = false;
                    puppetRun = new PuppetRun();
                    puppetRun.start();
                }else{
                    PuppetRun.exit = true;

                }
            }
        });
        this.setLayout(new GridLayout(3,1));
        this.add(introduce);
        this.add(buttonPanel);
        this.add(save);
        int line = 1;
        readAppointedLineNumber(filePath,line);
        if(result.equals("true")){
            yes.setSelected(true);
        }else{
            no.setSelected(true);
        }
    }
}
