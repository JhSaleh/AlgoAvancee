package Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Test {
    public static String cheminResource = "src/main/resources/";
    public static String cheminTest = "test/";


    public String fileName;
    public BufferedWriter writer;

    /**
     * Créer l'entete du fichier csv
     */
    public void enteteTour(){
        try{
            this.writer.write("montant, tour\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Créer l'entete du fichier csv
     */
    public void enteteTemps(){
        try{
            this.writer.write("montant, temps(ns)\n");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Ecrire une ligne dans le fichier csv
     * @param montant
     * @param tour
     */
    public void writeLine(int montant, int tour) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(montant);
            builder.append(",");
            builder.append(tour);
            builder.append("\n");
            this.writer.write(builder.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Ecrire une ligne dans le fichier csv
     * @param montant
     * @param tempsNanoSeconde
     */
    public void writeLine(int montant, long tempsNanoSeconde) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(montant);
            builder.append(",");
            builder.append(tempsNanoSeconde);
            builder.append("\n");
            this.writer.write(builder.toString());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Ferme le fichier apres écriture
     */
    public void stopWriting(){
        try {
            this.writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
