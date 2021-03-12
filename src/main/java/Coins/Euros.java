package Coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Euros extends Pieces{
    public static int deuxEuros = 200;
    public static int unEuro = 100;
    public static int cinquanteCentimes = 50;
    public static int vingtCentimes = 20;
    public static int dixCentimes = 10;
    public static int cinqCentimes = 5;
    public static int deuxCentimes = 2;
    public static int unCentime = 1;


    @Override
    public void initDecroissant() {
        //Initialisation ensemble de pièces
        ensemblePieces = new ArrayList<>(); //Vide la collection
        ensemblePieces.add(deuxEuros); //2 euros
        ensemblePieces.add(unEuro); //1 euro
        ensemblePieces.add(cinquanteCentimes); //50 centimes
        ensemblePieces.add(vingtCentimes); //20 centimes
        ensemblePieces.add(dixCentimes); //10 centimes
        ensemblePieces.add(cinqCentimes); //5 centimes
        ensemblePieces.add(deuxCentimes); //2 centimes
        ensemblePieces.add(unCentime); //2 centimes
    }

    @Override
    public void initCroissant() {
        ensemblePieces = new ArrayList<>();
        ensemblePieces.add(unCentime); //2 centimes
        ensemblePieces.add(deuxCentimes); //2 centimes
        ensemblePieces.add(cinqCentimes); //5 centimes
        ensemblePieces.add(dixCentimes); //10 centimes
        ensemblePieces.add(vingtCentimes); //20 centimes
        ensemblePieces.add(cinquanteCentimes); //50 centimes
        ensemblePieces.add(unEuro); //1 euro
        ensemblePieces.add(deuxEuros); //2 euro
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
