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
import model.types.RefType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import repo.IRepo;
import repo.Repo;
import view.IView;
import view.TextMenu;

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

        IStatement ex6 = createStatement6();
        IRepo repo6 = new Repo("logFile6.txt");
        repo6.add(new PrgState(ex6));
        Controller ctr6 = new Controller(repo6);

        IStatement ex7 = createWhileStatement();
        IRepo repo7 = new Repo("logFile7.txt");
        repo7.add(new PrgState(ex7));
        Controller ctr7 = new Controller(repo7);

        IStatement ex8 = createGarbageRemovalStatement();
        IRepo repo8 = new Repo("logFile8.txt");
        repo8.add(new PrgState(ex8));
        Controller ctr8 = new Controller(repo8);

        IStatement ex9 = createLabStatement1();
        IRepo repo9 = new Repo("logFile9.txt");
        repo9.add(new PrgState(ex9));
        Controller ctr9 = new Controller(repo9);

        // Add Commands
        menu.addCommand(new ExitCommand("0", "Exit"));
        menu.addCommand(new RunExample("1", "Run Example 1", ctr1));
        menu.addCommand(new RunExample("2", "Run Example 2", ctr2));
        menu.addCommand(new RunExample("3", "Run Example 3", ctr3));
        menu.addCommand(new RunExample("4", "Run Example 4", ctr4));
        menu.addCommand(new RunExample("5", "Run Example 5", ctr5));
        menu.addCommand(new RunExample("6", "Run Example 6", ctr6));
        menu.addCommand(new RunExample("7", "Run While Example ", ctr7));
        menu.addCommand(new RunExample("8", "Run Garbage Example ", ctr8));
        menu.addCommand(new RunExample("9", "Run Lab example 1 ", ctr9));

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

    private static IStatement createStatement6() {
        return new ComposedStatement(
                new VarDeclarationStatement("v", new RefType(new IntType())),
                new ComposedStatement(
                        new AllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new ComposedStatement(
                                new VarDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new ComposedStatement(
                                        new AllocationStatement("a", new VariableExpression("v")),
                                        new ComposedStatement(
                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(new VariableExpression("a"))),
                                                        ArithmeticalOperator.PLUS,
                                                        new ValueExpression(new IntValue(5)))
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static IStatement createWhileStatement() {
        return new ComposedStatement(
                new VarDeclarationStatement("v", new IntType()),
                new ComposedStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new ComposedStatement(
                                new WhileStatement(
                                        new RelationalExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(0)),
                                                RelationalOperator.GREATER
                                        ),
                                        new ComposedStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                new VariableExpression("v"),
                                                                ArithmeticalOperator.MINUS,
                                                                new ValueExpression(new IntValue(1))
                                                        )
                                                )
                                        )
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );
    }

    private static IStatement createGarbageStatement() {
        return new ComposedStatement(
                new VarDeclarationStatement("v", new RefType(new IntType())),
                new ComposedStatement(
                        new AllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new ComposedStatement(
                                new VarDeclarationStatement("a", new RefType(new RefType(new IntType()))),
                                new ComposedStatement(
                                        new AllocationStatement("a", new VariableExpression("v")),
                                        new ComposedStatement(
                                                new AllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                        )
                                )
                        )
                )
        );
    }

    private static IStatement createGarbageRemovalStatement() {
        return new ComposedStatement(
                new VarDeclarationStatement("v1", new RefType(new IntType())),
                new ComposedStatement(
                        new AllocationStatement("v1", new ValueExpression(new IntValue(10))),
                        new ComposedStatement(
                                new VarDeclarationStatement("v2", new RefType(new IntType())),
                                new ComposedStatement(
                                        new AllocationStatement("v2", new ValueExpression(new IntValue(20))),
                                        new ComposedStatement(
                                                new VarDeclarationStatement("v3", new RefType(new RefType(new IntType()))),
                                                new ComposedStatement(
                                                        new AllocationStatement("v3", new VariableExpression("v1")),
                                                        new ComposedStatement(
                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v2")))
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static IStatement createLabStatement1() {
        return new ComposedStatement(
                new VarDeclarationStatement("v", new RefType(new IntType())),
                new ComposedStatement(
                        new AllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new ComposedStatement(new AllocationStatement("v", new ValueExpression(new IntValue(30))),
                                new AllocationStatement("v", new ValueExpression(new IntValue(40)))
                        )
                )
        );
    }
}
