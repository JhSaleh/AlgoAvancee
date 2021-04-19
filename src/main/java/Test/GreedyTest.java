package Test;

import Algorithms.DynamicProgramming;
import Algorithms.Greedy;
import Algorithms.TrialAndError;
import Coins.Euros;
import Coins.Pieces;
import Coins.SpecialC;
import Coins.SpecialCPrime;
import Vectors.Solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GreedyTest extends Test {
    public static int nbTest = 10000;

    /**
     * Va créer un fichier csv qui permettra de grapher les résultats de compléxités plus tard
     *
     * @param fileName
     */
    public GreedyTest(String fileName) {
        this.fileName = fileName;
        String newFileName = cheminTest + fileName + ".csv";
        File newFile = new File(newFileName);
        try {
            this.writer = new BufferedWriter(new FileWriter(newFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test vérifiant experimentalement que le systeme C n'est pas canonique
     */
    public static void testNumericalSystemC() {
        Pieces C = new SpecialC();
        C.initDecroissant();
        int montant = 1;
        boolean done = false;

        while (!done) {
            Greedy greedy = new Greedy(C);
            Solution solutionGreedy = greedy.solveProblem(montant);

            DynamicProgramming dp = new DynamicProgramming(C);
            Solution solutionDP = dp.solveProblem(montant);


            System.out.println("===Programmation Dynamique");
            solutionDP.afficheResultat();
            System.out.println("===Algorithme Glouton");
            solutionGreedy.afficheResultat();

            if (solutionDP.getNbPieces() < solutionGreedy.getNbPieces()) {
                System.out.println("=========================Systeme non canonique==========================");
                solutionDP.afficheResultat();
                solutionGreedy.afficheResultat();
                done = true;
            } else {
                montant += 1;
            }
        }
    }

    /**
     * Test vérifiant experimentalement que le systeme de l'euro est canonique
     */
    public static void testNumericalSystemEuros() {
        Pieces euros = new Euros();
        euros.initDecroissant();
        int montant = 1;
        boolean done = false;
        Solution solutionGreedy = null;
        Solution solutionDP = null;
        while (!done) {
            Greedy greedy = new Greedy(euros);
            solutionGreedy = greedy.solveProblem(montant);
            DynamicProgramming.displayResult = false;
            DynamicProgramming dp = new DynamicProgramming(euros);
            solutionDP = dp.solveProblem(montant);


            if (solutionDP.getNbPieces() < solutionGreedy.getNbPieces()) {
                System.out.println("=========================Systeme non canonique==========================");
                solutionDP.afficheResultat();
                solutionGreedy.afficheResultat();
                done = true;
            } else {
                montant += 1;
            }
        }
        System.out.println("===Programmation Dynamique");
        solutionDP.afficheResultat();
        System.out.println("===Algorithme Glouton");
        solutionGreedy.afficheResultat();
    }


    /**
     * Test vérifiant expérimentalement que le système numéraire C' est canonique
     */
    public static void testNumericalSystemCPrime() {
        Pieces CP = new SpecialCPrime();
        CP.initDecroissant();
        int montant = 1;
        boolean done = false;

        Solution solutionGreedy = null;
        Solution solutionDP = null;
        while (!done) {
            Greedy greedy = new Greedy(CP);
            solutionGreedy = greedy.solveProblem(montant);

            DynamicProgramming dp = new DynamicProgramming(CP);
            solutionDP = dp.solveProblem(montant);

            if (solutionDP.getNbPieces() < solutionGreedy.getNbPieces()) {
                System.out.println("========================Systeme non canonique==============================");
                solutionDP.afficheResultat();
                solutionGreedy.afficheResultat();
                done = true;
            } else {
                montant += 1;
            }
        }
        System.out.println("===Programmation Dynamique");
        solutionDP.afficheResultat();
        System.out.println("===Algorithme Glouton");
        solutionGreedy.afficheResultat();
    }

    /**
     * Méthode réalisant des benchmarks de compléxité pour l'algorithme glouton
     * @param pieces
     * @param montant
     * @param stateSet
     */
    public void complexiteTempsGreedy(Pieces pieces, int montant, int stateSet) {
        long startTime = 0;
        long endTime = 0;

        Greedy greedy;
        Solution solution;
        enteteTemps();
        switch (stateSet) {
            case 0:
                pieces.init();
                break;
            case 1:
                pieces.initCroissant();
                break;
            case 2:
                pieces.initDecroissant();
                break;
        }

        for (int i = 0; i < montant; i++) {
            Greedy.displayResult = false;
            greedy = new Greedy(pieces);
            startTime = System.nanoTime();
            solution = greedy.solveProblem(i);
            endTime = System.nanoTime();
            writeLine(i, endTime - startTime);
        }
        stopWriting();
        System.out.println(this.fileName + " done !");
    }

    public static void main(String[] args) {
        Euros euros = new Euros();
        GreedyTest test0;
        //Test 1
        test0 = new GreedyTest("GreedyEnsembleDecroissantTemps");
        test0.complexiteTempsGreedy(euros, 100000, 2);

        //Test 2
        test0 = new GreedyTest("GreedyEnsembleCroissantTemps");
        test0.complexiteTempsGreedy(euros, 100000, 1);

        //Test 3
        test0 = new GreedyTest("GreedyEnsembleQuelconqueTemps");
        test0.complexiteTempsGreedy(euros, 100000, 0);

        testNumericalSystemC();
    }
}
