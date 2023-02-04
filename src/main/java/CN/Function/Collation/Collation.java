package CN.Function.Collation;

import java.io.*;
import java.util.Map;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Collation {
    static String name;
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

    public static void findEndsWith(File file, String sword) throws IOException {
        //将父文件下的所有文件夹以及文件存入File[]中
        File[] files = file.listFiles();
        //遍历所有文件夹以及文件
        assert files != null;
        for(File i : files) {
            //判断是否是文件夹
            if(i.isDirectory()) {
                //是文件夹，递归
                //findEndsWith(i,sword);
            }else {
                //是文件，判断是否是以.后缀名结尾
                if(i.getPath().endsWith(sword)) {
                    Map<String, String> map = System.getenv();
                    String userName = map.get("USERNAME");
                    File folderPath = new File("C:\\Users\\"+userName+"\\.DesktopAide\\collation");
                    File filePath = new File(folderPath+"\\path.da");
                    readAppointedLineNumber(filePath);
                    File doc = new File(data+"\\"+name+"\\"+i.getName());
                    File docp = new File(i.getPath());
                    docp.renameTo(doc);
                }

            }
        }
    }
    public static void Collation() throws IOException {
        BufferedReader reader;
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        File folderPath = new File("C:\\Users\\"+userName+"\\.DesktopAide\\collation");
        File filePath = new File(folderPath+"\\path.da");
        readAppointedLineNumber(filePath);
        try {
            reader = new BufferedReader(new FileReader(
                    folderPath+"\\data.da"));
            String line = reader.readLine();
            while (line != null) {
                int a = line.indexOf(" ");
                name = line.substring(0,a);
                String sword = line.substring(a+1);
                File desktopFolder = new File(data+"\\"+name);
                if (!desktopFolder.exists()){
                    desktopFolder.mkdirs();
                }
                findEndsWith(new File(data),sword);
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
