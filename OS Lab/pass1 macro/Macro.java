package pass1macrotrypackage;


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
		
		
		while((s=br.readLine())!=null) {
//			System.out.println("\nmflag= "+mflag);
//			2) Read the input.txt file line at a time into string s
//		 3) Tokenize the string s and capture the tokens in array of strings tokarray[i]	
//			System.out.println("\nTokarray is");
			StringTokenizer st=new StringTokenizer(s," ,",false); //The string tokenizer class allows an application to break astring into tokens
		
			String tokarray[]=new String[st.countTokens()];
			for(int i=0;i<tokarray.length;i++) {
				tokarray[i]=st.nextToken();
//				System.out.println(tokarray[i]);
			}
			
			if(defflag==1) {		//inside macro definition
				for(int i=0;i<tokarray.length;i++) {
					if(tokarray[i].contains("&")) {
						if(tokarray[i].contains("=")) {		//default argument
				            ind=tokarray[i].indexOf("=");			
							subs=tokarray[i].substring(0,ind);  // characters till '=' excluding it
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
						MNTnode mnt1=new MNTnode(mntindex,tokarray[i],lc); // macro's name and index no
						LLMNT.add(mnt1);
						mntindex++;
					}
					  
				}
				defflag=0;
			}
			
			if(mflag==1) {				//MDT entry made
				MDTnode mdt1=new MDTnode(lc,s);
				LLMDT.add(mdt1);    // adding location counter and string 
				lc++;
			}
			
			if((mflag==0)&& (!s.contains("MACRO"))) {  // statements after mend
				IntCodenode ic1=new IntCodenode(s);	//store normal code without macros
				
				LLIC.add(ic1);
				lc++;
			}
			
			if(tokarray[0].equalsIgnoreCase("MACRO")) {	//Macro definition found
				mflag = 1;
				
				defflag=1;   // for going inside that macro's loop again
				
			}
			if(tokarray[0].equalsIgnoreCase("MEND")) {
				mflag = 0;				//Macro definition ends, mflag becomes 0
			}
	}

		
		System.out.println("OUTPUT OF PASS 1");	
		System.out.println("*******");	
		
		System.out.println("\nMDT table");  // macro definition table (names to mend gets stored in the mdt)
		System.out.println("------------");
		FileWriter fw=new FileWriter("mdt.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		   
		for (MDTnode x : LLMDT) {
			
			s=x.index+"\t"+x.instr+"\n";  // x.instr
			//String instru;
			//instru=x.instr;
			
			//String[] newStr = instru.split("\\s+");
			
		
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
		
		System.out.println("\nMNT table");    // mnt = macro name table
		System.out.println("------------");
		FileWriter fw4=new FileWriter("mnt.txt");
		BufferedWriter bw4=new BufferedWriter(fw4);
		   
		for (MNTnode z : LLMNT) {
			s=z.sno+"\t"+z.macroname+"\t"+z.mdtindex+"\n";   // z.mdtindex is the index in the mdt table. It starts to read from that index in mdt
			System.out.print(s);
			bw4.write(s);
		}
		bw4.close();
		


}
}
