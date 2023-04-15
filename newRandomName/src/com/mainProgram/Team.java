package com.mainProgram;

/*包名：com.mainProgram.TeamFrame
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Team extends JFrame {

    private final JPanel mainPanel = new JPanel();
    private final JScrollPane jsp = new JScrollPane(mainPanel);

    public Team(List<String> names){
        mainPanel.setLayout(new GridLayout(0,1));
        addBox("序号      姓名");
        for(int i=0;i<names.size();i++){
            String name = names.get(i);
            addBox((i + 1) + "  ".repeat(7 - (String.valueOf(i+1).length() + name.length()))
                    + (String.valueOf(i+1).length() == 2 ? " " : "")+ name);
        }
        add(jsp);
        setSize(350,500);
        setAlwaysOnTop(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    private void addBox(String name){
        JPanel panel = new JPanel();
        JLabel jl = new JLabel(name);
        jl.setFont(new Font("黑体",Font.PLAIN,25));
        panel.add(jl);
        mainPanel.add(panel);
    }

}
