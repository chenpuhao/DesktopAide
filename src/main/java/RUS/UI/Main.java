package RUS.UI;

import RUS.Function.Collation.Collation;
import RUS.Function.Growth.Growth;
import RUS.UI.MainUI.HelloUI;
import RUS.UI.MainUI.MainUI;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.intellijthemes.FlatDarkFlatIJTheme;

import javax.swing.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;

/**
 * @version 3.2.x
 * @author chenpuhao
 * @Date 2023/2/4
 */

@SuppressWarnings({"ResultOfMethodCallIgnored", "InfiniteLoopStatement"})
public class Main {
    public static String readToString(String fileName) {
        String encoding = "UTF-8";
        File file = new File(fileName);
        long filelength = file.length();
        byte[] filecontent = new byte[(int) filelength];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        //使用look and feel
        FlatDarkLaf.setup();
        FlatDarculaLaf.setup();
        FlatDarkFlatIJTheme.setup();
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
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                String path = "C:\\Users\\" + userName + "\\Desktop";
                fileOutputStream.write(path.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        folderPath = new File("C:\\Users\\"+userName+"\\.DesktopAide\\growth");
        filePath = new File(folderPath+"\\growth.da");
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
        folderPath = new File("C:\\Users\\"+userName+"\\.DesktopAide\\puppet");
        filePath = new File(folderPath+"\\puppet.da");
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
        folderPath = new File("C:\\Users\\" + userName + "\\.DesktopAide\\user");
        filePath = new File(folderPath + "\\user.da");
        if(!filePath.exists()){
            HelloUI helloUI = new HelloUI();
            helloUI.setVisible(true);
        }
        MainUI mainUI = new MainUI();
        mainUI.setVisible(true);
        while (true) {
            if(MainUI.isInCollation){
                Collation.Collation();
            }
            //桌宠
            ImageIcon petImage;
            Growth growth = new Growth();
            int growthInt = Integer.parseInt(growth.growth());
            if (growthInt == 100) {
                folderPath = new File("Icon/MainUI/plant/success");
                File[] list = folderPath.listFiles();
                int fileCount = 0;
                assert list != null;
                for (File file : list) {
                    if (file.isFile()) {
                        fileCount++;
                    }
                }
                Random getSuccessPlant = new Random();
                int successPlantInt = getSuccessPlant.nextInt(fileCount-1);
                petImage = new ImageIcon(folderPath+"/"+successPlantInt+".png");
                MainUI.tablePet.setIcon(petImage);
                JOptionPane.showMessageDialog(null,"Поздравляю с успешными посадами, следующий раунд будет проведен после закрытия этого окна, и записи о посадах можно посмотреть в пользовательском интерфейсе","Успешно посажено.",JOptionPane.PLAIN_MESSAGE,petImage);
                File path = new File("C:\\Users\\"+userName+"\\.DesktopAide\\growth");
                filePath = new File(path+"\\growth.da");
                FileOutputStream fileOutputStream = new FileOutputStream(filePath);
                fileOutputStream.write("0".getBytes(StandardCharsets.UTF_8));
                fileOutputStream.flush();
                fileOutputStream.close();
                path = new File("C:\\Users\\"+userName+"\\.DesktopAide\\user");
                filePath = new File(path+"\\success.da");
                String result = readToString(String.valueOf(filePath));
                if(!(result ==null)) {
                    result = result + "\n" + successPlantInt + ".png";
                }else{
                    result = successPlantInt + ".png";
                }
                fileOutputStream = new FileOutputStream(filePath);
                fileOutputStream.write(result.getBytes(StandardCharsets.UTF_8));
                fileOutputStream.flush();
                fileOutputStream.close();
            } else if (growthInt < 100 && growthInt >=50) {
                petImage = new ImageIcon("Icon/MainUI/plant/growing/100of100.png");
            }else{
                petImage = new ImageIcon("Icon/MainUI/plant/growing/50of100.png");
            }
            MainUI.tablePet.setIcon(petImage);
            mainUI.revalidate();
            mainUI.repaint();
            Thread.sleep(500);
        }
    }

}
