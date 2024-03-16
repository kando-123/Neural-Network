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
public class Network
{
    /**
     * The list of the layers this network consists of.
     */
    private final List<Layer> layers;
    
    /**
     * Constructor.
     * 
     * @param sizes sizes of successive layers, from input through hidden to output
     * @throws Exception if there are too few arguments (less than 2) or any of them is non-positive
     */
    public Network(int... sizes) throws Exception
    {
        if (sizes.length >= 2)
        {
            layers = new ArrayList<>(sizes.length);
            boolean success = true;
            for (var size : sizes)
            {
                if (size > 0)
                {
                    layers.add(new Layer(size));
                }
                else
                {
                    success = false;
                    break;
                }
            }
            if (success)
            {
                for (int i = 1; i < layers.size(); ++i)
                {
                    Layer previous = layers.get(i - 1);
                    Layer current = layers.get(i);
                    Layer.joinLayers(previous, current);
                }
            }
            else
            {
                throw new Exception("Network.<init> : non-positive size of a layer");
            }
        }
        else
        {
            throw new Exception("Network.<init> : too small number of layers");
        }
    }
    
    /**
     * Assigns the neurons in the input layer with the values of
     * <code>input</code> vector, and has the values of the neurons in the successive
     * layers computed.
     * 
     * @param input
     * @throws Exception 
     */
    public void propagateForward(List<Double> input) throws Exception
    {
        /* Initialize the input layer's neurons. */
        layers.get(0).assign(input);

        /* Let the neurons in further layers. */
        for (int i = 1; i < layers.size(); ++i)
        {
            layers.get(i).computeValues();
        }
    }
    
    /**
     * Getter for the output layer computing neurons' values.
     * 
     * @return list of output layer computing neurons' values
     */
    public List<Double> getResults()
    {
        Layer outputLayer = layers.get(layers.size() - 1);
        return outputLayer.export();
    }
    
    // under development
    public void propagateBackward(List<Double> desiredOutputs) throws Exception
    {
        Layer outputLayer = layers.get(layers.size() - 1);
        List<Double> currentOutputs = outputLayer.export();
        
        if (desiredOutputs != null && currentOutputs.size() == desiredOutputs.size())
        {
            /* Calculate the RMS error. */
            double aggregateError = outputLayer.calculateError(desiredOutputs);
            
            /* Calculate the gradients. */
            outputLayer.computeOutputGradients(desiredOutputs);
            for (int i = layers.size() - 2; i > 0; --i)
            {
                layers.get(i).computeHiddenGradients();
            }
        }
        else
        {
            throw new Exception("Network.propagateBackward : incompatible vectors");
        }
    }
    
    @Override
    public String toString()
    {
        StringBuilder description = new StringBuilder();
        description.append("Network[")
                .append(layers.size())
                .append("]\n");
        for (var layer : layers)
        {
            description.append(layer.toString());
        }
        
        return description.toString();
    }
}
