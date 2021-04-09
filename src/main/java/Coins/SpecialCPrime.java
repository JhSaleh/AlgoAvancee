package Coins;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class SpecialCPrime extends Pieces{
        public static int p50 = 50;
        public static int p30 = 30;
        public static int p10 = 10;
        public static int p5 = 5;
        public static int p3 = 3;
        public static int p1 = 1;

        public SpecialCPrime(){
            init();
        }

        @Override
        public void init() {
            ArrayList<Integer> set = new ArrayList<>(Arrays.asList(p1, p3, p5, p10, p30, p50)); //Ensemble des pièces encore non pris
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
