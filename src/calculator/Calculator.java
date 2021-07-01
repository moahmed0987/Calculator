package calculator;

import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Calculator extends Application {

    private final Font buttonFont = new Font(20);

    private char operator;
    private double numberOne;
    private double numberTwo;

    private double memory;

    TextField calcBox, equationDisplay;

    @Override
    public void start(Stage stage) {
        calcBox = new TextField();
        calcBox.setEditable(false);
        calcBox.setPrefSize(260, 75);
        calcBox.setAlignment(Pos.CENTER_RIGHT);

        equationDisplay = new TextField();
        equationDisplay.setEditable(false);
        equationDisplay.setPrefSize(260, 25);
        equationDisplay.setAlignment(Pos.CENTER_RIGHT);

        calcBox.setFont(buttonFont);
        equationDisplay.setFont(buttonFont);

        VBox textFieldHolder = new VBox();
        textFieldHolder.getChildren().addAll(equationDisplay, calcBox);

        Button zeroButton, oneButton, twoButton, threeButton, fourButton, fiveButton, sixButton, sevenButton, eightButton, nineButton;
        Button[] numberButtons = new Button[10];

        zeroButton = new Button("0");
        oneButton = new Button("1");
        twoButton = new Button("2");
        threeButton = new Button("3");
        fourButton = new Button("4");
        fiveButton = new Button("5");
        sixButton = new Button("6");
        sevenButton = new Button("7");
        eightButton = new Button("8");
        nineButton = new Button("9");

        GridPane.setConstraints(zeroButton, 1, 5);
        GridPane.setConstraints(oneButton, 0, 4);
        GridPane.setConstraints(twoButton, 1, 4);
        GridPane.setConstraints(threeButton, 2, 4);
        GridPane.setConstraints(fourButton, 0, 3);
        GridPane.setConstraints(fiveButton, 1, 3);
        GridPane.setConstraints(sixButton, 2, 3);
        GridPane.setConstraints(sevenButton, 0, 2);
        GridPane.setConstraints(eightButton, 1, 2);
        GridPane.setConstraints(nineButton, 2, 2);

        numberButtons[0] = zeroButton;
        numberButtons[1] = oneButton;
        numberButtons[2] = twoButton;
        numberButtons[3] = threeButton;
        numberButtons[4] = fourButton;
        numberButtons[5] = fiveButton;
        numberButtons[6] = sixButton;
        numberButtons[7] = sevenButton;
        numberButtons[8] = eightButton;
        numberButtons[9] = nineButton;

        for (Button button : numberButtons) {
            button.setPrefSize(65, 65);
            button.setFont(buttonFont);
            button.setOnAction(e -> handleNumberPress(((Button) e.getSource()).getText()));
        }

        Button addButton, subtractButton, multiplyButton, divideButton;
        Button[] functionButtons = new Button[4];

        addButton = new Button("+");
        subtractButton = new Button("-");
        multiplyButton = new Button("*");
        divideButton = new Button("/");

        GridPane.setConstraints(addButton, 3, 4);
        GridPane.setConstraints(subtractButton, 3, 3);
        GridPane.setConstraints(multiplyButton, 3, 2);
        GridPane.setConstraints(divideButton, 3, 1);

        functionButtons[0] = addButton;
        functionButtons[1] = subtractButton;
        functionButtons[2] = multiplyButton;
        functionButtons[3] = divideButton;

        for (Button button : functionButtons) {
            button.setPrefSize(65, 65);
            button.setFont(buttonFont);
            button.setOnAction(e -> handleOperatorPress(((Button) e.getSource()).getText()));
        }

        Button memoryAddButton, memorySubtractButton, memoryClearButton, memoryRecallButton;
        Button[] memoryButtons = new Button[4];

        memoryAddButton = new Button("M+");
        memorySubtractButton = new Button("M-");
        memoryClearButton = new Button("MC");
        memoryRecallButton = new Button("MR");

        GridPane.setConstraints(memoryAddButton, 0, 1);
        GridPane.setConstraints(memorySubtractButton, 1, 1);
        GridPane.setConstraints(memoryClearButton, 2, 1);
        GridPane.setConstraints(memoryRecallButton, 2, 0);

        memoryButtons[0] = memoryAddButton;
        memoryButtons[1] = memorySubtractButton;
        memoryButtons[2] = memoryClearButton;
        memoryButtons[3] = memoryRecallButton;

        for (Button button : memoryButtons) {
            button.setPrefSize(65, 65);
            button.setFont(buttonFont);
        }

        memoryAddButton.setOnAction(e -> {
            if (!calcBox.getText().isEmpty()) {
                memory += Double.parseDouble(calcBox.getText());
                System.out.println("memory = " + memory);
            }
        });

        memorySubtractButton.setOnAction(e -> {
            if (!calcBox.getText().isEmpty()) {
                memory -= Double.parseDouble(calcBox.getText());
                System.out.println("memory = " + memory);
            }
        });

        memoryClearButton.setOnAction(e -> memory = 0);

        memoryRecallButton.setOnAction(e -> calcBox.setText(Double.toString(memory)));

        Button equalsButton, decimalButton, unaryMinusButton, clearButton, clearEntryButton, backSpaceButton;
        Button[] miscButtons = new Button[6];

        equalsButton = new Button("=");
        decimalButton = new Button(".");
        unaryMinusButton = new Button("-/+");
        clearButton = new Button("C");
        clearEntryButton = new Button("CE");
        backSpaceButton = new Button("<");

        GridPane.setConstraints(equalsButton, 3, 5);
        GridPane.setConstraints(decimalButton, 2, 5);
        GridPane.setConstraints(unaryMinusButton, 0, 5);
        GridPane.setConstraints(clearButton, 0, 0);
        GridPane.setConstraints(clearEntryButton, 1, 0);
        GridPane.setConstraints(backSpaceButton, 3, 0);

        miscButtons[0] = equalsButton;
        miscButtons[1] = decimalButton;
        miscButtons[2] = unaryMinusButton;
        miscButtons[3] = clearButton;
        miscButtons[4] = clearEntryButton;
        miscButtons[5] = backSpaceButton;

        for (Button button : miscButtons) {
            button.setPrefSize(65, 65);
            button.setFont(buttonFont);
        }

        equalsButton.setOnAction(e -> handleEqualsPress());
        decimalButton.setOnAction(e -> handleDecimalPress());
        unaryMinusButton.setOnAction(e -> {
            calcBox.setText(Double.toString(Double.parseDouble(calcBox.getText()) * -1));
        });
        clearButton.setOnAction(e -> handleClearPress());
        clearEntryButton.setOnAction(e -> calcBox.setText(""));
        backSpaceButton.setOnAction(e -> handleBackSpacePress());

        GridPane buttonHolder = new GridPane();
        buttonHolder.getChildren().addAll(Arrays.asList(numberButtons));
        buttonHolder.getChildren().addAll(Arrays.asList(functionButtons));
        buttonHolder.getChildren().addAll(Arrays.asList(miscButtons));
        buttonHolder.getChildren().addAll(Arrays.asList(memoryButtons));

        VBox root = new VBox();
        root.setPadding(new Insets(20));
        root.getChildren().addAll(textFieldHolder, buttonHolder);

        stage.setResizable(false);
        stage.setTitle("Calculator");
        Scene scene = new Scene(root, 300, 500);
        scene.setOnKeyPressed(e -> {
            System.out.println("Text = " + e.getText());
            if (e.getCode().isDigitKey()) {
                handleNumberPress(e.getText());
            }
            if (e.getCode().equals(KeyCode.ENTER)) {
                handleEqualsPress();
            }
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                handleClearPress();
            }
            if (e.getCode().equals(KeyCode.BACK_SPACE)) {
                handleBackSpacePress();
            }

            switch (e.getText()) {
                case "+":
                case "-":
                case "*":
                case "/":
                    handleOperatorPress(e.getText());
                    break;
                case ".":
                    handleDecimalPress();
                    break;

            }
        });
        stage.setScene(scene);
        stage.show();
    }

    private void handleNumberPress(String numberPressed) {
        calcBox.setText(calcBox.getText().concat(numberPressed));
    }

    private void handleOperatorPress(String operatorPressed) {
        if (!calcBox.getText().isEmpty()) {
            if (equationDisplay.getText().isEmpty()) {
                numberOne = Double.parseDouble(calcBox.getText());
                operator = operatorPressed.charAt(0);
                equationDisplay.setText(calcBox.getText().concat(operatorPressed));
                calcBox.setText("");
            } else {
                double temp = handleEqualsPress();
                operator = operatorPressed.charAt(0);
                equationDisplay.setText(Double.toString(temp).concat(operatorPressed));
                calcBox.setText("");
            }
        }
    }

    private double handleEqualsPress() {
        double result = 0;
        if (!calcBox.getText().isEmpty()) {
            numberTwo = Double.parseDouble(calcBox.getText());
        }
        switch (operator) {
            case '+':
                result = numberOne + numberTwo;
                break;
            case '-':
                result = numberOne - numberTwo;
                break;
            case '*':
                result = numberOne * numberTwo;
                break;
            case '/':
                result = numberOne / numberTwo;
                break;
        }
        calcBox.setText(Double.toString(result));
        numberOne = result;
        equationDisplay.setText("");
        return result;
    }

    private void handleDecimalPress() {
        if (!calcBox.getText().isEmpty()) {
            if (!calcBox.getText().contains(".")) {
                calcBox.setText(calcBox.getText().concat("."));
            }
        }
    }

    private void handleClearPress() {
        calcBox.setText("");
        equationDisplay.setText("");
        numberOne = 0;
        numberTwo = 0;
        operator = ' ';
    }

    private void handleBackSpacePress() {
        if (!calcBox.getText().isEmpty()) {
            calcBox.setText(calcBox.getText().substring(0, calcBox.getText().length() - 1));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
