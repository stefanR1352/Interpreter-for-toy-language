import controller.Controller;
import model.command.ExitCommand;
import model.command.RunExample;
import model.expressions.*;
import model.state.CloseFile;
import model.state.OpenFile;
import model.state.PrgState;
import model.state.ReadFile;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
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

        IStatement ex4 = createStatement4();
        IRepo repo4 = new Repo("logFile4.txt");
        repo4.add(new PrgState(ex4));
        Controller ctr4 = new Controller(repo4);

        IStatement ex5 = createStatement5();
        IRepo repo5 = new Repo("logFile5.txt");
        repo5.add(new PrgState(ex5));
        Controller ctr5 = new Controller(repo5);


        // Add Commands
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", "Run Example 1", ctr1));
        menu.addCommand(new RunExample("2", "Run Example 2", ctr2));
        menu.addCommand(new RunExample("3", "Run Example 3", ctr3));
        menu.addCommand(new RunExample("4", "Run Example 4", ctr4));
        menu.addCommand(new RunExample("5", "Run Example 5", ctr5));

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
                                        new ArithmeticExpression(new ValueExpression(new IntValue(3)), ArithmeticalOperator.MULTIPLY, new ValueExpression(new IntValue(5))))),
                                new ComposedStatement(
                                        new AssignmentStatement("b", new ArithmeticExpression(new VariableExpression("a"), ArithmeticalOperator.PLUS, new ValueExpression(new IntValue(1)))),
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

    private static IStatement createStatement4() {
        return new ComposedStatement(
                new VarDeclarationStatement("varf", new StringType()),
                new ComposedStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new ComposedStatement(
                                new OpenFile(new VariableExpression("varf")),
                                new ComposedStatement(
                                        new VarDeclarationStatement("varc", new IntType()),
                                        new ComposedStatement(
                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                new ComposedStatement(
                                                        new PrintStatement(new VariableExpression("varc")),
                                                        new ComposedStatement(
                                                                new ReadFile(new VariableExpression("varf"), "varc"),
                                                                new ComposedStatement(
                                                                        new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseFile(new VariableExpression("varf"))
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static IStatement createStatement5() {
        return new ComposedStatement(
                new VarDeclarationStatement("a", new IntType()),
                new ComposedStatement(
                        new VarDeclarationStatement("b", new IntType()),
                        new ComposedStatement(
                                new AssignmentStatement("a", new ValueExpression(new IntValue(10))),
                                new ComposedStatement(
                                        new AssignmentStatement("b", new ValueExpression(new IntValue(5))),
                                        new ComposedStatement(
                                                new PrintStatement(
                                                        new RelationalExpression(
                                                                new VariableExpression("a"),
                                                                new VariableExpression("b"),
                                                                RelationalOperator.GREATER
                                                        )
                                                ),
                                                new NopStatement()
                                        )
                                )
                        )
                )
        );
    }
}
