package com.mainProgram;

/*包名：com.mainProgram
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import com.temp.TextTemp.Temp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FilesCatch {

    private String root = Temp.DefaultPath;
    private List<String> files = new ArrayList<>();//文件路径
    private List<String> filesName = new ArrayList<>();//文件名

    private List<String[]> fileContent = new ArrayList<>();//文件内容

    public FilesCatch(String root) throws IOException {
        //创建存储文件夹
        this.root = root;
        File rootFile = new File(this.root);
        if (!rootFile.isDirectory()) {
            this.root = Temp.DefaultPath;
            rootFile = new File(this.root);
            if(!rootFile.exists()){
                rootFile.createNewFile();
            }
        }
        getFileString();
    }

    //获取文件夹中所有文件名
    //directoryPath  文件夹目录
    //isAddDirectory  是否获取子文件夹内容
    public List<String> getAllFile(String directoryPath, boolean isAddDirectory) {
        List<String> list = new ArrayList<>();
        File baseFile = new File(directoryPath);
        if (baseFile.isFile() || !baseFile.exists()) {
            return list;
        }
        File[] files = baseFile.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.isDirectory()) {
                if (isAddDirectory) {
                    list.add(file.getAbsolutePath());
                }
                list.addAll(getAllFile(file.getAbsolutePath(), isAddDirectory));
            } else {
                list.add(file.getAbsolutePath());
            }
        }
        return list;
    }

    public void getFileString() throws IOException {
        //获取文件中所有文件路径
        files = getAllFile(root, false);
        //保留指定文件类型（.txt）
        for (int ii = 0; ii < files.size(); ii++) {
            String ss = files.get(ii);
            String fe = "";
            int i = ss.lastIndexOf('.');
            if (i > 0) {
                fe = ss.substring(i);
            }
            if (!fe.equals(".txt")) {
                files.remove(ii);
            }
        }
        //获取文件名字
        for (String str : files) {
            String[] temp = str.split("\\\\");
            String s = temp[temp.length - 1];
            String[] ss = s.split("\\.");
            filesName.add(ss[0]);//将文件名添加入filesName
        }

        //获取所有文件的内容
        for (String file : files) {
            fileContent.add(getFileText(file));
        }

    }

    public String[] getFileText(String ff) throws IOException {
        //读取外部数据
        File file = new File(ff);
        Path path = Paths.get(ff);
        if (!file.exists()) {
            file.createNewFile();
        }
        return Files.readString(path).split("[,， ]");
    }

    public List<String> getFiles() {
        return files;
    }

    public List<String> getAllFilesName() {
        return filesName;
    }

    public String[] getFileContent(int index) {
        return fileContent.get(index);
    }

}

