 /*
        final String[] positiveText = "I love sunny days".split("\\s");
        bayes.learn("Obama", Arrays.asList(positiveText));

        final String[] negativeText = "I hate rain".split("\\s");
        bayes.learn("Romney", Arrays.asList(negativeText));
        
        final String[] neutralText = "I hate rain".split("\\s");
        bayes.learn("Neutral", Arrays.asList(negativeText));

      
        final String[] unknownText1 = "today is a sunny day".split("\\s");
        final String[] unknownText2 = "there will be rain".split("\\s");

        System.out.println(bayes.classify(Arrays.asList(unknownText1)).getCategory());
        System.out.println(bayes.classify(Arrays.asList(unknownText2)).getCategory());

      
        ((BayesClassifier<String, String>) bayes).classifyDetailed(Arrays.asList(unknownText1));
         bayes.setMemoryCapacity(500); */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class RunnableExample {

    public static void main(String[] args) {

       
        Classifier<String, String> bayes = new BayesClassifier<String, String>();
        
        bayes = learn(args);
        classifyAndResult(bayes, args);        
       
    }
    
    public static void classifyAndResult(Classifier<String, String> bayes, String[] args) {
    	
    	BufferedReader sc;
    	BufferedWriter fw;
    	String read;
    	String classify;
    	int obamaCounter = 0;
    	int romneyCounter = 0;
    	int neutralCounter = 0;
    	
    	
    	try {
    		
			sc = new BufferedReader(new FileReader(args[3]));
			fw = new BufferedWriter(new FileWriter(args[4], true));
			
			while(sc.ready())
			{
				read = sc.readLine();
				String[] unknownText = read.split("\\s");
				classify = bayes.classify(Arrays.asList(unknownText)).getCategory();
				if(classify.equals("obama"))
				{
					obamaCounter++;
				}
				else if(classify.equals("romney"))
				{
					romneyCounter++;
				}
				else if(classify.equals("neutral"))
				{
					neutralCounter++;
				}
				else
				{
					System.out.println("Error in Classifying \n");
					System.exit(0);
				}
				
				fw.write(read + " : " + classify.toUpperCase());
				
			}
			
			
			
			
			sc.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	System.out.println(obamaCounter + " tweets supported Obama.\n");
		System.out.println(romneyCounter + " tweets supported Romney.\n");
		System.out.println(neutralCounter + " tweets were Neutral.\n");
		
		if(obamaCounter > romneyCounter)
		{
			System.out.println("More tweets support OBAMA \n");
			
		}
		else if (obamaCounter < romneyCounter)
		{
			System.out.println("More tweets support ROMNEY \n");
		}
		else
		{
			System.out.println("There are equal number of tweets support both candidates \n");
		}
		
    }
    
    public static Classifier<String, String> learn(String[] args) {
    	
    	Classifier<String, String> bayes = new BayesClassifier<String, String>();
    	
    	String temp;
    	BufferedReader obama;
    	BufferedReader romney;
    	BufferedReader neutral;
    	
		try
		{
			obama = new BufferedReader(new FileReader(args[0]));
			while(obama.ready())
			{
				temp=obama.readLine();
				String[] positiveText = temp.split("\\s");
		        bayes.learn("obama", Arrays.asList(positiveText));				

			}			
			obama.close();
			
			romney = new BufferedReader(new FileReader(args[1]));
			while(romney.ready())
			{
				temp=romney.readLine();
				String[] negativeText = temp.split("\\s");
		        bayes.learn("romney", Arrays.asList(negativeText));				

			}			
			romney.close();
			
			neutral = new BufferedReader(new FileReader(args[2]));
			while(neutral.ready())
			{
				temp=neutral.readLine();
				String[] neutralText = temp.split("\\s");
		        bayes.learn("neutral", Arrays.asList(neutralText));				

			}			
			neutral.close();
			
			
		}
		catch (Exception e){
			System.out.println(e.getMessage());
			System.exit(1);			
		}
		
		 return bayes;
    }
    
    
   

}