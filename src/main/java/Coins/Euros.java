package Coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Euros extends Pieces{
    /**
     * Valeur du système numéraire
     */
    public static int deuxEuros = 200;
    public static int unEuro = 100;
    public static int cinquanteCentimes = 50;
    public static int vingtCentimes = 20;
    public static int dixCentimes = 10;
    public static int cinqCentimes = 5;
    public static int deuxCentimes = 2;
    public static int unCentime = 1;

    /**
     * Constructeur qui remplit l'ensemble de pièce de façon quelconque
     */
    public Euros(){
        init();
    }

    @Override
    public void init(){
        ArrayList<Integer> set = new ArrayList<>(Arrays.asList(unCentime, deuxCentimes, cinqCentimes, dixCentimes, vingtCentimes, cinquanteCentimes, unEuro, deuxEuros)); //Ensemble des pièces encore non pris
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
