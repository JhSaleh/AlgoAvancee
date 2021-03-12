package Vectors;

import Coins.Euros;
import Coins.Pieces;

public class Solution {
    public Pieces C; //Ensemble de pièces
    public int[] X; //Vecteur de solution



    /**
     * Simple constructeur vide, cas null
     */
    public Solution(){}


    /**
     * Version pour les essaies successifs optimaux
     * @param inC
     * @param state
     */
    public Solution(Pieces inC, Boolean state){
        this.C = inC;

        int tailleVecteur = this.C.ensemblePieces.size();
        X = new int[tailleVecteur];

        if(true){
            for(int i = 0; i < X.length; i++){
                X[i] = Integer.MAX_VALUE;
            }
        }
    }

    public Solution(Pieces inC, int[] X){
        this.C = inC;
        this.X = X;
    }


    /**
     * Initialise un vecteur solution en tant que vecteur nul
     * @param inC
     */
    public Solution(Pieces inC){
        this.C = inC;

        //Initialisation du vecteur solution
        int tailleVecteur = this.C.ensemblePieces.size();
        X = new int[tailleVecteur];
    }

    /**
     * Constructeur qui réalise la copie profonde d'un autre vecteur de solution
     * @param solution
     */
    public Solution(Solution solution){
        this.C = solution.C;
        int tailleVecteur = solution.X.length;
        //Copie hard du tableau de solution
        this.X = new int[tailleVecteur];
        for(int i = 0; i < solution.X.length; i++){
             this.X[i] = solution.X[i];
        }
    }

    /**
     * Renvoit le montant correspondant au vecteur solution
     * @return
     */
    public int getMontant(){
        int montantActuel = 0;
        for(int i = 0; i<X.length; i++){
            double coinValue = this.C.ensemblePieces.get(i);
            montantActuel += this.C.ensemblePieces.get(i)*X[i];
       }
        return montantActuel;
    }

    /**
     * Retourne le nombre de pièce utilisé dans le vecteur solution
     * @return
     */
    public int getNbPieces(){
        if(X[0] != Integer.MAX_VALUE) { //Cas normal
            int total = 0;
            for (int i = 0; i < X.length; i++) {
                total += X[i];
            }
            return total;
        } else { //Cas où il s'agit du nombre maximale de pieces entieres en java
            return Integer.MAX_VALUE;
        }
    }

    /**
     * Realise une copie profonde du meme vecteur de solution
     * @return
     */
    public Solution copy(){
        return new Solution(this);
    }

    /**
     * Affiche le vecteur de résultat
     */
    public void afficheResultat(){
        //this.C.afficheValeur();
        System.out.print("Vecteur solution : <");
        for(int i = 0; i < X.length; i++){
            if(i != X.length-1) {
                System.out.print(X[i] + ", ");
            } else {
                System.out.print(X[i]);
            }
        }
        System.out.print("> || Montant obtenu:"+getMontant()+" || Nb de pièces :"+getNbPieces()+"\n");
    }

    public static void main(String[] args) {
        Pieces euro = new Euros();
        euro.initDecroissant();

        Solution solution = new Solution(euro, new int[] {102, 2, 2, 2, 2, 2, 1});
        solution.getMontant();
    }
}
