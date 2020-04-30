package com.example.JournalPerso.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.JournalPerso.ConnexionActivity;
import com.example.JournalPerso.model.User;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class DataApi {
    private static AsyncHttpClient client;
    private JSONAsyncTask synchronisation;
    private AsyncHttpResponseHandler responseHandler;
    private Gson gson;
    private ConnexionActivity parentConnexion;
    private User test = null;
    private Context monContext;

    public DataApi(Context context, ConnexionActivity parent) {
        parentConnexion = parent;
        client = new AsyncHttpClient();
        monContext = context;
        gson = new Gson();

        responseHandler = new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                // called when response HTTP status is "200 OK"


                try {
                    JSONObject testV = new JSONObject(new String(response));
                    test = gson.fromJson(testV.toString(), User.class);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.i("IAM", test.toString());
                parentConnexion.connexionReussi(test);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                parentConnexion.connexionEchec();
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

        synchronisation.execute(email, password);

    }

    class JSONAsyncTask extends AsyncTask<String, Void, String> {
        // Params, Progress, Result
        String BASE_URL = "http://10.0.2.2/api_android/";
        Context monContext;

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
                connection(qs[0], qs[1]);
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
    }

}



