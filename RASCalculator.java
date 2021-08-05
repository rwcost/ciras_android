package com.example.sensor;

/*
 * This class receives the vital information from patient and calculates the risk of heart attack i the next ten years
 */  Returns the RAS score


import java.lang.reflect.Array;
import java.util.List;

import org.apache.http.NameValuePair;

public class RASCalculator {

	private int isMale;
	private int totalHdlAndLdl;
	private int RAS;

	public RASCalculator() {
		// TODO Auto-generated constructor stub
		
	
		
		
	}

	public int calculateRAS(int isMale,int age, int hdl, int ldl, int isSmoker,
			int systolic, int diastolic, int isOnMeds) {
		
		totalHdlAndLdl = hdl+ldl;
		int [] initialRiskMaleArray	=   {-9,-4,0,3,6,8,10,11,12,13};
		int [] initialRiskFemaleArray = {-7,-3,0,3,6,8,10,12,14,16};
		int [][]totalCholesterolMaleArray=    {{0,0,0,0,0},{4,3,2,1,0},{7,5,3,1,0},{9,6,4,2,1},{11,8,5,3,1}};
		int [][]totalcholesterolFemaleArray = {{0,0,0,0,0},{4,3,2,1,1},{8,6,4,2,1},{11,8,5,3,2},{13,10,7,4,2}};
		int [][]smokerMaleArray = {{0,0,0,0,0},{8,5,3,1,1}};
		int [][]smokerFemaleArray = {{0,0,0,0,0},{9,7,4,2,1}};
		int [] hdlMaleArray = {-1,0,1,2};
		int [] hdlFemaleArray = {-1,0,1,2};
		int [][] systolicMaleArray = {{0,0},{0,1},{1,2},{1,2},{2,3}};
		int [][] systolicFemaleArray = {{0,0},{1,3},{2,4},{3,5},{4,6}};
		int [] riskMaleArray =   {1,1,1,1,1,2,2,3,4,5,6,8,10,12,16,20,25};
		int [] riskFemaleArray = {1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,3,4,5,6,8,11,14,17,22,27};
		int points = 0;
//*************   initial age risk for male and female  ****************************		
		if (age>=20 && age<=34){
			if(isMale == 1){
				points += -9;
			}else{
				points +=-7;
			}
		}
		if (age>=35 && age<=39){
			if(isMale == 1){
				points += -4;
			}else{
				points +=-3;
			}
		}
		if (age>=40 && age<=44){
			if(isMale == 1){
				points += 0;
			}else{
				points +=0;
			}
		}
		if (age>=45 && age<=49){
			if(isMale == 1){
				points += 3;
			}else{
				points +=3;
			}
		}
		if (age>=50 && age<=54){
			if(isMale == 1){
				points += 6;
			}else{
				points +=6;
			}
		}
		if (age>=55 && age<=59){
			if(isMale == 1){
				points += 8;
			}else{
				points +=8;
			}
		}
		if (age>=60 && age<=64){
			if(isMale == 1){
				points += 10;
			}else{
				points +=10;
			}
		}
		if (age>=65 && age<=70){
			if(isMale == 1){
				points += 11;
			}else{
				points +=12;
			}
		}
		if (age>=70 && age<=74){
			if(isMale == 1){
				points += 12;
			}else{
				points +=14;
			}
		}
		if (age>=75 && age<=79){
			if(isMale == 1){
				points += 13;
			}else{
				points +=16;
			}
		}
//*********************** hdl and ldl points ****************************************************
		if (totalHdlAndLdl<160){
			points += 0;
		}
		if(totalHdlAndLdl >=160 && totalHdlAndLdl <=199){
			if (age>=20 && age<=39){
				if(isMale == 1){
					points += 4;
				}else{
					points +=4;
				}
			}
			if (age>=40 && age<=49){
				if(isMale == 1){
					points += 3;
				}else{
					points += 3;
				}
			}
			if (age>=50 && age<=59){
				if(isMale == 1){
					points += 2;
				}else{
					points += 2;
				}
			}
			if (age>=60 && age<=69){
				if(isMale == 1){
					points += 1;
				}else{
					points += 1;
				}
			}
			if (age>=70 && age<=79){
				if(isMale == 1){
					points += 0;
				}else{
					points += 1;
				}
			}
		}//
		if(totalHdlAndLdl >=200 && totalHdlAndLdl <=239){
			if (age>=20 && age<=39){
				if(isMale == 1){
					points += 7;
				}else{
					points +=8;
				}
			}
			if (age>=40 && age<=49){
				if(isMale == 1){
					points += 5;
				}else{
					points +=6;
				}
			}
			if (age>=50 && age<=59){
				if(isMale == 1){
					points += 3;
				}else{
					points +=4;
				}
			}
			if (age>=60 && age<=69){
				if(isMale == 1){
					points += 1;
				}else{
					points +=2;
				}
			}
			if (age>=70 && age<=79){
				if(isMale == 1){
					points += 0;
				}else{
					points +=1;
				}
			}
		}//
		if(totalHdlAndLdl >=240 && totalHdlAndLdl <=279){
			if (age>=20 && age<=39){
				if(isMale == 1){
					points += 9;
				}else{
					points +=11;
				}
			}
			if (age>=40 && age<=49){
				if(isMale == 1){
					points += 6;
				}else{
					points +=8;
				}
			}
			if (age>=50 && age<=59){
				if(isMale == 1){
					points += 4;
				}else{
					points +=5;
				}
			}
			if (age>=60 && age<=69){
				if(isMale == 1){
					points += 2;
				}else{
					points +=3;
				}
			}
			if (age>=70 && age<=79){
				if(isMale == 1){
					points += 1;
				}else{
					points +=2;
				}
			}
		}//
		if(totalHdlAndLdl >=280){
			if (age>=20 && age<=39){
				if(isMale == 1){
					points += 11;
				}else{
					points +=13;
				}
			}
			if (age>=40 && age<=49){
				if(isMale == 1){
					points += 8;
				}else{
					points +=10;
				}
			}
			if (age>=50 && age<=59){
				if(isMale == 1){
					points += 5;
				}else{
					points +=7;
				}
			}
			if (age>=60 && age<=69){
				if(isMale == 1){
					points += 3;
				}else{
					points +=4;
				}
			}
			if (age>=70 && age<=79){
				if(isMale == 1){
					points +=1;
				}else{
					points +=2;
				}
			}
		}//
//********************* smoker points  ******************************		
		if (age>=20 && age<=39){
			if(isMale==1){
				if(isSmoker == 1){
					points += 8;
				}else{//non smoker
					points +=0;
				}
			}else{//female
				if(isSmoker == 1){
					points += 9;
					}else{//non smoker
					points +=0;
					}
				}
		}
		if (age>=40 && age<=49){
			if(isMale==1){
				if(isSmoker == 1){
					points += 5;
				}else{//non smoker
					points +=0;
				}
			}else{//female
				if(isSmoker == 1){
					points += 7;
					}else{//non smoker
					points +=0;
					}
				}
		}
		if (age>=50 && age<=59){
			if(isMale==1){
				if(isSmoker == 1){
					points += 3;
				}else{//non smoker
					points +=0;
				}
			}else{//female
				if(isSmoker == 1){
					points += 4;
					}else{//non smoker
					points +=0;
					}
				}
		}
		if (age>=60 && age<=69){
			if(isMale==1){
				if(isSmoker == 1){
					points += 1;
				}else{//non smoker
					points +=0;
				}
			}else{//female
				if(isSmoker == 1){
					points += 2;
					}else{
					points +=0;
					}
				}
		}
		if (age>=70 && age<=79){
			if(isMale==1){
				if(isSmoker == 1){
					points += 1;
				}else{
					points +=0;
				}
			}else{//female
				if(isSmoker == 1){
					points += 1;
					}else{
					points +=0;
					}
				}
		}
//********************   hdl points *********************		
		if(hdl>=60){
			if(isMale==1){
				points += -1;
			}else{//female
				points += -1;
			}
		}
		if(hdl>=50 && hdl<=59){
			if(isMale==1){
				points += 0;
			}else{//female
				points += 0;
			}
		}
		if(hdl>=40 && hdl<=49){
			if(isMale==1){
				points += 1;
			}else{//female
				points += 1;
			}
		}
		if(hdl<40){
			if(isMale==1){
				points += 2;
			}else{//female
				points += 2;
			}
		}

// ****************** if on systolic and on treatment points *************************		
		if(systolic<120){
			if(isOnMeds==1){
				if(isMale==1){
					points +=0;
				}else{//female
					points +=0;
				}					
				}else{//not on meds
					if(isMale==1){
						points +=0;
					}else{//female
						points +=0;
					}					
			}
		}
		if(systolic>=120 && systolic<=129 ){
			if(isOnMeds==1){
				if(isMale==1){
					points +=1;
				}else{//female
					points +=3;
				}					
				}else{//not on meds
					if(isMale==1){
						points +=0;
					}else{//female
						points +=1;
					}					
			}
		}
		if(systolic>=130 && systolic<=139 ){
			if(isOnMeds==1){
				if(isMale==1){
					points +=2;
				}else{//female
					points +=4;
				}					
				}else{//not on meds
					if(isMale==1){
						points +=1;
					}else{//female
						points +=2;
					}					
			}
		}
		if(systolic>=140 && systolic<=159 ){
			if(isOnMeds==1){
				if(isMale==1){
					points +=2;
				}else{//female
					points +=5;
				}					
				}else{//not on meds
					if(isMale==1){
						points +=1;
					}else{//female
						points +=3;
					}					
			}
		}
		if(systolic>=160){
			if(isOnMeds==1){
				if(isMale==1){
					points +=3;
				}else{//female
					points +=6;
				}					
				}else{//not on meds
					if(isMale==1){
						points +=2;
					}else{//female
						points +=4;
					}					
			}
		}
//**********************  use point total to calculate risk female *********************		
	//set male points	
	if(isMale==1){	
		if(points<0){
			RAS = 1;
		}else{
		if(points>=17){
			RAS = 30;
		}else{
			RAS = riskMaleArray[points];
		}
		}
	}
	
	//set female points
	if(isMale==0){	
		if(points<9){
			RAS = 1;
		}else{
		if(points>=25){
			RAS = 30;
		}else{
			RAS = riskFemaleArray[points];
		}
		}
	}
	
		
		//finally the risk assessment score
		return RAS;
	}

}
