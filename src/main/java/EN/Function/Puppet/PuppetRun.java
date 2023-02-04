package EN.Function.Puppet;

import java.awt.*;

import static CN.UI.FunctionPanel.puppetPanel.puppet;

/**
 * @author chenpuhao
 * @version 3.2.x
 * @Date 2023/2/4
 */
public class PuppetRun extends Thread{
    public static boolean exit = false;
    public void run(){
        while (!exit){
            puppet.setVisible(true);
            PointerInfo pInfo = MouseInfo.getPointerInfo();
            Point p = pInfo.getLocation();
            puppet.setLocation((int) p.getX()-110, (int) p.getY());
        }
        puppet.setVisible(false);
    }
}
