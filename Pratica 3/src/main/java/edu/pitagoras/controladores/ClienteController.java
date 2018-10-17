/**
 * Classe {@code ClienteController} que gerencia a interface entre as funcionalidades de clientes e as páginas HTML
 * @author Pedro Resende
 * @since 1.0
 */

package edu.pitagoras.controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.pitagoras.modeloDAO.ClienteDAO;
import edu.pitagoras.modelodados.Cliente;

@Controller
public class ClienteController {
	
    
        /**
         * Método {@code novoCliente} que cria um novo objeto de cliente e chama a página novocliente.html
         * @param model
         * @return novocliente.html
         */
	@RequestMapping(value = "/novocliente", method = RequestMethod.GET)
        public String novoCliente(Model model) {
		List<String> profissoes = new ArrayList<String>();
		profissoes.add("Administrador");
		profissoes.add("Médico");
		profissoes.add("Professor");
		profissoes.add("Jogador de Futebol");
		
		model.addAttribute("profissoes", profissoes);
		model.addAttribute("cliente", new Cliente());
        return "novocliente";
    }
	/**
         * Método {@code salvarcliente} que recebe um novo objeto cliente e incia o procedimento de gravação no bando de dados
         * @param Objeto cliente
         * @return página novoCliente.html
         * @throws SQLException 
         */
	@RequestMapping(value = "/salvarcliente", method = RequestMethod.POST)
	public String salvarcliente(@ModelAttribute Cliente cliente, Model model) throws SQLException {
            cliente.informacoesCliente();
            try {
                ClienteDAO.adicionarCliente(cliente);
            } catch (SQLException e) {
                List<String> erros = new ArrayList<>();
                erros.add("Erro ao inserir registro no banco de dados");
                model.addAttribute("erros", erros);
                return "novocliente";
            }
            model.addAttribute("cliente", new Cliente());
            return "novocliente";
	}
	/**
         * Método {@code clientes} que inicia a rotina de busca e listagem de clientes salvos no Banco de Dados
         * @param model
         * @return página clientes.html
         * @throws SQLException 
         */
	@RequestMapping(value = "/clientes", method = RequestMethod.GET)
        public String clientes(Model model) throws SQLException {
		model.addAttribute("clientes", ClienteDAO.buscarClientes());
        return "clientes";
    }
}