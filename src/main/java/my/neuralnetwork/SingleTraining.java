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
public class SingleTraining
{
    private final List<Double> inputs;
    private final List<Double> outputs;
    
    public SingleTraining(List<Double> inputs, List<Double> outputs)
    {
        this.inputs = inputs;
        this.outputs = outputs;
    }
    
    public List<Double> getInputs()
    {
        return inputs;
    }
    
    public List<Double> getOutputs()
    {
        return outputs;
    }
}
