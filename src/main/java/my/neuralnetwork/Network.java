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
    private final List<Layer> layers;
    
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
            if (!success)
            {
                throw new Exception("Network.<init> : non-positive size of a layer");
            }
        }
        else
        {
            throw new Exception("Network.<init> : too small number of layers");
        }
    }
}
