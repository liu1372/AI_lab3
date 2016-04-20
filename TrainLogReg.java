import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TrainLogReg {
	
	//Multiplication of two vectors
	public static Float MVV(Float[] a, Float[] b){
		Float result = (float) 0;
		for(int i =0; i < a.length; i++){
			result += a[i] * b[i];
		}
		return result;
	}
	
	//Multiplication of vector and float
	public static Float[] MIV(Float a, Float[] b){
		Float[] result = new Float[b.length];
		for(int i =0; i < result.length; i++){
			result[i] = a * b[i];
		}
		return result;
	}
	//Minus of vector and vector
		public static Float[] MinusVV(Float[] a, Float[] b){
			Float[] result = new Float[b.length];
			for(int i =0; i < result.length; i++){
				result[i] = a[i] - b[i];
			}
			return result;
		}
	public static void main (String[] args) throws IOException {
    	//TrainLogReg trainingFeatureFile trainingLabelFile modelFile D Niter
        String trainingFeatureFile = args[0];
        String trainingLabelFile = args[1];
        String modelFile = args[2];
        int d = Integer.parseInt(args[3]);
        int n = Integer.parseInt(args[4]);


        
        Float c = 1E-6F;
    	Float t = (float) 0;
    	Float one = (float)1;
    	Float[] w = new Float[d];
    	for (int i =0; i < w.length;i++){
    		w[i] = (float) 0;
    	}
        //train file feature 
        File trainF = new File(trainingFeatureFile);
        BufferedReader trainFin = new BufferedReader(new FileReader(trainF));
        //read training file into array list 
        Float[][] trainFs = new Float[n][d];
        for (int i = 0; i < n;i ++){
        	String train = trainFin.readLine();

        	//convert one line string into a array
        	String[] ps = train.split(" ");
        	for (int j = 0; j < ps.length; j++)
        	{
        	    trainFs[i][j] = (float) Double.parseDouble(ps[j]);
        	}
        }
        File trainL = new File(trainingLabelFile);
        BufferedReader trainLin = new BufferedReader(new FileReader(trainL));
        //read training label into array
        Float[] trainLs = new Float[n];
        for (int i =0; i < n; i++){
        	String label = trainLin.readLine();

        	if(Integer.parseInt(label) == 0){
        		trainLs[i] = (float) -1;
        	}else{
        		trainLs[i] = (float) 1;
        	}
        }
    	//
        for (int iter =0; iter < n; iter++){
        		t = t + one;
        		Float exp = (float) Math.exp((-1)*trainLs[iter]*MVV(w,trainFs[iter]));
        		Float allInt = (-1)*trainLs[iter]*exp/(1+exp);
        		Float[] loss = MIV(allInt, trainFs[iter]);
        		w = MinusVV(w,MIV(c/t, loss)).clone();
        }
        //print to modelFile
        PrintWriter writer = new PrintWriter(modelFile);
        for(int i = 0; i < d;i++){
            writer.println(w[i]);
        }

        writer.close();     
        
        //close all file 
        trainFin.close();
        trainLin.close();
                
        
    }
	

}

