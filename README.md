# Toy Language Interpreter

This project implements an interpreter for a toy programming language using the **Model-View-Controller (MVC) architecture**. The project is designed to simulate the behavior of a simple programming language, making it a great tool for learning about interpreters, execution stacks, heap memory, and file management.
## Features
- Variables
- Arithmetic expressions
- Relational expressions
- Conditional statements
- Loops
- File handling
- Heap management
- Garbage collection

## Project Structure
The project is organized into the following packages:

### 1. `model`
- `expressions`: Represents expressions like arithmetic and relational operations.
- `statements`: Implements statements such as assignments, conditionals, loops, and heap operations.
- `types`: Defines types like `IntType`, `BoolType`, and `StringType`.
- `values`: Implements values like `IntValue`, `BoolValue`, and `StringValue`.
- `state`: Defines the program state (`PrgState`), including symbol tables, execution stack, heap, etc.

### 2. `controller`
- Manages the execution of programs and integrates garbage collection.

### 3. `repo`
- Stores and manages program states.

### 4. `view`
- Provides a menu-driven interface to run and test programs.

## Setup and Usage

### Prerequisites
- **Java Development Kit (JDK)**: Version 11 or later.
- A Java IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools for compilation and execution.

### Clone the Repository
```bash
git clone https://github.com/username/toy-language-interpreter.git
cd toy-language-interpreter