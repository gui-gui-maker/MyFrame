package util;

public class ReportNum {
	//以CO开头
	public static String fourNum(Float[] yearRNum,Float[] repSnNum,float fiveNum,Integer[] rsNumber,String[] arrFour1,String lastFive,int k,int yearRs){
		if(repSnNum[k]==null){
			repSnNum[k]=fiveNum;
			rsNumber[k]=1;//个数
			arrFour1[k]=arrFour1[k]+yearRs+"("+lastFive;
			yearRNum[k]=(float) yearRs;
		}else{
			if(yearRNum[k]==(float)yearRs){
				if(fiveNum==(repSnNum[k]+1)){
					repSnNum[k]=fiveNum;
					rsNumber[k]=rsNumber[k]+1;
					if(arrFour1[k].substring(arrFour1[k].length()-1, arrFour1[k].length()).equals(",")){
						arrFour1[k]=arrFour1[k].substring(0, arrFour1[k].length()-1)+"~"+lastFive;
					}else{
						if(arrFour1[k].substring((arrFour1[k].length()-6),(arrFour1[k].length()-5)).equals("~")){
							arrFour1[k]=arrFour1[k].substring(0, (arrFour1[k].length()-5))+lastFive;
						}else{
							arrFour1[k]=arrFour1[k]+"~"+lastFive;
						}
					}
				}else if(fiveNum!=(repSnNum[k]+1)){
					if(rsNumber[k]>1){
						arrFour1[k]=arrFour1[k]+","+lastFive+",";
						repSnNum[k]=fiveNum;
						rsNumber[k]=1;
					}else if(rsNumber[k]==1){
						if(arrFour1[k].substring(arrFour1[k].length()-1, arrFour1[k].length()).equals(",")){
							arrFour1[k]=arrFour1[k]+lastFive+",";
							repSnNum[k]=fiveNum;
							rsNumber[k]=1;
						}else{
							arrFour1[k]=arrFour1[k]+","+lastFive+",";
							repSnNum[k]=fiveNum;
							rsNumber[k]=1;
						}
					}
				}
			}else{
				if(arrFour1[k].substring(arrFour1[k].length()-1, arrFour1[k].length()).equals(",")){
					arrFour1[k]=arrFour1[k].substring(0, arrFour1[k].length()-1)+"),"+yearRs+"("+lastFive+",";
					repSnNum[k]=fiveNum;
					rsNumber[k]=1;
					yearRNum[k]=(float) yearRs;
				}else{
					arrFour1[k]=arrFour1[k].substring(0, arrFour1[k].length())+"),"+yearRs+"("+lastFive+",";
					repSnNum[k]=fiveNum;
					rsNumber[k]=1;
					yearRNum[k]=(float) yearRs;
				}
			}
			
		}
		return arrFour1[k];
	}
	
	//以R开头
	public static String threeNum(Float[] yearNum,Float[] rktNum,float fourNum,Integer[] rktNumber,String[] rSNFourS,String lastFour,int k,int years){
		if(rktNum[k]==null){
			rktNum[k]=fourNum;
			rktNumber[k]=1;//个数
			rSNFourS[k]=rSNFourS[k]+"-"+years+"-("+lastFour;
			yearNum[k]=(float) years;
		}else{
			if(yearNum[k]==(float)years){
				if(fourNum==(rktNum[k]+1)){
					rktNum[k]=fourNum;
					rktNumber[k]=rktNumber[k]+1;
					if(rSNFourS[k].substring(rSNFourS[k].length()-1, rSNFourS[k].length()).equals(",")){
						rSNFourS[k]=rSNFourS[k].substring(0, rSNFourS[k].length()-1)+"~"+lastFour;
					}else{
						if(rSNFourS[k].substring((rSNFourS[k].length()-4),(rSNFourS[k].length()-3)).equals("~")){
							rSNFourS[k]=rSNFourS[k].substring(0, (rSNFourS[k].length()-3))+lastFour;
						}else{
							rSNFourS[k]=rSNFourS[k]+"~"+lastFour;
						}
					}
				}else if(fourNum!=(rktNum[k]+1)){
					if(rktNumber[k]>1){
						rSNFourS[k]=rSNFourS[k]+","+lastFour+",";
						rktNum[k]=fourNum;
						rktNumber[k]=1;
					}else if(rktNumber[k]==1){
						if(rSNFourS[k].substring(rSNFourS[k].length()-1, rSNFourS[k].length()).equals(",")){
							rSNFourS[k]=rSNFourS[k]+lastFour+",";
							rktNum[k]=fourNum;
							rktNumber[k]=1;
						}else{
							rSNFourS[k]=rSNFourS[k]+","+lastFour+",";
							rktNum[k]=fourNum;
							rktNumber[k]=1;
						}
					}
				}
			}else{
				if(rSNFourS[k].substring(rSNFourS[k].length()-1, rSNFourS[k].length()).equals(",")){
					rSNFourS[k]=rSNFourS[k].substring(0, rSNFourS[k].length()-1)+"),"+years+"-("+lastFour+",";
					rktNum[k]=fourNum;
					rktNumber[k]=1;
					yearNum[k]=(float) years;
				}else{
					rSNFourS[k]=rSNFourS[k].substring(0, rSNFourS[k].length())+"),"+years+"-("+lastFour+",";
					rktNum[k]=fourNum;
					rktNumber[k]=1;
					yearNum[k]=(float) years;
				}
			}
			
		}
		return rSNFourS[k];
	}
	
