import javafx.util.Pair;

import java.util.*;

public class graphMatch {
    boolean found= false;
    boolean found1= false;
    Vector<Pair<Object, Object>> Map= new Vector<>();
    MoleculeGraph G = new MoleculeGraph();
    MoleculeGraph SUBG = new MoleculeGraph();

    public graphMatch(MoleculeGraph G1,MoleculeGraph SUBG1){
        G = G1;
        SUBG = SUBG1;
        found = false;
        found1 = false;
        Map = new Vector<>();
    }

    public boolean contain() {
//            Set verticesG = G.vertices;
//            Set verticesSUBG = (Set)SUBG.vertices;
        Map = new Vector<>();
        Iterator I = SUBG.vertices.iterator();
        Iterator I1 = G.vertices.iterator();
        MoleculeGraph.Vertex VertexA = (MoleculeGraph.Vertex) I.next();
        while (I1.hasNext()) {
            MoleculeGraph.Vertex VertexB = (MoleculeGraph.Vertex) I1.next();
            if(Mapping(VertexA,VertexB)) {
                found = true;
                return true;
            }
        }
        if (!found)
            return false;
        return false;
    }

    boolean Mapping(MoleculeGraph.Vertex VertexA, MoleculeGraph.Vertex VertexB) {

        boolean found1 = false;
        if (Map.size() == SUBG.vertices.size()) {
            found = true;
            return (true);
        }
        if (equalVetex(VertexA,VertexB)){
            Vector<Pair<MoleculeGraph.Vertex, MoleculeGraph.Vertex>> Pairs = getequalVertex(VertexA, VertexB);
            if (Pairs == null)
                return false;
            else {
                Pair P1 = new Pair(VertexA,VertexB);
                Map.add(P1);
                for(int i = 0; i < Pairs.size(); i++) {
                    if (found)
                        break;
                    if (found1) {
                        found1 = false;
                        continue;
                    }
                    Mapping(Pairs.get(i).getKey(),Pairs.get(i).getValue());
                }
                if (Map.size() == SUBG.vertices.size()) {
                    Map.remove(Map.size() - 1);
                    return false;
                }
                else
                    return true;
            }
        }
        return false;
    }

    Vector<Pair<MoleculeGraph.Vertex, MoleculeGraph.Vertex>> getequalVertex (MoleculeGraph.Vertex VertexA, MoleculeGraph.Vertex VertexB) {

        Vector<Pair<MoleculeGraph.Vertex, MoleculeGraph.Vertex>> Res = new Vector<>();

        ArrayList<MoleculeGraph.Vertex> A_neighbor = SUBG.neighborList(VertexA);

        ArrayList<MoleculeGraph.Vertex> B_neighbor = G.neighborList(VertexB);

        for (int i=0; i < A_neighbor.size();i++) {
            MoleculeGraph.Vertex A = A_neighbor.get(i);
            for(int j=0; j < B_neighbor.size();j++) {
                MoleculeGraph.Vertex B = B_neighbor.get(j);
                if (equalVetex(A,B)){
                    if(!Map.contains(A)|| !Map.contains(B)) {
                        Pair P1 = new Pair(A,B);
                        Res.add(P1);
                    }
                    if(SUBG.vertices.size() == Map.size()) {
                        found1 = true;
                        return Res;
                    }
                }
            }
        }
        return Res;
    }

    boolean equalVetex(MoleculeGraph.Vertex VertexA, MoleculeGraph.Vertex VertexB) {
        if(VertexA.name.equals(VertexB.name)) {
            if (SUBG.degreeOf(VertexA) <= G.degreeOf(VertexB)) {
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
