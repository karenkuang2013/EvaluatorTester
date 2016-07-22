/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluatortester;

import java.util.*;

/**
 *
 * @author qihongkuang
 */
public class Evaluator {

    private Stack<Operand> opdStack;
    private Stack<Operator> oprStack;

    /**
     * This method is to handle the right-associated operator, such as power.
     *
     * @param testOpr
     */
    public void rightAssociated(Operator testOpr) {

        while (((Operator) oprStack.peek()).inpriority() > testOpr.inpriority()) {
            Operator oldOpr = ((Operator) oprStack.pop());
            if (oldOpr != Operator.operators.get("(")) {
                Operand op2 = (Operand) opdStack.pop();
                Operand op1 = (Operand) opdStack.pop();

                Operand re = oldOpr.execute(op1, op2);

                opdStack.push(re);
            }
        }
        if (((Operator) oprStack.peek()).inpriority() <= testOpr.outpriority()) {
            oprStack.push(testOpr);
            if (testOpr == Operator.operators.get(")")) {
                oprStack.pop();
            }
        }

    }

    /**
     * This method is to handle the right-associated operator, such as power.
     * @param testOpr 
     */
    public void leftAssociated(Operator testOpr) {
        int newPri = testOpr.outpriority();
        //newPri is the out priority of the new operator
        while (((Operator) oprStack.peek()).inpriority() >= newPri) {

            Operator oldOpr = ((Operator) oprStack.pop());
            if (oldOpr != Operator.operators.get("(")) {
                Operand op2 = (Operand) opdStack.pop();
                Operand op1 = (Operand) opdStack.pop();

                Operand re = oldOpr.execute(op1, op2);

                opdStack.push(re);

            } else {
                newPri = 7;
                //When left parenthesis is popped, set the out priority of 
                //the right parenthesis to the highest in order to push it
                //on the stack and then pop it out immediately.
            }

        }
        if (((Operator) oprStack.peek()).inpriority() < testOpr.outpriority()) {
            oprStack.push(testOpr);
            if (testOpr == Operator.operators.get(")")) {
                oprStack.pop();

            }
        }
    }

    /**
     *
     */
    public Evaluator() {
        opdStack = new Stack<Operand>();
        oprStack = new Stack<Operator>();
        Operator.operators.put("+", new AdditionOperator());
        Operator.operators.put("-", new SubtractionOperator());
        Operator.operators.put("*", new mulOperator());
        Operator.operators.put("/", new divisionOperator());
        Operator.operators.put("^", new powOperator());
        Operator.operators.put("#", new checkEmpOperator());
        Operator.operators.put("!", new terminateOperator());
        Operator.operators.put("(", new leftParOperator());
        Operator.operators.put(")", new rightParOperator());
    }

    /**
     * This method is to do the calculation for the input expression. It will
     * first filter out the operands and operators based on the delimiters given
     * in the code. Then it checks the precedence of each operator. It will
     * return the final result of the expression.
     * @param expr
     * @return 
     */
    public int eval(String expr) {
        opdStack.clear();
        opdStack.clear();
        String tok;
        expr = expr + "!";
        // init stack - necessary with operator priority schema;
        // the priority of any operator in the operator stack other then
        // the usual operators - "+-*/" - should be less than the priority
        // of the usual operators 

        oprStack.push(new checkEmpOperator());
        String delimiters = "()+-*^/#! ";

        StringTokenizer st = new StringTokenizer(expr, delimiters, true);
        // the 3rd arg is true to indicate to use the delimiters as tokens, too
        // but we'll filter out spaces

        while (st.hasMoreTokens()) {
            if (!(tok = st.nextToken()).equals(" ")) {
                // filter out spaces 
                if (Operand.check(tok)) {
                    // check if tok is an operand 
                    opdStack.push(new Operand(tok));
                } else {
                    if (!Operator.check(tok)) {
                        System.out.println("*****invalid token******");
                        System.exit(1);
                    }

                    Operator newOpr = Operator.operators.get(tok);
                    // POINT 1

                    if (newOpr == Operator.operators.get("^")) {
                        rightAssociated(newOpr);

                    } else {
                        leftAssociated(newOpr);
                        //checkRightParenthesis(newOpr);
                    }

                }

            }
// Control gets here when we've picked up all of the tokens; you must add
// code to complete the evaluation - consider how the code given here
// will evaluate the expression 1+2*3
// When we have no more tokens to scan, the operand stack will contain 1 2
// and the operator stack will have + * with 2 and * on the top;
// In order to complete the evaluation we must empty the stacks (except
// the init operator on the operator stack); that is, we should keep
// evaluating the operator stack until it only contains the init operator;
// Suggestion: create a method that takes an operator as argument and
// then executes the while loop; also, move the stacks out of the main
// method 

        }
        return opdStack.peek().getValue();
    }

}

abstract class Operator {

    static HashMap<String, Operator> operators = new HashMap<String, Operator>();

    public abstract int inpriority();

    public abstract int outpriority();

    static boolean check(String tok) {
        return operators.containsKey(tok);
    }

    abstract Operand execute(Operand opd1, Operand opd2);

}

class checkEmpOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        return null;
    }

    @Override
    public int inpriority() {
        return 0;
    }

    @Override
    public int outpriority() {
        return 0;
    }

}

class terminateOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        return null;
    }

    @Override
    public int inpriority() {
        return 1;
    }

    @Override
    public int outpriority() {
        return 1;
    }

}

class AdditionOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        Operand result = new Operand(opd1.getValue() + opd2.getValue());
        return result;
    }

    @Override
    public int inpriority() {
        return 3;
    }

    @Override
    public int outpriority() {
        return 3;
    }

}

class SubtractionOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        Operand result = new Operand(opd1.getValue() - opd2.getValue());
        return result;
    }

    @Override
    public int inpriority() {
        return 3;
    }

    @Override
    public int outpriority() {
        return 3;
    }

}

class mulOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        Operand result = new Operand(opd1.getValue() * opd2.getValue());
        return result;
    }

    @Override
    public int inpriority() {
        return 4;
    }

    @Override
    public int outpriority() {
        return 4;
    }

}

class divisionOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        Operand result = new Operand(opd1.getValue() / opd2.getValue());
        return result;
    }

    @Override
    public int inpriority() {
        return 4;
    }

    @Override
    public int outpriority() {
        return 4;
    }

}

class powOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        double re = Math.pow(opd1.getValue(), opd2.getValue());
        Operand result = new Operand((int) re);
        return result;
    }

    @Override
    public int inpriority() {
        return 5;
    }

    @Override
    public int outpriority() {
        return 5;
    }

}

class leftParOperator extends Operator {

    @Override
    Operand execute(Operand opd1, Operand opd2) {
        return null;
    }

    @Override
    public int inpriority() {
        return 2;
    }

    @Override
    public int outpriority() {
        return 6;
    }

}

class rightParOperator extends Operator {

    //execute function overloading
    @Override
    Operand execute(Operand opd1, Operand opd2) {
        return null;
    }

    @Override
    public int inpriority() {
        return 2;
    }

    @Override
    public int outpriority() {
        return 2;
    }

}

class Operand {

    private int OperandValue;

    Operand(String tok) {
        OperandValue = Integer.parseInt(tok);
    }

    Operand(int value) {
        OperandValue = value;
    }

    static boolean check(String tok) {
        try {
            Integer.parseInt(tok);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }

    }

    int getValue() {
        return OperandValue;
    }

}
