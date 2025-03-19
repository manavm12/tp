package seedu.duke;

import java.util.NoSuchElementException;
import java.util.Scanner;

import seedu.duke.exception.InvalidInputException;
import seedu.duke.Parser;


/**
 * The InputManager class handles user input, processes commands, and interacts with the BudgetManager.
 * It continuously listens for input until the "bye" command is entered, and processes commands to
 * add expenses to budgets or handle invalid input.
 */
public class InputManager {
    private final BudgetManager budgetManager;
    private final Parser parser;


    /**
     * Constructs an InputManager that interacts with the specified BudgetManager.
     *
     * @param budgetManager The BudgetManager instance to be used for managing budgets and expenses.
     */
    public InputManager(BudgetManager budgetManager) {
        assert budgetManager != null : "BudgetManager cannot be null.";
        this.budgetManager = budgetManager;
        this.parser = new Parser();
    }

    /**
     * Starts an input processing loop where the user can enter commands.
     * The loop continues until the user types "bye".
     * Valid commands include "add" to add expenses to budgets.
     */
    public void processInputLoop() {
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {

            try {
                line = in.nextLine().trim();

                if (line.equalsIgnoreCase("bye")) {
                    break;
                } else if (line.toLowerCase().startsWith("add")) {
                    String[] splitLine = parser.parseAddCommand(line);
                    double amount = Double.parseDouble(splitLine[0]);
                    String category = splitLine[1];
                    String description = splitLine[1];
                    budgetManager.addExpenseToBudget(category, amount, description);

                } else if (line.toLowerCase().startsWith("alert")) {
                    // Handle the alert command
                    String[] parts = line.split(" ");
                    if (parts.length != 2) {
                        throw new InvalidInputException("Please use the format: alert <AMOUNT>");
                    }
                    double amount = Double.parseDouble(parts[1]);
                    budgetManager.setBudgetAlert(amount);

                } else if (line.equalsIgnoreCase("summary")) {
                    BudgetSummary budgetSummary = new BudgetSummary(budgetManager);
                    budgetSummary.summariseBudget();

                } else if (line.equalsIgnoreCase("list")) {
                    budgetManager.listAllExpenses();

                } else if (line.toLowerCase().startsWith("delete")) {
                    String[] parts = line.split(" ");
                    if (parts.length != 2) {
                        throw new InvalidInputException("Please use the format: delete <INDEX>");
                    }
                    int index = Integer.parseInt(parts[1]);
                    budgetManager.deleteExpense(index);

                } else if (line.toLowerCase().startsWith("set-budget")) {
                    String[] splitline = parser.parseSetBudgetCommand(line);
                    String category = splitline[0];
                    Double amount = Double.parseDouble(splitline[1]);
                    budgetManager.setBudget(category, amount);
                }
                else {
                    throw new InvalidInputException("Please try again with one of the valid commands:" +
                            "\nadd, alert, summary, list, delete, set-budget, bye");
                }
            } catch (InvalidInputException e) {
                e.print();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: Please enter a valid number.");
            } catch (NoSuchElementException e) {
                System.out.println("No input found, exiting...");
                break;
            }
        }
        in.close();
    }
}