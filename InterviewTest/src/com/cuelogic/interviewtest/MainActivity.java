package com.cuelogic.interviewtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView imgview;
	HorizontalScrollView horizontalview;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        new Task_FetchData(this).execute("http://192.168.10.104/imageData.php");
        
    }    
}
