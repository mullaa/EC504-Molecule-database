import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class newDatabase {
    //Field
    static HashMap<MolecularProperty, MoleculeGraph> data = new HashMap<>();
    static HashMap<String, MoleculeGraph> data2 = new HashMap<>();

    //constructor


    //method


    //add the molecule by the molecule name
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
        MoleculeGraph G = new MoleculeGraph(secretCode);
        MolecularProperty P = new MolecularProperty(moleculeName,G.hMoleFormula, G.hnumOfAtom, G.hnumOfEdge, G.weightEdge);
        data2.put(moleculeName, G);

        System.out.println(P.name);
        System.out.println(moleculeName);
        System.out.println("Submit successfully!");
        data.put(P, G);
    }

    // find the molecule by using the molecule name
    public void findMolecule(String filePath) {
        String moleculeName = "";
        try {
            File file = new File(filePath);
            InputStreamReader var4 = new InputStreamReader(new FileInputStream(file));
            BufferedReader var5 = new BufferedReader(var4);
            moleculeName = var5.readLine();
        } catch (Exception var7) {
            var7.printStackTrace();
        }
        if (data2.containsKey(moleculeName)) {
            System.out.println("-----");
            System.out.println(moleculeName + " IS FOUND");
            System.out.println("-----");
        } else {
            System.out.println("-----");
            System.out.println("NOT FOUND");
            System.out.println("-----");
        }
    }

    @SuppressWarnings("unchecked")
    public void findSubgraph(String secretC) {

        MoleculeGraph T = new MoleculeGraph(secretC);
        // Vector<String> key = new Vector<>();
        // Graph<String, DefaultEdge> key2 = new Multigraph<>(DefaultEdge.class);

        try {
            boolean bonds_and_elements = false;

            //Expands the possible graphs to compare Textfile too by looking at all the MolecularProperty Keys in data
            for(MolecularProperty curr: data.keySet()) {
                if(curr.numOfEdge >= T.hnumOfEdge && curr.numOfAtom >= T.hnumOfAtom) {
                    for (String atom : T.hMoleFormula.keySet()) { // T = Cerium;oxalate small
                        if(curr.MoleFormula.keySet().contains(atom)
                                && curr.MoleFormula.get(atom) >= T.hMoleFormula.get(atom)) {
                            bonds_and_elements = true;
                        } else {
                            bonds_and_elements = false;
                            break;
                        }

                    }
                }
                //If numOfEdge and numOfAtom of G is greater than SG continue
                if(bonds_and_elements){
                    graphMatch one = new graphMatch(T, data.get(curr));
                    if(one.contain()){
                        System.out.println("Subgraph " + secretC +" founded in " + curr.name);
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