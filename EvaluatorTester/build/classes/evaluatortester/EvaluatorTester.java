/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluatortester;

/**
 *
 * @author qihongkuang
 */
public class EvaluatorTester 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Evaluator anEvaluator = new Evaluator();
        for (String arg : args) 
        {
            System.out.println(arg + " = " + anEvaluator.eval(arg));
        }

    }
}