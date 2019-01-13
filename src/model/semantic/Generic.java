package model.semantic;

import exception.semantic.OperationNotSupportedException;

public interface Generic {
    void addVariable(Variable v);

    void addMethods(Method m) throws OperationNotSupportedException;
}
