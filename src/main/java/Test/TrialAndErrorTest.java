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
    public static int nbTest = 10000;

    /**
     * Désigne le nombre de tests dans le cas sans élagage
     */
    public static int nbTestSansElagage = 36;

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

    /**
     * Mesure la compléxité de l'algorithme en désactivant toutes les conditions d'élagage
     * @param pieces
     * @param montant
     * @param stateSet
     */
    public void complexiteSansConditionElagage(Pieces pieces, int montant, int stateSet){
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
            TrialAndError.conditionElagage = false; //Désactivation des conditions d'élagage
            trialAndError = new TrialAndError(pieces);
            solution =  trialAndError.solveProblem(i);
            writeLine(i, solution.nbTour);
        }
        stopWriting();
        System.out.println(this.fileName + "done !");
    }

    /**
     * Mesure la complexité de l'algorithme en activant qu'une seule condition d'élagage à la fois
     * @param pieces
     * @param montant
     * @param stateSet
     * @param numeroConditionElagage
     */
    public void complexiteAvecUneSeuleConditionElagage(Pieces pieces, int montant, int stateSet, int numeroConditionElagage){
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
            TrialAndError.conditionElagage = true;
            switch (numeroConditionElagage){
                case 0: TrialAndError.conditionElagageInterditDuplicat = true; TrialAndError.conditionElagageEncorePossible = false; TrialAndError.conditionElagageEvaluation = false; TrialAndError.conditionElagageEvaluationCheminSolutionOptimale = false; TrialAndError.conditionElagageEvaluationCheminEstimationSolutionOptimale = false; break;
                case 1: TrialAndError.conditionElagageInterditDuplicat = false; TrialAndError.conditionElagageEncorePossible = true; TrialAndError.conditionElagageEvaluation = false; TrialAndError.conditionElagageEvaluationCheminSolutionOptimale = false; TrialAndError.conditionElagageEvaluationCheminEstimationSolutionOptimale = false; break;
                case 2: TrialAndError.conditionElagageInterditDuplicat = false; TrialAndError.conditionElagageEncorePossible = false; TrialAndError.conditionElagageEvaluation = true; TrialAndError.conditionElagageEvaluationCheminSolutionOptimale = false; TrialAndError.conditionElagageEvaluationCheminEstimationSolutionOptimale = false; break;
                case 3: TrialAndError.conditionElagageInterditDuplicat = false; TrialAndError.conditionElagageEncorePossible = false; TrialAndError.conditionElagageEvaluation = false; TrialAndError.conditionElagageEvaluationCheminSolutionOptimale = true; TrialAndError.conditionElagageEvaluationCheminEstimationSolutionOptimale = false; break;
                case 4: TrialAndError.conditionElagageInterditDuplicat = false; TrialAndError.conditionElagageEncorePossible = false; TrialAndError.conditionElagageEvaluation = false; TrialAndError.conditionElagageEvaluationCheminSolutionOptimale = false; TrialAndError.conditionElagageEvaluationCheminEstimationSolutionOptimale = true; break;
                default: System.out.println("Les exceptions vont de 0 à 4."); break;
            }
            trialAndError = new TrialAndError(pieces);
            solution =  trialAndError.solveProblem(i);
            writeLine(i, solution.nbTour);
        }
        stopWriting();
        System.out.println(this.fileName + "done !");
    }

    /**
     * Mesure la complexité en nombre d'appel
     * @param pieces Ensemble de pièce initialisé n'importe comment
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

    /**
     * Mesure la compléxité temporelle de la méthode
     * @param pieces
     * @param montant
     * @param stateSet
     */
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


        //Sans conditions d'élagages
        //Test 1
        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursion");
        test0.complexiteSansConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 2);

        //Test 2
        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleCroissantNbRecursion");
        test0.complexiteSansConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 1);

        //Test 3
        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleQuelconqueNbRecursion");
        test0.complexiteSansConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0);

        //Sans condition d'élagage avec une seule condition activé à la fois
        //Cas décroissant

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEncorePossible");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 1);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEvaluation");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 2);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEvaluationCheminSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 3);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEvaluationCheminEstimationSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 4);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionInterditDuplicat");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 0);


        //Cas croissant
        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleCroissantNbRecursionInterditDuplicat");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 1, 0);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleCroissantNbRecursionEncorePossible");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 1, 1);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleCroissantNbRecursionEvaluation");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 1, 2);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleCroissantNbRecursionEvaluationCheminSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 1, 3);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleCroissantNbRecursionEvaluationCheminEstimationSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 1, 4);

        //Cas quelconque
        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleQuelconqueNbRecursionInterditDuplicat");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 2, 0);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleQuelconqueNbRecursionEncorePossible");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 2, 1);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleQuelconqueNbRecursionEvaluation");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 2, 2);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleQuelconqueNbRecursionEvaluationCheminSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 2, 3);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleQuelconqueNbRecursionEvaluationCheminEstimationSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 2, 4);

    }
}
