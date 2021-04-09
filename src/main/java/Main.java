import Algorithms.DynamicProgramming;
import Algorithms.Greedy;

import Coins.Pieces;
import Coins.SpecialCPrime;
import Vectors.Solution;

public class Main {
    public static void main(String[] args) {
        Pieces CP = new SpecialCPrime();
        CP.initDecroissant();
        int montant = 1;
        boolean done = false;

        while(!done){
            Greedy greedy = new Greedy(CP);
            Solution solutionGreedy = greedy.solveProblem(montant);

            DynamicProgramming dp = new DynamicProgramming(CP);
            Solution solutionDP = dp.solveProblem(montant);


            System.out.println("===Programmation Dynamique");
            solutionDP.afficheResultat();
            System.out.println("===Algorithme Glouton");
            solutionGreedy.afficheResultat();

            if(solutionDP.getNbPieces() < solutionGreedy.getNbPieces()){
                System.out.println("===============================================Systeme non canonique===============================================");
                solutionDP.afficheResultat();
                solutionGreedy.afficheResultat();
                done = true;
            } else {
                montant += 1;
            }
        }

    }
}
