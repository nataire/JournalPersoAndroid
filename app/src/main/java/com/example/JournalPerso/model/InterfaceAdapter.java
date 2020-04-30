package com.example.JournalPerso.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Vector;

public class InterfaceAdapter implements JsonSerializer<Indicateur>, JsonDeserializer {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String DATA = "DATA";

    public Vector<Espace> deserialize(JsonElement jsonElement, Type typeOfT,
                                      JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {


        Vector<Espace> mesEspaces = new Vector<>();

        JsonObject jsonObjectListeEspace = jsonElement.getAsJsonObject();

        JsonArray listeEspace = jsonObjectListeEspace.get("listeEspace").getAsJsonArray();


        if (listeEspace != null) {
            for (int i = 0; i < listeEspace.size(); i++) {

                Espace monEspace = new Espace();

                JsonObject jsonObjectEspace = listeEspace.get(i).getAsJsonObject();

                monEspace.setNomEspace(jsonObjectEspace.get("nomEspace").getAsString());

                JsonArray listeIndicateur = jsonObjectEspace.get("listeIndicateur").getAsJsonArray();

                if (listeIndicateur != null) {
                    for (int j = 0; j < listeIndicateur.size(); j++) {

                        JsonObject jsonObjectIndicateur = listeIndicateur.get(i).getAsJsonObject();


                        if (jsonObjectIndicateur.get("typeIndicateur").getAsString().equals("CaseCochee")) {
                            IndicateurCaseCochee monIndicateur = new IndicateurCaseCochee();

                            monIndicateur.nomIndicateur = jsonObjectIndicateur.get("nomIndicateur").getAsString();
                            monIndicateur.typeIndicateur = jsonObjectIndicateur.get("typeIndicateur").getAsString();
                            monIndicateur.setEtatBoutonSaisie(jsonObjectIndicateur.get("etatBoutonSaisie").getAsBoolean());
                            monIndicateur.setObjectifCaseCochee(jsonObjectIndicateur.get("objectifCaseCochee").getAsBoolean());

                            monEspace.addIndicateur(monIndicateur);
                        }


                    }
                }


                mesEspaces.add(monEspace);
            }

        }


        return mesEspaces;
    }

    public JsonElement serialize(Indicateur jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
        jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
        return jsonObject;
    }

    /****** Helper method to get the className of the object to be deserialized *****/
    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }
    }

}