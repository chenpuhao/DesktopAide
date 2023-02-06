package EN.Function.Update;

import java.io.*;
import java.net.URL;

/**
 * @author chenpuhao
 * @Date 2023/2/6
 */
public class CheckUpdate {

    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
    static String result;
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
                result = s;
            }
        }
        reader.close();
        in.close();
    }

    public String CheckUpdate() throws IOException {
        File file = new File("update.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        downloadUsingStream("https://desktopaide.chenpuhao.me/update.txt",file.toString());
        readAppointedLineNumber(file);
        file.delete();
        if(result.equals("3.4.1")){
            result = "true";
        }
        return result;
    }
    }

