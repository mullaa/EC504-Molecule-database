package Database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MoleculeGraph {
    // Field
    public ArrayList<Vertex> vertices;
    public ArrayList<Edge> edges;
    public String secret;
    public int hnumOfAtom = 0;
    public int hnumOfEdge = 0;
    public HashMap<String, Integer> hMoleFormula;
    public HashMap<String, Integer> weightEdge;

    //constructor
    public MoleculeGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        secret = "";
    }

    public static void main(String[] args) {
        MoleculeGraph G = new MoleculeGraph("isomeric1@4@C@O@H@H@0 2@0 1@1 3@");
        System.out.println("hnumOfEdge: "+G.hnumOfEdge);
        System.out.println("hnumOfAtom: "+G.hnumOfAtom);
        System.out.println("hMoleFormula: "+G.hMoleFormula);
        System.out.println("weightEdge: "+G.weightEdge);
    }

    @SuppressWarnings("unchecked")

    public MoleculeGraph(String secretCode) {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        secret = secretCode;
        String tmp = "";
        List<String> list = new ArrayList();
        String[] array = secretCode.split("@");
        for (String value : array) {
            list.add(value);
            // System.out.println(value);
        }
        int moleculeNum = Integer.parseInt(list.get(1));

        // count Formula
        hMoleFormula = new HashMap<String, Integer>();
        for(int i = 2;i < moleculeNum+2; i++) {
            if (hMoleFormula.containsKey(list.get(i))) {
                hMoleFormula.put(list.get(i), hMoleFormula.get(list.get(i))+1);
            } else {
                hMoleFormula.put(list.get(i), 1);
            }
        }

        // count weight
        weightEdge = new HashMap<>();
        for(int i = moleculeNum+2;i < list.size();i++) {
            if (weightEdge.containsKey(list.get(i))) {
                weightEdge.put(list.get(i), weightEdge.get(list.get(i))+1);
            } else {
                weightEdge.put(list.get(i), 1);
            }
        }

        Integer count = Integer.parseInt(list.get(1));
        MoleculeGraph.Vertex[] Vlist = new MoleculeGraph.Vertex[count];
        for (int i = 0; i < count; i++) {
            Vlist[i] = addVertex(list.get(i+2));
            hnumOfAtom++;
        }
        Set<String> keys= weightEdge.keySet();
        for(String key:keys){
            int kongge = key.indexOf(" ");
            Integer temp1 = Integer.parseInt(String.valueOf(key.substring(0,kongge)));
            Integer temp2 = Integer.parseInt(String.valueOf(key.substring(kongge+1)));
            Integer weight = weightEdge.get(key);
            addEdge(Vlist[temp1],Vlist[temp2],weight);
            hnumOfEdge++;
        }
    }

    //method
    public String getSecret() {
        return secret;
    }


    public Vertex addVertex(String name) {
        Vertex newVertex = new Vertex(name);
        vertices.add(newVertex);
        return newVertex;
    }

    public Edge addEdge(Vertex vertexA,Vertex vertexB,Integer weight) {
        Edge newEdge = new Edge(vertexA,vertexB,weight);
        edges.add(new Edge(vertexA,vertexB,weight));
        edges.add(new Edge(vertexB,vertexA,weight));
        return newEdge;
    }

    public boolean isAdjacentQ(Vertex theVertexA, Vertex theVertexB) {
        for (Edge undirectedEdge : edges)
            if (undirectedEdge.VertexA == theVertexA &&
                    undirectedEdge.VertexB == theVertexB)
                return true;
        return false;
    }

    public Integer countEdge(Vertex vertexA, Vertex vertexB) {
        Integer count = 0;
        for (Edge undirectedEdge : edges)
            if (undirectedEdge.VertexA == vertexA &&
                    undirectedEdge.VertexB == vertexB)
                count = count + undirectedEdge.edgeWeight;
        return count;
    }

    public Integer degreeOf(Vertex vertexA) {
        Integer count = 0;
        for (int jj=0; jj<numVertices(); jj++)
            count = count + countEdge(vertexA,ithVertex(jj));
        return count;
    }

    public ArrayList<Vertex> neighborList(Vertex vertexA) {
        ArrayList<Vertex> Res = new ArrayList<>();
        for (int jj=0; jj<numVertices(); jj++)
            if (isAdjacentQ(vertexA,ithVertex(jj)))
                Res.add(ithVertex(jj));
        return Res;
    }

    public int numVertices() {
        return vertices.size();
    }

    public Vertex ithVertex(int ii) { return vertices.get(ii); }

    public String toString() {
        String result = "";
        for (int ii=0; ii<numVertices(); ii++) {
            result += vertices.get(ii) + ": ";
            for (int jj=0; jj<numVertices(); jj++)
                if (isAdjacentQ(ithVertex(ii),ithVertex(jj)))
                    result += vertices.get(jj) + " ";
            result+="\n";
        }
        return result;
    }

    static class Vertex{
        String name;
        boolean visited = false;
        public Vertex(String name) {
            this.name = name;
        }
        public  String toString(){
            return  "[" + name + "]";
        }
        @Override
        public boolean equals(Object b){
            if(b == null) return false;
            if(!(b instanceof Vertex)) return false;
            if(b == this ) return true;
            if (name.equals(((Vertex) b).name)) {
                return true;
            } else {
                return false;
            }
        }
    }

    class Edge{
        public Edge(Vertex myVertexA, Vertex myVertexB, Integer weight){
            VertexA = myVertexA;
            VertexB = myVertexB;
            if (weight != null)
                edgeWeight = weight;
            else edgeWeight = null;
        }
        public String toString() { return "(Edge from "+VertexA+" to "+VertexB+" edge's weight: "+ edgeWeight +")"; }
        final public Vertex VertexA;
        final public Vertex VertexB;
        public Integer edgeWeight;

        @Override
        public boolean equals(Object b){
            if(b == null) return false;
            if(!(b instanceof Edge)) return false;
            if(b == this ) return true;
            if (VertexA.equals (((Edge) b).VertexA) && VertexB.equals (((Edge) b).VertexB) && edgeWeight == ((Edge) b).edgeWeight) {
                return true;
            } else {
                return false;
            }
        }
    }



}
