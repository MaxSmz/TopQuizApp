# QuizApp
## Project Description
QuizApp is a Java-based answering program. After the user enters the name, selects the age group and question category in the main interface, the program will randomly select 10 questions from the database for the user to answer. After answering a question, the program calculates and displays the user's score and the distribution of scores by question type and question category.

## Function
The user can enter a name.
User can select age group.
Users can select topic categories.
Users can answer randomly selected questions.
Users can view scores and score distributions for question types and question categories.

## System Requirements
Java 8 or later
MySQL database

## installation steps
1. Install Java Development Kit (JDK): Please visit Oracle JDK official website to download and install the JDK version suitable for your operating system.
2. Install the MySQL database: Please visit the MySQL official website to download and install the MySQL version suitable for your operating system.
3. Create a database and data table: Please use the MySQL command line tool or other MySQL management tools to create a database named mydb and create a questions data table in it. The structure of the data table can refer to the SQL query statement in the program.
4. Import question data: In the questions data table, import the question data you want to use for answering questions.
5. Modify the database connection information: In the TestController class, modify the values of the DB_URL, DB_USER, and DB_PASSWORD variables to match your MySQL database settings.
6. Compile and run the program: Compile the program using a Java compiler (such as javac) and run the program using a Java runtime environment (such as java).

## Instructions for use
1. After starting the program, first enter your name in the main interface and click the "Next" button.
2. In the question category interface, select the question category you want to answer, and click the "Next" button.
3. Select your age group. The program loads questions based on your selected age group and question category.
4. Answer the questions, you can use the "Next" and "Back" buttons to switch between questions.
5. After you have answered all the questions, the program will display your score and the distribution of scores by question type and question category.
6. You can choose to start over, or exit the program.