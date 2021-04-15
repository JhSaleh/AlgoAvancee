package Algorithms;

import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

public class DynamicProgramming extends MoneyChangeProblem {
    public static Solution solutionDP;
    public static int[] A;
    public static int[] S;
    /**
     * Matrice contenant le nombre minimal de pièce pour tous les montants et tous les ensembles
     * de pièces
     */
    public static int[][] T;

    /**
     * Matrice contenant les vecteurs de solutions pour le nombre minimal de pièce pour tous les montants et tous les ensembles
     * de pièces
     */
    public static Solution[][] TSol;



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
        //System.out.print("\nSol<");
        while (montant > 0) {
            //System.out.print(S[montant] + ",");
            montant -= S[montant];
        }
        //System.out.print(">\n");
    }

    public void afficheSolutionT(int montant){
        //System.out.print("\nSol<");
        while (montant > 0) {
            //System.out.print(S[montant] + ",");
            montant -= T[0][montant];
        }
        //System.out.print(">\n");
    }

    @Override
    public void solve(Solution solution, int montant) {
        A = new int[montant + 1]; //NBP
        S = new int[montant + 1]; //Pieces utilisées pour la solution
        //Plus un pour atteidre la dénomination
        A[0] = 0;

        int min;
        int di;

        int piece;
        for(int p = 1; p<=montant; p++){ //p va explorer les différents sous montants jusqu'à atteindre le montant optimal
            piece = - 1;
            min = Integer.MAX_VALUE; //min<-+inf
            for(int i = 0; i < getTailleEnsemblePiece(); i++){ //On parcourt chaque pieces de la monnaie
                di = C.ensemblePieces.get(i); //Valeur de la piece
                if(di <= p){
                    if(1 + A[p - di] < min){
                        min = 1 + A[p-di];
                        piece = di;
                    }
                }
            }
            A[p] = min; //Nombre minimale de pieces pour le montant p
            S[p] = piece;
        }
    }

    public void solveT(Solution solution, int montant){
        //Initialisation de la structure tabulaire
        int nbPiece = this.C.ensemblePieces.size();
        T = new int[nbPiece+1][montant+1];
        TSol = new Solution[nbPiece+1][montant+1];

        //Initialisation de la matrice du nombres de pièces minimales pour chaque montant et pour chaque sous-ensemble de pièces
        for(int i = 0; i<=montant; i++){
            T[0][i] = Integer.MAX_VALUE;
        }
        //Initialisation de la matrice des vecteurs solutions du nombres de pièces minimales pour chaque montant et pour chaque sous-ensemble de pièces
        for(int j = 0; j<=nbPiece;j++){
            for(int k = 0; k<=montant; k++){
                TSol[j][k] = new Solution(this.C, false);
            }
        }

        for(int c=1; c<=nbPiece; c++) {
            for (int r = 1; r <= montant; r++) {
                int valeurPiece = C.ensemblePieces.get(c-1);
                if(valeurPiece == r) {
                    //Utilisation de la pièce correspondant au sous-problème de montant r
                    T[c][r] = 1;
                    //Version vecteur de solution
                    TSol[c][r].X[c-1] = 1;
                } else if (valeurPiece > r) {
                    //La pièce d'indice c-1 ne peut plus etre utilisées pour le montant r,
                    //les solutions pour le meme montant r donné avec des ensembles de pièces de plus en plus grand, sont les solutions précèdentes
                    T[c][r] = T[c - 1][r];

                    TSol[c][r] = TSol[c-1][r].copy();
                } else {
                    //Application de la formule de récurrence
                    T[c][r] = Math.min(T[c - 1][r], 1 + T[c][r - valeurPiece]);

                    if(T[c - 1][r] < 1 + T[c][r - valeurPiece]) {
                        TSol[c][r] = TSol[c - 1][r].copy();
                    } else {
                        TSol[c][r] = TSol[c][r - valeurPiece].copy();
                        TSol[c][r].X[c-1] += 1;
                    }
                }
            }
        }
        System.out.println(T[nbPiece][montant]);
        TSol[nbPiece][montant].afficheResultat();
    }

    @Override
    public Solution solveProblem(int montant) {
        this.C.initCroissant(); //La recherche doit toujours commencer par les plus petites pièces
        solutionDP = new Solution(C, false);
        solveT(null, montant);

        for(int i = 0; i<T.length; i++){
            for(int j = 0; j<T[0].length; j++){
                System.out.print("|"+T[i][j]);
            }
            System.out.print("\n");
        }


        /**
        System.out.print("Pieces utilise\n------------------------------------\n");
        for(int j = 0; j<A.length; j++){
            System.out.print(S[j]+" ");
        }
        **/

        //afficheSolution(montant);

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
        //return appliqueSolution(montant);
        return null;
    }

    public static void main(String[] args) {
        Pieces euros = new Euros();
        euros.initDecroissant();
        DynamicProgramming test = new DynamicProgramming(euros);
        Solution solution = test.solveProblem(150);
        //solution.afficheResultat();

        /*
        Solution solution1 = test.appliqueSolution(7453);
        solution1.afficheResultat();
        */
    }
}
