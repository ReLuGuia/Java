import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/agenda_db?useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String SENHA = ""; // Coloque sua senha aqui, se houver

    public static Connection conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado", e);
        }
    }

    public static void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS contatos (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "nome VARCHAR(100) NOT NULL," +
                     "email VARCHAR(100) NOT NULL," +
                     "telefone VARCHAR(20) NOT NULL)";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {  // Substituição do var por Statement
            stmt.execute(sql);
            System.out.println("Tabela criada/verificada com sucesso.");
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }
}