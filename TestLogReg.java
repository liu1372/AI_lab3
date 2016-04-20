import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TestLogReg {
	//Multiplication of two vectors
	public static Float MVV(Float[] a, Float[] b){
		Float result = (float) 0;
		for(int i =0; i < b.length; i++){
			result += a[i] * b[i];
		}
		return result;
	}
	public static void main (String[] args) throws IOException {
		//TestLogReg modelFile testFeatureFile predLabelFile D
		String predLabelFile = args[2];
        String testFeatureFile = args[1];
        String modelFile = args[0];
		int d = Integer.parseInt(args[3]);
        int n = 2115;
		File testF = new File(testFeatureFile);
        BufferedReader testFin = new BufferedReader(new FileReader(testF));
        //read test file into array list 
        Float[][] testFs = new Float[n][d];
        for (int i = 0; i < n;i ++){
        	String test = testFin.readLine();

        	//convert one line string into a array
        	String[] ps = test.split(" ");
        	for (int j = 0; j < ps.length; j++)
        	{
        	    testFs[i][j] = (float) Double.parseDouble(ps[j]);
        	}
        }
        //
        /*for (int i =0; i < 10; i++){
			System.out.println("pppppppppppppppppppppppppppppppppppppppppppppppppppppppppppp");

        	for (int j =0; j<785;j++){
        		if(j%28==0){
        
        			System.out.println("");
        		}
        		
        		System.out.print(testFs[i][j]);
        	}
        	
        	
        }*/
        
        
		File model = new File(modelFile);
	    BufferedReader modelin = new BufferedReader(new FileReader(model));
	        //read model file into array
	    Float[] w = new Float[d];
	    for (int i =0; i < d; i++){
	        	
	    	String label = modelin.readLine();
	    	w[i] = (float) Double.parseDouble(label);
	        
	    }
	    	//
	        

	        //print to modelFile
	       
	    PrintWriter writer = new PrintWriter(predLabelFile);
	    for(int i = 0; i < n;i++){
	        float zero = (float)0;
	        float testResult = MVV(w, testFs[i]);
	        if(testResult > zero){
	            writer.println("1");
	        }else{
	            writer.println("0");

	        }
	        
	    }

	        
	    writer.close(); 
	    testFin.close();
	    modelin.close();
	}

}
