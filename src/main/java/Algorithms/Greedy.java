package Algorithms;

import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

public class Greedy extends MoneyChangeProblem {
    public static Solution solutionGreedy;
    public static int valeurBloquante = 8;

    public Greedy(Pieces inC){
        this.C = inC;
    }


    public void solve(Solution solution, int montant) {
        solutionGreedy = new Solution(C);
        int i = 0;
        while(solutionGreedy.getMontant() < montant){
            if(solutionGreedy.getMontant() + C.ensemblePieces.get(i) <= montant ){ //La pièce de plus grande valeur est donnée tant que c'est possible
                solutionGreedy.X[i] += 1; //Ajout de la piece
            } else  { //Sinon on passe à la pièce suivante
                i++;
            }
        }
    }

    public void solveT(Solution solution, int montant) { //nbpiece*log(montant) : majore par le plus grand log
        solutionGreedy = new Solution(C);
        int quotient = 0;
        for(int i = 0; i < solutionGreedy.C.ensemblePieces.size(); i++){
            quotient = montant/(solutionGreedy.C.ensemblePieces.get(i));
            solutionGreedy.X[i] += quotient;
            montant -= quotient*(solutionGreedy.C.ensemblePieces.get(i));
        }
    }

    @Override
    public Solution solveProblem(int montant) {
        C.initDecroissant(); //Garantit que les pièces seront choisie dans l'ordre décroissant
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


    public Solution solveProblemT(int montant) {
        C.initDecroissant(); //Garantit que les pièces seront choisie dans l'ordre décroissant
        //Initialisation de la valeur optimale
        //Lancement de la résolution
        solveT(null, montant);
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
        test.solveProblemT(7453);
    }
}
