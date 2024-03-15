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
public class Layer
{
    private final List<Neuron> neurons;
    
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
    
    public int size()
    {
        return neurons.size();
    }
    
    public Neuron get(int i)
    {
        return (i >= 0 && i < neurons.size()) ? neurons.get(i) : null;
    }
    
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
}
