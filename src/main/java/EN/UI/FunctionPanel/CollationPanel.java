package EN.UI.FunctionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class CollationPanel extends JPanel {
    static String data;
    static void readAppointedLineNumber(File sourceFile)
            throws IOException {
        FileReader in = new FileReader(sourceFile);
        LineNumberReader reader = new LineNumberReader(in);
        String s = "";
        int lines = 0;
        while (s != null) {
            lines++;
            s = reader.readLine();
            if((lines - 1) == 0) {
                data = s;
            }
        }
        reader.close();
        in.close();
    }
    public CollationPanel() throws IOException {
        this.setLayout(new BorderLayout());
        //定义组件
        //桌面路径
        JPanel path = new JPanel();
        path.setLayout(new GridLayout(4,1));
        JLabel desktopPath = new JLabel("Select your desktop path:");
        ButtonGroup pathButtonGroup = new ButtonGroup();
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        JRadioButton desktop = new JRadioButton("C:\\Users\\" + userName + "\\Desktop");
        JRadioButton oneDrive = new JRadioButton("C:\\Users\\" + userName + "\\OneDrive\\Desktop");
        JRadioButton other  = new JRadioButton("other:");
        pathButtonGroup.add(desktop);
        pathButtonGroup.add(oneDrive);
        pathButtonGroup.add(other);
        JTextField otherTxt = new JTextField();
        File folderPath = new File("C:\\Users\\" + userName + "\\.DesktopAide\\collation");
        File filePath = new File(folderPath + "\\path.da");
        if(filePath.exists()) {
            readAppointedLineNumber(filePath);
            if (data.equals("C:\\Users\\" + userName + "\\Desktop")) {
                desktop.setSelected(true);
            } else if (data.equals("C:\\Users\\" + userName + "\\OneDrive\\Desktop")) {
                oneDrive.setSelected(true);
            } else {
                other.setSelected(true);
                otherTxt.setText(data);
            }
        }
        JPanel otherPanel = new JPanel();
        otherPanel.setLayout(new BorderLayout());
        otherPanel.add(other,BorderLayout.WEST);
        otherPanel.add(otherTxt,BorderLayout.CENTER);
        path.add(desktopPath);
        path.add(desktop);
        path.add(oneDrive);
        path.add(otherPanel);
        //配置后缀
        JPanel methodPanel = new JPanel();
        methodPanel.setLayout(new BorderLayout());
        JLabel method = new JLabel("The format of file suffix \n  folder name [space] suffix, for example, ppt .ppt");
        JTextArea methodArea = new JTextArea();
        filePath = new File(folderPath + "\\data.da");
        if (filePath.exists()){
            long fileLengthLong = filePath.length();
            byte[] fileContent = new byte[(int) fileLengthLong];
            try {
                FileInputStream inputStream = new FileInputStream(filePath);
                inputStream.read(fileContent);
                inputStream.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            String string = new String(fileContent);
            methodArea.setText(string);
        }
        JScrollPane methodJSP = new JScrollPane(methodArea);
        JPanel methodCheck = new JPanel();
        methodCheck.setLayout(new GridLayout(1,2));
        JButton methodSave = new JButton("save");
        methodSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!desktop.isSelected() && !oneDrive.isSelected() && !other.isSelected()) {
                    JOptionPane.showMessageDialog(null,"Select a desktop path "," Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    Map<String, String> map = System.getenv();
                    String userName = map.get("USERNAME");
                    File folderPath = new File("C:\\Users\\" + userName + "\\.DesktopAide\\collation");
                    File filePath = new File(folderPath + "\\path.da");
                    if(!folderPath.exists()){
                        folderPath.mkdirs();
                    }
                    if (!filePath.exists()) {
                        try {
                            filePath.createNewFile();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (desktop.isSelected()) {
                        FileOutputStream fileOutputStream;
                        try {
                            fileOutputStream = new FileOutputStream(filePath);
                            fileOutputStream.write(desktop.getText().getBytes(StandardCharsets.UTF_8));
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (oneDrive.isSelected()) {
                        FileOutputStream fileOutputStream;
                        try {
                            fileOutputStream = new FileOutputStream(filePath);
                            fileOutputStream.write(oneDrive.getText().getBytes(StandardCharsets.UTF_8));
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    if (other.isSelected()) {
                        FileOutputStream fileOutputStream;
                        try {
                            fileOutputStream = new FileOutputStream(filePath);
                            fileOutputStream.write(otherTxt.getText().getBytes(StandardCharsets.UTF_8));
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    filePath = new File(folderPath + "\\data.da");
                    if(!filePath.exists()){
                        try {
                            filePath.createNewFile();
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream = new FileOutputStream(filePath);
                        fileOutputStream.write(methodArea.getText().getBytes(StandardCharsets.UTF_8));
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    JOptionPane.showMessageDialog(null,"Save successfully!" ," Success!",JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        JButton methodClean = new JButton("clean");
        methodClean.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                methodArea.setText("");
            }
        });
        methodCheck.add(methodSave);
        methodCheck.add(methodClean);
        methodPanel.add(method,BorderLayout.NORTH);
        methodPanel.add(methodJSP,BorderLayout.CENTER);
        methodPanel.add(methodCheck,BorderLayout.SOUTH);
        this.add(path,BorderLayout.NORTH);
        this.add(methodPanel,BorderLayout.CENTER);
    }
}
