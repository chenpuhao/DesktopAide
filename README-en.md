<!-- PROJECT SHIELDS -->

[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

<br />

<p align="center">
  <a href="https://github.com/shaojintian/Best_README_template/">
    <img src="icon/DesktopAide.ico" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">DesktopAide</h3>
  <p align="center">
   Welcome to DesktopAide
    <br />
    <a href="https://github.com/chenpuhao/DesktopAide/blob/2245ce7c39e096135f6c4f7990c7efbed634c689/README-en.md"><strong>Explore the documentation for this project »</strong></a>
    <br />
    <br />
    <a href="https://github.com/chenpuhao/DesktopAide/releases/download/DesktopAide/DesktopAide-withjre-3.3-setup.exe">Download the installation package</a>
    ·
    <a href="https://github.com/chenpuhao/DesktopAide/blob/main/README-en.md#known-issues">Report a bug</a>
    ·
    <a href="https://github.com/chenpuhao/DesktopAide/blob/2245ce7c39e096135f6c4f7990c7efbed634c689/README.md">Chinese interface</a>
  </p>

directory
===
<!-- TOC -->
* [directory](#directory)
  * [Introduction to DesktopAide](#introduction-to-desktopaide)
    * [Copyright notice](#copyright-notice)
    * [Known issues](#known-issues)
    * [note](#note)
    * [donate](#donate)
    * [Software screenshots](#software-screenshots)
    * [Part of the code](#part-of-the-code)
    * [Future plans](#future-plans)
  * [Release version](#release-version)
    * [Version V3.3.0 content update](#version-v330-content-update)
<!-- TOC -->
[Chinese](README.md)|English
## Introduction to DesktopAide
DeskAide is an open source Windows-based desktop program
It helps you organize your desktop and find files, especially for school teachers
### Copyright notice
The project is licensed under the MIT license, see [LICENSE](LICENSE)
### Known issues
**If you find a bug during use, please list it in the table below or send it to chenpuhao1229@163.com, thank you**
1. The default type is not available in the Organize desktop, use a custom type
### note
1. If you can help me modify the code, please use IntelliJ IDEA, thanks! No IDEA?[Click me to download](https://www.jetbrains.com/zh-cn/idea/download/)
2. The Java development front-end will be abandoned, and C# ~~(as long as the author remembers)~~ will be used
3. So far, we do not provide English installation packages, if necessary, please clone to run locally

### donate
If you like this product, please generate electricity for me in love power generation, thank you:
[Point me to generate electricity](https://afdian.net/a/desktopaide)

### Software screenshots
1. #### Table pet interface (replaceable)
   ![](icon/Body.png)
2. #### Declutter the interface
   ![](icon/readme/collation-en.png)
3. #### More functional interface
   ![](icon/readme/more-en.png)
### Part of the code
src/main/java/en/Function/Collation.java
```java
public class Collation extends Thread {
  static void readAppointedLineNumber(File sourceFile, int lineNumber) {
    FileReader in = new FileReader(sourceFile);
    LineNumberReader reader = new LineNumberReader(in);
    String s = "";
    if (lineNumber <= 0 || lineNumber > getTotalLines(sourceFile)) {
      JOptionPane.showMessageDialog(null, "Please select a desktop path", "An error has occurred", JOptionPane.ERROR_MESSAGE);
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
src/main/java/en/Function/Find.java
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
          // If it is a file, the file is added to the results list
          result.add(file);
        } else {
          // If it is a folder, call this method recursively and add all files to the resulting list
          result.addAll(searchFiles(file, keyword));
        }
      }
    }
    return result;
  }
}

```
### Future plans
- [x] Completion of the third edition of the procedure (completion date: 2022/12/01)
- [ ] Fix default mode for grooming (completion date:)
- [ ] Complete the new UI for the Organize Desktop feature (Completion date:)
- [ ] ~~Development with C# (Completion date: )~~

## Release version

### Version V3.3.0 content update
1. New cn.UI
2. Optimize most features
3. Turn off chat
4. [Download the link](https://github.com/chenpuhao/DesktopAide/releases/download/DesktopAide/DesktopAide-withjre-3.3-setup.exe)

