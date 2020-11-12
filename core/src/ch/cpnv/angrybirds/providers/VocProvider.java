package ch.cpnv.angrybirds.providers;

import java.util.ArrayList;

import ch.cpnv.angrybirds.AngryWirds;
import ch.cpnv.angrybirds.model.data.Language;
import ch.cpnv.angrybirds.model.data.Vocabulary;
import ch.cpnv.angrybirds.model.data.SemanticWord;

public class VocProvider {
    public ArrayList<Vocabulary> vocabularies;
    private ArrayList<Language> languages;

    // singleton
    private static VocProvider single_instance = null;

    public static VocProvider getInstance() {
        if (single_instance == null) {
            single_instance = new VocProvider(false);
        }
        return single_instance;
    }

    public Vocabulary pickAVoc() {
        return vocabularies.get(AngryWirds.alea.nextInt(vocabularies.size()));
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    private VocProvider(boolean smallVoc) {
        this();
        if(smallVoc) {
            vocabularies = new ArrayList<Vocabulary>();
            SemanticWord w;
            Vocabulary voc = new Vocabulary("L'argent");
            w = new SemanticWord("la banque","the bank"); voc.addWord(w);
            w = new SemanticWord("l''argent liquide","cash"); voc.addWord(w);
            vocabularies.add(voc);
        }
    }

    private VocProvider() {
        vocabularies = new ArrayList<Vocabulary>();
        SemanticWord w;
        Vocabulary voc = new Vocabulary("L'argent");
        w = new SemanticWord("la banque","the bank"); voc.addWord(w);
        w = new SemanticWord("l''argent liquide","cash"); voc.addWord(w);
        w = new SemanticWord("le paiement","payment"); voc.addWord(w);
        w = new SemanticWord("un carnet de chèques","checkbook"); voc.addWord(w);
        w = new SemanticWord("déposer de l''argent","to deposit money"); voc.addWord(w);
        w = new SemanticWord("prêter de l''argent","to borrow money"); voc.addWord(w);
        w = new SemanticWord("les services financiers","financial services"); voc.addWord(w);
        w = new SemanticWord("la commission","commission"); voc.addWord(w);
        w = new SemanticWord("un investissement","an investment"); voc.addWord(w);
        w = new SemanticWord("un virement","transfer"); voc.addWord(w);
        w = new SemanticWord("la gestion de l''argent","money management"); voc.addWord(w);
        w = new SemanticWord("une transaction","a transaction"); voc.addWord(w);
        w = new SemanticWord("un guichet automatique","ATM machine"); voc.addWord(w);
        w = new SemanticWord("attendre dans la queue","to wait in line"); voc.addWord(w);
        w = new SemanticWord("une carte bancaire","banking/ATM card"); voc.addWord(w);
        w = new SemanticWord("faire des économies","to save money"); voc.addWord(w);
        w = new SemanticWord("le montant","sum/total amount"); voc.addWord(w);
        w = new SemanticWord("un compte-chèques","checking account"); voc.addWord(w);
        w = new SemanticWord("l''argent","money"); voc.addWord(w);
        w = new SemanticWord("les fonds","funds"); voc.addWord(w);
        w = new SemanticWord("un chèque","check"); voc.addWord(w);
        w = new SemanticWord("un dépôt","deposit"); voc.addWord(w);
        w = new SemanticWord("le crédit","credit"); voc.addWord(w);
        w = new SemanticWord("les marchés financiers","financial markets"); voc.addWord(w);
        w = new SemanticWord("la clientèle","clientele"); voc.addWord(w);
        w = new SemanticWord("les frais","fees"); voc.addWord(w);
        w = new SemanticWord("une institution financière","financial institution"); voc.addWord(w);
        w = new SemanticWord("le taux d''intérêt","interest rate"); voc.addWord(w);
        w = new SemanticWord("la monnaie","currency"); voc.addWord(w);
        w = new SemanticWord("le bilan","balance"); voc.addWord(w);
        w = new SemanticWord("prendre un numéro","to take a number"); voc.addWord(w);
        w = new SemanticWord("une carte de crédit","credit card"); voc.addWord(w);
        w = new SemanticWord("un emprunt","a loan"); voc.addWord(w);
        w = new SemanticWord("retirer de l''argent","to take out money"); voc.addWord(w);
        w = new SemanticWord("un compte d''épargne","savings account"); voc.addWord(w);
        w = new SemanticWord("un distributeur automatique","ATM machine"); voc.addWord(w);
        w = new SemanticWord("un reçu","receipt"); voc.addWord(w);
        vocabularies.add(voc);

        voc = new Vocabulary("Les meubles");
        w = new SemanticWord("une table","a table"); voc.addWord(w);
        w = new SemanticWord("une chaise","a chair"); voc.addWord(w);
        w = new SemanticWord("une bibliothèque","a bookcase"); voc.addWord(w);
        w = new SemanticWord("une table basse","a coffee table"); voc.addWord(w);
        w = new SemanticWord("une cheminée","a fireplace"); voc.addWord(w);
        w = new SemanticWord("une table de chevet","a bedside table"); voc.addWord(w);
        w = new SemanticWord("une étagère","a shelf"); voc.addWord(w);
        w = new SemanticWord("une armoire","a wardrobe"); voc.addWord(w);
        w = new SemanticWord("une commode","a dresser"); voc.addWord(w);
        w = new SemanticWord("une moquette","a carpet"); voc.addWord(w);
        w = new SemanticWord("un siège","a seat"); voc.addWord(w);
        w = new SemanticWord("un tabouret","a stool"); voc.addWord(w);
        w = new SemanticWord("un placard","a cupboard"); voc.addWord(w);
        w = new SemanticWord("un tiroir","a drawer"); voc.addWord(w);
        w = new SemanticWord("un fauteuil","a armchair"); voc.addWord(w);
        w = new SemanticWord("un lit","a bed"); voc.addWord(w);
        w = new SemanticWord("un bureau","a desk"); voc.addWord(w);
        w = new SemanticWord("un miroir","a mrror"); voc.addWord(w);
        w = new SemanticWord("un meuble","a piece of furniture"); voc.addWord(w);
        w = new SemanticWord("un oreiller","a pillow"); voc.addWord(w);
        vocabularies.add(voc);

        voc = new Vocabulary("Les couleurs");
        w = new SemanticWord("blanc","white"); voc.addWord(w);
        w = new SemanticWord("bleu clair","light blue"); voc.addWord(w);
        w = new SemanticWord("multicolore","muli-colored"); voc.addWord(w);
        w = new SemanticWord("gris","grey"); voc.addWord(w);
        w = new SemanticWord("vert","green"); voc.addWord(w);
        w = new SemanticWord("noir","black"); voc.addWord(w);
        w = new SemanticWord("argenté","silver"); voc.addWord(w);
        w = new SemanticWord("jaune","yellow"); voc.addWord(w);
        w = new SemanticWord("orange","orange"); voc.addWord(w);
        w = new SemanticWord("bleu","blue"); voc.addWord(w);
        w = new SemanticWord("bleu foncé","dark blue"); voc.addWord(w);
        w = new SemanticWord("rose","pink"); voc.addWord(w);
        w = new SemanticWord("rouge","red"); voc.addWord(w);
        w = new SemanticWord("marron","brown"); voc.addWord(w);
        w = new SemanticWord("violet","purple"); voc.addWord(w);
        w = new SemanticWord("beige","beige"); voc.addWord(w);
        w = new SemanticWord("turquoise","turquoise"); voc.addWord(w);
        w = new SemanticWord("doré","golden"); voc.addWord(w);
        vocabularies.add(voc);

        voc = new Vocabulary("La famille");
        w = new SemanticWord("le père","the father"); voc.addWord(w);
        w = new SemanticWord("les parents","the parents"); voc.addWord(w);
        w = new SemanticWord("la sœur","the sister"); voc.addWord(w);
        w = new SemanticWord("la fille","the daughter"); voc.addWord(w);
        w = new SemanticWord("le bébé","the baby"); voc.addWord(w);
        w = new SemanticWord("la femme","the wife"); voc.addWord(w);
        w = new SemanticWord("la fiancée","the fiancée"); voc.addWord(w);
        w = new SemanticWord("la grand-mère","the grandmother"); voc.addWord(w);
        w = new SemanticWord("les petits-enfants","the grandchildren"); voc.addWord(w);
        w = new SemanticWord("la tante","the aunt"); voc.addWord(w);
        w = new SemanticWord("la nièce","the niece"); voc.addWord(w);
        w = new SemanticWord("la belle-mère","the step mother"); voc.addWord(w);
        w = new SemanticWord("la cousine","the cousin (female)"); voc.addWord(w);
        w = new SemanticWord("la belle-sœur","sister in-law"); voc.addWord(w);
        w = new SemanticWord("la mère","the mother"); voc.addWord(w);
        w = new SemanticWord("le frère","the brother"); voc.addWord(w);
        w = new SemanticWord("le fils","the son"); voc.addWord(w);
        w = new SemanticWord("l’enfant","the child"); voc.addWord(w);
        w = new SemanticWord("le mari","the husband"); voc.addWord(w);
        w = new SemanticWord("le fiancé","the fiancé"); voc.addWord(w);
        w = new SemanticWord("le grand-père","the grandfather"); voc.addWord(w);
        w = new SemanticWord("les grand-parents","the grandparents"); voc.addWord(w);
        w = new SemanticWord("l’oncle","the uncle"); voc.addWord(w);
        w = new SemanticWord("le neveu","the nephew"); voc.addWord(w);
        w = new SemanticWord("le beau-père","the step father"); voc.addWord(w);
        w = new SemanticWord("le cousin","the cousin (male)"); voc.addWord(w);
        w = new SemanticWord("le beau-frère","brother in-law"); voc.addWord(w);
        w = new SemanticWord("le beau-père","father in-law"); voc.addWord(w);
        vocabularies.add(voc);
    }
}
