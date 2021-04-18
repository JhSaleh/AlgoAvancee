package Algorithms;

import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

public class TrialAndError extends MoneyChangeProblem{
    /**
     * Vecteur de la solution optimale
     */
    public static Solution solOpt;
    /**
     * Entier indiquant le nombre de tour réalisé par l'algorithme
     */
    public int nbTour;
    /**
     * Booléen indiquant si première solution optimale local a été trouvé
     */
    public static boolean solOptFound;

    /**
     * Boolèen gérant toutes les conditions d'élagages
     */
    public static boolean avecConditionElagage = true;

    public TrialAndError(Pieces inC){
        this.C = inC;
    }

    /**
     * Methode qui retiendra seulement les vecteurs optimaux
     * But : minimisé le nombre de pièce utilisé pour atteindre la solution
     */
    public boolean meilleur(Solution solutionActuelle){
            if (solutionActuelle.getNbPieces() < solOpt.getNbPieces()) {
                solOpt = solutionActuelle.copy(); //Mise à jour de la valeur optimal
                //System.out.println("-----------------------------------Sol Opt :");
                //solOpt.afficheResultat();
                return true;
            }
            return false;
    }

    /**
     * Condition d'élagage qui examine les branches futurs
     * Si la plus petite pièce ajoutée au vecteur de solution actuelle aboutit est supérieur au montant demandé, l'exploration de branche supplémentaire est inutile
     * @param solutionActuelle
     * @param montant
     * @return
     */
    public boolean encorePossible(Solution solutionActuelle, double montant, int from){
        if(solutionActuelle.getMontant() + solutionActuelle.C.getMin(from) > montant){
            return false;
        }
        return true;
    }

    /**
     * Seconde condition d'élagage qui va évaluer l'intérêt des branches suivantes.
     * Si les branches qui suit nécessite plus de pièces que la solution optimale, ce n'est pas la peine de l'examiner
     * @param solutionActuelle
     * @return
     */
    public boolean evaluation(Solution solutionActuelle){
        if(solutionActuelle.getNbPieces() > solOpt.getNbPieces()){
            return false;
        }
        return true;
    }

    /**
     * Troisième condition d'élagage qui va évaluer l'intérêt des branches suivantes.
     * Si les branches qui suivent ne peuvent atteindre le montant avec le nombre de pieces restantes en adequation avec la solution optimale
     * pour la plus grande valeur de l'ensemble de pièce, la branche suivante n'est pas à explorer
     * @param solutionActuelle
     * @return
     */
    public boolean evaluationCheminSolutionOptimale(Solution solutionActuelle, int montant, int from){
        int nbPiecesRestantes = (solOpt.getNbPieces() - solutionActuelle.getNbPieces());
        int valeurPieceMax = solutionActuelle.C.getMax(from);

        if(nbPiecesRestantes < 0 || (solutionActuelle.getMontant() + nbPiecesRestantes*valeurPieceMax < montant && solOptFound)){ //On ne regarde ce critère que si la solution optimale existe déja
            return false;
        }
        return true;
    }

    /**
     * Quatrième condition d'élagage
     * Condition d'élagage basé sur la valeur de la majoration du nombre de pieces optimales, qui va regardé si un chemin est
     * atteignable pour le nombre restant de pieces. Cela permet d'éliminer une grande partie des branches au tout début du programme
     * @param solutionActuelle
     * @param montant
     * @return
     */
    public boolean evaluationCheminEstimationSolutionOptimale(Solution solutionActuelle, int montant, int from){
        int valeurPieceMax = this.C.getMax(from);
        int majorationNbPieceOptimale = approximationNbPieceGreedy(montant); //Choix du majorant
        int nbPiecesRestantes = majorationNbPieceOptimale-solutionActuelle.getNbPieces();
        if(nbPiecesRestantes < 0 || solutionActuelle.getMontant() + nbPiecesRestantes*valeurPieceMax < montant){
            return false;
        }
        return true;
    }

    /**
     * Donne une majoration / approximation du véritable résultat de la solution optimale via la méthode gloutonne
     * @param montant
     * @return
     */
    public int approximationNbPieceGreedy(int montant){
        int nbPieceApproximationGreedy = 0;
        Pieces duplique = this.C.copy();
        duplique.initDecroissant();
        int i = 0;
        int valeurPiece = 0;
        while (montant != 0 && duplique.ensemblePieces.size() > i){
            valeurPiece = duplique.ensemblePieces.get(i);
            if(montant >= valeurPiece) {
                nbPieceApproximationGreedy += montant / valeurPiece;
                montant = montant % valeurPiece;
            }
            i++;
        }
        return nbPieceApproximationGreedy;
    }


    /**
     *
     * @param montantActuel montant du vecteur candidat
     * @param ajout valeur de la pièce examinée
     * @param montant montant visée
     * @return
     */
    public boolean satisfaisant(double montantActuel, double ajout, double montant){
        if(montantActuel + ajout <= montant){
            return true;
        }
        return false;
    }




    /**
     * La dernière solution affichée est optimale.
     */
    public void solve(Solution solution, int montant, int from) {
        nbTour++; //compte le nombre d'appels récursifs
        Solution solutionActuel = new Solution();
        //Traitement du vecteur de solution
        if(solution == null){ //Initialisation au vecteur nul s'il s'agit du 1er appel
            solutionActuel = new Solution(this.C);
        } else { //Reprise de la solution précèdente.
            solutionActuel = solution.copy();
        }
        //Initialisation de variable
        double montantActuel = solutionActuel.getMontant();

        for(int i = from; i < getTailleEnsemblePiece(); i++){ //on va travailler avec des tableaux de plus en plus petit pour éviter les transpositions, pour
            double ajout = this.C.ensemblePieces.get(i);
            if(satisfaisant(montantActuel, ajout, montant) && evaluation(solutionActuel)){ //satisfaisant
                //Enregistrer
                montantActuel += ajout;
                solutionActuel.X[i] += 1;


                if(montantActuel == montant){ //soltrouvé
                    if(meilleur(solutionActuel)){
                        solOptFound = true; //Une première solution optimale local a été trouvé
                        if(displayResult) {
                            solutionActuel.afficheResultat();
                        }
                    }

                } else if(encorePossible(solutionActuel, montant, from)) { //condition d'élagage
                    //solutionActuel.afficheResultat();
                    if(evaluationCheminSolutionOptimale(solutionActuel, montant, from) && evaluationCheminEstimationSolutionOptimale(solutionActuel, montant, from)){
                        solve(solutionActuel, montant, i);
                    }
                }
                //Défaire
                montantActuel -= ajout;
                solutionActuel.X[i] -= 1;
            }
        }
    }

    public Solution solveProblem(int montant){
        //Initialisation de la valeur optimale
        nbTour = 0;
        solOptFound = false;
        solOpt = new Solution(this.C, true);
        if(displayResult) {
            solOpt.afficheResultat();
        }
        //Lancement de la résolution
        solve(null, montant, 0);
        if(displayResult) {
            this.C.afficheValeur();
            System.out.println("\n\n-----------------");
            System.out.print("La solution optimale est : ");
            solOpt.afficheResultat();
            System.out.println("-----------------");
        }
        solOpt.nbTour = nbTour;
        return solOpt.copy();
    }


    public static void main(String[] args) {
        Pieces euro = new Euros();
        //euro.initCroissant();
        euro.afficheValeur();
        TrialAndError test = new TrialAndError(euro);
        test.solveProblem(157453);
        euro.afficheValeur();
        System.out.println("nb de tour :"+test.nbTour);
    }
}