import controller.Controller;
import model.command.ExitCommand;
import model.command.RunExample;
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
import view.IView;
import view.TextMenu;
import view.View;

import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        TextMenu menu = new TextMenu();

        // Example Programs
        IStatement ex1 = createStatement1();
        IRepo repo1 = new Repo("logFile1.txt");
        repo1.add(new PrgState(ex1));
        Controller ctr1 = new Controller(repo1);

        IStatement ex2 = createStatement2();
        IRepo repo2 = new Repo("logFile2.txt");
        repo2.add(new PrgState(ex2));
        Controller ctr2 = new Controller(repo2);

        IStatement ex3 = createStatement3();
        IRepo repo3 = new Repo("logFile3.txt");
        repo3.add(new PrgState(ex3));
        Controller ctr3 = new Controller(repo3);

        // Add Commands
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", "Run Example 1", ctr1));
        menu.addCommand(new RunExample("2", "Run Example 2", ctr2));
        menu.addCommand(new RunExample("3", "Run Example 3", ctr3));

        // Display the menu
        menu.show();
    }
    private static IStatement createStatement1() {
        return new ComposedStatement(new VarDeclarationStatement("v", new IntType()),
                new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );
    }

    private static IStatement createStatement2() {
        return new ComposedStatement(
                new VarDeclarationStatement("a", new IntType()),
                new ComposedStatement(
                        new VarDeclarationStatement("b", new IntType()),
                        new ComposedStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)), ArithmeticalOperator.PLUS,
                                        new ArithmeticExpression( new ValueExpression(new IntValue(3)), ArithmeticalOperator.MULTIPLY, new ValueExpression(new IntValue(5))))),
                                new ComposedStatement(
                                        new AssignmentStatement("b", new ArithmeticExpression( new VariableExpression("a"), ArithmeticalOperator.PLUS, new ValueExpression(new IntValue(1)))),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );
    }

    private static IStatement createStatement3() {
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
