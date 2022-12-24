 目录
 ===
<!-- TOC -->
* [目录](#目录)
  * [DesktopAide](#desktopaide)
    * [已知问题](#已知问题)
    * [注意](#注意)
    * [捐助](#捐助)
    * [软件截图](#软件截图)
    * [部分代码](#部分代码)
    * [未来计划](#未来计划)
  * [发行版本](#发行版本)
    * [V3.3.0 版本内容更新](#v330-版本内容更新)
<!-- TOC -->
中文|[英文](README-en.md)
## DesktopAide
DeskAide是一款开源的基于Windows系统的桌面程序
它可以帮助您整理桌面，查找文件，尤其适合学校教师使用
### 已知问题
**如果您在使用过程中发现了Bug，请在下表列出或者发送给chenpuhao1229@163.com，谢谢**
1. 整理桌面中默认类型不可用，请使用自定义类型
### 注意
1. 如果您可以帮助我修改代码，请使用IntelliJ IDEA，谢谢！没有IDEA?[点我去下载](https://www.jetbrains.com/zh-cn/idea/download/)
2. 后续将会放弃Java开发前端，将使用C#~~（只要作者想起来）~~

### 捐助
如果您喜欢本产品，请在爱发电为我发电，谢谢：
[点我去发电](https://afdian.net/a/desktopaide)


### 软件截图
1. #### 桌宠界面(可更换)
    ![](icon/Body.png)
2. #### 整理界面
    ![](icon/readme/collation.png)
3. #### 更多功能界面
    ![](icon/readme/more.png)
### 部分代码
src/main/java/cn/Function/Collation.java
```java
public class Collation extends Thread {
  static void readAppointedLineNumber(File sourceFile, int lineNumber) {
    FileReader in = new FileReader(sourceFile);
    LineNumberReader reader = new LineNumberReader(in);
    String s = "";
    if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {
      JOptionPane.showMessageDialog(null, "请选择桌面路径", "出现了一个错误", JOptionPane.ERROR_MESSAGE);
    }
    int lines = 0;
    while (s != null) {
      lines++;
      s = reader.readLine();
      if ((lines - lineNumber) == 0) {
        data = s;
      }
    }
    reader.close();
    in.close();
  }

}
```
src/main/java/cn/Function/Find.java
```java
public class Find extends JDialog {
  public static List<File> searchFiles(File folder, final String keyword) {
    List<File> result = new ArrayList<File>();
    if (folder.isFile())
      result.add(folder);
    File[] subFolders = folder.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        if (file.isDirectory()) {
          return true;
        }
        return file.getName().toLowerCase().contains(keyword);
      }
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

```
### 未来计划
- [x] 完成第三版程序(完成日期:2022/12/01)
- [ ] 修复整理功能的默认模式(完成日期:)
- [ ] 完成整理桌面功能新UI(完成日期:) 
- [ ] ~~使用C#进行开发(完成日期:)~~

## 发行版本

### V3.3.0 版本内容更新
1. 新UI
2. 优化大多数功能
3. 关闭聊天功能
4. [下载链接](https://github.com/chenpuhao/DesktopAide/releases/download/DesktopAide/DesktopAide-withjre-3.3-setup.exe)

