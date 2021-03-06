/**
 * Classe {@code FuncionarioDAO} responsável por gerenciar os objetos de funcionarios no Banco de dados
 * @author Pedro Resende
 * @since 1.0
 */

package edu.pitagoras.modeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.pitagoras.config.Conexao;
import edu.pitagoras.modelodados.Funcionario;

public abstract class FuncionarioDAO {
    
     /**
     * Método {@code adicionarFuncionario} reponsável por inserir um novo registro de funcionario no Banco de Dados
     * @param funcionario
     * @throws SQLException 
     */
    public static void adicionarFuncionario(Funcionario funcionario) throws SQLException{
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "insert into funcionarios " +
            "(nome,cpf,data_de_nascimento,data_de_admissao, cargo)" +
            " values (?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, funcionario.getNome());
        stmt.setString(2, funcionario.getCpf());
        stmt.setString(3, funcionario.getDataNascimento());
        stmt.setString(4, funcionario.getDataAdmissao());
        stmt.setString(5, funcionario.getCargo());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    
     /**
     * Método {@code buscarFuncionarios} responsável por buscar todos os registros de funcionarios existentes no banco
     * e apresentá-los aos usuários
     * @param List<Funcionario>
     * @throws SQLException 
     */
    public static List<Funcionario> buscarFuncionarios() throws SQLException{
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "select * from funcionarios ";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        while (rs.next()) {
        	Funcionario funcionario = new Funcionario();
        	funcionario.setNome(rs.getString("nome"));
                funcionario.setCpf(rs.getString("cpf"));
                funcionario.setDataNascimento(rs.getString("data_de_nascimento"));
                funcionario.setDataAdmissao(rs.getString("data_de_admissao"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionarios.add(funcionario);
        }
        rs.close();
        stmt.close();
        con.close();
        return funcionarios;
    }
}
