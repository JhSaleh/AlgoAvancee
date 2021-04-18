package Test;

import Algorithms.TrialAndError;
import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TrialAndErrorTest extends Test {
    /**
     * Désigne le nombre de tests
     */
    public static int nbTest = 100000;

    /**
     * Va créer un fichier csv qui permettra de grapher les résultats de compléxités plus tard
     * @param fileName
     */
    public TrialAndErrorTest(String fileName){
        this.fileName = fileName;
        String newFileName = cheminTest+fileName+".csv";
        File newFile = new File(newFileName);
        try {
            this.writer = new BufferedWriter(new FileWriter(newFile));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void complexiteSansConditionElagage(){

    }

    /**
     * Ensemble de pièce initialisé n'importe comment
     * @param pieces
     * @param montant
     * @param stateSet determine l'organisation de l'ensemble pour les test, 0 : 1 ensemble quelconque, 1 : ensemble ordre croissant, 2 ensemble ordre décroissant
     */
    public void complexiteAvecConditionElagage(Pieces pieces, int montant, int stateSet){
        TrialAndError trialAndError;
        Solution solution;
        enteteTour();
        switch (stateSet){
            case 0: break; //par défaut quelconque
            case 1: pieces.initCroissant(); break;
            case 2: pieces.initDecroissant(); break;
        }

        for(int i = 0; i < montant; i++) {
            TrialAndError.displayResult = false;
            trialAndError = new TrialAndError(pieces);
            solution =  trialAndError.solveProblem(i);
            writeLine(i, solution.nbTour);
        }
        stopWriting();
        System.out.println(this.fileName + "done !");
    }

    public void complexiteTempsAvecConditionElagage(Pieces pieces, int montant, int stateSet){
        long startTime = 0;
        long endTime = 0;

        TrialAndError trialAndError;
        Solution solution;
        enteteTemps();
        switch (stateSet){
            case 0: pieces.init(); break;
            case 1: pieces.initCroissant(); break;
            case 2: pieces.initDecroissant(); break;
        }

        for(int i = 0; i < montant; i++) {
            TrialAndError.displayResult = false;
            trialAndError = new TrialAndError(pieces);
            startTime = System.nanoTime();
            solution =  trialAndError.solveProblem(i);
            endTime = System.nanoTime();
            writeLine(i, endTime-startTime);
        }
        stopWriting();
        System.out.println(this.fileName + " done !");
    }


    public static void main(String[] args) {
        Euros euros = new Euros();
        TrialAndErrorTest test0;

        //Test 1
        test0 = new TrialAndErrorTest("Trial&ErrorAvecConditionElagageEnsembleDecroissantNbRecursion");
        test0.complexiteAvecConditionElagage(euros, nbTest, 2);


        //Test 2
        test0 = new TrialAndErrorTest("Trial&ErrorAvecConditionElagageEnsembleCroissantNbRecursion");
        test0.complexiteAvecConditionElagage(euros, nbTest, 1);

        //Test 3
        test0 = new TrialAndErrorTest("Trial&ErrorAvecConditionElagageEnsembleQuelconqueNbRecursion");
        test0.complexiteAvecConditionElagage(euros, nbTest, 0);



        //Mesure Temps
        test0 = new TrialAndErrorTest("Trial&ErrorAvecConditionElagageEnsembleDecroissantTemps");
        test0.complexiteTempsAvecConditionElagage(euros, nbTest, 2);


        //Test 2
        test0 = new TrialAndErrorTest("Trial&ErrorAvecConditionElagageEnsembleCroissantTemps");
        test0.complexiteTempsAvecConditionElagage(euros, nbTest, 1);

        //Test 3
        test0 = new TrialAndErrorTest("Trial&ErrorAvecConditionElagageEnsembleQuelconqueTemps");
        test0.complexiteTempsAvecConditionElagage(euros, nbTest, 0);

     }
}
