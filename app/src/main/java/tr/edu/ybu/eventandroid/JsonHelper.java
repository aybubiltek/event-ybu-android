package tr.edu.ybu.eventandroid;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Melih on 7.10.2016.
 */

public class JsonHelper extends AsyncTask<String, String, JSONObject> {

    private final JsonRequest implemented;

    public JsonHelper(JsonRequest implemented) {
        this.implemented = implemented;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        implemented.onPreExecute();
    }

    @Override
    protected JSONObject doInBackground(String... params) {
        try {
            URL url = new URL("http://event.ybu.edu.tr/api/" + params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestMethod("POST");

            OutputStreamWriter request = new OutputStreamWriter(connection.getOutputStream());
            String requestParams = "";
            for(int i=1;i<params.length;i+=2){
                requestParams += URLEncoder.encode(params[i], "utf-8") + "=" + URLEncoder.encode(params[i+1], "utf-8") + "&";
            }
            requestParams = requestParams.substring(0, requestParams.length()-1);
            request.write(requestParams);
            request.flush();
            request.close();
            InputStream stream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder = new StringBuilder();

            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                builder.append(inputString);
            }
            connection.disconnect();
            return implemented.parse(builder.toString());
        } catch (IOException | JSONException e) {
            Welcome.log(e.toString());
            return null;
        }
    }

    @Override
    protected void onPostExecute(JSONObject json){
        super.onPostExecute(json);
        implemented.onPostExecute(json);
    }
    public interface JsonRequest{
        void onPreExecute();
        JSONObject parse(String jsonString) throws JSONException;
        void onPostExecute(JSONObject json);
    }
}
