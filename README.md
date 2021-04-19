# README

### Comment utiliser le projet ?
Le projet ne possède pas un programme principal particulier.

Il existe plusieurs algorithmes de résolutions du problème de rendu de monnaie avec leur classe.
Leur utilisation est détaillée plus bas.

## Vérification du fonctionnement des algorithmes
Chacun des 3 algorithmes possèdent leurs propres classes.
Pour vérifier le bon fonctionnement de ces algorithmes, il faut aller dans
une des classes suivantes du package **Algorithms/** :
```
* TrialAndError
* DynamicProgramming
* Greedy
```

Puis exécutez le **main** ou **programme principale**.

## Utilisation d'un algorithme de résolution du rendu de monnaie
#### Etape 1 : Déclarer un système numéraire
Exemple :
```
Pieces euro = new Euros();
```
On peut créer ou choisir un système numéraire parmi les classes qui héritent de **Pieces** :
```
* Euros
* Dollars
* SpecialC
* SpecialCPrime
```
#### Etape 2 : Initialisation de l'ordre des pièces 
En reprenant l'exemple précédent :
```
euro.init();
```

On peut choisir parmi plusieurs méthodes d'initialisation :
```
* init() //Méthode initialisant la liste des pièces dans un ordre quelconque
* initCroissant() //Méthode initialisant la liste des pièces dans un ordre croissant
* initDecroissant() //Méthode initialisant la liste des pièces dans un ordre décroissant
```
#### Etape 3 : Choix d'un algorithme de résolution
En reprenant l'exemple précédent, cela revient à écrire la ligne :

```
Greedy greedy = new Greedy(euro);
```
ou
```
DynamicProgramming dp = new DynamicProgramming(euro);
```
ou
```
TrialAndError trialAndError = new TrialAndError(euro);
```


L'instanciation de l'algorithme demande comme argument le système numéraire choisi.

On peut donc choisir parmi les 3 algorithmes de résolution des classes suivantes :
```
* TrialAndError
* DynamicProgramming
* Greedy
```

#### Etape 4 : Execution d'un algorithme de résolution
En reprenant l'exemple précédent, on applique la méthode **solveProblem(montant)**, par exemple **montant = 8**:
```
Solution solution = greedy.solveProblem(8);
```
ou
```
Solution solution = dp.solveProblem(8);
```
ou
```
Solution solution = trialAndError.solveProblem(8);
```

La solution est affichée sur le terminal et elle est récupérée dans l'objet **solution**.


## Partie Tests
Pour vérifier les données des tests,
dirigez-vous dans le **package** **Test/**
et sélectionner la classe désirée parmi :

```
* TrialAndErrorTest
* DynamicProgrammingTest
* GreedyTest
```

Et sélectionnez les méthodes désirées pour les exécuter dans un **main**.
Le résultat des tests est disponible après dans le dossier **test/** en racine de projet.