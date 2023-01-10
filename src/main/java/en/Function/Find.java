package en.Function;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;
import en.UI.MainUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Find extends JDialog {
    static final JTextField jt = new JTextField();
    static final JTextField jt1 = new JTextField();
    static File file = new File(jt.getText());
    static String s = jt1.getText();
    public Find(MainUI UI) {
        super(UI, null, true);
        FlatLaf.setup(new FlatIntelliJLaf());
        FlatDarkFlatIJTheme.setup();
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((screen.width-500)/2,(screen.height-300)/2,500,300);
        //	setAlwaysOnTop(true);
        setResizable(false);
        setType(Type.UTILITY);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JLabel jl = new JLabel("The directory you need to find, such as C:\\Users\\" + Collation.userName + "\\Desktop");
        JLabel jl1 = new JLabel("The file name you need to look for, such as DesktopAide.exe");
        JButton jb = new JButton("Sure");
        JCheckBox jc =new JCheckBox("Do you want to open the found file directly?");
        jb.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                file = new File(jt.getText());
                File file3 = new File(file.toURI());
                s = jt1.getText();
                List<File> files = searchFiles(file3, s );
                for (File file : files) {
                    File file1 = new File(file.getAbsolutePath());
                    if(jc.isSelected()) {
                        try {
                            Desktop.getDesktop().open(new File(file1.toURI()));
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });
        JPanel hp = new JPanel();
        hp.setLayout(new GridLayout (6 , 1 , 10 , 10));
        hp.add(jl);
        hp.add(jt);
        hp.add(jl1);
        hp.add(jt1);
        hp.add(jc);
        hp.add(jb);
        add(hp);
    }
    public static List<File> searchFiles(File folder, final String keyword) {
        List<File> result = new ArrayList<>();
        if (folder.isFile())
            result.add(folder);
        File[] subFolders = folder.listFiles(file -> {
            if (file.isDirectory()) {
                return true;
            }
            return file.getName().toLowerCase().contains(keyword);
        });
        if (subFolders != null) {
            for (File file : subFolders) {
                if (file.isFile()) {
                    // 如果是文件则将文件添加到结果列表中
                    result.add(file);
                } else {
                    // 如果是文件夹，则递归调用本方法，然后把所有的文件加到结果列表中
                    result.addAll(searchFiles(file, keyword));
                }
            }
        }
        return result;
    }
}
