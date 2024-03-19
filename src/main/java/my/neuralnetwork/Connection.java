/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

public class Connection
{
    public double weight;
    public final Neuron tail;
    public final Neuron head;
    
    private static final Random random = new Random();
    
    private Connection(Neuron tail, Neuron head)
    {
        weight = random.nextDouble(-1.0, +1.0);
        this.tail = tail;
        this.head = head;
    }
    
    public static void joinNeurons(Neuron tail, Neuron head)
    {
        Connection connection = new Connection(tail, head);
        tail.addOutputConnection(connection);
        head.addInputConnection(connection);
    }
    
    @Override
    public String toString()
    {
        return String.format("Connection[weight = %.3f]", weight);
    }
}
