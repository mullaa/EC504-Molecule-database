package com.example.lib;

//import java.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class newDatabase {
    //Field
    static HashMap<molecularProperty, MoleculeGraph> data = new HashMap<>();
    static HashMap<String, MoleculeGraph> data2 = new HashMap<>();

    //constructor


    //method


    //add the molecule by the molecule name
    public String addMolecule(String filePath) {
        String moleculeName = "";
        String secretCode = "";
        String tmp = "";
        try {
            File file = new File(filePath);
            InputStreamReader var4 = new InputStreamReader(new FileInputStream(file));
            BufferedReader var5 = new BufferedReader(var4);
            moleculeName = var5.readLine();
            secretCode += moleculeName + ",";
            while ((tmp = var5.readLine()) != null) {
                secretCode += tmp+ ",";
            }

        } catch (Exception var7) {
            var7.printStackTrace();
        }
        MoleculeGraph G = new MoleculeGraph(secretCode);
        molecularProperty P = new molecularProperty();
        data2.put(moleculeName, G);
        data.put(P, G);
        return "Molecule Added!";
    }

    // find the molecule by using the molecule name
    public String findMolecule(String filePath) {
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
            System.out.println(filePath);
            System.out.println("-----");
            System.out.println(moleculeName + " IS FOUND");
            System.out.println("-----");
            return(data2.get(moleculeName).toString());
        } else {
            System.out.println(filePath);
            System.out.println("-----");
            System.out.println("NOT FOUND");
            System.out.println("-----");
            return("NOT FOUND");
        }

    }

    public class molecularProperty implements Serializable {
        String name;
        int numOfAtom;
        int numOfEdge;
        HashMap<String, Integer> MoleFormula;

        //constructor
        public molecularProperty() {
            name = "";
            numOfAtom = 0;
            numOfEdge = 0;
            MoleFormula = new HashMap<>();
        }
        public molecularProperty(int atomNum, int EdgeNum, String moleName, HashMap<String, Integer> formula) {
            name = moleName;
            numOfAtom = atomNum;
            numOfEdge = EdgeNum;
            MoleFormula = formula;
        }
    }




}
