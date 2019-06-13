package nai_2;

import java.util.ArrayList;
import java.util.List;

public class NeuronVector {
	
	private List<Double> coordinates = new ArrayList<>();
	int answer;
	int yourAnswer;
	
	public NeuronVector(List<Double> coordinate, int answer) {
		this.coordinates=coordinate;
		this.answer=answer;
	}
	
	public NeuronVector(List<Double> coordinates) {
		this.coordinates=coordinates;
	}
	public NeuronVector() {
	}

	public int getLength() {
		return this.coordinates.size();
	}

	public List<Double> getVector(){
		return this.coordinates;
	}

	@Override
	public String toString() {
			return this.coordinates + " " + this.answer;
	}
}
