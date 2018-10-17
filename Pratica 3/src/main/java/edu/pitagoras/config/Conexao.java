/**
 * Classe {@code Conexao} cria a conexão com o banco de dados
 * @suthor Pedro Resende
 * @since 1.0
 */

package edu.pitagoras.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class Conexao {
    
    /**
     * Dados de conexão com o banco de dados Postgress
     */
    private static final String STRING_CONEXAO = 
            "jdbc:postgresql://localhost:5432/postgres";
    private static final String USUARIO_CONEXAO = "postgres";
    private static final String SENHA_CONEXAO = "postgres";
    
   /**
    * Método que cria a sessão com o Banco
    * @return Objeto Connection
    * @throws SQLException
    */
    public static Connection getConnection() throws SQLException {
        return DriverManager
            .getConnection(STRING_CONEXAO, USUARIO_CONEXAO, SENHA_CONEXAO);
    }
}
