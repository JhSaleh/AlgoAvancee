package Coins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SpecialC extends Pieces{
    public static int cUn = 6;
    public static int cDeux = 4;
    public static int cTrois = 1;

    @Override
    public void init() {
        ArrayList<Integer> set = new ArrayList<>(Arrays.asList(cUn, cDeux, cTrois)); //Ensemble des pièces encore non pris
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

    @Override
    public void initCroissant() {
        ensemblePieces = new ArrayList<>(); //Vide la collection
        ensemblePieces.add(cTrois);
        ensemblePieces.add(cDeux);
        ensemblePieces.add(cUn);
    }

    @Override
    public void initDecroissant() {
        ensemblePieces = new ArrayList<>(); //Vide la collection
        ensemblePieces.add(cUn);
        ensemblePieces.add(cDeux);
        ensemblePieces.add(cTrois);
    }
}
