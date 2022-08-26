package src;

import ch.aplu.jgamegrid.Actor;
import src.utility.PropertiesLoader;

import java.awt.*;
import java.util.Arrays;
import java.util.Properties;

public class Driver {

    public static final String DEFAULT_PROPERTIES_PATH = "properties/test2.properties";
    //DEFAULT_PROPERTIES_PATH 即配置文件 原值为"properties/test1.properties"
    //猜测：madness可能被做好了，看test2时的表现猜的
    //问问啥叫same output

    /**
     * Starting point
     * @param args the command line arguments
     */

    public static void main(String args[]) {
        String propertiesPath = DEFAULT_PROPERTIES_PATH;

        //可能是所谓的output？
        System.out.println("Arrays.asList(args) = " + Arrays.asList(args));
        if (args.length > 0) {
            propertiesPath = args[0];
        }
        final Properties properties = PropertiesLoader.loadPropertiesFile(propertiesPath);
        boolean isLoggingTest = Boolean.parseBoolean(properties.getProperty("logTest", "false"));

        //不要改TetrisGameCallback
        TetrisGameCallback gameCallback = new TetrisGameCallback(isLoggingTest);

        EventQueue.invokeLater(new Runnable() {
            //线程：不停地显示窗口，override了run
            //setVisible 是JFrame里的method
           public void run() {
                new Tetris(gameCallback, properties).setVisible(true);
            }

        });

    }
}
