package br.edu.ifpb.ads.questao_04_node2.dao;

import br.edu.ifpb.ads.questao_04_node2.User;
import br.edu.ifpb.ads.questao_04_node2.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 17:43:48
 */
public class MySQLDao implements Dao {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/questao_04";
    static final String USER = "root";
    static final String PASS = "12345";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }

    @Override
    public boolean persist(User user) {
        System.out.println("Saving in mysqldao: " + user.toString());
        try {
            Connection connection = getConnection();
            String sql = "INSERT INTO users(login, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            int i = 1;
            ps.setString(i++, user.getLogin());
            ps.setString(i++, user.getPassword());
            
            return ps.executeUpdate() != 0;
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MySQLDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

}
