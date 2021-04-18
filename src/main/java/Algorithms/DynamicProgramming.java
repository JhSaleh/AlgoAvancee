package Algorithms;

import Coins.Pieces;
import Coins.SpecialC;
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
    public static double[][] T;

    /**
     * Matrice contenant les vecteurs solutions de nombre minimal de pièce pour tous les montants et tous les ensembles
     * de pièces
     */
    public static Solution[][] TSol;

    public DynamicProgramming(Pieces inC){
        this.C = inC;
    }

    /**
     * Initialise les deux structures tabulaires : nombres de pièces minimales et les vecteurs solutions dont le nombre de pièce est minimal
     * @param montant
     */
    public void initialisationTableaux(int montant){
        //Initialisation de la structure tabulaire
        int nbPiece = this.C.ensemblePieces.size();
        T = new double[nbPiece+1][montant+1];
        TSol = new Solution[nbPiece+1][montant+1];

        //Initialisation de la matrice du nombres de pièces minimales pour chaque montant et pour chaque sous-ensemble de pièces
        //Initialisation de la première ligne avec une valeur représentant l'infini, parce qu'il est impossible
        //pour un montant d'etre fait avec 0 pièce
        for(int i = 0; i<=montant; i++){
            T[0][i] = Integer.MAX_VALUE;
        }

        //Initialisation de la première colonne avec des 0
        //car un montant de valeur 0 peut etre obtenu avec 0 piece
        for(int j = 1; j<=nbPiece;j++) {
            T[j][0] = 0;
        }

        //Initialisation de la matrice des vecteurs solutions du nombres de pièces minimales pour chaque montant et pour chaque sous-ensemble de pièces
        for(int j = 0; j<=nbPiece;j++){
            for(int k = 0; k<=montant; k++){
                TSol[j][k] = new Solution(this.C, false);
            }
        }
    }

    /**
     * Algorithme Programmation Dynamique
     * @param solution
     * @param montant
     */
    public void solve(Solution solution, int montant){
        int nbPiece = this.C.ensemblePieces.size();

        for(int c=1; c<=nbPiece; c++) {
            for (int r = 1; r <= montant; r++) {
                int valeurPiece = C.ensemblePieces.get(c-1);
                //Application de la formule de récurrence
                if (valeurPiece <= r) {
                     T[c][r] = Math.min(T[c - 1][r], 1 + T[c][r - valeurPiece]);

                     //Version tableau de vecteur candidat
                     if(T[c - 1][r] < 1 + T[c][r - valeurPiece]) {
                         TSol[c][r] = TSol[c - 1][r].copy();
                     } else {
                         TSol[c][r] = TSol[c][r - valeurPiece].copy();
                         TSol[c][r].X[c-1] += 1;
                     }
                } else {
                     T[c][r] = T[c - 1][r];

                    //Version tableau de vecteur candidat
                    TSol[c][r] = TSol[c-1][r].copy();
                }
            }
        }
        solutionDP = TSol[nbPiece][montant].copy();
    }



    @Override
    public Solution solveProblem(int montant) {
        solutionDP = new Solution(C, false); //Initialisation du vecteur solution
        initialisationTableaux(montant); //Initialisation des structures tabulaires
        solve(null, montant); //Résolution selon l'algorithme de programmation dynamique
        if(displayResult) { //Booléen pour permettre l'affichage des résultats
            for (int i = 0; i < T.length; i++) {
                for (int j = 0; j < T[0].length; j++) {
                    System.out.print("|" + (int) T[i][j]);
                }
                System.out.print("\n");
            }
        }
        return solutionDP.copy();
    }

    public static void main(String[] args) {
        Pieces euros = new SpecialC();
        euros.init();
        DynamicProgramming test = new DynamicProgramming(euros);
        Solution solution = test.solveProblem(8);
        solution.afficheResultat();
    }
}
