package ConnectSQL;

import java.sql.*;

public class AccountService {
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public static final String SELECT = "SELECT * FROM ACCOUNTS";

    public AccountService(Connection connection, Statement statement, PreparedStatement preparedStatement, ResultSet resultSet) {
        this.connection = connection;
        this.statement = statement;
        this.preparedStatement = preparedStatement;
        this.resultSet = resultSet;
    }

    void balance(int accountId) throws UnknownAccountException, SQLException {
        String sql = "SELECT * FROM ACCOUNTS WHERE id = ?";
        int maxID = -1;
        resultSet = statement.executeQuery(SELECT);
        while (resultSet.next()) {
            maxID++;
        }
        if (accountId > maxID) {
            throw new UnknownAccountException("Unknown Account");
        } else {
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, accountId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println("Баланс счета " + resultSet.getString(2) + ": " + resultSet.getInt(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    void withdraw(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, SQLException {
        String sql = "SELECT * FROM ACCOUNTS WHERE id = ?";
        String sql2 = "UPDATE ACCOUNTS SET amount = ? WHERE id = ?";
        int maxID = -1;
        resultSet = statement.executeQuery(SELECT);
        while (resultSet.next()) {
            maxID++;
        }
        if (accountId > maxID) {
            throw new UnknownAccountException("Unknown Account");
        } else {
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, accountId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int temp = resultSet.getInt(3);
                    if (temp < amount) {
                        throw new NotEnoughMoneyException("Not Enough Money");

                    } else {
                        preparedStatement = connection.prepareStatement(sql2);
                        preparedStatement.setInt(1, (temp - amount));
                        preparedStatement.setInt(2, accountId);
                        preparedStatement.executeUpdate();
                        System.out.println("C баланса снято " + amount);
                        printBase();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    void deposit(int accountId, int amount) throws NotEnoughMoneyException, UnknownAccountException, SQLException {
        String sql = "SELECT * FROM ACCOUNTS WHERE id = ?";
        String sql2 = "UPDATE ACCOUNTS SET amount = ? WHERE id = ?";
        int maxID = -1;
        resultSet = statement.executeQuery(SELECT);
        while (resultSet.next()) {
            maxID++;
        }
        if (accountId > maxID) {
            throw new UnknownAccountException("Unknown Account");
        } else {
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, accountId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int temp = resultSet.getInt(3);
                    preparedStatement = connection.prepareStatement(sql2);
                    preparedStatement.setInt(1, (temp + amount));
                    preparedStatement.setInt(2, accountId);
                    preparedStatement.executeUpdate();
                    System.out.println("На счет зачислено " + amount);
                    printBase();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    void transfer(int from, int to, int amount) throws NotEnoughMoneyException, UnknownAccountException, SQLException {
        String sql = "SELECT * FROM ACCOUNTS WHERE id = ?";
        String sql2 = "UPDATE ACCOUNTS SET amount = ? WHERE id = ?";
        int maxID = -1;
        resultSet = statement.executeQuery(SELECT);
        while (resultSet.next()) {
            maxID++;
        }
        if ((from > maxID) | (to > maxID)) {
            throw new UnknownAccountException("Unknown Account");
        } else {
            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, from);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int temp = resultSet.getInt(3);
                    if (temp < amount) {
                        throw new NotEnoughMoneyException("Not Enough Money");

                    } else {
                        try {
                            preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setInt(1, to);
                            resultSet = preparedStatement.executeQuery();
                            while (resultSet.next()) {
                                temp = resultSet.getInt(3);
                                preparedStatement = connection.prepareStatement(sql2);
                                preparedStatement.setInt(1, (temp + amount));
                                preparedStatement.setInt(2, to);
                                preparedStatement.executeUpdate();
                            }

                            preparedStatement = connection.prepareStatement(sql);
                            preparedStatement.setInt(1, from);
                            resultSet = preparedStatement.executeQuery();

                            while (resultSet.next()) {
                                temp = resultSet.getInt(3);
                                preparedStatement = connection.prepareStatement(sql2);
                                preparedStatement.setInt(1, (temp - amount));
                                preparedStatement.setInt(2, from);
                                preparedStatement.executeUpdate();
                                System.out.println("Между счетами перемещено " + amount);
                                printBase();

                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public void printBase() throws SQLException {
        resultSet = statement.executeQuery(SELECT);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String holder = resultSet.getString(2);
            int amount = resultSet.getInt(3);
            System.out.println(id + " | " + holder + " | " + amount);
        }
    }
}