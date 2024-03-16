/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package my.neuralnetwork;

import java.util.*;

/**
 * Connection between two <code>Neuron</code>s.
 * 
 * A connection is characterized by its weight. The object also stores
 * references to the neurons it connects.
 * 
 * @author Kay Jay O'Nail
 */
public class Connection
{
    /**
     * The weight of the connection.
     */
    private double weight;
    
    /**
     * The neuron <em>from which</em> this connection leads.
     */
    private Neuron tail;
    
    /**
     * The neuron <em>to which</em> this connection leads.
     */
    private Neuron head;
    
    /**
     * Random number generator.
     */
    private static Random random = new Random();
    
    /**
     * Constructor.
     */
    private Connection()
    {
        weight = random.nextDouble(-1.0, +1.0);
    }
    
    /**
     * Joins neuron <code>previous</code> with neuron <code>next</code>.
     * 
     * @param previous tail of the connection
     * @param next head of the connection
     */
    public static void joinNeurons(Neuron previous, Neuron next)
    {
        Connection connection = new Connection();
        connection.tail = previous;
        connection.head = next;
        previous.addOutputConnection(connection);
        next.addInputConnection(connection);
    }
    
    /**
     * Returns the value of the <code>tail</code> neuron multiplied by this
     * connection's <code>weight</code>.
     * 
     * @return product of the <code>tail</code>'s value and the <code>weight</code>
     */
    public double getValue()
    {
        assert (tail != null);
        return tail.value * weight;
    }
    
    @Override
    public String toString()
    {
        return String.format("Connection[weight = %.3f]", weight);
    }
}
