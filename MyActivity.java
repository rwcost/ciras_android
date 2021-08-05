package com.example.sensor;

import java.util.ArrayList;
import java.util.List;
import com.example.sensor.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


/* this activity stores the screen vitals data to the local sqlite database 
 * and then saves the screen data to the web page mysql database
 */

public class MyActivity extends Activity {
    private TextView myTextView;
	private HttpPost httppost;
	private DefaultHttpClient httpclient;
	private HttpResponse response;
	String responsed = " ";
	private BasicNameValuePair myPair;
	private boolean succeeded;
	private String hdlKey = "hdlKey";
	private SQLiteDatabase db;
	private String RASValue;
	

	@SuppressWarnings("unchecked")
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        
        //get the data from last the Main_Activity (Main screen)
        //This section gets the entered data from the data entered into "Main_Activity's Intent"
        //This is a way to pass variables from one activity(screen display in xml)
        //to another screen activity
        Intent intent = getIntent();
        
       String hdlValue = intent.getStringExtra("com.example.sensor.hdlKey"); 
       int hdl = Integer.parseInt(hdlValue);
       
       String userIdValue = intent.getStringExtra("com.example.sensor.userIdKey"); 
       int userId = Integer.parseInt(userIdValue);
       
       String ldlValue = intent.getStringExtra("com.example.sensor.ldlKey"); 
       int ldl = Integer.parseInt(ldlValue);
       
       String ageValue = intent.getStringExtra("com.example.sensor.ageKey"); 
       int age = Integer.parseInt(ageValue);
       
       String diastolicValue = intent.getStringExtra("com.example.sensor.diastolicKey"); 
       int diastolic = Integer.parseInt(diastolicValue);
       
       String systolicValue = intent.getStringExtra("com.example.sensor.systolicKey"); 
       int systolic = Integer.parseInt(systolicValue);
       
       String isDiabeticValue = intent.getStringExtra("com.example.sensor.isDiabeticKey");
       int isDiabetic = Integer.parseInt(isDiabeticValue);
       
       String isMaleValue = intent.getStringExtra("com.example.sensor.isMaleKey"); 
       int isMale = Integer.parseInt(isMaleValue);
       
       String isSmokerValue = intent.getStringExtra("com.example.sensor.isSmokerKey"); 
       int isSmoker = Integer.parseInt(isSmokerValue);
       
       String isOnMedsValue = intent.getStringExtra("com.example.sensor.isOnMedsKey"); 
       int isOnMeds = Integer.parseInt(isOnMedsValue);
       
       //create a RAS calculator object to do calculation       
       RASCalculator  myCalc = new RASCalculator();
       //call and pass calculation variables to newly created instance of RASCalculator
       int RAS = myCalc.calculateRAS(isMale,age,hdl,ldl,isSmoker,systolic,diastolic,isOnMeds);

     // final TextView tv2 = (TextView)findViewById(R.id.tv2);
       
       RASValue = Integer.toString(RAS);
       //tv2.setText(RASValue);
       
       EditText ras = (EditText)findViewById(R.id.editText1);
       ras.setText(RASValue);
       
      

       
       
        //view the passed data on a Textview box on the screen layout
        final TextView tv = (TextView)findViewById(R.id.tv);

