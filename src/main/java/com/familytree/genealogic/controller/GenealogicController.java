package com.familytree.genealogic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.familytree.genealogic.model.Pessoa;
import com.familytree.genealogic.repository.PessoaRepository;

@Controller
public class GenealogicController {
	

//	if (Pessoa.pessoalAtual == null)
	

	@Autowired
	private PessoaRepository pR;

	@RequestMapping("/1")
	public String index2() {
		Pessoa p = new Pessoa();
		Pessoa irmao1 = new Pessoa();
		Pessoa irmao2 = new Pessoa();

		p.setNome("raiz");
////		p.getPessoas().add(p);
		irmao1.setNome("caique");
		irmao1.getIrmaos().add(irmao2);
////		irmao1.getIrmaos().add(irmao2);
		irmao2.setNome("joao");
////		irmao2.getIrmaos().add(irmao1);
		p.getIrmaos().add(irmao1);
////		irmao1.getPessoas().add(p);
////		irmao2.getPessoas().add(p);
//		
		pR.save(irmao2);
		pR.save(irmao1);
		pR.save(p);

//		Pessoa cancelada = pR.findById(18);
//		pR.delete(cancelada);

		System.out.println("------------------------------");
		for (Pessoa pessoa : pR.findAll()) {
			System.out.println(pessoa.getNome());
			System.out.println("Irmaos:");
			for (Pessoa irmaos : pessoa.getIrmaos()) {
				System.out.println(irmaos.getNome());
			}
			for (Pessoa irmaos2 : pessoa.getIrmaosReferencia()) {
				System.out.println(irmaos2.getNome());
			}
			System.out.println("------------------------------");
		}

		return "index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		Pessoa.pessoalAtual = buscarPessoas().get(0);
		mv.addObject("pessoa", Pessoa.pessoalAtual);
		return mv;
	}


	@RequestMapping(value = "familytree-{idPessoa}", method = RequestMethod.GET)
	public ModelAndView arvoreGenealogica(@PathVariable("idPessoa") int idPessoa) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas = buscarPessoas();
		Pessoa.pessoalAtual = pR.findById(idPessoa);

		ModelAndView mv = new ModelAndView("familytree");
		mv.addObject("pessoa", Pessoa.pessoalAtual);

		List<Pessoa> todosIrmaos = Pessoa.pessoalAtual.getIrmaos();
		todosIrmaos.addAll(Pessoa.pessoalAtual.getIrmaosReferencia());

		Iterable<Pessoa> irmaos = todosIrmaos;
		mv.addObject("irmaos", irmaos);

		List<Pessoa> acharFilhos = new ArrayList<Pessoa>();
		for (Pessoa p : pessoas) {
			if (p.getMae() == Pessoa.pessoalAtual || p.getPai() == Pessoa.pessoalAtual) {
				acharFilhos.add(p);
			}
		}

		Iterable<Pessoa> filhos = acharFilhos;
		mv.addObject("filhos", filhos);

		return mv;
	}

	public List<Pessoa> buscarPessoas() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for (Pessoa p : pR.findAll()) {
			pessoas.add(p);
		}
		return pessoas;
	}

	@RequestMapping(value = "/cadastro-pessoa", method = RequestMethod.GET)
	public ModelAndView cadastroPessoa() {
		ModelAndView mv = new ModelAndView("cadastrar_pessoa");
		mv.addObject("pessoa", Pessoa.pessoalAtual);
	
		return mv;
	}

	@RequestMapping(value = "/cadastro-pessoa/{tipoCadastro}", method = RequestMethod.POST)
	public String cadastrarPessoa(Pessoa pessoa, @PathVariable("tipoCadasttro") String tipo) {
		switch (tipo) {
		case "PAIS":
			if (pessoa.getSexo().equalsIgnoreCase("MASCULINO")) {
				Pessoa.pessoalAtual.setPai(pessoa);
			} else {
				Pessoa.pessoalAtual.setMae(pessoa);
			}
			break;
		case "FILHOS":
			if (Pessoa.pessoalAtual.getSexo().equalsIgnoreCase("MASCULINO")) {
				pessoa.setPai(Pessoa.pessoalAtual);
			}else {
				pessoa.setMae(Pessoa.pessoalAtual);
			}
			break;
		case "IRMAOS": Pessoa.pessoalAtual.getIrmaos().add(pessoa);
			break;
		default:
			break;
		}
		
		pR.save(pessoa);
		pR.save(Pessoa.pessoalAtual);


		return "redirect:/familytree-" + Pessoa.pessoalAtual.getId();
	}

}