import Algorithms.DynamicProgramming;
import Algorithms.Greedy;

import Coins.Euros;
import Coins.Pieces;
import Coins.SpecialCPrime;
import Test.TrialAndErrorTest;
import Vectors.Solution;

public class Main {
    public static void main(String[] args) {
        //=====> Nouveau tests à run
        Euros euros = new Euros();
        TrialAndErrorTest test0;
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
        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionInterditDuplicat");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 0);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEncorePossible");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 1);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEvaluation");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 2);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEvaluationCheminSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 3);

        test0 = new TrialAndErrorTest("Trial&ErrorSansConditionElagageEnsembleDecroissantNbRecursionEvaluationCheminEstimationSolutionOptimale");
        test0.complexiteAvecUneSeuleConditionElagage(euros, TrialAndErrorTest.nbTestSansElagage, 0, 4);

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
