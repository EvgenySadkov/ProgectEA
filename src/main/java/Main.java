import java.sql.*;
import java.util.Scanner;


public class Main {
    public static final String SELECT = "SELECT * FROM ACCOUNTS";
    private static final String address = "jdbc:mysql://localhost:3306/myprogect_1?serverTimezone=UTC";
private static final String USERNAME = "admin";
private static final String PASSWORD = "admin";
    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(address, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(SELECT);
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT);
            AccountService accountService = new AccountService(connection, statement, preparedStatement, resultSet);
            accountService.printBase();
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.println("Какую банковскую операцию хотите произвести: (Or enter exit to exit)");
                String input = sc.nextLine();
                if (input.equals("exit")){
                    break;}
                String[] arr = input.split(" ");
                try {
                    switch (arr[0]) {
                        case "balance":
                            accountService.balance(Integer.valueOf(arr[1]));
                            break;
                        case "withdraw":
                            accountService.withdraw(Integer.valueOf(arr[1]), Integer.valueOf(arr[2]));
                            break;
                        case "deposite":
                            accountService.deposit(Integer.valueOf(arr[1]), Integer.valueOf(arr[2]));
                            break;
                        case "transfer":
                            accountService.transfer(Integer.valueOf(arr[1]), Integer.valueOf(arr[2]), Integer.valueOf(arr[3]));
                        default:
                            System.out.println("Неизвестная операция");
                            break;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                } catch (UnknownAccountException e) {
                    e.printStackTrace();
                } catch (NotEnoughMoneyException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

