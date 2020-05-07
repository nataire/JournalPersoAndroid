package com.example.JournalPerso.data;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.JournalPerso.ConnexionActivity;
import com.example.JournalPerso.InscriptionActivity;
import com.example.JournalPerso.model.Espace;
import com.example.JournalPerso.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;



public class DataApi {
    private static AsyncHttpClient client;
    private JSONAsyncTask synchronisation;
    private AsyncHttpResponseHandler responseHandler;
    private Gson gson;
    private Activity parentActivity;
    private User test;
    private Context monContext;
    private String typeRequete;

    public DataApi(Context context, Activity parent) {
        parentActivity  = parent ;

        client = new AsyncHttpClient();
        monContext = context;
        gson = new Gson();

        initResponseHandler();

    }

    public DataApi(Context context) {
        client = new AsyncHttpClient();
        monContext = context;
        gson = new Gson();

        initResponseHandler();
    }


    private void initResponseHandler()
    {
        responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"


                try {
                    if(typeRequete.equals("connexion"))
                    {
                        JSONObject testV = new JSONObject(new String(response));
                        test = gson.fromJson(testV.toString(), User.class);
                        Log.i("IAM", test.toString());
                        ((ConnexionActivity)parentActivity).connexionReussi(test);
                    }
                    else if (typeRequete.equals("inscription"))
                    {
                        Log.i("IAM", "inscription reussie");
                        ((InscriptionActivity) parentActivity).inscriptionReussi();
                    }
                    else if (typeRequete.equals("saveEspace"))
                    {
                        Log.i("IAM", "saveEspace reussie");

                    }
                    else if (typeRequete.equals("updateEspace"))
                    {
                        Log.i("IAM", "updateEspace reussi");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                if(typeRequete.equals("connexion"))
                {
                    ((ConnexionActivity)parentActivity).connexionEchec();
                }
                else if (typeRequete.equals("inscription"))
                {
                    Log.i("IAM", "inscription echec");
                    ((InscriptionActivity) parentActivity).inscriptionEchec();
                }
                else if (typeRequete.equals("updateEspace"))
                {
                    Log.i("IAM", "updateEspace echec");
                }

            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried
            }
        };

    }
    public void connexion(String email, String password) {
        synchronisation = new JSONAsyncTask();
        synchronisation.setMonContext(monContext);
        typeRequete = "connexion";
        synchronisation.execute("connexion", email, password);


    }

    public void inscription(String nom, String prenom, String email, String password) {
        synchronisation = new JSONAsyncTask();
        synchronisation.setMonContext(monContext);
        typeRequete = "inscription";
        synchronisation.execute("inscription",nom, prenom, email, password);

    }


    public void saveEspace(int idUser, int idEspace, String nomEspace, String commentaireEspace, Map<String, Boolean> detailJour)
    {
        synchronisation = new JSONAsyncTask();
        synchronisation.setMonContext(monContext);
        typeRequete = "saveEspace";
        synchronisation.setDetailJour(detailJour);
        synchronisation.execute("saveEspace",String.valueOf(idUser), String.valueOf(idEspace), nomEspace, commentaireEspace);
    }

    public void updateEspace(int idUser, int idEspace, String nomEspace, Map<String, Boolean> detailJour)
    {
        synchronisation = new JSONAsyncTask();
        synchronisation.setMonContext(monContext);
        typeRequete = "updateEspace";
        synchronisation.setDetailJour(detailJour);
        synchronisation.execute("updateEspace",String.valueOf(idUser), String.valueOf(idEspace), nomEspace);
    }



    public void saveIndicateur(int idIndicateur, int idEspace, String nomIndicateur, String objectif, String type, String valeur)
    {
        synchronisation = new JSONAsyncTask();
        synchronisation.setMonContext(monContext);
        typeRequete = "saveIndicateur";
        synchronisation.execute("saveIndicateur",String.valueOf(idIndicateur), String.valueOf(idEspace), nomIndicateur, objectif, type, valeur);
    }

    public void updateIndicateur(int idIndicateur, int idEspace, String nomIndicateur, String objectif, String type, String valeur)
    {
        synchronisation = new JSONAsyncTask();
        synchronisation.setMonContext(monContext);
        typeRequete = "updateIndicateur";
        synchronisation.execute("updateIndicateur",String.valueOf(idIndicateur), String.valueOf(idEspace), nomIndicateur, objectif, type, valeur);
    }

    public void updateUser(int idUser, String nom, String prenom, String email, String password)
    {
        synchronisation = new JSONAsyncTask();
        synchronisation.setMonContext(monContext);
        typeRequete = "updateUser";
        synchronisation.execute("updateUser",String.valueOf(idUser), nom, prenom, email, password);
    }


    class JSONAsyncTask extends AsyncTask<String, Void, String> {
        // Params, Progress, Result
        String BASE_URL = "http://10.0.2.2/api_android/";
        Context monContext;
        Map<String, Boolean> detailJour;


        public Map<String, Boolean> getDetailJour() {
            return detailJour;
        }

        public void setDetailJour(Map<String, Boolean> detailJour) {
            this.detailJour = detailJour;
        }

