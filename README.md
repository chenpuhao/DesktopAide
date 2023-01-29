

<br />

<p align="center">
  <a href="https://github.com/chenpuhao/DesktopAide">
    <img src="icon/DesktopAide.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">DesktopAide</h3>
  <p align="center">
   欢迎使用DesktopAide
   <br/>
   <a href="https://forms.office.com/Pages/ResponsePage.aspx?id=6hE_meNolUOCo53YXSTdVFMu8zZCWEFGqs11nYrpuz5UMkwwSzNRU0lDWkpYRjJTVVBLQUlDT1FYNC4u"><strong>参与我们的调研</strong></a>
    <br />
    <a href="https://desktopaide.chenpuhao.me/"><strong>官方网站</strong></a>
  <br />
  <a href="README.md"><strong>探索本项目的文档 »</strong></a>
  <br />
    <a href="https://github.com/chenpuhao/DesktopAide/blob/main/README.md#%E5%8F%91%E8%A1%8C%E7%89%88%E6%9C%AC">下载安装包</a>
  ·
    <a href="https://316myk-my.sharepoint.com/:f:/g/personal/chenpuhao_316myk_onmicrosoft_com/EgdXuj60H3BOoW3XWsFDTIYBIVsx5P7EUSQzpooeZKkXLQ?e=TaAflH">备用下载渠道</a>
    ·
    <a href="mailto:chenpuhao1229@163.com?subject=报告Bug">报告Bug</a>
    ·
    <a href="README-en.md">英文界面</a>
  </p>

![](https://img.shields.io/github/license/chenpuhao/desktopaide)
![](https://img.shields.io/github/languages/count/chenpuhao/DesktopAide)
![](https://img.shields.io/github/languages/top/chenpuhao/desktopaide)
![](https://img.shields.io/github/languages/code-size/chenpuhao/desktopaide)
![](https://img.shields.io/github/downloads/chenpuhao/desktopaide/total)
![](https://img.shields.io/github/stars/chenpuhao/desktopaide)
![](https://img.shields.io/github/watchers/chenpuhao/desktopaide)
![](https://img.shields.io/github/last-commit/chenpuhao/desktopaide)
![](https://img.shields.io/github/release-date/chenpuhao/desktopaide)

注意
===
请使用管理员模式运行该程序

目录
 ===
<!-- TOC -->
* [注意](#注意)
* [目录](#目录)
  * [DesktopAide简介](#desktopaide简介)
    * [版权说明](#版权说明)
    * [已知问题](#已知问题)
    * [注意](#注意-1)
    * [捐助](#捐助)
    * [软件截图](#软件截图)
    * [部分代码](#部分代码)
    * [未来计划](#未来计划)
  * [发行版本](#发行版本)
    * [V3.3.1 版本内容更新](#v331-版本内容更新)
    * [V3.3.0 版本内容更新](#v330-版本内容更新)
<!-- TOC -->


## DesktopAide简介
DeskAide是一款开源的基于Windows系统的桌面程序
它可以帮助您整理桌面，查找文件，尤其适合学校教师使用

### 版权说明
该项目签署了GPL-3.0 授权许可，详情请参阅[LICENSE](LICENSE)
### 已知问题
1. ~~整理桌面中默认类型不可用，请使用自定义类型~~
### 注意
1. 如果您可以帮助我修改代码，请使用IntelliJ IDEA，谢谢！没有IDEA?[点我去下载](https://www.jetbrains.com/zh-cn/idea/download/)
2. 后续将会放弃Java开发前端，将使用C#~~（只要作者想起来）~~
3. ~~到目前为止我们并不提供英文安装包，如有需求请克隆到本地运行~~

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
- [x] 修复整理功能的默认模式(完成日期:2022/12/26)
- [ ] 完成整理桌面功能新UI(完成日期:) 
- [ ] ~~使用C#进行开发(完成日期:)~~

## 发行版本

### V3.3.1 版本内容更新
1. 修复整理桌面中默认类型不可用问题，增加英文界面
2. [下载链接cn](https://github.com/chenpuhao/DesktopAide/releases/download/V3.3.1/DesktopAide-withjre-3.3.1-cn-setup.exe)
3. [下载链接en](https://github.com/chenpuhao/DesktopAide/releases/download/V3.3.1/DesktopAide-withjre-3.3.1-en-setup.exe)

### V3.3.0 版本内容更新
1. 新UI
2. 优化大多数功能
3. 关闭聊天功能
4. [下载链接](https://github.com/chenpuhao/DesktopAide/releases/download/DesktopAide/DesktopAide-withjre-3.3-setup.exe)

