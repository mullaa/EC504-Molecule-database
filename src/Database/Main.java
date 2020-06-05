package Database;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // newDatabase store = new newDatabase();
        // Storage st = new Storage();
        // store.addMolecule("/Users/sulihu/ec504/final/EC504-Molecule-database/new/water.txt");
        // st.writeCSV(store);
        System.out.println("Initializing the Database...");
        newDatabase base = new newDatabase();
        Storage store = new Storage();
        base = store.readCSV("Graphwriter.csv");
        System.out.println("Initialization completed ^ ^\n");
        long startTime=System.currentTimeMillis();
        System.out.println("Running the test case...\n");
        System.out.println("Add Function: \n");
        base.addMolecule("sulfuric_acid.txt");
        base.addMolecule("Mastoparan.txt");
        base.addMolecule("ammonia.txt");
        base.addMolecule("acetylene.txt");

        System.out.println("\nSearch Function: \n");
        base.findMolecule("water.txt");
        base.findMolecule("isomeric.txt");
        base.findMolecule("ammonia.txt");
        base.findMolecule("sulfuric_acid.txt");


        System.out.println("\nSubgraph Search Function: \n");
        base.findSubgraph("moleculesBromochlorodifluoromethane.txt");
        base.findSubgraph("moleculesDehydrophleomycin D1.txt");
        base.findSubgraph("Mastoparan.txt");

        long endTime=System.currentTimeMillis();
        System.out.println("10 operation running time (10,000 molecules)： " + (endTime-startTime) + "ms");


        System.out.println("__________________________________________________________________");
        System.out.println("Instructions:");
        System.out.println("./md --addMolecule [FILE PATH]");
        System.out.println("./md --findMolecule [FILE PATH]");
        System.out.println("./md --findSubGraph [FILE PATH]");
        System.out.println("./md --download  (To download 1000 compounds from online database)");
        System.out.println("./md exit");
        System.out.println("__________________________________________________________________");
        Scanner s = new Scanner(System.in);
        String input = s.nextLine();



        newDatabase basecmd = new newDatabase();
        Storage storecmd = new Storage();
        basecmd = storecmd.readCSV("Graphwriter.csv");

        while (input != null) {
            try {
                String[] command = input.trim().split("\\s+");
                if (!command[0].equals("./md")) {
                    System.out.println("Invalid command.");
                    System.out.println("------------------------------------");
                    System.out.println("Try \"./md --help\"  for instructions of all commands.");
                }else if (command[1].equals("--addMolecule")) {
                    try {
                        basecmd.addMolecule(command[2]);
                        System.out.println("------------------------------------");

                    } catch (Exception e) {
                        System.out.println("Filepath Error");
                        System.out.println("------------------------------------");
                        System.out.println("Try \"./md --help\"  for instructions of all commands.");
                    }
                } else if (command[1].equals("--findMolecule")) {
                    try {
                        basecmd.findMolecule(command[2]);
                        System.out.println("------------------------------------");

                    } catch (Exception e) {
                        System.out.println("Filepath Error");
                        System.out.println("------------------------------------");
                        System.out.println("Try \"./md --help\"  for instructions of all commands.");
                    }
                } else if (command[1].equals("--download")) {
                    System.out.println("Downloading ...");
                    Datastore download = new Datastore();
                    download.finaltest();
                    System.out.println("Database updated");
                    System.out.println("------------------------------------");
                    System.out.println("Try \"./md --help\"  for instructions of all commands.");
                } else if (command[1].equals("--findSubGraph")) {
                    try {
                        basecmd.findSubgraph(command[2]);
                        System.out.println("------------------------------------");

                    } catch (Exception e) {
                        System.out.println("Filepath Error");
                        System.out.println("------------------------------------");
                        System.out.println("Try \"./md --help\"  for instructions of all commands.");
                    }
                } else if (command[1].equals("exit")) {
                    System.out.println("Exiting...");
                    return;
                } else if (command[1].equals("--help")) {
                    System.out.println("___________________________________________________________________");
                    System.out.println("Instructions:");
                    System.out.println("./md --addMolecule [FILE PATH]");
                    System.out.println("./md --findMolecule [FILE PATH]");
                    System.out.println("./md --findSubGraph [FILE PATH]");
                    System.out.println("./md --download   (To download 1000 compounds from online database)");
                    System.out.println("./md exit");
                    System.out.println("___________________________________________________________________");
                } else {
                    System.out.println("Invalid command.");
                    System.out.println("------------------------------------");
                    System.out.println("Try \"./md --help\"  for instructions of all commands.");
                    //System.out.println("Try \"./md --help\" for instructions.");
                }
            } catch(Exception Ex){
                System.out.println("Invalid command.");
                System.out.println("------------------------------------");
                System.out.println("Try \"./md --help\"  for instructions of all commands.");

            }
            input = s.nextLine();
        }
        System.exit(0);
    }

}
