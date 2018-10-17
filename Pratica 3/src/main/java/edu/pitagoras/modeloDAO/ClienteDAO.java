/**
 * Classe {@code ClienteDAO} responsável por gerenciar os objetos de clientes no Banco de dados
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
import edu.pitagoras.modelodados.Cliente;

public abstract class ClienteDAO {
    
    /**
     * Método {@code adicionarCliente} reponsável por inserir um novo registro de cliente no Banco de Dados
     * @param cliente
     * @throws SQLException 
     */
    
    public static void adicionarCliente(Cliente cliente) throws SQLException{
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "insert into clientes " +
            "(nome,cpf,celular,profissao)" +
            " values (?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, cliente.getNome());
        stmt.setString(2, cliente.getCpf());
        stmt.setString(3, cliente.getTelefoneCelular());
        stmt.setString(4, cliente.getProfissao());
        stmt.execute();
        stmt.close();
        con.close();
    }
    
    /**
     * Método {@code buscarClientes} responsável por buscar todos os registros de clientes existentes no banco
     * e apresentá-los aos usuários
     * @param List<Cliente>
     * @throws SQLException 
     */
    
    public static List<Cliente> buscarClientes() throws SQLException{
        Connection con = Conexao.getConnection();
        System.out.println("Conectado!");
        String sql = "select * from clientes ";
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        List<Cliente> clientes = new ArrayList<Cliente>();
        while (rs.next()) {
        	Cliente cliente = new Cliente();
        	cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefoneCelular(rs.getString("celular"));
                cliente.setProfissao(rs.getString("profissao"));
                clientes.add(cliente);
        }
        rs.close();
        stmt.close();
        con.close();
        return clientes;
    }
}
