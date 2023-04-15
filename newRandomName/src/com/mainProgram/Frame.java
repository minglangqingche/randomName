package com.mainProgram;

/*包名：com.program
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import com.temp.TextTemp.TempReader;
import com.temp.TextTemp.Temp;
import com.temp.TextTemp.TempWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Frame extends JFrame {

    //floatingFrame
    private final FloatingFrame floatingFrame = new FloatingFrame(this);

    //temp
    private final Temp temp = TempReader.readTemp();

    //抽取内容获取
    private FilesCatch filesCatch;
    {
        try {
            filesCatch = new FilesCatch(temp.getPath());
        } catch (IOException e) {
            try {
                filesCatch = new FilesCatch(Temp.DefaultPath);
                temp.setPath(Temp.DefaultPath);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    //内容抽取
    private final RandomName randomName = new RandomName();

    //中部组件
    private final JLabel nameLabel = new JLabel("点击抽取按钮开始抽取！",SwingConstants.CENTER);

    private final JSlider slider = new JSlider(1,20,1);
    private final JLabel numberView = new JLabel("中抽取1人");
    private final JLabel storeView = new JLabel("将从");
    private final JComboBox<String> combo = new JComboBox<>();

    //底部组件
    private final JButton randomButton = new JButton("抽取一次");

    public Frame(){

        //----底部组件----
        randomButton.addActionListener(event -> {
            String[] names;
            try {
                 names = filesCatch.getFileContent(combo.getSelectedIndex());
            }catch (IndexOutOfBoundsException e){
                return;
            }
            int num = slider.getValue();

            List<String> nameList = randomName.getName(names,num);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //抽取动画
                    for(int i=0;i<10;i++){
                        List<String> nameTemp = randomName.getFlashName(names,(Math.min(num, 5)));
                        StringBuilder sb = new StringBuilder();
                        for(int ii=0;ii<nameTemp.size()-1;ii++){
                            String str = nameTemp.get(ii);
                            sb.append(str).append("    ");
                        }
                        sb.append(nameTemp.get(nameTemp.size() - 1));
                        nameLabel.setText(sb.toString());
                        try {
                            Thread.sleep(80);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    if(num < 6){
                        StringBuilder sb = new StringBuilder();
                        for(int i=0;i<nameList.size()-1;i++){
                            String str = nameList.get(i);
                            sb.append(str).append("    ");
                        }
                        sb.append(nameList.get(nameList.size() - 1));
                        nameLabel.setText(sb.toString());
                    }else{
                        nameLabel.setText("您可以继续进行抽取！");
                        new Team(nameList);
                    }
                }
            }).start();
        });
        add(randomButton,BorderLayout.SOUTH);

        //----字号更改按钮----
        //字号加
        JButton fontSizePlus = new JButton("字号+");
        fontSizePlus.addActionListener(event -> {
            nameLabel.setFont(new Font("黑体",Font.PLAIN,(nameLabel.getFont().getSize() + 5)));
        });
        add(fontSizePlus,BorderLayout.EAST);
        //字号减
        JButton fontSizeMinus = new JButton("字号-");
        fontSizeMinus.addActionListener(event -> {
            nameLabel.setFont(new Font("黑体",Font.PLAIN,(nameLabel.getFont().getSize() - 5)));
        });
        add(fontSizeMinus,BorderLayout.WEST);

        //----中部组件----
        //姓名显示框
        nameLabel.setFont(new Font("黑体",Font.PLAIN,25));
        add(nameLabel, BorderLayout.CENTER);

        //----顶部组件----
        //抽取目标库提示
        //顶部组件
        JPanel northPanel = new JPanel();
        northPanel.add(storeView);
        //抽取目标库combo设置
        for(String fileName : filesCatch.getAllFilesName()) {
            combo.addItem(fileName);
        }
        northPanel.add(combo);

        //抽取数量提示
        northPanel.add(numberView);

        //抽取数量滑动条
        slider.addChangeListener(event -> {
            numberView.setText("中抽取" + slider.getValue() + "人");
        });
        northPanel.add(slider);
        add(northPanel,BorderLayout.NORTH);

        //----frame工具栏----
        JMenuBar menuBar = new JMenuBar();
        JMenu settingMenu = new JMenu("设置");
        JMenuItem fileSettingItem = new JMenuItem("更改源文件夹");
        fileSettingItem.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser("D:\\"){
                @Override
                public void approveSelection(){
                    File file = new File(getSelectedFile().getPath());
                    if (file.exists()) {
                        super.approveSelection();
                    }else {
                        JOptionPane.showMessageDialog(null, "你选择的文件不存在，请重新选择！");
                    }
                }
            };
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int result = fileChooser.showOpenDialog(null);
            if(result == JFileChooser.APPROVE_OPTION){
                String path = fileChooser.getSelectedFile().getPath();
                temp.setPath(path);
                TempWriter.writ(temp);
                try {
                    filesCatch = new FilesCatch(temp.getPath());
                } catch (IOException e) {
                    try {
                        filesCatch = new FilesCatch(Temp.DefaultPath);
                        temp.setPath(Temp.DefaultPath);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                combo.removeAllItems();
                for(String fileName : filesCatch.getAllFilesName()) {
                    combo.addItem(fileName);
                }
                JOptionPane.showMessageDialog(null, "已将源文件夹更改至" + path);
                setTitle("RandomName -version alpha_0.1 船新版本更新！！源文件位置：" + temp.getPath());
            }
        });
        settingMenu.add(fileSettingItem);

        JMenuItem fileResetItem = new JMenuItem("重置源文件夹");
        fileResetItem.addActionListener(event -> {
            temp.setPath(Temp.DefaultPath);
            TempWriter.writ(temp);
            try {
                filesCatch = new FilesCatch(temp.getPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            combo.removeAllItems();
            for(String fileName : filesCatch.getAllFilesName()) {
                combo.addItem(fileName);
            }
            JOptionPane.showMessageDialog(null, "已将源文件夹更改至" + Temp.DefaultPath);
            setTitle("RandomName -version alpha_0.1 船新版本更新！！源文件位置：" + temp.getPath());
        });
        settingMenu.add(fileResetItem);

        JCheckBoxMenuItem floatingChoose = new JCheckBoxMenuItem("最小化程序时浮窗化");
        floatingChoose.setState(temp.getFloatChoose());
        floatingChoose.addChangeListener(event -> {
            temp.setFloatChoose(floatingChoose.getState());
        });
        settingMenu.add(floatingChoose);

        JCheckBoxMenuItem alwaysOnTop = new JCheckBoxMenuItem("窗口置于顶层");
        alwaysOnTop.setState(temp.getAlwaysOnTop());
        alwaysOnTop.addChangeListener(event -> {
            temp.setAlwaysOnTop(alwaysOnTop.getState());
            setAlwaysOnTop(alwaysOnTop.getState());
        });
        settingMenu.add(alwaysOnTop);

        menuBar.add(settingMenu);

        JMenu aboutMenu = new JMenu("关于软件");
        JMenuItem helpItem = new JMenuItem("使用帮助");
        Help help = new Help();
        helpItem.addActionListener(event -> {
            help.setVisible(true);
        });
        aboutMenu.add(helpItem);

        JMenuItem boardItem = new JMenuItem("更新公告");
        Board board = new Board();
        boardItem.addActionListener(event -> {
            board.setVisible(true);
        });
        aboutMenu.add(boardItem);
        menuBar.add(aboutMenu);
        setJMenuBar(menuBar);

        //----frame监听----
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                super.windowIconified(e);
                if(!floatingChoose.getState()){
                    return;
                }
                floatingFrame.setVisible(true);
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                super.windowDeiconified(e);
                floatingFrame.setVisible(false);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                //关闭动作
                temp.setPoint(getX(),getY());
                TempWriter.writ(temp);
            }
        });

        //----frame基本设置----
        setAlwaysOnTop(alwaysOnTop.getState());
        setLocation(temp.getX(), temp.getY());
        setTitle("RandomName -version alpha_0.1 船新版本更新！！源文件位置：" + temp.getPath());
        setSize(850,200);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

