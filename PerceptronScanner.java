package nai_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

public class PerceptronScanner {
	
	public static String FIRSTCLASS = null;
	public static String SECONDCLASS = null;


	public static List<NeuronVector> readData(String fileName) {
	File file = new File(fileName);
	List<NeuronVector> readedData = new ArrayList<>();
	    try{
	    	BufferedReader reader = Files.newBufferedReader(Paths.get(file.toURI()), UTF_8);
	    	String line = reader.readLine();
            do {
            	String points[] = line.split(",");
            	readedData.add(new NeuronVector());
            	for (int i = 0; i < points.length-1; ++i) {
            		try {
            				readedData.get(readedData.size()-1).getVector().add(Double.parseDouble(points[i])); 
            		} catch (NumberFormatException e) {
            		}
            	}
            		try{
            			readedData.get(readedData.size()-1).answer = (int)Double.parseDouble(points[points.length -1]);
            		} catch (NumberFormatException e) {
            			if(FIRSTCLASS == null) {
            				FIRSTCLASS = points[points.length -1];
            			}
            			if (points[points.length -1].equals(FIRSTCLASS)) {
            				FIRSTCLASS=points[points.length -1];
            				readedData.get(readedData.size()-1).answer = 1;
            				}
            			else {
            				SECONDCLASS=points[points.length -1];
            				readedData.get(readedData.size()-1).answer = 0;
            			}
            		}
            		
            		
                line = reader.readLine();
            }   while (line != null);
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Impossible to read data!");
            System.exit(-2);
        }
	    
	    return readedData;
}
public String getClasses() {
	return "THE FIRST CLASS :"+FIRSTCLASS +"THE SECOND CLASS :"+SECONDCLASS;
}


public void readConsolData() {}

public void writeState() {}

public void readState() {}

}


