package Algorithms;

import Coins.Pieces;
import Vectors.Solution;

public abstract class MoneyChangeProblem {
    /**
     * Ensemble C de pieces représenté par un tableau, dont l'indice reprèsente ci et l'accès donne la valeur di associée
     */
    public Pieces C;

    /**
     * Booléen permettant l'affichage des résultats de solution des algorithmes
     */
    public static boolean displayResult = true;

    /**
     * Méthode résolvant le probleme
     * La dernière solution affichée est celle optimale
     */
    public abstract Solution solveProblem(int montant);

    /**
     * Retourne la taille de l'ensemble de pieces actuellement utilises
     * @return
     */
    public int getTailleEnsemblePiece(){
        return C.ensemblePieces.size();
    }
}
