# README

### Comment utiliser le programme ?
Le projet ne possède pas un programme principal particulier.

Il existe plusieurs algorithmes de résolutions du problème de rendue de monnaie avec leur classe.
Leur utilisation est détaillé plus bas.

## Vérification du fonctionnement des algorithmes
Chacun des 3 algorithmes possèdent leurs propres classes.
Pour vérifier le bon fonctionnement de ces algorithmes, allez dans
une des classes suivantes :
```
* TrialAndError
* DynamicProgramming
* Greedy
```

Puis exécuter le **main** ou **programme principale**.

## Utilisation d'un algorithme de résolution du rendu de monnaie
#### Etape 1 : Déclarer un système numéraire
Exemple :
```
Pieces euro = new Euros();
```
On peut créer ou choisir un sytème numéraire parmi les classes qui héritent de **Pieces** :
```
* Euros
* Dollars
* SpecialC
* SpecialCPrime
```
#### Etape 2 : Initialisation de l'ordre des pièces 
En reprenant l'exemple précèdent :
```
euro.init();
```

On peut choisir parmi plusieurs méthodes d'initialisation :
```
* init()
* initCroissant()
* initDecroissant()
```
#### Etape 3 : Choix d'un algorithme de résolution
En reprenant l'exemple précèdent :

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


L'instanciation de l'algorithme demande comme argument le système numéraire choisit.

On peut donc chosir parmi les 3 algorithmes de résolution suivant :
```
* TrialAndError
* DynamicProgramming
* Greedy
```

#### Etape 4 : Execution d'un algorithme de résolution
En reprenant l'exemple précèdent :
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

La solution est affichée sur le terminal et est recupérée dans l'objet **solution**.


## Partie Tests
Pour vérifier les données des tests,
dirigez-vous dans le dossier Test/
et sélectionner la classe désirée parmi :

```
* TrialAndErrorTest
* DynamicProgrammingTest
* GreedyTest
```

Et sélectionnez les méthodes désirées.