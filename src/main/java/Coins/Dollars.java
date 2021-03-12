package Coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Dollars extends Pieces {
    public static int unDollar = 100;
    public static int cinquanteCents = 50;
    public static int vingtcinqCents = 25;
    public static int dixCents = 10;
    public static int cinqCents = 5;
    public static int unCent = 1;


    @Override
    public void initDecroissant() {
        //Initialisation ensemble de pièces
        ensemblePieces = new ArrayList<>(); //Vide la collection
        ensemblePieces.add(unDollar); //1 dollar
        ensemblePieces.add(cinquanteCents); //50 cents
        ensemblePieces.add(vingtcinqCents); //20 cents
        ensemblePieces.add(dixCents); //10 cents
        ensemblePieces.add(cinqCents); //5 cents
        ensemblePieces.add(unCent); //1 cent or penny
    }

    @Override
    public void initCroissant() {
        ensemblePieces = new ArrayList<>(); //Vide la collection
        ensemblePieces.add(unCent); //1 cent or penny
        ensemblePieces.add(cinqCents); //5 cents
        ensemblePieces.add(dixCents); //10 cents
        ensemblePieces.add(vingtcinqCents); //20 cents
        ensemblePieces.add(cinquanteCents); //50 cents
        ensemblePieces.add(unDollar); //1 dollar
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
