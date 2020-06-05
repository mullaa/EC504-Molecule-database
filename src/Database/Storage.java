package Database;

import java.io.*;
import java.util.*;

public class Storage {

    //unit test
    public static void main(String[] args) throws IOException {
//        String path1 = "water.txt";
//        String path2 = "water2.txt";
//        newDatabase data = new newDatabase();
//        data.addMolecule(path1);
//        data.findMolecule("water3.txt");
        Storage st = new Storage();
        newDatabase data = st.readCSV("Graphwriter.csv");
        System.out.println(data.database.size());
        data.findSubgraph("water.txt");



//        newDatabase database = new newDatabase();
//        File file = new File("C:/Users/M S I/Desktop/molecule datbase/huData");
//        String [] fileName = file.list();
//        for (String x: fileName) {
//            database.addMolecule("C:/Users/M S I/Desktop/molecule datbase/huData/" + x);
//        }
//        Storage st = new Storage();
//        st.writeCSV(database);
    }

    public void writeCSV(newDatabase NewDatabase) throws IOException {
        try {
            File csv = new File("Graphwriter.csv");
            csv.delete();
            csv.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(csv, true));
            for (MolecularProperty P : newDatabase.database.keySet()) {
                for (MoleculeGraph G : newDatabase.database.get(P).keySet()) {
                    String tmp = "";
                    tmp = G.getSecret();
                    bw.write(tmp);
                    bw.newLine();
                }
            }
            bw.close();
            System.out.println("Write successfully");
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }

    public newDatabase readCSV(String graphFilePath) {
        newDatabase database = new newDatabase();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(graphFilePath));
            String line = null;
            while((line=reader.readLine())!=null){
                MoleculeGraph  G = new MoleculeGraph(line);
                MolecularProperty P = new MolecularProperty(line);
                if (newDatabase.database.containsKey(P)) {
                    newDatabase.database.get(P).put(G,line.split("@")[0]);
                } else {
                    HashMap<MoleculeGraph,String> moledata = new HashMap<>();
                    moledata.put(G, line.split("@")[0]);
                    newDatabase.database.put(P,moledata);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }
}
