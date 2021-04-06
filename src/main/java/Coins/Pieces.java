package Coins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class Pieces {
    /**
     * Ensemble des pièces
     */
    public ArrayList<Integer> ensemblePieces;


    /**
     * Méthode initialisant la liste des pièces dans un ordre quelconque
     */
    public abstract void init();

    /**
     * Méthode initialisant la liste des pièces dans un ordre croissant
     */
    public abstract void initCroissant();

    /**
     * Méthode initialisant la liste des pièces dans un ordre décroissant
     */
    public abstract void initDecroissant();

    /**
     * Affiche l'ensemble des pieces fournit
     */
    public void afficheValeur(){
        System.out.print("Ensemble de pièces : ");
        for(int i = 0; i < ensemblePieces.size(); i++){
            System.out.print(ensemblePieces.get(i) + " ");
        }
        System.out.print("\n");
    }

    /**
     * Renvoit la plus petite valeur de l'ensemble des pièces
     */
    public double getMin(){
        return Collections.min(ensemblePieces);
    }

    /**
     * Renvoit la plus grande valeur de l'ensemble des pièces
     */
    public int getMax(){
        return Collections.max(ensemblePieces);
    }

    /**
     * Retour la plus grande valeur de l'ensemble entre l'indice from et la limite du tableau
     * @param from
     * @return
     */
    public int getMax(int from){
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int i = from; i<this.ensemblePieces.size(); i++){
            arrayList.add(this.ensemblePieces.get(i));
        }
        return Collections.max(arrayList);
    }

    /**
     * Copie le type de piece
     * @return
     */
    public Pieces copy(){
        if (this instanceof Euros){
            return new Euros();
        } else if( this instanceof Dollars){
            return new Dollars();
        } else if (this instanceof SpecialC){
            return new SpecialC();
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Pieces euro = new Euros();
        euro.init();
        euro.afficheValeur();
        System.out.println(euro.getMax(5));
    }
}
