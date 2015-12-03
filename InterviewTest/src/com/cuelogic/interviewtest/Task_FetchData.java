package com.cuelogic.interviewtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Task_FetchData extends AsyncTask<String, Void, Void> {

	private final HttpClient Client = new DefaultHttpClient();
	public static List<Elements> animalList=new ArrayList<Elements>();
	public static List<Elements> birdslist=new ArrayList<Elements>();
	public static List<Elements> flaglist=new ArrayList<Elements>();
	public static List<Elements> flowerlist=new ArrayList<Elements>();
	public static List<Elements> fuirtlist=new ArrayList<Elements>();
	public static List<Elements> techlist=new ArrayList<Elements>();
	public static List<Elements> veglist=new ArrayList<Elements>();
	
	private String Content;
	Context context;
	LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7;
	static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";

	private String Error = null;

	 ProgressDialog Dialog;
		public Task_FetchData(Context context) {
			this.context=context;
		}
		public Task_FetchData(Context context, LinearLayout layout1, LinearLayout layout2, LinearLayout layout3, LinearLayout layout4, LinearLayout layout5, LinearLayout layout6, LinearLayout layout7) {
				this.context=context;
				this.layout1=layout1;
				this.layout2=layout2;
				this.layout3=layout3;
				this.layout4=layout4;
				this.layout5=layout5;
				this.layout6=layout6;
				this.layout7=layout7;
				
		}
		protected void onPreExecute() {
			Dialog=new ProgressDialog(context);
			Dialog.setMessage("Downloading source..");
			Dialog.show();
		}
		
		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			fetchimage();
			Dialog.dismiss();
		}
		
		
		@Override
		protected Void doInBackground(String... url) {
			try{				
				DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpPost httpPost = new HttpPost(url[0]);

	            HttpResponse httpResponse = httpClient.execute(httpPost);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            is = httpEntity.getContent();
	            
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(
	                    is, "iso-8859-1"), 8);
	            StringBuilder sb = new StringBuilder();
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	            is.close();
	            json = sb.toString();
	            parsedata(json);  
	            
			}catch(IOException e){
				e.printStackTrace();
			}
			return null;
		}
		
		
		private void fetchimage() {
			for(int i = 0; i < animalList.size(); i++){
				layout1.addView(insertPhoto(animalList.get(i).getUrl()));		
			}
			for(int i = 0; i < birdslist.size(); i++){
				layout2.addView(insertPhoto(birdslist.get(i).getUrl()));		
			}
			for(int i = 0; i < flaglist.size(); i++){
				layout3.addView(insertPhoto(flaglist.get(i).getUrl()));		
			}
			/*for(int i = 0; i < flowerlist.size(); i++){
				layout4.addView(insertPhoto(flowerlist.get(i).getUrl()));		
			}
			for(int i = 0; i < fuirtlist.size(); i++){
				layout5.addView(insertPhoto(fuirtlist.get(i).getUrl()));		
			}
			for(int i = 0; i < techlist.size(); i++){
				layout6.addView(insertPhoto(techlist.get(i).getUrl()));		
			}
			for(int i = 0; i < veglist.size(); i++){
				layout7.addView(insertPhoto(veglist.get(i).getUrl()));		
			}*/
		}
		private void parsedata(String content) {		
	        try {
	            jObj = new JSONObject(json);
	            JSONArray jarray=jObj.getJSONArray("animals");
	            for(int i = 0; i < jarray.length(); i++){
	                JSONObject c = jarray.getJSONObject(i);
	                String name = c.getString("name");
	                String imgurl = c.getString("imgURL");	                
	                animalList.add(new Elements(name,imgurl));
	            }
	            
	            JSONArray jarray1=jObj.getJSONArray("birds");
	            for(int i = 0; i < jarray1.length(); i++){
	                JSONObject c = jarray1.getJSONObject(i);
	                String name = c.getString("name");
	                String imgurl = c.getString("imgURL");	                
	                birdslist.add(new Elements(name,imgurl));
	            }
	            
	            JSONArray jarray2=jObj.getJSONArray("flags");
	            for(int i = 0; i < jarray2.length(); i++){
	                JSONObject c = jarray2.getJSONObject(i);
	                String name = c.getString("name");
	                String imgurl = c.getString("imgURL");	                
	                flaglist.add(new Elements(name,imgurl));
	            }
	            
	            JSONArray jarray3=jObj.getJSONArray("flowers");
	            for(int i = 0; i < jarray3.length(); i++){
	                JSONObject c = jarray3.getJSONObject(i);
	                String name = c.getString("name");
	                String imgurl = c.getString("imgURL");	                
	                flowerlist.add(new Elements(name,imgurl));
	            }
	            
	            JSONArray jarray4=jObj.getJSONArray("fruits");
	            for(int i = 0; i < jarray4.length(); i++){
	                JSONObject c = jarray4.getJSONObject(i);
	                String name = c.getString("name");
	                String imgurl = c.getString("imgURL");	                
	                fuirtlist.add(new Elements(name,imgurl));
	            }
	            
	            JSONArray jarray5=jObj.getJSONArray("technology");
	            for(int i = 0; i < jarray5.length(); i++){
	                JSONObject c = jarray5.getJSONObject(i);
	                String name = c.getString("name");
	                String imgurl = c.getString("imgURL");	                
	                techlist.add(new Elements(name,imgurl));
	            }
	            
	            JSONArray jarray6=jObj.getJSONArray("vegetables");
	            for(int i = 0; i < jarray6.length(); i++){
	                JSONObject c = jarray6.getJSONObject(i);
	                String name = c.getString("name");
	                String imgurl = c.getString("imgURL");	                
	                veglist.add(new Elements(name,imgurl));
	            }
	            
	        } catch (JSONException e) {
	            Log.e("JSON Parser", "Error parsing data " + e.toString());
	        }
			
		}
		
		
		View insertPhoto(String path){
			Bitmap image = null;
	        try {
	        	//Log.v("path", path);
	        	String urlpath="http://192.168.10.104/"+path;
                URL url = new URL(urlpath);
                image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
	        LinearLayout layout = new LinearLayout(context);
	        layout.setLayoutParams(new LayoutParams(100,100));
	        layout.setGravity(Gravity.CENTER);
	        
	        ImageView imageView = new ImageView(context);
	        imageView.setLayoutParams(new LayoutParams(150, 150));
	        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	        imageView.setImageBitmap(image);
	        
	        layout.addView(imageView);
	        return layout;
	       }
	       
	       
}