package com.example.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MoleculeGraph {
    // Field
    public ArrayList<Vertex> vertices;
    public ArrayList<Edge> edges;
    public String secret;

    //constructor
    public MoleculeGraph() {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        secret = "";
    }

    public static void main(String[] args) {
        MoleculeGraph G = new MoleculeGraph("isomeric1,4,C,O,H,H,0 2,0 1,1 3,");
        System.out.println(G);
        System.out.println(G.edges);
    }


    @SuppressWarnings("unchecked")

    public MoleculeGraph(String secretCode) {
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        secret = secretCode;
        String tmp = "";
        List<String> list = new ArrayList();
        String[] array = secretCode.split(",");
        for (String value : array) {
            list.add(value);
        }
        int moleculeNum = Integer.parseInt(list.get(1));
        // count weight
        HashMap<String,Integer> weightEdge = new HashMap<>();
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
        }
        Set<String> keys= weightEdge.keySet();
        for(String key:keys){
            int kongge = key.indexOf(" ");
            Integer temp1 = Integer.parseInt(String.valueOf(key.substring(0,kongge)));
            Integer temp2 = Integer.parseInt(String.valueOf(key.substring(kongge+1)));
            Integer weight = weightEdge.get(key);
            addEdge(Vlist[temp1],Vlist[temp2],weight);
        }
    }


//    public MoleculeGraph(String filepath) {
//        vertices = new ArrayList<>();
//        edges = new ArrayList<>();
//        secret = "";
//        String tmp = "";
//        List<String> list = new ArrayList();
//        try { // file build or open mistake
//            /* Read text file of module */
//            //String pathname = moculename+".txt";
//            File filename = new File(filepath); //
//            InputStreamReader reader = new InputStreamReader(
//                    new FileInputStream(filename)); // build an input stream object reader
//            BufferedReader br = new BufferedReader(reader); // transfer the text file to machine language
//            String line = "";
//            line = br.readLine();
//            tmp += line + ",";
//            list.add(line);
//            while (line != null) {
//                line = br.readLine(); // read the file line by line
//                if (line == null) {
//                    break;
//                }
//                tmp += line+ ",";
//                list.add(line);
//            }
//            secret = tmp;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        list.remove(null);
//
//        int moleculeNum = Integer.parseInt(list.get(1));
//        // count weight
//        HashMap<String,Integer> weightEdge = new HashMap<>();
//        for(int i = moleculeNum+2;i < list.size();i++) {
//            if (weightEdge.containsKey(list.get(i))) {
//                weightEdge.put(list.get(i), weightEdge.get(list.get(i))+1);
//            } else {
//                weightEdge.put(list.get(i), 1);
//            }
//        }
//        Integer count = Integer.parseInt(list.get(1));
//        MoleculeGraph.Vertex[] Vlist = new MoleculeGraph.Vertex[count];
//        for (int i = 0; i < count; i++) {
//            Vlist[i] = addVertex(list.get(i+2));
//        }
//        Set<String> keys= weightEdge.keySet();
//        for(String key:keys){
//            int kongge = key.indexOf(" ");
//            Integer temp1 = Integer.parseInt(String.valueOf(key.substring(0,kongge)));
//            Integer temp2 = Integer.parseInt(String.valueOf(key.substring(kongge+1)));
//            Integer weight = weightEdge.get(key);
//            addEdge(Vlist[temp1],Vlist[temp2],weight);
//        }
//    }



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
    }



}
