package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


public class newDatabase {
    static HashMap<MolecularProperty, HashMap<MoleculeGraph,String>> database = new HashMap<>();

    public static void main(String[] args) {
        String path1 = "water.txt";
        String path2 = "water2.txt";
        newDatabase data = new newDatabase();
        data.addMolecule(path1);
        data.findSubgraph("water3.txt");

    }

    public void addMolecule(String filePath) {
        String moleculeName = "";
        String secretCode = "";
        String tmp = "";
        try {
            File file = new File(filePath);
            InputStreamReader var4 = new InputStreamReader(new FileInputStream(file));
            BufferedReader var5 = new BufferedReader(var4);
            moleculeName = var5.readLine();
            secretCode += moleculeName + "@";
            while ((tmp = var5.readLine()) != null) {
                secretCode += tmp+ "@";
            }

        } catch (Exception var7) {
            var7.printStackTrace();
        }

        MolecularProperty P = new MolecularProperty(secretCode);
        MoleculeGraph G = new MoleculeGraph(secretCode);
        if (database.containsKey(P)) {
            int count = 0;
            for (MoleculeGraph graph : database.get(P).keySet()) {
                if (graph.secret == G.secret) {
                    System.out.println(moleculeName + "already in the database.");
                } else {
                    count++;
                }
            }
            if (count == database.get(P).size()) {
                database.get(P).put(G,moleculeName);
                System.out.println(moleculeName + " add successfully");
            }
        } else {
            HashMap<MoleculeGraph,String> moledata = new HashMap<>();
            moledata.put(G, moleculeName);
            database.put(P,moledata);
            System.out.println(moleculeName + " add successfully");
        }
    }

    public void findMolecule(String filePath) {
        String moleculeName = "";
        String secretCode = "";
        String tmp = "";
        try {
            File file = new File(filePath);
            InputStreamReader var4 = new InputStreamReader(new FileInputStream(file));
            BufferedReader var5 = new BufferedReader(var4);
            moleculeName = var5.readLine();
            secretCode += moleculeName + "@";
            while ((tmp = var5.readLine()) != null) {
                secretCode += tmp+ "@";
            }

        } catch (Exception var7) {
            var7.printStackTrace();
        }
        MolecularProperty P = new MolecularProperty(secretCode);
        MoleculeGraph G = new MoleculeGraph(secretCode);
        int count = 0;
        if (database != null && database.containsKey(P)) {
            for (MoleculeGraph graph : database.get(P).keySet()) {
                graphMatch one = new graphMatch(G , graph);
                if (one.isocontain()) {
                    System.out.println("Founded in " + database.get(P).get(graph));
                } else {
                    count++;
                }
            }
            if (count == database.get(P).size()) {
                System.out.println("-----");
                System.out.println(moleculeName + "NOT FOUND");
                System.out.println("-----");
            }
        } else {
            System.out.println("-----");
            System.out.println(moleculeName +" NOT FOUND !!!");
            System.out.println("-----");
        }
    }




    public void findSubgraph(String filePath) {
        String moleculeName = "";
        String secretC = "";
        String tmp = "";
        try {
            File file = new File(filePath);
            InputStreamReader var4 = new InputStreamReader(new FileInputStream(file));
            BufferedReader var5 = new BufferedReader(var4);
            moleculeName = var5.readLine();
            secretC += moleculeName + "@";
            while ((tmp = var5.readLine()) != null) {
                secretC += tmp+ "@";
            }

        } catch (Exception var7) {
            var7.printStackTrace();
        }
        MoleculeGraph T = new MoleculeGraph(secretC);
        // Vector<String> key = new Vector<>();
        // Graph<String, DefaultEdge> key2 = new Multigraph<>(DefaultEdge.class);

        try {
            boolean bonds_and_elements = false;

            //Expands the possible graphs to compare Textfile too by looking at all the MolecularProperty Keys in data
            System.out.println("Subgraph Search for " + moleculeName + " :");
            for(MolecularProperty curr: database.keySet()) {
                if(curr.numEdges >= T.hnumOfEdge && curr.numAtoms >= T.hnumOfAtom) {
                    for (String atom : T.hMoleFormula.keySet()) {
                        if(curr.molecularFormula.keySet().contains(atom)
                                && curr.molecularFormula.get(atom) >= T.hMoleFormula.get(atom)) {
                            bonds_and_elements = true;
                        } else {
                            bonds_and_elements = false;
                            break;
                        }
                    }
                }
                //If numOfEdge and numOfAtom of G is greater than SG continue
                if(bonds_and_elements){
                    for (MoleculeGraph graph : database.get(curr).keySet()) {
                        graphMatch one = new graphMatch(T, graph);
                        if(one.contain()){
                            System.out.println("           Founded in: " + graph.secret.split("@")[0]);
                        }
                    }
                }
                bonds_and_elements = false;
            }

        }
        // catch (FileNotFoundException ex) {
        //     System.out.println("File not found");
        //     //return null;
        // }
        // catch (IOException ex2) {
        //     System.out.println("IO");
        //     //return null;
        // }
        catch (Exception ex3) {
            System.out.print("Incorrect format");
        }
    }

}
