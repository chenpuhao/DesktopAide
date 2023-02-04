package RUS.Function.Growth;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class Growth {
    static String result;
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
    public String growth() throws IOException {

        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        File folderPath = new File("C:\\Users\\"+userName+"\\.DesktopAide\\growth");
        File filePath = new File(folderPath+"\\growth.da");
        if(!folderPath.exists()) {
            folderPath.mkdirs();
        }
        if(!filePath.exists()){
            filePath.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write("0".getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
        }
        int needReadLine = 1;
        readAppointedLineNumber(filePath,needReadLine);
            return result;
    }
}
