package com.temp.TextTemp;

/*包名：com.temp.TextTemp
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TempReader {

    public static Temp readTemp(){
        Temp temp = new Temp();
        try {

            //root文件夹创建
            System.out.println("root -> " + Temp.DefaultRoot);
            File rootFile = new File(Temp.DefaultRoot);
            if(!rootFile.isDirectory()){
                rootFile.mkdirs();
            }

            //获取缓存内容
            File tempFile = new File(Temp.DefaultTemp);
            if(!tempFile.exists()){
                try {
                    tempFile.createNewFile();
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            Scanner scanner = new Scanner(tempFile);
            List<String> line = new ArrayList<>();
            while (scanner.hasNextLine()){
                line.add(scanner.nextLine());
            }

            //数据处理
            Map<String,String> valueMap = new HashMap<>();
            for(String str : line){
                String[] s = str.split("=");
                if(s.length == 0){
                    continue;
                }
                if(s.length < 2){
                    valueMap.put(s[0],"");
                    continue;
                }
                valueMap.put(s[0],s[1]);
            }

            //获取坐标
            if(valueMap.get("location") != null){
                String[] str1 = valueMap.get("location").split(",");
                if(str1.length == 2 && isNumeric(str1[0]) && isNumeric(str1[1])){
                    temp.setPoint(Integer.parseInt(str1[0]),Integer.parseInt(str1[1]));
                }else if(str1.length == 1 && isNumeric(str1[0])){
                    temp.setPoint(Integer.parseInt(str1[0]),0);
                }
            }

            //获取目标文件位置
            if(valueMap.get("namePath") != null){
                if((new File(valueMap.get("namePath")).exists())){
                    temp.setPath(valueMap.get("namePath"));
                }
            }

            //获取悬浮窗设置
            if(valueMap.get("floatChoose") != null){
                if(valueMap.get("floatChoose").equals("true")){
                    temp.setFloatChoose(true);
                }else if(valueMap.get("floatChoose").equals("false")){
                    temp.setFloatChoose(false);
                }
            }

            //获取窗口置顶设置
            if(valueMap.get("alwaysOnTop") != null){
                if(valueMap.get("alwaysOnTop").equals("true")){
                    temp.setAlwaysOnTop(true);
                }else if(valueMap.get("alwaysOnTop").equals("false")){
                    temp.setAlwaysOnTop(false);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    //检测String是否为数字
    private static boolean isNumeric(String str){
        try {
            double a = Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            try {
                int a = Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e1) {
                String[] sss = str.split("");
                return sss[sss.length - 1].equals("%");
            }
        }
    }

}