        public Context getMonContext() {
            return monContext;
        }

        public void setMonContext(Context monContext) {
            this.monContext = monContext;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("IAM", "onPreExecute");
        }

        @Override
        protected String doInBackground(String... qs) {
            // pas d'interaction avec l'UI Thread ici
            Log.i("IAM", "doInBackground");
            try {
                if (qs[0].equals("connexion")) {
                    connection(qs[1], qs[2]);
                } else if (qs[0].equals("inscription")) {
                    inscription(qs[1], qs[2], qs[3], qs[4]);
                }
                else if (qs[0].equals("saveEspace")) {
                    saveEspace(qs[1], qs[2], qs[3]);
                   // saveEspace(qs[1]);
                }
                else if (qs[0].equals("saveIndicateur")) {
                    saveIndicateur(qs[1], qs[2], qs[3], qs[4], qs[5], qs[6]);
                }
                else if (qs[0].equals("updateEspace")) {
                    updateEspace(qs[1], qs[2], qs[3]);
                }
                else if (qs[0].equals("updateIndicateur")) {
                    updateIndicateur(qs[1], qs[2], qs[3], qs[4], qs[5], qs[6]);
                }
                else if (qs[0].equals("updateUser")) {
                    updateUser(qs[1], qs[2], qs[3], qs[4], qs[5]);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return "";
        }

        protected void onPostExecute(String result) {

            Log.i("IAM", "onPostExecute");
        }

        private void connection(String email, String password) throws JSONException, UnsupportedEncodingException {

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("email", email);
            jsonParams.put("password", password);
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(monContext, BASE_URL + "user/connection.php", entity, "application/json", responseHandler);
        }

        private void inscription(String prenom, String nom, String email, String password) throws JSONException, UnsupportedEncodingException {

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("email", email);
            jsonParams.put("password", password);
            jsonParams.put("nomUser", nom);
            jsonParams.put("prenomUser", prenom);
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(monContext, BASE_URL + "user/enregistrement.php", entity, "application/json", responseHandler);
        }

        private void saveEspace(String idUser, String idEspace, String nomEspace) throws JSONException, UnsupportedEncodingException {
       // private void saveEspace(String contenu) throws JSONException, UnsupportedEncodingException {

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("idUser", idUser);
            jsonParams.put("idEspace", idEspace);
            jsonParams.put("nomEspace", nomEspace);


            JSONObject jsonParams2 = new JSONObject();
            for (Map.Entry<String, Boolean> entry : detailJour.entrySet()) {
                jsonParams2.put(entry.getKey(), entry.getValue());
            }

            jsonParams.put("detailJour", jsonParams2);
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(monContext, BASE_URL + "espace/SauvegardeEspace.php", entity, "application/json", responseHandler);
        }

        private void saveIndicateur(String idIndicateur, String idEspace, String nomIndicateur, String objectif, String type, String valeur) throws JSONException, UnsupportedEncodingException {

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("idIndicateur", idIndicateur);
            jsonParams.put("idEspace", idEspace);
            jsonParams.put("nomIndicateur", nomIndicateur);
            jsonParams.put("objectif", objectif);
            jsonParams.put("type", type);
            jsonParams.put("valeur", valeur);
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(monContext, BASE_URL + "indicateur/SauvegardeIndicateur.php", entity, "application/json", responseHandler);
        }

        private void updateIndicateur(String idIndicateur, String idEspace, String nomIndicateur, String objectif, String type, String valeur) throws JSONException, UnsupportedEncodingException {

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("idIndicateur", idIndicateur);
            jsonParams.put("idEspace", idEspace);
            jsonParams.put("nomIndicateur", nomIndicateur);
            jsonParams.put("objectif", objectif);
            jsonParams.put("type", type);
            jsonParams.put("valeur", valeur);
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(monContext, BASE_URL + "indicateur/UpdateIndicateur.php", entity, "application/json", responseHandler);
        }

        private void updateEspace(String idUser, String idEspace, String nomEspace) throws JSONException, UnsupportedEncodingException {

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("idUser", idUser);
            jsonParams.put("idEspace", idEspace);
            jsonParams.put("nomEspace", nomEspace);


            JSONObject jsonParams2 = new JSONObject();
            for (Map.Entry<String, Boolean> entry : detailJour.entrySet()) {
                jsonParams2.put(entry.getKey(), entry.getValue());
            }

            jsonParams.put("detailJour", jsonParams2);
            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(monContext, BASE_URL + "espace/UpdateEspace.php", entity, "application/json", responseHandler);
        }

        private void updateUser( String idUser, String nom, String  prenom, String email, String password) throws JSONException, UnsupportedEncodingException {

            JSONObject jsonParams = new JSONObject();
            jsonParams.put("idUser", idUser);
            jsonParams.put("email", email);
            jsonParams.put("password", password);
            jsonParams.put("nomUser", nom);
            jsonParams.put("prenomUser", prenom);


            StringEntity entity = new StringEntity(jsonParams.toString());
            client.post(monContext, BASE_URL + "user/updateUser.php", entity, "application/json", responseHandler);
        }

    }

}



