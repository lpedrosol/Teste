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
	
	@RequestMapping(value = "/funcionarios", method = RequestMethod.GET)
    public String funcionarios(Model model) throws SQLException {
		model.addAttribute("funcionarios", FuncionarioDAO.buscarFuncionarios());
        return "funcionarios";
    }
}