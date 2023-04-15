package com.temp.TextTemp;

/*包名：com.temp.TextTemp
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import java.io.File;

public class Temp {

    public static String DefaultRoot = "C:\\Users\\" + System.getenv().get("USERNAME") + "\\Documents\\randomNameData";
    public static String DefaultTemp = DefaultRoot + "\\localTemp.txt";

    public static String DefaultPath = "D:\\randomData";
    private String path = null;

    private boolean floatChoose = true;

    private boolean alwaysOnTop = true;

    private int x = 0;
    private int y = 0;

    public boolean getFloatChoose(){
        return floatChoose;
    }

    public void setFloatChoose(boolean floatChoose){
        this.floatChoose = floatChoose;
    }

    public boolean getAlwaysOnTop(){
        return alwaysOnTop;
    }

    public void setAlwaysOnTop(boolean alwaysOnTop){
        this.alwaysOnTop = alwaysOnTop;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPoint(int x,int y) {
        this.x = x;
        this.y = y;
    }

    public void setPath(String str){
        path = str;
    }

    public String getPath(){
        if(path == null || !(new File(path).exists())){
            return DefaultPath;
        }
        return path;
    }

}
