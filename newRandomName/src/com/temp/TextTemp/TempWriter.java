package com.temp.TextTemp;

/*包名：com.program
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TempWriter {

    public TempWriter(){

    }

    public static void writ(Temp temp){
        File file = new File(Temp.DefaultTemp);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Writer writer = null;
        StringBuilder outputString = new StringBuilder();
        try{
            //写入内容
            //写入坐标位置
            outputString.append("location=").append(temp.getX()).append(",").append(temp.getY()).append("\n");
            //写入默认路径
            outputString.append("namePath=").append(temp.getPath()).append("\n");
            //写入悬浮窗设置状态
            outputString.append("floatChoose=").append(temp.getFloatChoose()).append("\n");
            //窗口置顶状态设置
            outputString.append("alwaysOnTop=").append(temp.getAlwaysOnTop()).append("\n");


            writer = new FileWriter(file,false);
            writer.write(outputString.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try{
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