	//以TSWJGLSC123-开头
	public static String tswjglscNum(Integer [] repSnNum,int fiveNum,Integer[] rsNumber,String[] arrFour1,String lastFive,int k){
		if(repSnNum[k]==null){
			repSnNum[k]=fiveNum;
			rsNumber[k]=1;//个数
			arrFour1[k]=arrFour1[k]+lastFive;
		}else{
			if(fiveNum==(repSnNum[k]+1)){
				repSnNum[k]=fiveNum;
				rsNumber[k]=rsNumber[k]+1;
				if(arrFour1[k].substring(arrFour1[k].length()-1, arrFour1[k].length()).equals(",")){
					arrFour1[k]=arrFour1[k].substring(0, arrFour1[k].length()-1)+"~"+lastFive;
				}else{
					if(arrFour1[k].substring((arrFour1[k].length()-6),(arrFour1[k].length()-5)).equals("~")){
						arrFour1[k]=arrFour1[k].substring(0, (arrFour1[k].length()-5))+lastFive;
					}else{
						arrFour1[k]=arrFour1[k]+"~"+lastFive;
					}
				}
			}else if(fiveNum!=(repSnNum[k]+1)){
				if(rsNumber[k]>1){
					arrFour1[k]=arrFour1[k]+","+lastFive+",";
					repSnNum[k]=fiveNum;
					rsNumber[k]=1;
					
				}else if(rsNumber[k]==1){
					if(arrFour1[k].substring(arrFour1[k].length()-1, arrFour1[k].length()).equals(",")){
						arrFour1[k]=arrFour1[k]+lastFive+",";
						repSnNum[k]=fiveNum;
						rsNumber[k]=1;
					}else{
						arrFour1[k]=arrFour1[k]+","+lastFive+",";
						repSnNum[k]=fiveNum;
						rsNumber[k]=1;
					}
					
				}
			}
		}
		return arrFour1[k];
	}
	
	//以W开头
	public static String wOne(Float[] yearNum,Float[] rktNum,float fourNum,Integer[] rktNumber,String[] rSNFourS,String lastFour,int k,int years,String between){
		if(rktNum[k]==null){
			rktNum[k]=fourNum;
			rktNumber[k]=1;//个数
			rSNFourS[k]=rSNFourS[k]+years+"-("+between+lastFour;
			yearNum[k]=(float) years;
		}else{
			if(yearNum[k]==(float)years){
				if(fourNum==(rktNum[k]+1)){
					rktNum[k]=fourNum;
					rktNumber[k]=rktNumber[k]+1;
					if(rSNFourS[k].substring(rSNFourS[k].length()-1, rSNFourS[k].length()).equals(",")){
						rSNFourS[k]=rSNFourS[k].substring(0, rSNFourS[k].length()-1)+"~"+lastFour;
					}else{
						if(rSNFourS[k].substring((rSNFourS[k].length()-6),(rSNFourS[k].length()-5)).equals("~")){
							rSNFourS[k]=rSNFourS[k].substring(0, (rSNFourS[k].length()-5))+lastFour;
						}else{
							rSNFourS[k]=rSNFourS[k]+"~"+lastFour;
						}
					}
				}else if(fourNum!=(rktNum[k]+1)){
					if(rktNumber[k]>1){
						rSNFourS[k]=rSNFourS[k]+","+between+lastFour+",";
						rktNum[k]=fourNum;
						rktNumber[k]=1;
					}else if(rktNumber[k]==1){
						if(rSNFourS[k].substring(rSNFourS[k].length()-1, rSNFourS[k].length()).equals(",")){
							rSNFourS[k]=rSNFourS[k]+between+lastFour+",";
							rktNum[k]=fourNum;
							rktNumber[k]=1;
						}else{
							rSNFourS[k]=rSNFourS[k]+","+between+lastFour+",";
							rktNum[k]=fourNum;
							rktNumber[k]=1;
						}
					}
				}
			}else{
				if(rSNFourS[k].substring(rSNFourS[k].length()-1, rSNFourS[k].length()).equals(",")){
					rSNFourS[k]=rSNFourS[k].substring(0, rSNFourS[k].length()-1)+"),"+years+"-("+between+lastFour+",";
					rktNum[k]=fourNum;
					rktNumber[k]=1;
					yearNum[k]=(float) years;
				}else{
					rSNFourS[k]=rSNFourS[k].substring(0, rSNFourS[k].length())+"),"+years+"-("+between+lastFour+",";
					rktNum[k]=fourNum;
					rktNumber[k]=1;
					yearNum[k]=(float) years;
				}
			}
			
		}
		return rSNFourS[k];
	}
}
