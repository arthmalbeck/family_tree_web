package com.familytree.genealogic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.familytree.genealogic.model.Pessoa;
import com.familytree.genealogic.model.Usuario;
import com.familytree.genealogic.repository.PessoaRepository;
import com.familytree.genealogic.repository.UsuarioRepository;

@Controller
public class LoginController {

	@Autowired
	private static PessoaRepository pR;
	
	@Autowired
	UsuarioRepository ur;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		verificaSession();
		mv.addObject("pessoa", Pessoa.pessoaAtual);
		mv.addObject("usuario", Usuario.user);
		return mv;
	}

	public static void verificaSession() {
		if(Pessoa.pessoaAtual == null) {
			System.out.println(pR.findById(1).getNome());
			Pessoa.pessoaAtual = pR.findById(1);
		}
		if(Usuario.user == null) {
			Usuario.user = new Usuario(1,"Visitante");
		}
	}

	@RequestMapping("/criarAdm")
	public String sucesso() {
		Usuario user = new Usuario();
		user.setNome("Administrador");
		user.setNickname("admin");
		user.setSenha("praticas123");
		ur.save(user);
		
		return "redirect:/falha";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		Usuario.user = new Usuario(1,"Visitante");
	
		if(Pessoa.pessoaAtual == null) {
			Pessoa.pessoaAtual = pR.findById(1);
		}
		return "redirect:/";
	}

	@RequestMapping("/falha")
	public String falha() {
		return "falha.xhtml";
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String formUser(Usuario usuario) {
		for (Usuario u : ur.findAll()) {
			if (usuario.getNickname().equals(u.getNickname()) && usuario.getSenha().equals(u.getSenha())) {
				Usuario.user = u;
				return "redirect:/";
			}
		}
		return "redirect:/falha";
	}

}
