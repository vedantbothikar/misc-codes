package pass2macrotrypackage;


import java.util.*;
import java.io.*;

class MNTnode{
	int sno;String macroname;int mdtindex;
	
	MNTnode(int sno,String macroname,int mdtindex){
		this.sno=sno;
		this.macroname=macroname;
		this.mdtindex=mdtindex;
	}
}

class MDTnode{
	int index;String instr;
	
	MDTnode(int index,String instr){
		this.index=index;
		this.instr=instr;
	}
}

class ALAnode{
	int index;String arg;
	
	ALAnode(int index,String arg){
		this.index=index;
		this.arg=arg;
	}
}

class IntCodenode{
	String text;
	
	IntCodenode(String text){
		this.text=text;
	}
}


public class Macro {

	public static void main(String args[]) throws IOException {
		
		int lc=1; int alaindex=1; int mntindex=1;
		int mflag=0; int defflag=0;
		List <MNTnode> LLMNT=new LinkedList<MNTnode>();
		List <MDTnode> LLMDT=new LinkedList<MDTnode>();
		List <ALAnode> LLALA=new LinkedList<ALAnode>();
		List <IntCodenode> LLIC=new LinkedList<IntCodenode>();
		
		FileReader fr=new FileReader("macinput.txt");
		BufferedReader br=new BufferedReader(fr);
		String s,subs;
		int ind;
		boolean flag=false;
		
		
		while((s=br.readLine())!=null) {
//			System.out.println("\nmflag= "+mflag);
//			2) Read the input.txt file line at a time into string s
//		 3) Tokenize the string s and capture the tokens in array of strings tokarray[i]	
//			System.out.println("\nTokarray is");
			StringTokenizer st=new StringTokenizer(s," ,",false);
			String tokarray[]=new String[st.countTokens()];
			for(int i=0;i<tokarray.length;i++) {
				tokarray[i]=st.nextToken();
//				System.out.println(tokarray[i]);
			}
			
			if(defflag==1) {
				for(int i=0;i<tokarray.length;i++) {
					if(tokarray[i].contains("&")) {
						if(tokarray[i].contains("=")) {
				            ind=tokarray[i].indexOf("=");			
							subs=tokarray[i].substring(0,ind);
							ALAnode ala1=new ALAnode(alaindex,subs);
							LLALA.add(ala1);
							alaindex++;
						}
						else {
						  ALAnode ala1=new ALAnode(alaindex,tokarray[i]);
						  LLALA.add(ala1);
						  alaindex++;
					     }
					}
					else {
						MNTnode mnt1=new MNTnode(mntindex,tokarray[i],lc);
						LLMNT.add(mnt1);
						mntindex++;
					}
					  
				}
				defflag=0;
			}
			
			if(mflag==1) {
				MDTnode mdt1=new MDTnode(lc,s);
				LLMDT.add(mdt1);
				lc++;
			}
			
			if((mflag==0)&& (!s.contains("MACRO"))) {
				IntCodenode ic1=new IntCodenode(s);
				LLIC.add(ic1);
				lc++;
			}
			
			if(tokarray[0].equalsIgnoreCase("MACRO")) {
				mflag = 1;
				defflag=1;
				
			}
			if(tokarray[0].equalsIgnoreCase("MEND")) {
				mflag = 0;
			}
	}

		
		System.out.println("OUTPUT OF PASS 1");	
		System.out.println("*****************");	
		
		System.out.println("\nMDT table");
		System.out.println("------------");
		FileWriter fw=new FileWriter("mdt.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		   
		for (MDTnode x : LLMDT) {
			s=x.index+"\t"+x.instr+"\n";
			System.out.print(s);
			bw.write(s);
		}
		bw.close();
		
		System.out.println("\nIntermediate code");
		System.out.println("--------------------");
		FileWriter fw2=new FileWriter("macroic.txt");
		BufferedWriter bw2=new BufferedWriter(fw2);
		for (IntCodenode x : LLIC) {
			s=x.text+"\n";
			System.out.print(s);
			bw2.write(s);
		}
		bw2.close();
		
		System.out.println("\nALA table");
		System.out.println("------------");
		FileWriter fw3=new FileWriter("ala.txt");
		BufferedWriter bw3=new BufferedWriter(fw3);
		   
		for (ALAnode y : LLALA) {
			s=y.index+"\t"+y.arg+"\n";
			System.out.print(s);
			bw3.write(s);
		}
		bw3.close();
		
		System.out.println("\nMNT table");
		System.out.println("------------");
		FileWriter fw4=new FileWriter("mnt.txt");
		BufferedWriter bw4=new BufferedWriter(fw4);
		   
		for (MNTnode z : LLMNT) {
			s=z.sno+"\t"+z.macroname+"\t"+z.mdtindex+"\n";
			System.out.print(s);
			bw4.write(s);
		}
		bw4.close();
		
		System.out.println("\nOUTPUT OF PASS 2");	
		System.out.println("*****************");
		System.out.println("\nExpanded code");
		System.out.println("--------------------");
		FileWriter fw5=new FileWriter("macroop.txt");
		BufferedWriter bw5=new BufferedWriter(fw5);
		
		int alactr=1;
		
		String dummy;
		for (IntCodenode x : LLIC) {
			flag=true;
			int j=0;
			for(MNTnode y : LLMNT) {
				if((x.text).contains(y.macroname)) {					//if the code contains call to macro
					StringTokenizer st1=new StringTokenizer(x.text," ,",false);
					String params[]=new String[st1.countTokens()];
					String aparams[]=new String[st1.countTokens()-1];
					
					for(int i=0;i<params.length;i++) {
							params[i]=st1.nextToken();
							if(!params[i].equals(y.macroname)) {
								aparams[j]=params[i];			//storing the actual arguments in aparams
								j++;
							}
					}
					
					
					
					for(MDTnode z: LLMDT) {
						if (z.index>=y.mdtindex){
							if(z.instr.contains("MEND")) flag=false;
								if (flag==true) {
									s=z.instr;
									for(ALAnode q : LLALA) {
										for(int r=1;r<=aparams.length;r++) {
											if(q.index==r) {
											   s=s.replace(q.arg,aparams[r-1]);	//replace the formal parameters
																//with actual parameters
										}
										
										}
									}
									if(!s.contains(y.macroname)) {
										s=s+"\n";
										System.out.print(s);	//only print those statements in MDT not containing macroname
										bw5.write(s);	
									}
									
								}
						}
					}
				}
				else {
					s=x.text+"\n";
					System.out.print(s);
					bw5.write(s);	
				}	
				
			}
			
			
		}
		bw5.close();
		


}
}
