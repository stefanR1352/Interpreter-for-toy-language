package examples;

import model.expressions.ArithmeticExpression;
import model.expressions.ArithmeticalOperator;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;

public class Examples {
    public IStatement createStatement1() {
        return new ComposedStatement(new VarDeclarationStatement("v", new IntType()),
                new ComposedStatement(new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                 new PrintStatement(new VariableExpression("v"))
                )
        );
    }

    public IStatement createStatement2() {
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

    public IStatement createStatement3() {
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
