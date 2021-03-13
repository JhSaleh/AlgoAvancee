package Algorithms;

import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

public class DynamicProgramming extends MoneyChangeProblem {
    public static Solution solutionDP;
    public static int[] A;
    public static int[] S;

    public DynamicProgramming(Pieces inC){
        this.C = inC;
    }

    public Solution appliqueSolution(int montant){
        int i;
        while (montant > 0) {
            i = this.C.ensemblePieces.indexOf(S[montant]); //Récupération de l'indice de la piece
            solutionDP.X[i] += 1; //AJout
            montant -= S[montant]; //Maj du montant
        }
        return solutionDP.copy();
    }

    public void afficheSolution(int montant){
        System.out.print("\nSol<");
        while (montant > 0) {
            System.out.print(S[montant] + ",");
            montant -= S[montant];
        }
        System.out.print(">\n");
    }

    @Override
    public void solve(Solution solution, int montant) {
        A = new int[montant + 1]; //NBP
        S = new int[montant + 1]; //Pieces utilisées pour la solution
        //Plus un pour atteidre la dénomination 500
        A[0] = 0;

        int min;
        int di;

        int piece;
        for(int p = 1; p<=montant; p++){ //p va explorer les différents sous montants jusqu'à atteindre le montant optimal
            piece = - 1;
            min = Integer.MAX_VALUE; //min<-+inf
            for(int i = 0; i< getTailleEnsemblePiece(); i++){ //On parcourt chaque pieces de la monnaie
                di = C.ensemblePieces.get(i); //Valeur de la piece

                if(di <= p){
                    if(1 + A[p - di]< min){
                        min = 1 + A[p-di];
                        piece = di;
                    }
                }
            }
            A[p] = min; //Nombre minimale de pieces pour le montant p
            S[p] = piece;
        }
    }

    @Override
    public Solution solveProblem(int montant) {
        solutionDP = new Solution(C, false);
        solve(null, montant);

        /**
        System.out.print("Pieces utilise\n------------------------------------\n");
        for(int j = 0; j<A.length; j++){
            System.out.print(S[j]+" ");
        }
        **/

        afficheSolution(montant);

        /*
        System.out.println("Sous-probleme\n----------------------------");
        for(int k = 0; k<A.length; k++){
            System.out.print(k+" ");
        }
        System.out.print("Solutions\n------------------------------------");
        for(int i = 0; i<A.length; i++){
            System.out.print(A[i]+" ");
        }
        System.out.print("Pieces utilise\n------------------------------------");
        for(int j = 0; j<A.length; j++){
            System.out.print(S[j]+" ");
        }
        return null;

         */
        return appliqueSolution(montant);
    }

    public static void main(String[] args) {
        Pieces euros = new Euros();
        euros.init();
        DynamicProgramming test = new DynamicProgramming(euros);
        Solution solution = test.solveProblem(2508060);
        solution.afficheResultat();
    }
}
