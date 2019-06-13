package nai_2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	private JFrame frmNai;
	private List<Perceptron> perceptrons;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmNai.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		perceptrons = new ArrayList<Perceptron>();
		runGui();
	}

	
	private void trainDataAndTest(String file, int numberOfInterations, String testFile) {
		
		double alpha = Double.parseDouble(JOptionPane.showInputDialog("Learning rate:"));
		List<NeuronVector> TrainingVectors = PerceptronScanner.readData(file);
		TrainingVectors.forEach(System.out::println);
		perceptrons.add(new Perceptron(alpha));
		perceptrons.get(perceptrons.size()-1).setModel(TrainingVectors.get(0));
		for (int i=0; i < numberOfInterations; i++) {
			perceptrons.get(perceptrons.size()-1).learn(TrainingVectors);
			perceptrons.get(perceptrons.size()-1).train(TrainingVectors);
		}

		System.out.println("------------------------------------------------------------------------------------");
		
		List<NeuronVector> testVectors = PerceptronScanner.readData(testFile);
		testVectors.forEach(System.out::println);
		perceptrons.get(perceptrons.size()-1).test(testVectors,true);
		perceptrons.get(perceptrons.size()-1).train(testVectors);

		System.out.println("------------------------------------------------------------------------------------");
	}
	
	private void runGui() {
		trainDataAndTest("D:\\Рабочий стол\\training.txt", 1000, "D:\\Рабочий стол\\testIris.txt");
		trainDataAndTest("D:\\Рабочий стол\\train.txt", 1000, "D:\\Рабочий стол\\test.txt");
		
		frmNai = new JFrame();
		frmNai.setTitle("NAI 2 - S17135");
		frmNai.setBounds(100, 100, 534, 304);
		frmNai.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmNai.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(0, 0, 520, 217);
		frmNai.getContentPane().add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JTextArea taResult = new JTextArea();
		panel.add(taResult);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 220, 520, 50);
		frmNai.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JTextField tfInput = new JTextField();
		tfInput.setBounds(9, 7, 405, 33);
		panel_1.add(tfInput);
		
		JButton btnNewButton = new JButton("Check Class");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] input = tfInput.getText().split("\\s+");
				for(int i=0; i<perceptrons.size();i++){
					if(input.length==perceptrons.get(i).getWeightLength()) {
						List<Double> vector = new ArrayList<Double>();
						for(int j = 0; j<input.length; j++) {
							vector.add(Double.parseDouble(input[j]));
						}
						List<NeuronVector> neuronVector = new ArrayList<NeuronVector>();
						neuronVector.add(new NeuronVector(vector));
						perceptrons.get(i).test(neuronVector,false);
					}
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 8));
		btnNewButton.setBounds(425, 7, 85, 33);
		panel_1.add(btnNewButton);
	}	
}
