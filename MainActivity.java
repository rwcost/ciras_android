package com.example.sensor;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.*;
import android.hardware.*;

/* Final Project for 
 * Authors Gabrielle Vanderburgh and Robert Cost
 * 
 * This is the main activity of the application, in android terminology, the activity is basically
 * a screen on the phone(represented by xml in the layout folder section).  
 * This activity will ask the user to input their information in the appropriate textbox, 
 * creates a database(if needed).  Insertion of the data is in the next activity called
 * which is MyActivity.
 */
public class MainActivity extends Activity implements SensorEventListener {

	SQLiteDatabase db;
    private float fahrenheit;
    SensorManager mgr;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //dummy data values to test display
        float fahrenheit = 2;
          
      //********   questionnaire button to go to page on android display  rwc 10/12/12 ***************************************/
        //this button will send the user to the page that asks the user health questions
     //   Button questionButton = (Button) findViewById(R.id.button1);
     //   questionButton.setText("Send", TextView.BufferType.EDITABLE);
      
       //create a sqlite local database on the android phone
        db = openOrCreateDatabase( "myAndroidPHR7.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);
       
        try {
            
        	 final String createTableIfNeeded = "CREATE TABLE IF NOT EXISTS tbl_user_vitals ("
                     + "ID INTEGER primary key AUTOINCREMENT,"
                     + "userid INT,"
                     + "date_time DATETIME,"
                     + "systolic INT,"
                     + "diastolic INT,"
                     + "hdl INT,"
                     + "ldl INT,"
                     + "RAS INT,"
                     + "isOnMeds INT,"
                     + "isSmoker INT,"
                     + "age INT,"
                     + "isDiabetic INT,"
                     + "isMale INT" 
                     + ");";
            
       // this is actual executing statement that creates a SQLite database if needed
            db.execSQL(createTableIfNeeded);
            Toast.makeText(MainActivity.this, "table created ", Toast.LENGTH_LONG).show();
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this, "ERROR "+e.toString(), Toast.LENGTH_LONG).show();  
    } 
     //get id of the send button on the screen   
     final Button mySendButton = (Button) findViewById(R.id.sendButton);
    
    // activate listener for send button
    mySendButton.setOnClickListener(new View.OnClickListener()  {
    	
		private int ldlValue; 
		private int RASValue;
		private int ageValue;
		private int systolicValue;
		private int diastolicValue;
		private int gender;
		private int isSmoker;
		private int isOnMeds;
		private int isDiabetic;
		private int isMale;
		private String ldlKey;
		private String ageKey;
		private String systolicKey;
		private String diastolicKey;
		private String isMaleKey;
		private String isSmokerKey;
		private String isOnMedsKey;
		private String isDiabeticKey;
		private String hdlValue;

		public void onClick(View v){

			//This creates a container myIntent in which to pass data to next called activity
    		Intent myIntent = new Intent(MainActivity.this, MyActivity.class);
    		
    		//gets information from the appropriate text box on screen
    		EditText hdlEditText = (EditText)findViewById(R.id.hdlBox);
    		String hdlValue = hdlEditText.getText().toString();
    		myIntent.putExtra("com.example.sensor.hdlKey",hdlValue);   		
   		 	
    		//gets information from the appropriate text box on screen
    		EditText userIdEditText = (EditText)findViewById(R.id.EditText05);
    		String userIdValue = userIdEditText.getText().toString();
    		myIntent.putExtra("com.example.sensor.userIdKey",userIdValue);  
    		
    		
    		//gets information from the appropriate text box on screen
    		EditText ldlEditText = (EditText)findViewById(R.id.ldlBox);
    		String ldlValue = ldlEditText.getText().toString();
    		myIntent.putExtra("com.example.sensor.ldlKey",ldlValue); 
   		 	
    		//gets information from the appropriate text box on screen
    		EditText ageEditText = (EditText)findViewById(R.id.ageBox);
    		String ageValue = ageEditText.getText().toString();
    		myIntent.putExtra("com.example.sensor.ageKey",ageValue);
    		
    		//gets information from the appropriate text box on screen
    		EditText diastolicEditText = (EditText)findViewById(R.id.diastolicBox);
    		String diastolicValue = diastolicEditText.getText().toString();
    		myIntent.putExtra("com.example.sensor.diastolicKey",diastolicValue);
    		
    		//gets information from the appropriate text box on screen
    		EditText systolicEditText = (EditText)findViewById(R.id.systolicBox);
    		String systolicValue = systolicEditText.getText().toString();
    		myIntent.putExtra("com.example.sensor.systolicKey",systolicValue);
    		
    		//gets information from the appropriate toggle button on screen
    		ToggleButton isDiabeticButton = (ToggleButton) findViewById(R.id.isDiabetic);
    		if(isDiabeticButton.isChecked()){
		   	   isDiabetic = 1;
		   	   }else{
		   		   isDiabetic = 0;
		   	   }
    		String isDiabeticValue = Integer.toString(isDiabetic);
    		myIntent.putExtra("com.example.sensor.isDiabeticKey",isDiabeticValue);
    		
    		//gets information from the appropriate toggle button on screen
    		ToggleButton isMaleButton = (ToggleButton) findViewById(R.id.IsMale);
    		if(isMaleButton.isChecked()){
		   	   isMale = 1;
		   	   }else{
		   		   isMale = 0;
		   	   }
    		String isMaleValue = Integer.toString(isMale);
    		myIntent.putExtra("com.example.sensor.isMaleKey",isMaleValue);
    		
    		//gets information from the appropriate toggle button on screen
    		ToggleButton isSmokerButton = (ToggleButton) findViewById(R.id.isSmoker);
    		if(isSmokerButton.isChecked()){
		   	   isSmoker = 1;
		   	   }else{
		   		   isSmoker = 0;
		   	   }
    		String isSmokerValue = Integer.toString(isSmoker);
    		myIntent.putExtra("com.example.sensor.isSmokerKey",isSmokerValue);
    		
    		ToggleButton isOnMedsButton = (ToggleButton) findViewById(R.id.isOnMeds);
    		if(isOnMedsButton.isChecked()){
    			isOnMeds = 1;
		   	   }else{
		   		isOnMeds = 0;
		   	   }
    		String isOnMedsValue = Integer.toString(isOnMeds);
    		myIntent.putExtra("com.example.sensor.isOnMedsKey",isOnMedsValue);

    		// This starts a new activity referenced in the object myIntent'
   		 	MainActivity.this.startActivity(myIntent);
   	   	}
   
    });
    
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onSensorChanged(SensorEvent event) {
		
		fahrenheit = event.values[0] * 9 / 5 + 32; 
	}
}
