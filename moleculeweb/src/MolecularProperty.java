
import java.io.*;
import java.util.*;
import java.util.HashMap;
public class MolecularProperty {
    String name;
    int numOfAtom;
    int numOfEdge;
    HashMap<String, Integer> MoleFormula;
    HashMap<String, Integer> EdgeMap;
    String secret;

    //constructor
    public MolecularProperty() {
        name = "";
        numOfAtom = 0;
        numOfEdge = 0;
        MoleFormula = new HashMap<>();
        EdgeMap = new HashMap<>();
    }
    // without name
    public MolecularProperty(HashMap<String, Integer> iformula, int inumOfAtom, int inumOfEdge, HashMap<String, Integer> iedgeMap) {
        numOfAtom = inumOfAtom;
        numOfEdge = inumOfEdge;
        MoleFormula = iformula;
        EdgeMap = iedgeMap;
    }
    // with name
    public MolecularProperty(String name, HashMap<String, Integer> iformula, int inumOfAtom, int inumOfEdge, HashMap<String, Integer> iedgeMap) {
        this.name = name;
        numOfAtom = inumOfAtom;
        numOfEdge = inumOfEdge;
        MoleFormula = iformula;
        EdgeMap = iedgeMap;
        secret = encode();
    }

    String encode() {
        // water@H:2/O:1/@3@2@0 1:1/1 2:1
        System.out.println(name);
        String sc = "";
        sc = sc+name+"@";
        for (String e : MoleFormula.keySet()) {
            sc = sc + e +":"+String.valueOf(MoleFormula.get(e))+"/";
        }
        sc = sc +"@"+ String.valueOf(numOfAtom) +"@"+ String.valueOf(numOfEdge) +"@";
        for (String e : EdgeMap.keySet()) {
            sc = sc + e +":"+ String.valueOf(EdgeMap.get(e))+"/";
        }
        return sc;
    }
    public String getPSecret() {
        return secret;
    }
}
