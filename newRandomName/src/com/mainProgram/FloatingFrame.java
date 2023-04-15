package com.mainProgram;

/*包名：com.mainProgram
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class FloatingFrame extends JFrame {

    private int mouseAtX;
    private int mouseAtY;

    public FloatingFrame(Frame frame){
        addMouseListener(new MouseAdapter() {        //设置窗口可拖动，添加监听器
            @Override
            public void mousePressed(MouseEvent e) {        //获取点击鼠标时的坐标
                mouseAtX=e.getPoint().x;
                mouseAtY=e.getPoint().y;
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.setExtendedState(NORMAL);
                frame.requestFocus();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {        //设置拖拽后，窗口的位置
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen()-mouseAtX,e.getYOnScreen()-mouseAtY);
            }
        });
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.add(new JLabel("点击返回"));
        mainPanel.add(new JLabel("拖拽移动"));
        add(mainPanel);
        setSize(50,50);
        setLocation(0,200);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setVisible(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }

}
