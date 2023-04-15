package com.mainProgram;

/*包名：com.mainProgram
 *项目名：newRandomName
 *@author MLQC
 *2023/1/24
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomName {

    private List<String> names = new ArrayList<>();
    private List<String> usingName = new ArrayList<>();

    private Random random = new Random();

    public List<String> getName(String[] inNames,int num){
        if(!usingName.equals(new ArrayList<>(Arrays.asList(inNames)))){
            usingName = new ArrayList<>(Arrays.asList(inNames));
            try {
                names = deepCopy(usingName);
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        List<String> nameList = new ArrayList<>();
        for(int i=0;i<num;i++){
            if(names.size() == 0){
                try {
                    names = deepCopy(usingName);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
            int index = random.nextInt(names.size());
            nameList.add(names.get(index));
            names.remove(index);
        }
        return nameList;
    }

    public List<String> getFlashName(String[] inNames,int num){
        List<String> nameList = new ArrayList<>();
        for(int i = 0;i<num;i++){
            nameList.add(inNames[random.nextInt(inNames.length)]);
        }
        return nameList;
    }

    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

}