        //view the text from the screen to check for accuracy...
        tv.setText("user id = " + userIdValue
        		+ "hdl value =  "  + hdlValue
        		+ "\n"
        		+  "ldl value =  "  + ldlValue 
        		+ "\n"
        		+  "age value =  "  + ageValue
        		+ "\n"
        		+  "diastolic value =  "  + diastolicValue
        		+ "\n"
        		+  "systolic value =  "  + systolicValue
        		+ "\n"
        		+  "Diabetic value =  "  + isDiabeticValue
        		+ "\n"
        		+  "gender value =  "  + isMaleValue
        		+ "\n"
        		+  "smoker value =  "  + isSmokerValue
        		+ "\n"
        		+  "is on meds value =  "  + isOnMedsValue);
 
//     insert data into the local sqlite database on the android phone...
        db = openOrCreateDatabase( "myAndroidPHR7.db",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	       try {
	           String sql =
	          "INSERT or replace INTO tbl_user_vitals ("
	          +"userid," +
	          "date_time," +
	          "systolic," +
	          "diastolic," +
	          "hdl," +
	          "ldl," +
	          "RAS," +
	          "isOnMeds," +
	          "isSmoker," +
	          "age," +
	          "isDiabetic," +
	          "isMale) VALUES("
	          + userId
	          + ","
	          + "'03/04/2005'" 
	          + "," 
	          + systolic 
	          + "," 
	          + diastolic 
	          + "," 
	          + hdl 
	          +","
	          + ldl 
	          + "," 
	          + RAS 
	          + "," 
	          + isOnMeds 
	          + "," 
	          + isSmoker 
	          + "," 
	          + ageValue 
	          + "," 
	          + isDiabetic 
	          + "," 
	          + isMale 
	          + ")";  	    	   
	    	   
    	   
	    	   //execute the query             
	         db.execSQL(sql);
	        
// after inserting the data into the database,  the next sql statement will view the data inserted	         
	          sql = "Select * from tbl_user_vitals";
	          Cursor cursor =  db.rawQuery(sql,null);
	         //move to the last record to see the most recent data row...
	          cursor.moveToLast();
	    	 //this is how you see the data from the cursor           
	            String alert =
	            		"sqlite saved data... \n userid = " + (cursor.getString(cursor.getColumnIndex("userid"))) + "\n" 
	            		+"date_time = " + (cursor.getString(cursor.getColumnIndex("date_time"))) + "\n" 
	            		+"hdl = "		+ (cursor.getString(cursor.getColumnIndex("hdl"))) + "\n" 
	            		+"ldl = " 		+ (cursor.getString(cursor.getColumnIndex("ldl"))) + "\n" 
	            		+"age = "  		+ (cursor.getString(cursor.getColumnIndex("age"))) + "\n"
	            		+"diastolic = " + (cursor.getString(cursor.getColumnIndex("diastolic"))) + "\n"
	            		+"systolic = "	+ (cursor.getString(cursor.getColumnIndex("systolic"))) + "\n"
	            		+"isOnMeds = "	+ (cursor.getString(cursor.getColumnIndex("isOnMeds"))) + "\n" 
	            		+"isSmoker = "	+ (cursor.getString(cursor.getColumnIndex("isSmoker"))) + "\n" 
	            		+"isDiabetic = "+ (cursor.getString(cursor.getColumnIndex("isDiabetic"))) + "\n" 
	            		+"isMale = "	+ (cursor.getString(cursor.getColumnIndex("isMale")));   		                     

	            //view the passed data
	    //        final TextView tv1 = (TextView)findViewById(R.id.tv1);
	      //      tv1.setText(alert);
	            } catch (Exception e) 
	            {Toast.makeText(this, "ERROR "+e.toString(), Toast.LENGTH_LONG).show();}//end catch         
        String RASValue = Integer.toString(RAS);
      
      //need to make an arraylist of named pairs with the screen data to save to the web page mysql server  
      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
	    nameValuePairs.add(new BasicNameValuePair("useridName",userIdValue)); 
	    nameValuePairs.add(new BasicNameValuePair("date_timeName","")); 
	    nameValuePairs.add(new BasicNameValuePair("hdlName",hdlValue)); 
	    nameValuePairs.add(new BasicNameValuePair("ldlName",ldlValue)); 
	    nameValuePairs.add(new BasicNameValuePair("RASName",RASValue));
	    nameValuePairs.add(new BasicNameValuePair("ageName",ageValue)); 
	    nameValuePairs.add(new BasicNameValuePair("diastolicName",diastolicValue)); 
	    nameValuePairs.add(new BasicNameValuePair("systolicName",systolicValue)); 
	    nameValuePairs.add(new BasicNameValuePair("isDiabeticName",isDiabeticValue)); 
	    nameValuePairs.add(new BasicNameValuePair("isMaleName",isMaleValue)); 
	    nameValuePairs.add(new BasicNameValuePair("isSmokerName",isSmokerValue)); 
	    nameValuePairs.add(new BasicNameValuePair("isOnMedsName",isOnMedsValue));

//      send the vitals to the web server php page that puts the pairs into the mysql database
        SendToCirasSite myPut = new SendToCirasSite(nameValuePairs);
//        
        String myAnswer = myPut.getResponse();
//        

        
        
    }


    
}
