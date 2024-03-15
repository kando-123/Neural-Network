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
public class Connection
{
    private double weight;
    private Neuron tail;
    private Neuron head;
    
    private static Random random = new Random();
    
    private Connection()
    {
        weight = random.nextDouble(-1.0, +1.0);
    }
    
    public static void joinNeurons(Neuron previous, Neuron next)
    {
        Connection connection = new Connection();
        connection.tail = previous;
        connection.head = next;
        previous.addOutputConnection(connection);
        next.addInputConnection(connection);
    }
}
