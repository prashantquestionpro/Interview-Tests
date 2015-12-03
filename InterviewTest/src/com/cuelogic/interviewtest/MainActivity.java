package com.cuelogic.interviewtest;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView imgview;
	//HorizontalScrollView horizontalview;
	LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1 = (LinearLayout)findViewById(R.id.gallry1);   
        layout2 = (LinearLayout)findViewById(R.id.gallry2); 
        layout3 = (LinearLayout)findViewById(R.id.gallry3); 
        layout4 = (LinearLayout)findViewById(R.id.gallry4); 
        layout5 = (LinearLayout)findViewById(R.id.gallry5); 
        layout6 = (LinearLayout)findViewById(R.id.gallry6); 
        layout7 = (LinearLayout)findViewById(R.id.gallry7); 
        new Task_FetchData(this,layout1,layout2,layout3,layout4,layout5,layout6,layout7).execute("http://192.168.10.104/imageData.php");
       
        
       
        
    
    }  
}
