package Algorithms;

import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

public class DynamicProgramming extends MoneyChangeProblem {
    /**
     * Vecteur contenant la solution de l'algorithme dynamique
     */
    public static Solution solutionDP;

    /**
     * Matrice contenant le nombre minimal de pièce pour tous les montants et tous les ensembles
     * de pièces
     * NB : Si la valeur est égal à Integer.MAX_VALUE, i.e. l'infini, c'est qu'il n'y a pas de solution
     */
    public static double[][] TD;

    /**
     * Matrice contenant les vecteurs solutions de nombre minimal de pièce pour tous les montants et tous les ensembles
     * de pièces
     */
    public static Solution[][] TSol;

    public DynamicProgramming(Pieces inC){
        this.C = inC;
    }

    public void initialisationTableaux(int montant){
        //Initialisation de la structure tabulaire
        int nbPiece = this.C.ensemblePieces.size();
        TD = new double[nbPiece+1][montant+1];
        TSol = new Solution[nbPiece+1][montant+1];

        //Initialisation de la matrice du nombres de pièces minimales pour chaque montant et pour chaque sous-ensemble de pièces
        //Initialisation de la première ligne avec une valeur représentant l'infini, parce qu'il est impossible
        //pour un montant d'etre fait avec 0 pièce
        for(int i = 0; i<=montant; i++){
            TD[0][i] = Integer.MAX_VALUE;
        }

        //Initialisation de la première colonne avec des 0
        //car un montant de valeur 0 peut etre obtenu avec 0 piece
        for(int j = 1; j<=nbPiece;j++) {
            TD[j][0] = 0;
        }

        //Initialisation de la matrice des vecteurs solutions du nombres de pièces minimales pour chaque montant et pour chaque sous-ensemble de pièces
        for(int j = 0; j<=nbPiece;j++){
            for(int k = 0; k<=montant; k++){
                TSol[j][k] = new Solution(this.C, false);
            }
        }
    }

    public void solve(Solution solution, int montant){
        int nbPiece = this.C.ensemblePieces.size();
        initialisationTableaux(montant);

        for(int c=1; c<=nbPiece; c++) {
            for (int r = 1; r <= montant; r++) {
                int valeurPiece = C.ensemblePieces.get(c-1);
                //Application de la formule de récurrence
                if (valeurPiece <= r) {
                     TD[c][r] = Math.min(TD[c - 1][r], 1 + TD[c][r - valeurPiece]);

                     //Version tableau de vecteur candidat
                     if(TD[c - 1][r] < 1 + TD[c][r - valeurPiece]) {
                         TSol[c][r] = TSol[c - 1][r].copy();
                     } else {
                         TSol[c][r] = TSol[c][r - valeurPiece].copy();
                         TSol[c][r].X[c-1] += 1;
                     }
                } else {
                     TD[c][r] = TD[c - 1][r];

                    //Version tableau de vecteur candidat
                    TSol[c][r] = TSol[c-1][r].copy();
                }
            }
        }
        solutionDP = TSol[nbPiece][montant].copy();
    }



    @Override
    public Solution solveProblem(int montant) {
        solutionDP = new Solution(C, false);
        solve(null, montant);
        if(displayResult) {
            for (int i = 0; i < TD.length; i++) {
                for (int j = 0; j < TD[0].length; j++) {
                    System.out.print("|" + (int) TD[i][j]);
                }
                System.out.print("\n");
            }
        }
        return solutionDP.copy();
    }

    public static void main(String[] args) {
        Pieces euros = new Euros();
        euros.init();
        DynamicProgramming test = new DynamicProgramming(euros);
        Solution solution = test.solveProblem(7453);
        solution.afficheResultat();
    }
}
