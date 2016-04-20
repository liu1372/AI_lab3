import java.io.BufferedReader;	
import java.io.File;	
import java.io.FileReader;	
import java.io.IOException;

	
public class Accurary {
		
	public static void main (String[] args) throws IOException {
			//Accuracy predLabelFile trueLabelFile
			
		String predLabelFile = args[0];	        
		String trueLabelFile = args[1];		
		double all =0;		
		double correct =0;		
		String plabel;		
		String tlabel;		
		File pL = new File(predLabelFile);	    
		BufferedReader pLin = new BufferedReader(new FileReader(pL));	    
		File tL = new File(trueLabelFile);	    
		BufferedReader tLin = new BufferedReader(new FileReader(tL));	    
		//read predicted label into array	    
		plabel = pLin.readLine();	    
		tlabel = tLin.readLine();	    
		while(plabel != null){	    
			if(plabel.equals(tlabel)){	        
				all = all + 1.0;	        	
				correct = correct +1.0;	        	
				plabel = pLin.readLine();	            
				tlabel = tLin.readLine();	        	
			}else{	        
				all = all + 1.0;	            
				plabel = pLin.readLine();	            
				tlabel = tLin.readLine();	        	
			}	        		        
		}    
		double acc = correct * 100 / all;	    
		System.out.println("The accuracy is: " + acc +"%");	        	    
		pLin.close();	    
		tLin.close();		
	}	
}
