package Algorithms;

import Coins.Pieces;
import Vectors.Solution;

public abstract class MoneyChangeProblem {
    public Pieces C; //Ensemble C de pieces représenté par un tableau, dont l'index reprèsente ci et l'accès donne la valeur di associée
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
