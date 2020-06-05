package Database;

import java.util.HashMap;
import java.util.Objects;

public class MolecularProperty {
    HashMap<String, Integer> molecularFormula;
    int numEdges;
    int numAtoms;
    String secretCode;

    public static void main(String[] args) {
        String x ="isomeric1@4@C@O@H@H@0 2@0 1@1 3@";
        String y ="isomeric2@4@C@O@H@H@0 2@0 1@1 3@";
        MolecularProperty a = new MolecularProperty(x);
        MolecularProperty b = new MolecularProperty(y);
        System.out.println(a.equals(b));
    }



    //constructor
    public MolecularProperty() {
        molecularFormula = new HashMap<>();
        numEdges = 0;
        secretCode = null;
    }

    public MolecularProperty(HashMap<String, Integer> inputFormula, int inputEdges, int inputAtoms, String secret) {
        molecularFormula = inputFormula;
        numEdges = inputEdges;
        numAtoms = inputAtoms;
        secretCode = secret;
    }

    public MolecularProperty(String secret) {
        secretCode = secret;
        molecularFormula = new HashMap<>();
        String[] array = secretCode.split("@");
        numAtoms = Integer.valueOf(array[1]);
        numEdges = array.length - 2 - numAtoms;
        for (int i = 2; i < numAtoms + 2; i++) {
            Integer curnum = molecularFormula.get(array[i]);
            if (curnum == null) {
                molecularFormula.put(array[i], 1);
            } else {
                molecularFormula.put(array[i], curnum+1);
            }
        }
    }

    //Since we are using molecularProperty as hashmap data's key we have to overwrite the equals fcn and the hashcode function to ensure it is hashing properly
    @Override
    public boolean equals(Object b){
        if(b == null) return false;
        if(!(b instanceof MolecularProperty)) return false;
        if(b == this ) return true;
        if(numEdges == ((MolecularProperty) b).numEdges && numAtoms == ((MolecularProperty) b).numAtoms &&molecularFormula.equals(((MolecularProperty) b).molecularFormula)){
            return true;
        }else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numEdges, molecularFormula,numAtoms);
    }
}
