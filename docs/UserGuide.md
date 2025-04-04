# User Guide

## Introduction

Budget Buddy is designed for students who want to manage their finances efficiently. These users often have limited 
income, and busy schedules, and need an easy-to-use tool to track their daily expenses, set budgets, and stay 
financially aware. They may struggle with overspending, forgetfulness, and the challenge of balancing expenses across 
different categories.

## Quick Start

1. Ensure that you have Java 17 or above installed.
2. Download the latest version of `tp.jar` from [here]().
3. Copy the file to the folder you want to use as the home folder for your address book.
4. Open a command terminal, cd into the folder you put the jar file in and type the following command:
`java -jar tp.jar`
5. A GUI similar to the one below should appear in a few seconds. 

## Features 

{Give detailed description of each feature}

### Adding an expense: `add`
Adds a new item to the list of expenses. Users can choose to leave `CATEGORY` and `DATE TIME` blank, in which case:
 - The expense will default to the **Overall Budget** if `CATEGORY` is not provided or cannot be found.
 - The `DATE TIME` will default to the **current date and time** if left blank or formatted incorrectly.

Format: `add <AMOUNT> c/ <CATEGORY> d/ <DESCRIPTION> t/ <DATE TIME>`

* * The `AMOUNT` can be any positive number, if a number too large in entered, it will be displayed as `Infinity`.
* The `CATEGORY` can be in natural language format. If it does not exist, the expense defaults to the **Overall Budget**.
* The `DESCRIPTION` can be in natural language format.  
* The `CATEGORY` and `DESCRIPTION` cannot contain `d/` and `t/`
* The `DATE TIME` has to follow the format of `MMM dd yyyy at HH:mm`, where **only** the first letter of `MMM` is 
capitalized, and HH:mm follows the 24-hour clock format. 
* The `DATE TIME` must follow the format `MMM dd yyyy at HH:mm`, where:
  - **Only the first letter of `MMM` is capitalized** (e.g., `Jan 15 2025 at 11:30`).
  - **`HH:mm` follows the 24-hour clock format** (e.g., `13:45 for 1:45 PM`). 
  - If an incorrect date format is provided, the **current date and time** will be used instead.
* Users **must still include `c/` and `t/` even if leaving them blank**.

Example of usage: 

`add 200 c/ d/ food t/`

`add 100 c/ Overall d/ bus fares t/`

`add 50 c/ Overall d/ cab fares t/ Jan 15 2025 at 11:30`

### Deleting an expense: `delete`
Removes a recorded expense from the Overall Budget and its corresponding category budget using its index.
This `INDEX` is found from the list command, which displays all recorded expenses.

Format: `delete <INDEX>`

* The `INDEX` must be a positive integer representing the position of the expense in the Overall Budget. 
* Deleting an expense from the Overall Budget will also remove it from the corresponding category budget.

**Example:** `delete 4`

**Expected Output:**
```
___________________________________________
The following expense has been deleted successfully.
-> $5.00 spent on Tacos (Apr 02 2025 at 17:48)
___________________________________________
Expense also deleted from category 'Food'.
___________________________________________
```

### Listing All Expenses: `list`
Displays all recorded expenses under the Overall Budget, including their amount, description, and date/time.

Format: `list`

* Lists expenses in chronological order from the Overall Budget, with latest displayed first. 
* Each expense includes the amount, description, and timestamp.

**Example:** `list`

**Expected Output:**
```
___________________________________________
Expense List:
1. $25.00 spent on Bus Fare (Apr 03 2025 at 08:15)
2. $12.50 spent on Coffee (Apr 02 2025 at 10:30)
3. $60.00 spent on Groceries (Apr 01 2025 at 16:45)
___________________________________________
```

### Editing an expense: `edit-expense`
Edit an item on the list of expenses. Expense `AMOUNT`, `DESCRIPTION` or `DATE TIME` can be edited, with all three being 
optional but requiring minimally one.

