/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

/**
 * Layer of the network. Holds neurons.
 * 
 * @author Kay Jay O'Nail
 */
public class Layer
{
    /**
     * The list of neurons.
     */
    private final List<Neuron> neurons;
    
    /**
     * Constructor. Creates the list of <code>size</code> neurons <em>plus</em>
     * one constant neuron; <code>size</code> shall be positive.
     * 
     * @param size number of computing neurons (without the constant neuron)
     * @throws Exception if <code>size</code> is non-positive
     */
    public Layer(int size) throws Exception
    {
        if (size > 0)
        {
            neurons = new ArrayList<>(size + 1);
            for (int i = 0; i <= size; ++i)
            {
                neurons.add(new Neuron());
            }
        }
        else
        {
            throw new Exception("Layer.<init> : non-positive size of the layer");
        }
    }
    
    /**
     * Size accessor.
     * 
     * @return the number of <em>all</em> neurons of the layer (including
     *  the constant neuron)
     */
    public int size()
    {
        return neurons.size();
    }
    
    /**
     * Accessor for particular neuron of the layer.
     * 
     * @param i index of the neuron
     * @return the <code>i</code><sup>th</sup> neuron in the layer
     */
    private Neuron get(int i)
    {
        assert (i >= 0 && i < neurons.size());
        return neurons.get(i);
    }
    
    /**
     * Joins all neurons of the <code>previous</code> layer with the computing
     * neurons of the <code>next</code> layer (i.e. excluding the constant
     * neuron under index 0).
     * 
     * @param previous
     * @param next
     */
    public static void joinLayers(Layer previous, Layer next)
    {
        for (int i = 0; i < previous.size(); ++i)
        {
            for (int j = 1; j < next.size(); ++j)
            {
                Neuron tail = previous.get(i);
                Neuron head = next.get(j);
                Connection.joinNeurons(tail, head);
            }
        }
    }
    
    /**
     * Assigns the values of <code>input</code> to the computing neurons in this
     * layer, i.e. skipping the 0<sup>th</sup> neuron.
     * 
     * The <code>i</code><sup>th</sup> position in <code>input</code> is assigned
     * to the <code>(i + 1)</code><sup>th</sup> neuron.
     * 
     * @param input
     * @throws Exception 
     */
    public void assign(List<Double> input) throws Exception
    {
        if (input != null && input.size() == neurons.size() - 1)
        {
            for (int i = 0; i < input.size(); ++i)
            {
                Neuron neuron = neurons.get(i + 1);
                neuron.value = input.get(i);
            }
        }
        else
        {
            throw new Exception("Layer.assign : input is null or it has wrong size");
        }
    }
    
    /**
     * Makes the <u>computing</u> neurons compute their values.
     */
    public void computeValues()
    {
        for (int i = 1; i < neurons.size(); ++i)
        {
            neurons.get(i).computeValue();
        }
    }
    
    /**
     * Creates a list containing the values of the computing neurons (skipping
     * the constant neuron).
     * 
     * @return list of values of the computing neurons
     */
    public List<Double> export()
    {
        List<Double> result = new ArrayList<>(neurons.size() - 1);
        for (int i = 1; i < neurons.size(); ++i)
        {
            result.add(neurons.get(i).value);
        }
        return result;
    }
    
    /**
     * Calculates the root mean square error between the vector of computing
     * neurons' values and the vector of the desired outputs.
     * 
     * @param desiredOutputs
     * @return
     * @throws Exception
     */
    public double calculateError(List<Double> desiredOutputs) throws Exception
    {
        if (desiredOutputs != null && neurons.size() - 1 == desiredOutputs.size())
        {
            double aggregateError = 0.0;
            for (int i = 0; i < desiredOutputs.size(); ++i)
            {
                double partialError = neurons.get(i).value - desiredOutputs.get(i);
                aggregateError += partialError * partialError;
            }
            aggregateError /= desiredOutputs.size();
            aggregateError = Math.sqrt(aggregateError);
            
            return aggregateError;
        }
        else
        {
            throw new Exception("Layer.calculateError : "
                    + "desiredOutput is null or it has wrong size");
        }
    }
    
    /**
     * Makes all the neurons compute their gradients according to the procedure
     * for output gradients.
     * 
     * @param desiredOutputs 
     */
    public void computeOutputGradients(List<Double> desiredOutputs)
    {
        for (int i = 1; i < neurons.size(); ++i)
        {
            neurons.get(i).computeOutputGradient(desiredOutputs.get(i));
        }
    }
    
    /**
     * Makes all the neurons compute their gradients according to the procedure
     * for hidden gradients.
     */
    public void computeHiddenGradients()
    {
        for (int i = 0; i < neurons.size(); ++i)
        {
            neurons.get(i).computeHiddenGradient();
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder description = new StringBuilder();
        description.append("Layer[")
                .append(neurons.size())
                .append(" neurons]\n");
        for (var neuron : neurons)
        {
            description.append(neuron.toString());
        }
        
        return description.toString();
    }
}
