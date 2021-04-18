package Test;

import Algorithms.DynamicProgramming;
import Algorithms.Greedy;
import Algorithms.TrialAndError;
import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DynamicProgrammingTest extends Test {
    public static int nbTest = 1000000;

    /**
     * Va créer un fichier csv qui permettra de grapher les résultats de compléxités plus tard
     * @param fileName
     */
    public DynamicProgrammingTest(String fileName){
        this.fileName = fileName;
        String newFileName = cheminTest+fileName+".csv";
        File newFile = new File(newFileName);
        try {
            this.writer = new BufferedWriter(new FileWriter(newFile));
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void complexiteTempsDynamicProgramming(Pieces pieces, int montant, int stateSet){
        long startTime = 0;
        long endTime = 0;

        DynamicProgramming dynamicProgramming;
        Solution solution;
        enteteTemps();
        switch (stateSet){
            case 0: pieces.init(); break;
            case 1: pieces.initCroissant(); break;
            case 2: pieces.initDecroissant(); break;
        }

        for(int i = 0; i < montant; i++) {
            DynamicProgramming.displayResult = false;
            dynamicProgramming = new DynamicProgramming(pieces);
            startTime = System.nanoTime();
            solution =  dynamicProgramming.solveProblem(i);
            endTime = System.nanoTime();
            writeLine(i, endTime-startTime);
        }
        stopWriting();
        System.out.println(this.fileName + " done !");
    }

    public static void main(String[] args) {
        Euros euros = new Euros();
        DynamicProgrammingTest test0;

        //Test 1
        test0 = new DynamicProgrammingTest("DynamicProgrammingTestEnsembleDecroissantTemps");
        test0.complexiteTempsDynamicProgramming(euros, nbTest, 2);

        //Test 2
        test0 = new DynamicProgrammingTest("DynamicProgrammingTestEnsembleCroissantTemps");
        test0.complexiteTempsDynamicProgramming(euros, nbTest, 1);

        //Test 3
        test0 = new DynamicProgrammingTest("DynamicProgrammingTestEnsembleQuelconqueTemps");
        test0.complexiteTempsDynamicProgramming(euros, nbTest, 0);
    }
}
