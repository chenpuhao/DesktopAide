package cn.Function;
import javax.swing.*;
import java.io.File;
public class Cleaner  extends Thread{
    static int flag = 1;//用来判断文件是否删除成功
    static String using;

    public Cleaner() {
    }

    public void run() {
        //删除一个文件夹下的所有文件(包括子目录内的文件)
        File c = new File("c:");
        long freeSpace = c.getFreeSpace();
        long buse = freeSpace / 1024 / 1024 / 1024;
        File file = new File("C:\\Windows\\Temp");//输入要删除文件目录的绝对路径
        deleteFile(file);
        File file1 = new File("C:\\Windows\\system32\\logfiles");//输入要删除文件目录的绝对路径
        deleteFile(file1);
        File file3 = new File("C:\\Windows\\Prefetch");//输入要删除文件目录的绝对路径
        deleteFile(file3);
        File file4 = new File("C:\\Windows\\winsxs\\backup");//输入要删除文件目录的绝对路径
        deleteFile(file4);
        long afreeSpace = c.getFreeSpace();
        long ause = afreeSpace / 1024 / 1024 / 1024;
        long used = buse - ause;
        using = used + "G";
        JOptionPane.showMessageDialog (null, "本次一共清理了"+using, "清理完成", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void deleteFile(File file){
        //判断文件不为null或文件目录存在
        if (file.listFiles() == null){
            flag = 0;
            return;
        }else {
            //取得这个目录下的所有子文件对象
            File[] files = file.listFiles();
            //遍历该目录下的文件对象
            for (File f: files){
                //判断子目录是否存在子目录,如果是文件则删除
                if (f.isDirectory()){
                    deleteFile(f);
                }else {
                    f.delete();
                }
            }
            //删除空文件夹  for循环已经把上一层节点的目录清空。
            file.delete();
        }
    }
}
