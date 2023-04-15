package com.mainProgram;

/*包名：com.mainProgram
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import javax.swing.*;

public class Help extends JFrame {

    String str =
            """
            使用帮助 -version alpha_0.1
                        
                （1）如何定义抽取的内容？
                    进入内容源目录，
                    在内容源目录下添加编码为UTF-8的txt文件，并在文件中按照指定的格式书写内容，程序会自动识别书写的内容。
                    文件名即为抽取内容的名字。
                    ****
                    关于书写格式的描述请看第（2）项。
                    关于内容源目录的描述请看第（3）项。
                    
                （2）内容书写的格式要求？
                    您可以使用英文半角逗号（,）或空格（ ）或全角逗号（，）来分割每一个内容。
                    软件在提取内容时会跳过对回车符，换行符的提取，如果您写入换行符或回车将不会影响内容的读取，
                    但请注意，换行符不会分割内容，所以在添加每一个内容时注意写入分隔符，但最后一个内容后不输入分隔符。
                
                （3）关于内容源目录
                    内容源目录是存放抽取的内容的文件夹。
                    内容源目录默认为 D:\\randomData ，但可自行修改为其他文件夹。
                    修改文件夹可在 设置 > 更改源文件夹 完成设置。
                    如果无法确认源文件夹的位置，您可以在 设置 > 重置源文件夹 将文件夹重新定向到 D:\\randomData 。
                    
                （4）关于多人抽取的独立窗口显示
                    在抽取人数超过五人时，程序将自动选择在独立窗口中显示这些内容。
                    
                （5）关于抽取概率的问题
                    本程序确保每个人被抽到的概率相同。
                    程序进行了一定的算法设计，使得在一定时间内一个人不会重复出现，直到某些条件改变后。
                    使得某人出现第二次的条件为：
                        1.改变抽取范围
                        2.范围内所有人都已经被抽取过
                    为确保公平对待每一个人，对算法有疑问可以公布源码。
        
            """;

    public Help(){
        JTextArea jta = new JTextArea(str);
        jta.setLineWrap(true);
        jta.setEditable(false);
        add(new JScrollPane(jta));
        setTitle("使用帮助");
        setSize(300,400);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

}
