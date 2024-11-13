package view;

import controller.Controller;
import exceptions.ADTException;
import exceptions.ReadException;
import model.expressions.ArithmeticExpression;
import model.expressions.ArithmeticalOperator;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.state.PrgState;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import repo.IRepo;
import repo.Repo;

import java.io.IOException;
import java.util.Scanner;

public class View implements IView {
    Controller controller;

    public View(){}

    @Override
    public void display() throws IOException {
        System.out.println("Start interpreter\n");
        System.out.println("""
                1. Input program
                2. Complete execution
                3. One step execution
                4. Exit program""");

        boolean running = true;
        while (running) {
            int userInput = readInt();
            switch (userInput) {
                case 1:
                    inputProgram();
                    break;
                case 2:
                    completeExecution();
                    break;
                case 3:
                    oneStepExecution();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

    }

    public int readInt() throws ReadException {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;
        int input;
        do{
            System.out.print("Enter an integer : ");
            try {
                input = scanner.nextInt();
                validInput = true;
            } catch (Exception e) {
                throw new ReadException("Invalid int");
            }
        }while(!validInput);
        return input;
    }

    public void inputProgram(){
        System.out.println("Input program(1, 2, 3): ");
        IStatement chosenProgram;
        int userInput = readInt();
        switch (userInput) {
            case 1 -> chosenProgram = createStatement1();
            case 2 -> chosenProgram = createStatement2();
            case 3 -> chosenProgram = createStatement3();
            default -> throw new ReadException("Invalid program");
        };

        IRepo repo = new Repo("logFile.txt");
        repo.add(new PrgState(chosenProgram));
        controller = new Controller(repo);
        controller.setDisplayFlag(true);
        System.out.println("Program selected successfully.");
    }

    public void completeExecution(){
        if(controller == null){
            System.out.println("No program selected");
            return;
        }
        try{
            controller.allSteps();
            System.out.println('\n');
        } catch (Exception e) {
            System.out.println("Error while executing program "+e.getMessage());
        }
    }

    public void oneStepExecution() throws IOException {
        if(controller == null){
            System.out.println("No program selected");
            return;
        }
        try{
            controller.oneStep(controller.returnCrtState());
        } catch (ADTException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    private IStatement createStatement1() {
        return new ComposedStatement(new VarDeclarationStatement("v", new IntType()),
                new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );
    }

    private IStatement createStatement2() {
        return new ComposedStatement(
                new VarDeclarationStatement("a", new IntType()),
                new ComposedStatement(
                        new VarDeclarationStatement("b", new IntType()),
                        new ComposedStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new BoolValue(true)), ArithmeticalOperator.PLUS,
                                        new ArithmeticExpression( new ValueExpression(new IntValue(3)), ArithmeticalOperator.MULTIPLY, new ValueExpression(new IntValue(5))))),
                                new ComposedStatement(
                                        new AssignmentStatement("b", new ArithmeticExpression( new VariableExpression("a"), ArithmeticalOperator.PLUS, new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );
    }

    private IStatement createStatement3() {
        return new ComposedStatement(
                new VarDeclarationStatement("a", new BoolType()),
                new ComposedStatement(
                        new VarDeclarationStatement("v", new IntType()),
                        new ComposedStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new ComposedStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(3))
                                                )),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );
    }
}
