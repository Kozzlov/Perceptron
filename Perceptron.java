package nai_2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Perceptron {
	
	private List <Double> weight;
	private double theta;
	private double alpha;
	public Scanner scanner = new Scanner(System.in);
	public List<String> classes;
	
	public Perceptron() {
		this.weight= new ArrayList<>();
	}
	
	public Perceptron(double alpha) {
		this.weight = new ArrayList<>();
		this.alpha = alpha;
	}
	
	public void setModel(NeuronVector neuronVector){
		int numberOfWeights = neuronVector.getLength();
		while(numberOfWeights!=0) {
			this.weight.add(0.1);
			numberOfWeights--;
		}
		this.theta=0.1;
	}
	public int getWeightLength() {
		return this.weight.size();
	}


	
	public void learn(List<NeuronVector> trainVectors){
		
		double total=0;
		double trueOnes=0;
		double incorrectTrueAns=0;
		double correctTrueAns=0;

		for(NeuronVector inputVector: trainVectors) {
		double sum=0;
		for(int i=0; i<this.weight.size();i++) {
			sum+= inputVector.getVector().get(i)*this.weight.get(i);
		}
		if (sum >= this.theta) inputVector.yourAnswer = 1;
		else inputVector.yourAnswer = 0;
        if(inputVector.answer==1) {
        	if(inputVector.yourAnswer==1) {
        		correctTrueAns++;
        	}
        	else
        	incorrectTrueAns++;
        }

		if (inputVector.yourAnswer==inputVector.answer) {
			trueOnes++;
		}
        total++;
		}
	}
	
	public void train(List<NeuronVector> vectors) {
		if(vectors.get(0).getLength()==4) {
		System.out.println("First Class : "+ 1 +" "+ PerceptronScanner.FIRSTCLASS.toString()+" SecondClass : "+ 0 +" "+PerceptronScanner.SECONDCLASS.toString() );
		PerceptronScanner.FIRSTCLASS.toString();
		PerceptronScanner.SECONDCLASS.toString();
		}
		for (NeuronVector inputVector: vectors) {
			for(int i=0; i<this.weight.size();i++) {
				double newWeight = this.weight.get(i) + (inputVector.answer - inputVector.yourAnswer) *this.alpha * inputVector.getVector().get(i);
				this.weight.set(i, newWeight);
			}
			this.theta= this.theta + this.alpha * (inputVector.answer - inputVector.yourAnswer) * -1;
		}
	}
	
	
	public void test(List<NeuronVector> vectors, boolean checkError) {
		double total=0;
		double trueOnes=0;
		double incorrectTrueAns=0;
		double correctTrueAns=0;
		double correctOutputCounter = 0;
		for(int i=0; i<vectors.size();i++){
			List<Double> coordinates = vectors.get(i).getVector();
			double possibleSum = 0;
			for(int j = 0; j<coordinates.size(); j++) {
					possibleSum += coordinates.get(j)*this.weight.get(j);
				}
				int gClass = (possibleSum >= this.theta) ? 1 : 0;
				System.out.println(vectors.get(i).getVector() + " Answer: " + gClass);//+this.classes.get(gClass));//vectors.get(i).classes.get(gClass));
				if(checkError)
					if (vectors.get(i).answer == gClass) ++correctOutputCounter;
				else incorrectTrueAns++;
				total++;
			}
			if(checkError) {
				System.out.println("RECALL : " + correctOutputCounter/(correctOutputCounter+incorrectTrueAns) + " weightVector: "+ this.weight+ ", THETA modification : "+ theta);
				System.out.println("ACCURACY : " + correctOutputCounter/vectors.size() + " weightVector: "+ this.weight);

				//BigDecimal  falsePositive = new BigDecimal( (vectors.size() + correctOutputCounter));
		        //BigDecimal truePositive =  new BigDecimal(vectors.size());
				//System.out.println("error " + truePositive.divide(falsePositive, 2, RoundingMode.HALF_UP)+" %");
				System.out.println("number of correct outputs " + correctOutputCounter + " weightVector: "+ this.weight);

			}
	}
}