Format: `edit-expense <INDEX> a/ <AMOUNT> d/ <DESCRIPTION> t/ <DATE TIME>`

* The `INDEX` refers to the index of the expense when the `list` function is used.
* The `AMOUNT` can be any positive number, if a number too large in entered, it will be displayed as `Infinity`.
* The `DESCRIPTION` can be in natural language format.
* The `DESCRIPTION` cannot contain `d/` and `t/`
* The `DATE TIME` has to follow the format of `MMM dd yyyy at HH:mm`, where **only** the first letter of `MMM` is
  capitalized, and HH:mm follows the 24-hour clock format.
* The `DATE TIME` must follow the format `MMM dd yyyy at HH:mm`, where:
  - **Only the first letter of `MMM` is capitalized** (e.g., `Jan 15 2025 at 11:30`).
  - **`HH:mm` follows the 24-hour clock format** (e.g., `13:45 for 1:45 PM`).
  - If an incorrect date format is provided, the **current date and time** will be used instead.
* Users can omit `a/`, `c/` **or** `t/` but cannot omit all three.

Example of usage:

`edit-expense 1 a/ 200`

`edit-expense 1 a/ 100 d/ bus fares`

`edit-expense 1 t/ Jan 15 2025 at 11:30`

### Add Alert: 'alert'
Sets a budget alert to notify the user when expenses exceed a specific limit. 

Format: `alert <AMOUNT>`

* The `AMOUNT` can be any positive number, if a number too large in entered, it will be displayed as Infinity.
* The budget alert remains active until it is manually updated or removed
* To remove the alert, input `alert 0`

### Summary of Budget: `summary`
View a summarized budget by category, which includes the total expenses and spending limits for each category.

Example of usage: `summary`

**Expected output:**
````
___________________________________________
Budget Summary:

Category: Overall
Total Expenses: $20.0
Spending Limit: $0.0

___________________________________________
````


### Checking Budget: `check-budget`
Displays the budget allocation, amount spent, and remaining balance for a specified category.
If no category is provided, it displays the Overall Budget.

Format: `check-budget [c/<CATEGORY>]`

* The budget breakdown includes:
  * Total Budget (allocated amount)
  * Spent (total expenses recorded)
  * Remaining (total budget minus spent amount)

**Example 1:** `check-budget`

**Expected Output 1 :**
```
===== Overall Budget Usage =====
Total Budget: $1000.0
Spent: $100.0
Remaining: $900.0
===============================
```

**Example 2:** `check-budget c/Groceries`

**Expected Output 2 :**
```
===== Budget for Groceries =====
Total Budget: $50.0
Spent: $5.0
Remaining: $45.0
===============================
```

### Editing Budget: `edit-budget`
Modifies an existing budget by updating its limit, name, or both.

Format: `edit-budget old/<CURRENT_NAME> a/<NEW_AMOUNT> c/<NEW_NAME>`

* `CURRENT_NAME` must be an existing budget name. 
* `NEW_AMOUNT` is optional but must be a positive number to update the budget limit. 
* `NEW_NAME` is optional but must be non-empty if provided. 
* If `NEW_NAME` is provided, the budget will be renamed accordingly.
* At least one of the two, new amount or new name  must be specified.

**Example 1:** `edit-budget old/Food a/500`

**Expected Output 1 :**
```
Updated limit of budget 'Food' to $500.0
```

**Example 2:** `edit-budget old/Food c/Groceries`

**Expected Output 2 :**
```
Renamed budget 'Food' to 'Groceries'
```

**Example 3:** `edit-budget old/Food a/500 c/Groceries`

**Expected Output 3 :**
```
Updated limit of budget 'Food' to $1000.0
Renamed budget 'Food' to 'Groceries'
```

### Help: `help`
View all available commands in Budget Buddy, including their functions and formats.

Example of usage: `help`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
