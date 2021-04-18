package Algorithms;

import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

public class Greedy extends MoneyChangeProblem {
    /**
     * Vecteur solution de l'algorithme glouton
     */
    public static Solution solutionGreedy;

    /**
     * Montant ne donnant pas la solution optimale dans le systeme C pour l'algorithme glouton
     */
    public static int valeurBloquante = 8;

    public Greedy(Pieces inC){
        this.C = inC;
    }

    public void solve(Solution solution, int montant) { //nbpiece*log(montant) : majore par le plus grand log
        this.C.initDecroissant(); //Tri de l'ensemble de pièce dans l'ordre de valeur décroissante
        solutionGreedy = new Solution(C);
        int quotient = 0;
        int i = 0;
        int nbPiece = solutionGreedy.C.ensemblePieces.size();
        int valeurPiece = 0;
        while(i < nbPiece && montant > 0){
            valeurPiece = solutionGreedy.C.ensemblePieces.get(i);
            quotient = montant/(valeurPiece);
            solutionGreedy.X[i] += quotient;
            montant -= quotient*(valeurPiece);
            i++;
        }
    }

    @Override
    public Solution solveProblem(int montant) {
        //Initialisation de la valeur optimale
        //Lancement de la résolution
        solve(null, montant);
        if(displayResult){
            this.C.afficheValeur();
            System.out.println("\n\n-----------------");
            System.out.print("La solution est : ");
            solutionGreedy.afficheResultat();
            System.out.println("-----------------");
        }
        return solutionGreedy.copy();
    }


    public static void main(String[] args) {
        Pieces euro = new Euros();
        euro.init();
        Greedy test = new Greedy(euro);
        test.solveProblem(7453);
    }
}
