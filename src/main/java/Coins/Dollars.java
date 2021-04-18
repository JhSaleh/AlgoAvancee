package Coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dollars extends Pieces {
    /**
     * Valeur du système numéraire
     */
    public static int unDollar = 100;
    public static int cinquanteCents = 50;
    public static int vingtcinqCents = 25;
    public static int dixCents = 10;
    public static int cinqCents = 5;
    public static int unCent = 1;

    /**
     * Constructeur qui remplit l'ensemble de pièce de façon quelconque
     */
    public Dollars(){
        init();
    }

    @Override
    public void init() {
        ArrayList<Integer> set = new ArrayList<>(Arrays.asList(unCent, cinqCents, dixCents, vingtcinqCents, cinquanteCents, unDollar)); //Ensemble des pièces encore non pris
        this.ensemblePieces = new ArrayList<>();

        Random generator = new Random();
        int pieceChoisit;
        int tailleSet = set.size();
        while (tailleSet != 0){
            pieceChoisit = generator.nextInt(tailleSet); //Choix de la prochaine pièce
            ensemblePieces.add(set.get(pieceChoisit)); //Ajout à la liste des pièces
            set.remove(pieceChoisit); //Retire la pièce des possibles à choisir
            tailleSet = set.size(); //Mise à jour du set
        }

    }
}
