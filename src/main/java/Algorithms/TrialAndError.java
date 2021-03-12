package Algorithms;

import Coins.Euros;
import Coins.Pieces;
import Vectors.Solution;

public class TrialAndError extends MoneyChangeProblem{

    public static Solution solOpt;

    public TrialAndError(Pieces inC){
        this.C = inC;
    }

    /**
     * soltrouvé : montant actuel = montant
     * satisfaisant : montant actuel <= montant
     * enregistré : X[i] += 1
     * défaire: X[i] -= 1
     * condition d'élagage : nb de piece actuelle supérieur à celle d'avant et le montant est inférieur
     *
     * but : minimisé le nombre de pièce utilisé pour atteindre la solution
     */

    public boolean meilleur(Solution solutionActuelle){
            if (solutionActuelle.getNbPieces() < solOpt.getNbPieces()) {
                solOpt = solutionActuelle.copy(); //Mise à jour de la valeur optimal
                return true;
            }
            return false;
    }

    /**
     * Condition d'élagage qui examine les branches futurs
     * Si la plus petite pièce ajouté au vecteur de solution actuelle aboutit est supérieur au montant demandé, l'exploration de branche supplémentaire est inutile
     * @param solutionActuelle
     * @param montant
     * @return
     */
    public boolean encorePossible(Solution solutionActuelle, double montant){
        if(solutionActuelle.getMontant() + solutionActuelle.C.getMin() > montant){
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
     *
     * @param montantActuel
     * @param ajout
     * @param montant
     * @return
     */
    public boolean satisfaisant(double montantActuel, double ajout, double montant){
        if(montantActuel + ajout <= montant){
            return true;
        }
        return false;
    }

    @Override
    /**
     * La dernière solution affichée est optimale.
     */
    public void solve(Solution solution, int montant) { //Nombre maximale d'appels récursifs : ??
        Solution solutionActuel = new Solution();
        //Traitement du vecteur de solution
        if(solution == null){ //Initialisation au vecteur nul s'il s'agit du 1er appel
            solutionActuel = new Solution(this.C);
        } else { //Reprise de la solution précèdente.
            solutionActuel = solution.copy();
        }
        //Initialisation de variable
        double montantActuel = solutionActuel.getMontant();

        for(int i = 0; i < getTailleEnsemblePiece(); i++){
            double ajout = this.C.ensemblePieces.get(i);
            if(satisfaisant(montantActuel, ajout, montant) && evaluation(solutionActuel)){ //satisfaisant
                //Enregistrer
                montantActuel += ajout;
                solutionActuel.X[i] += 1;


                if(montantActuel == montant){ //soltrouvé
                    if(meilleur(solutionActuel)){
                        solutionActuel.afficheResultat();
                    }

                } else if(encorePossible(solutionActuel, montant)) { //condition d'élagage
                    solve(solutionActuel, montant);
                }
                //Défaire
                montantActuel -= ajout;
                solutionActuel.X[i] -= 1;
            }
        }
    }

    public Solution solveProblem(int montant){
        //Initialisation de la valeur optimale
        solOpt = new Solution(this.C, true);
        solOpt.afficheResultat();
        //Lancement de la résolution
        solve(null, montant);
        this.C.afficheValeur();
        System.out.println("\n\n-----------------");
        System.out.print("La solution optimale est : ");
        solOpt.afficheResultat();
        System.out.println("-----------------");
        return solOpt.copy();
    }


    public static void main(String[] args) {
        Pieces euro = new Euros();
        euro.init();
        euro.afficheValeur();
        TrialAndError test = new TrialAndError(euro);
        test.solveProblem(500);
        euro.afficheValeur();
    }
}
