package fr.dok.chabadabada.daogenerator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by loic on 27/05/14.
 */
public class ChabadaDaoGenerator {
	public static void main(String args[]) throws Exception {
		Schema schema = new Schema(3, "fr.dok.chabadabada.model");
		Entity chabadaCard = schema.addEntity("ChabadaCard");
		chabadaCard.addIdProperty();
		chabadaCard.addStringProperty("FRENCH_WORD");
		chabadaCard.addStringProperty("ENGLISH_WORD");
		chabadaCard.addIntProperty("CARD_TYPE");
		new DaoGenerator().generateAll(schema, "chabadabada/src-gen");
	}
}
