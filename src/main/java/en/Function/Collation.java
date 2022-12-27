package en.Function;

import javax.swing.*;
import java.io.*;
import java.util.Map;

public class Collation extends Thread {
    static String name1 = null;
    static String result = null;
    static String name;
    static String data;
    static Map<String, String> map = System.getenv();
    public static String userName = map.get("USERNAME");// 获取用户名
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

    public void run() {
        int lineNumber = 3;
        File sourceFile = new File("data/collation/collation.data");
        try {
            readAppointedLineNumber(sourceFile, lineNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (data.equals("false")) {
            lineNumber = 1;
            try {
                readAppointedLineNumber(sourceFile, lineNumber);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            File word = new File(data + "\\word");
            File txt = new File(data + "\\txt");
            File pdf = new File(data + "\\pdf");
            File powerpoint = new File(data + "\\powerpoint");
            File excel = new File(data + "\\excel");
            if (!pdf.exists()) {//如果文件夹不存在
                pdf.mkdir();//创建文件夹
            }
            if (!txt.exists()) {//如果文件夹不存在
                txt.mkdir();//创建文件夹
            }
            if (!word.exists()) {//如果文件夹不存在
                word.mkdir();//创建文件夹
            }
            if (!powerpoint.exists()) {//如果文件夹不存在
                powerpoint.mkdir();//创建文件夹
            }
            if (!excel.exists()) {//如果文件夹不存在
                excel.mkdir();//创建文件夹
            }

            //查找word
            //定义需要查找的父路径
            File file = new File(data);
            //定义需要查找的后缀格式(.后缀名)
            String sword = ".doc";
            String ssword = ".docx";
            String sppt = ".ppt";
            String ssppt = ".pptx";
            String sexcel = ".xls";
            String ssexcel = ".xlsx";
            String stxt = ".txt";
            String spdf = ".pdf";
            //调用查找XXX.后缀结尾的所有文件
            name = "word";
            findEndsWith(file, sword);
            name = "powerpoint";
            findEndsWith(file, sppt);
            name = "powerpoint";
            findEndsWith(file, ssppt);
            name = "excel";
            findEndsWith(file, sexcel);
            name = "excel";
            findEndsWith(file, ssexcel);
            name = "word";
            findEndsWith(file, ssword);
            name = "txt";
            findEndsWith(file, stxt);
            name = "pdf";
            findEndsWith(file, spdf);
        }else{
            lineNumber = 1;
            try {
                readAppointedLineNumber(sourceFile, lineNumber);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            FileInputStream fis;
            try {
                fis = new FileInputStream("data/collation/collationUI.data");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            //Construct BufferedReader from InputStreamReader
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int a = line.indexOf(" ");
                name1 = line.substring(0,a);
                result = line.substring(a+1);

                File path = new File(data+"\\"+result);
                if(!path.exists()){
                    path.mkdir();
                }
                findEndsWith(new File(data),"."+name1);
            }

            try {
                br.close();
                fis.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void findEndsWith(File file, String sword) {
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
                    File doc;
                    int lineNumber = 3;
                    File sourceFile = new File("data/collation/collation.data");
                    try {
                        readAppointedLineNumber(sourceFile, lineNumber);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    if (data.equals("false")) {
                        lineNumber = 1;
                        try {
                            readAppointedLineNumber(sourceFile, lineNumber);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        doc = new File(data + "\\" + name + "文件\\" + i.getName());
                    }else{
                        lineNumber = 1;
                        try {
                            readAppointedLineNumber(sourceFile, lineNumber);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        doc = new File(data + "\\" + result + "\\" + i.getName());
                    }
                    File docp = new File(i.getPath());
                    docp.renameTo(doc);
                }

            }
        }
    }
}
