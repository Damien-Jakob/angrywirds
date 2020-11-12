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
            single_instance = new VocProvider();
        }
        return single_instance;
    }

    public Vocabulary pickAVoc() {
        return vocabularies.get(AngryWirds.alea.nextInt(vocabularies.size()));
    }

    public ArrayList<Language> getLanguages() {
        return languages;
    }

    private VocProvider() {
        vocabularies = new ArrayList<>();
        languages = new ArrayList<>();
        Language fr = new Language("fr", "Français");
        Language en = new Language("en", "Anglais");
        Language es = new Language("es", "Espagnol");
        languages.add(fr);
        languages.add(en);
        languages.add(es);
        
        
        SemanticWord sw;
        Vocabulary voc = new Vocabulary("L'argent");

        try {
            sw = new SemanticWord(); sw.addTranslation(fr,"la banque"); sw.addTranslation(en,"the bank"); sw.addTranslation(es,"el Banco"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"l''argent liquide"); sw.addTranslation(en,"cash"); sw.addTranslation(es,"efectivo"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le paiement"); sw.addTranslation(en,"payment"); sw.addTranslation(es,"pago"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un carnet de chèques"); sw.addTranslation(en,"checkbook"); sw.addTranslation(es,"talonario de cheques"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"déposer de l''argent"); sw.addTranslation(en,"to deposit money"); sw.addTranslation(es,"depositar dinero"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"prêter de l''argent"); sw.addTranslation(en,"to borrow money"); sw.addTranslation(es,"pedir dinero prestado"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"les services financiers"); sw.addTranslation(en,"financial services"); sw.addTranslation(es,"servicios financieros"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la commission"); sw.addTranslation(en,"commission"); sw.addTranslation(es,"comisión"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un investissement"); sw.addTranslation(en,"an investment"); sw.addTranslation(es,"una inversión"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un virement"); sw.addTranslation(en,"transfer"); sw.addTranslation(es,"transferir"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la gestion de l''argent"); sw.addTranslation(en,"money management"); sw.addTranslation(es,"administración del dinero"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une transaction"); sw.addTranslation(en,"a transaction"); sw.addTranslation(es,"una transacción"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un guichet automatique"); sw.addTranslation(en,"ATM machine"); sw.addTranslation(es,"cajero automático"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"attendre dans la queue"); sw.addTranslation(en,"to wait in line"); sw.addTranslation(es,"a esperar en línea"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une carte bancaire"); sw.addTranslation(en,"banking/ATM card"); sw.addTranslation(es,"tarjeta bancaria / ATM"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"faire des économies"); sw.addTranslation(en,"to save money"); sw.addTranslation(es,"para ahorrar dinero"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le montant"); sw.addTranslation(en,"sum/total amount"); sw.addTranslation(es,"suma / monto total"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un compte-chèques"); sw.addTranslation(en,"checking account"); sw.addTranslation(es,"cuenta de cheques"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"l''argent"); sw.addTranslation(en,"money"); sw.addTranslation(es,"dinero"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"les fonds"); sw.addTranslation(en,"funds"); sw.addTranslation(es,"fondos"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un chèque"); sw.addTranslation(en,"check"); sw.addTranslation(es,"cheque"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un dépôt"); sw.addTranslation(en,"deposit"); sw.addTranslation(es,"depositar"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le crédit"); sw.addTranslation(en,"credit"); sw.addTranslation(es,"crédito"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"les marchés financiers"); sw.addTranslation(en,"financial markets"); sw.addTranslation(es,"mercados financieros"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la clientèle"); sw.addTranslation(en,"clientele"); sw.addTranslation(es,"clientela"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"les frais"); sw.addTranslation(en,"fees"); sw.addTranslation(es,"Tarifa"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une institution financière"); sw.addTranslation(en,"financial institution"); sw.addTranslation(es,"institución financiera"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le taux d''intérêt"); sw.addTranslation(en,"interest rate"); sw.addTranslation(es,"tasa de interés"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la monnaie"); sw.addTranslation(en,"currency"); sw.addTranslation(es,"moneda"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le bilan"); sw.addTranslation(en,"balance"); sw.addTranslation(es,"equilibrar"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"prendre un numéro"); sw.addTranslation(en,"to take a number"); sw.addTranslation(es,"tomar un número"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une carte de crédit"); sw.addTranslation(en,"credit card"); sw.addTranslation(es,"tarjeta de crédito"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un emprunt"); sw.addTranslation(en,"a loan"); sw.addTranslation(es,"un préstamo"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"retirer de l''argent"); sw.addTranslation(en,"to take out money"); sw.addTranslation(es,"sacar dinero"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un compte d''épargne"); sw.addTranslation(en,"savings account"); sw.addTranslation(es,"cuenta de ahorros"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un distributeur automatique"); sw.addTranslation(en,"ATM machine"); sw.addTranslation(es,"cajero automático"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un reçu"); sw.addTranslation(en,"receipt"); sw.addTranslation(es,"recibo"); voc.addSemanticWord(sw);
        } catch (Exception e) {

        }

        vocabularies.add(voc);

        voc = new Vocabulary("Les meubles");

        try {
            sw = new SemanticWord(); sw.addTranslation(fr,"une table"); sw.addTranslation(en,"a table"); sw.addTranslation(es,"una mesa"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une chaise"); sw.addTranslation(en,"a chair"); sw.addTranslation(es,"una silla"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une bibliothèque"); sw.addTranslation(en,"a bookcase"); sw.addTranslation(es,"una estantería"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une table basse"); sw.addTranslation(en,"a coffee table"); sw.addTranslation(es,"una mesa para cafe"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une cheminée"); sw.addTranslation(en,"a fireplace"); sw.addTranslation(es,"una chimenea"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une table de chevet"); sw.addTranslation(en,"a bedside table"); sw.addTranslation(es,"una mesita de noche"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une étagère"); sw.addTranslation(en,"a shelf"); sw.addTranslation(es,"un estante"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une armoire"); sw.addTranslation(en,"a wardrobe"); sw.addTranslation(es,"un armario"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une commode"); sw.addTranslation(en,"a dresser"); sw.addTranslation(es,"un vestidor"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"une moquette"); sw.addTranslation(en,"a carpet"); sw.addTranslation(es,"Una alfombra"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un siège"); sw.addTranslation(en,"a seat"); sw.addTranslation(es,"un asiento"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un tabouret"); sw.addTranslation(en,"a stool"); sw.addTranslation(es,"un taburete"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un placard"); sw.addTranslation(en,"a cupboard"); sw.addTranslation(es,"un armario"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un tiroir"); sw.addTranslation(en,"a drawer"); sw.addTranslation(es,"un cajon"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un fauteuil"); sw.addTranslation(en,"a armchair"); sw.addTranslation(es,"un sillón"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un lit"); sw.addTranslation(en,"a bed"); sw.addTranslation(es,"una cama"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un bureau"); sw.addTranslation(en,"a desk"); sw.addTranslation(es,"un escritorio"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un miroir"); sw.addTranslation(en,"a mrror"); sw.addTranslation(es,"un mrror"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un meuble"); sw.addTranslation(en,"a piece of furniture"); sw.addTranslation(es,"un mueble"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"un oreiller"); sw.addTranslation(en,"a pillow"); sw.addTranslation(es,"una almohada"); voc.addSemanticWord(sw);
        } catch (Exception e) {

        }
        vocabularies.add(voc);

        voc = new Vocabulary("Les couleurs");

        try {
            sw = new SemanticWord(); sw.addTranslation(fr,"blanc"); sw.addTranslation(en,"white"); sw.addTranslation(es,"blanco"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"bleu clair"); sw.addTranslation(en,"light blue"); sw.addTranslation(es,"azul claro"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"multicolore"); sw.addTranslation(en,"muli-colored"); sw.addTranslation(es,"multicolor"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"gris"); sw.addTranslation(en,"grey"); sw.addTranslation(es,"gris"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"vert"); sw.addTranslation(en,"green"); sw.addTranslation(es,"verde"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"noir"); sw.addTranslation(en,"black"); sw.addTranslation(es,"negro"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"argenté"); sw.addTranslation(en,"silver"); sw.addTranslation(es,"plata"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"jaune"); sw.addTranslation(en,"yellow"); sw.addTranslation(es,"amarillo"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"orange"); sw.addTranslation(en,"orange"); sw.addTranslation(es,"naranja"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"bleu"); sw.addTranslation(en,"blue"); sw.addTranslation(es,"azul"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"bleu foncé"); sw.addTranslation(en,"dark blue"); sw.addTranslation(es,"azul oscuro"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"rose"); sw.addTranslation(en,"pink"); sw.addTranslation(es,"rosado"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"rouge"); sw.addTranslation(en,"red"); sw.addTranslation(es,"rojo"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"marron"); sw.addTranslation(en,"brown"); sw.addTranslation(es,"marrón"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"violet"); sw.addTranslation(en,"purple"); sw.addTranslation(es,"púrpura"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"beige"); sw.addTranslation(en,"beige"); sw.addTranslation(es,"beige"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"turquoise"); sw.addTranslation(en,"turquoise"); sw.addTranslation(es,"turquesa"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"doré"); sw.addTranslation(en,"golden"); sw.addTranslation(es,"dorado"); voc.addSemanticWord(sw);
        } catch (Exception e) {

        }
        vocabularies.add(voc);

        voc = new Vocabulary("La famille");

        try {
            sw = new SemanticWord(); sw.addTranslation(fr,"le père"); sw.addTranslation(en,"the father"); sw.addTranslation(es,"el padre"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"les parents"); sw.addTranslation(en,"the parents"); sw.addTranslation(es,"los padres"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la sœur"); sw.addTranslation(en,"the sister"); sw.addTranslation(es,"la hermana"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la fille"); sw.addTranslation(en,"the daughter"); sw.addTranslation(es,"la hija"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le bébé"); sw.addTranslation(en,"the baby"); sw.addTranslation(es,"el bebé"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la femme"); sw.addTranslation(en,"the wife"); sw.addTranslation(es,"La esposa"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la fiancée"); sw.addTranslation(en,"the fiancée"); sw.addTranslation(es,"la prometida"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la grand-mère"); sw.addTranslation(en,"the grandmother"); sw.addTranslation(es,"la abuela"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"les petits-enfants"); sw.addTranslation(en,"the grandchildren"); sw.addTranslation(es,"los nietos"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la tante"); sw.addTranslation(en,"the aunt"); sw.addTranslation(es,"la tía"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la nièce"); sw.addTranslation(en,"the niece"); sw.addTranslation(es,"la sobrina"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la belle-mère"); sw.addTranslation(en,"the step mother"); sw.addTranslation(es,"La Madrastra"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la cousine"); sw.addTranslation(en,"the cousin (female)"); sw.addTranslation(es,"la prima (mujer)"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la belle-sœur"); sw.addTranslation(en,"sister in-law"); sw.addTranslation(es,"cuñada"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"la mère"); sw.addTranslation(en,"the mother"); sw.addTranslation(es,"La madre"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le frère"); sw.addTranslation(en,"the brother"); sw.addTranslation(es,"el hermano"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le fils"); sw.addTranslation(en,"the son"); sw.addTranslation(es,"el hijo"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"l’enfant"); sw.addTranslation(en,"the child"); sw.addTranslation(es,"el niño"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le mari"); sw.addTranslation(en,"the husband"); sw.addTranslation(es,"el esposo"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le fiancé"); sw.addTranslation(en,"the fiancé"); sw.addTranslation(es,"el prometido"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le grand-père"); sw.addTranslation(en,"the grandfather"); sw.addTranslation(es,"el abuelo"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"les grand-parents"); sw.addTranslation(en,"the grandparents"); sw.addTranslation(es,"los abuelos"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"l’oncle"); sw.addTranslation(en,"the uncle"); sw.addTranslation(es,"el tío"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le neveu"); sw.addTranslation(en,"the nephew"); sw.addTranslation(es,"el sobrino"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le beau-père"); sw.addTranslation(en,"the step father"); sw.addTranslation(es,"el padrastro"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le cousin"); sw.addTranslation(en,"the cousin (male)"); sw.addTranslation(es,"el primo (hombre)"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le beau-frère"); sw.addTranslation(en,"brother in-law"); sw.addTranslation(es,"cuñado"); voc.addSemanticWord(sw);
            sw = new SemanticWord(); sw.addTranslation(fr,"le beau-père"); sw.addTranslation(en,"father in-law"); sw.addTranslation(es,"suegro"); voc.addSemanticWord(sw);
        } catch (Exception e) {

        }
        vocabularies.add(voc);
    }
}
