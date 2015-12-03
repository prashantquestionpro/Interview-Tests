package com.cuelogic.interviewtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Task_FetchData extends AsyncTask<String, Void, Void> {

	private final HttpClient Client = new DefaultHttpClient();

	private String Content;
	Context con;
	static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

	private String Error = null;

	 ProgressDialog Dialog;
		public Task_FetchData(Context context) {
			con=context;
		}
		protected void onPreExecute() {
			Dialog=new ProgressDialog(con);
			Dialog.setMessage("Downloading source..");
			Dialog.show();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Dialog.dismiss();
		}
		
		
		@Override
		protected Void doInBackground(String... url) {
			try{
//				HttpGet httpget = new HttpGet(url[0]);
//				ResponseHandler<String> responseHandler = new BasicResponseHandler();
//				Content=Client.execute(httpget,responseHandler);
//				Log.v("hi", Content);
//				parsedata(Content);
				
				DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(url[0]);

	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            is = httpEntity.getContent();
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
		
		
		private void parsedata(String content) {
			try {
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is, "iso-8859-1"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            json = sb.toString();
	        } catch (Exception e) {
	            Log.e("Buffer Error", "Error converting result " + e.toString());
	        }

	        // try parse the string to a JSON object
	        try {
	            jObj = new JSONObject(json);
	        } catch (JSONException e) {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }
	        
	        
	        /*try {
	            contacts = json.getJSONArray();

	            // looping through All Contacts
	            for(int i = 0; i < contacts.length(); i++){
	                JSONObject c = contacts.getJSONObject(i);

	                // Storing each json item in variable
	                String id = c.getString(TAG_ID);
	                String name = c.getString(TAG_NAME);
	                String email = c.getString(TAG_EMAIL);
	                String address = c.getString(TAG_ADDRESS);
	                String gender = c.getString(TAG_GENDER);

	            }
	        } catch (JSONException e) {
	            e.printStackTrace();
	        }*/
			
		}
}