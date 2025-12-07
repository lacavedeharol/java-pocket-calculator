# Java Pocket Calculator

A functional desktop calculator application built with Java and Swing, featuring a graphical user interface, performs basic arithmetic operations.

## Features

* **Basic Arithmetic Operations**: Addition, subtraction, multiplication, and division.
* **Decimal Support**: Full support for decimal point calculations.
* **Keyboard Input**: Complete keyboard support including:
  * Number keys (0-9).
  * Operator keys (+, -, \*, /).
  * Enter key for equals.
  * Escape key for clear.
  * Backspace/Delete for clear entry.
* **Error Handling**: Displays error messages for invalid operations.
* **Clean UI**: Intuitive button layout with operation and result display.

## Project Structure

```src/main/java/com/lacavedeharol/calculator/
├── JavaPocketCalculator.java      # Application entry point.
├── controller/
│   └── CalculatorController.java  # Event handling and user input processing.
├── model/
│   └── CalculatorModel.java       # Calculation logic.
└── view/
    ├── CalculatorFrame.java       # Main application window.
    ├── CalculatorButton.java      # Custom button component.
    ├── CalculatorDisplayText.java # Display text component.
    ├── CalculatorConstants.java   # UI constants and labels.
    └── Utilities.java             # Utility functions.
```

## Architecture

The application follows the **Model-View-Controller (MVC)** pattern:

* **Model**: `CalculatorModel` - Handles mathematical calculations.
* **View**: `CalculatorFrame`, `CalculatorButton`, `CalculatorDisplayText` - GUI components.
* **Controller**: `CalculatorController` - Manages user interactions and state.

## Build & Run

### Prerequisites

* Java 25 or higher.
* Maven 3.6+.

### Build

```bash
mvn clean package
```

### Run

```bash
mvn exec:java
```

Or run the JAR directly:

```bash
java -jar target/javaPocketCalculator-1.0-SNAPSHOT.jar
```

## Usage

1. **Enter Numbers**: Click number buttons or use keyboard (0-9).
2. **Select Operation**: Click an operator button (+, -, \*, /) or use keyboard.
3. **Calculate**: Click the equals button or press Enter.
4. **Clear**: Press Escape to clear all, or Backspace/Delete to clear the current entry.

**Note**: Pressing a different operator after selecting one will override the previous operator without requiring a number in between.

## Technologies

* **Language**: Java 25
* **GUI Framework**: Swing
* **Build Tool**: Maven

## Author

lacavedeharol
