/**
 * Classe {@code FuncionarioController} que gerencia a interface entre as funcionalidades de funcionarios e as páginas HTML
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

import edu.pitagoras.modeloDAO.FuncionarioDAO;
import edu.pitagoras.modelodados.Funcionario;

@Controller
public class FuncionarioController {
	
    
        /**
         * Método {@code novoFuncionario} que cria um novo objeto de funcionario e chama a página novofuncionario.html
         * @param model
         * @return novofuncionario.html
         */
	@RequestMapping(value = "/novofuncionario", method = RequestMethod.GET)
        public String novoFuncionario(Model model) {
		List<String> cargos = new ArrayList<String>();
		cargos.add("Diretor");
		cargos.add("Gerente");
		cargos.add("Supervisor");
		cargos.add("Tecnico");
		
		model.addAttribute("cargos", cargos);
		model.addAttribute("funcionario", new Funcionario());
        return "novofuncionario";
    }
	
        
        /**
         * Método {@code salvarfuncionario} que recebe um novo objeto funcionario e incia o procedimento de gravação no bando de dados
         * @param funcionario
         * @return página novofuncionario.html
         * @throws SQLException 
         */
	@RequestMapping(value = "/salvarfuncionario", method = RequestMethod.POST)
	public String salvarfuncionario(@ModelAttribute Funcionario funcionario, Model model) throws SQLException {
            funcionario.informacoesFuncionario();
            try {
                FuncionarioDAO.adicionarFuncionario(funcionario);
            } catch (SQLException e) {
                List<String> erros = new ArrayList<>();
                erros.add("Erro ao inserir registro no banco de dados");
                model.addAttribute("erros", erros);
                return "novofuncionario";
            }
            model.addAttribute("funcionario", new Funcionario());
            return "novofuncionario";
	}
	
        
        /**
         * Método {@code funcionarios} que inicia a rotina de busca e listagem de funcionarios salvos no Banco de Dados
         * @param model
         * @return página funcionarios.html
         * @throws SQLException 
         */
	@RequestMapping(value = "/funcionarios", method = RequestMethod.GET)
        public String funcionarios(Model model) throws SQLException {
		model.addAttribute("funcionarios", FuncionarioDAO.buscarFuncionarios());
        return "funcionarios";
    }
}