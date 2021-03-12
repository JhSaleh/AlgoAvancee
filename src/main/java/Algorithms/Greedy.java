package Algorithms;

import Coins.Pieces;
import Coins.SpecialC;
import Vectors.Solution;

public class Greedy extends MoneyChangeProblem {
    public static Solution solutionGreedy;
    public static int valeurBloquante = 8;

    public Greedy(Pieces inC){
        this.C = inC;
    }

    @Override
    public void solve(Solution solution, int montant) {
        solutionGreedy = new Solution(C);
        int i = 0;
        while(montant > 0){
            solutionGreedy.X[i] = montant / C.ensemblePieces.get(i);
            montant = montant % C.ensemblePieces.get(i);
            i++;
        }
    }

    @Override
    public Solution solveProblem(int montant) {
        C.initDecroissant(); //Garantit que les pièces seront choisie dans l'ordre décroissant
        //Initialisation de la valeur optimale
        //Lancement de la résolution
        solve(null, montant);
        this.C.afficheValeur();
        System.out.println("\n\n-----------------");
        System.out.print("La solution est : ");
        solutionGreedy.afficheResultat();
        System.out.println("-----------------");
        return solutionGreedy.copy();
    }

    public static void main(String[] args) {
        Pieces specialC = new SpecialC();
        specialC.initCroissant();
        Greedy test = new Greedy(specialC);
        test.solveProblem(valeurBloquante);
    }
}
