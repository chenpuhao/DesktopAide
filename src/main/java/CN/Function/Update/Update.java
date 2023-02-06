package CN.Function.Update;

import javax.net.ssl.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author chenpuhao
 * @Date 2023/2/6
 */
public class Update {
    private static void downloadUsingStream(String urlStr, String file) throws IOException{
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[10240000];
        int count=0;
        while((count = bis.read(buffer,0,10240000)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[10240];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
    public static void trustAllHttpsCertificates() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements TrustManager,X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(X509Certificate[] certs) {
            return true;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType)
                throws CertificateException {
            return;
        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException{
            return;
        }
    }

    /**
     * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用
     * @throws Exception
     */
    public static void ignoreSsl() throws Exception{
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };
        trustAllHttpsCertificates();
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
    }
    private static void browse2(String url) throws Exception {
        Desktop desktop = Desktop.getDesktop();
        if (Desktop.isDesktopSupported() && desktop.isSupported(Desktop.Action.BROWSE)) {
            URI uri = new URI(url);
            desktop.browse(uri);
        }
    }

    public Update() throws Exception {
        Update.ignoreSsl();
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
        CheckUpdate checkUpdate = new CheckUpdate();
        String result = checkUpdate.CheckUpdate();
        File filePath = new File("download");
        if(!filePath.exists()){
            filePath.mkdirs();
        }
        if(!result.equals("true")){
            File file = new File("download/DesktopAide-cn-"+result+"-beta-setup.exe");
            if(!file.exists()){
                file.createNewFile();
            }
            int n = JOptionPane.showConfirmDialog(null,"检测到有版本更新可用，选是则使用软件下载安装包，预计时间15-20min,选否则跳转官网下载","有版本更新可用",JOptionPane.YES_NO_OPTION);
            if(n == 0) {

                downloadUsingStream("https://github.com/chenpuhao/DesktopAide/releases/download/V" + result + "/DesktopAide-cn-" + result + "-setup.exe", String.valueOf(file));
                JOptionPane.showMessageDialog(null, "下载完成，稍后将为您打开安装程序", "下载完成", JOptionPane.PLAIN_MESSAGE);
                Desktop.getDesktop().open(file);
                System.exit(0);
            }else{
                browse2("https://desktopaide.chenpuhao.me/download.html");
                System.exit(0);
            }
        }
    }
}
