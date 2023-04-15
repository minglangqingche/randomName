package com.mainProgram;

/*包名：com.mainProgram
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import javax.swing.*;

public class Board extends JFrame {

    String str =
            """
            更新公告 -version alpha_0.1
            
            进行了重大更新。
            1.完全重写源程序，优化代码及程序结构，优化程序运行效率及资源占用；
            2.更新了外观组件；
            3.添加新功能：自定义内容根目录，可以自定义抽取内容存储的位置，详见使用帮助；
            4.添加新功能：记录软件位置，并自动重置到记录位置；
            5.添加了抽取动画。
            
            """;

    public Board(){
        JTextArea jta = new JTextArea(str);
        jta.setLineWrap(true);
        jta.setEditable(false);
        add(jta);
        setTitle("更新公告");
        setSize(300,300);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

}
