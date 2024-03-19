/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

/**
 *
 * @author Kay Jay O'Nail
 */
public class TrainingSet
{
    private final List<SingleTraining> trainings;
    private final int inputSize;
    private final int outputSize;
    private int counter;
    
    public TrainingSet(int inputSize, int outputSize) throws Exception
    {
        if (inputSize > 0 && outputSize > 0)
        {
            this.inputSize = inputSize;
            this.outputSize = outputSize;
            trainings = new ArrayList<>();
            counter = 0;
        }
        else
        {
            throw new Exception("TrainingSet.<init> : non-positive size");
        }
    }
    
    public void add(List<Double> inputs, List<Double> outputs) throws Exception
    {
        if (inputs.size() == inputSize && outputs.size() == outputSize)
        {
            SingleTraining training = new SingleTraining(inputs, outputs);
            trainings.add(training);
        }
        else
        {
            throw new Exception("TrainingSet.add : incompatible sizes");
        }
    }
    
    public int getInputSize()
    {
        return inputSize;
    }
    
    public int getOutputSize()
    {
        return outputSize;
    }
    
    public boolean epochHasPassed()
    {
        return counter == trainings.size();
    }
    
    public SingleTraining getNext()
    {
        if (counter == trainings.size())
        {
            counter = 0;
        }
        return trainings.get(counter++);
    }
}
