import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class storage {
    public static void main(String[] args) throws IOException {
        Map<Object, List<String>> map = new HashMap<>();
//        //read test
//        storage test = new storage();
//        map = test.readCSV("writers.csv");
//        System.out.println(Arrays.toString(map.entrySet().toArray()));
        //write test
        List<String> list = new ArrayList<String>();
        list.add("3");
        list.add("H");
        list.add("H");
        list.add("o");
        list.add("0 1");
        list.add("0 2");
        map.put("water", list);
        storage test = new storage();
        test.writeCSV(map);

    }

    public void writeCSV(Map<Object, List<String>> map) throws IOException { // read the cvs and initial the easy hashmap
        try {
            File csv = new File("writers.csv");
            csv.delete();
            csv.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            for (Map.Entry<Object, List<String>> entry: map.entrySet()) {
                String tmp = entry.getKey() + "," + entry.getValue();
                tmp = tmp.replace("[", " ");
                tmp = tmp.replace("]", "");
                bw.write(tmp);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<Object, List<String>> readCSV(String path) { // transfer the hashmap to csv file stored in the file system.
        Map<Object, List<String>> map = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = null;
            while((line=reader.readLine())!=null){
                List<String> tmpList = new ArrayList<String>();
                String item[] = line.split(",");
                for (int i = 1; i < item.length; i++) {
                    tmpList.add(item[i]);
                }
                map.put(item[0], tmpList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
