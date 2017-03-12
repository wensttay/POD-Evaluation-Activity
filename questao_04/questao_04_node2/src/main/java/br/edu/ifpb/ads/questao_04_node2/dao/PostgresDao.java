package br.edu.ifpb.ads.questao_04_node2.dao;

import br.edu.ifpb.ads.questao_04_node2.User;
import br.edu.ifpb.ads.questao_04_node2.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wensttay de Sousa Alencar <yattsnew@gmail.com>
 * @date 11/03/2017, 17:43:03
 */
public class PostgresDao implements Dao{

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/questao_04";
    static final String USER = "postgres";
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
