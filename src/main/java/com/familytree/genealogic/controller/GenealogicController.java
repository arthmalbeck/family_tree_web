package com.familytree.genealogic.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.familytree.genealogic.model.DBFile;
import com.familytree.genealogic.model.Pessoa;
import com.familytree.genealogic.model.Usuario;
import com.familytree.genealogic.repository.CertidaoRepository;
import com.familytree.genealogic.repository.DBFileRepository;
import com.familytree.genealogic.repository.PessoaRepository;


@Controller
public class GenealogicController {
	
	@Autowired
    private DBFileRepository dbFileRepository;

	@Autowired
	private PessoaRepository pR;

	@Autowired
	private CertidaoRepository cR;

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
		Pessoa.pessoaAtual = pR.findById(2);
		LoginController.verificaSession();
		mv.addObject("pessoa", Pessoa.pessoaAtual);
		mv.addObject("usuario", Usuario.user);
		return mv;
	}
	
	@RequestMapping(value = "/galeria", method = RequestMethod.GET)
	public ModelAndView galeria() {
		ModelAndView mv = new ModelAndView("galeria");
		Pessoa.pessoaAtual = pR.findById(2);
		LoginController.verificaSession();
		mv.addObject("pessoa", Pessoa.pessoaAtual);
		mv.addObject("usuario", Usuario.user);
		List<DBFile> file = dbFileRepository.findAll();
		mv.addObject("numImagens", file.size());
		mv.addObject("imagens", file);
		
		return mv;
	}

	@RequestMapping(value = "familytree-{idPessoa}", method = RequestMethod.GET)
	public ModelAndView arvoreGenealogica(@PathVariable("idPessoa") int idPessoa) {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas = buscarPessoas();
		Pessoa.pessoaAtual = pR.findById(idPessoa);
		LoginController.verificaSession();
		System.out.println(Usuario.user.getNome() + "acucar");

		ModelAndView mv = new ModelAndView("familytree");
		mv.addObject("pessoa", Pessoa.pessoaAtual);

		List<Pessoa> todosIrmaos = Pessoa.pessoaAtual.getIrmaos();
		todosIrmaos.addAll(Pessoa.pessoaAtual.getIrmaosReferencia());

		Iterable<Pessoa> irmaos = todosIrmaos;
		mv.addObject("listaIrmaos", irmaos);

		List<Pessoa> acharFilhos = new ArrayList<Pessoa>();
		for (Pessoa p : pessoas) {
			if (p.getMae().getId() == Pessoa.pessoaAtual.getId() || p.getPai().getId() == Pessoa.pessoaAtual.getId()) {
				acharFilhos.add(p);
			}
		}
		Iterable<Pessoa> filhos = acharFilhos;
		mv.addObject("listaFilhos", filhos);

		mv.addObject("pai", "Pai");
		mv.addObject("mae", "Mae");
		mv.addObject("filhos", "Filhos");
		mv.addObject("irmaos", "Irmaos");
		mv.addObject("usuario", Usuario.user);

		return mv;
	}

	@RequestMapping(value = "formCadastroPessoa-{tipo}", method = RequestMethod.GET)
	public ModelAndView formCadPessoa(@PathVariable("tipo") String tipo) {
		ModelAndView mv = new ModelAndView("cadastroPessoa");
		if(Pessoa.pessoaAtual == null) {
			Pessoa.pessoaAtual = pR.findById(1);
			LoginController.verificaSession();
			ModelAndView mv2 = new ModelAndView("index");
			mv2.addObject("pessoa", Pessoa.pessoaAtual);
			mv2.addObject("usuario", Usuario.user);
			return mv2;
		}
		LoginController.verificaSession();
		mv.addObject("pessoa", Pessoa.pessoaAtual);
		mv.addObject("tipo", tipo);
		mv.addObject("usuario", Usuario.user);

		return mv;
	}

	@RequestMapping(value = "formEditCadastroPessoa-{tipo}-id{idPessoa}", method = RequestMethod.GET)
	public ModelAndView formEditCadPessoa(@PathVariable("tipo") String tipo, @PathVariable("idPessoa") int idPessoa) {
		ModelAndView mv = new ModelAndView("EditarPessoa");
		if(Pessoa.pessoaAtual == null) {
			Pessoa.pessoaAtual = pR.findById(1);
			LoginController.verificaSession();
			ModelAndView mv2 = new ModelAndView("index");
			mv2.addObject("pessoa", Pessoa.pessoaAtual);
			mv2.addObject("usuario", Usuario.user);
			return mv2;
		}
		mv.addObject("pessoa", Pessoa.pessoaAtual);
		mv.addObject("pessoaEdit", pR.findById(idPessoa));
		mv.addObject("tipo", tipo);
		mv.addObject("usuario", Usuario.user);

		return mv;
	}

	@RequestMapping(value = "formEditCadastroPessoa-{tipo}-id{idPessoa}", method = RequestMethod.POST)
	public String EditarPessoa(Pessoa pessoa, @PathVariable("tipo") String tipo,
			@PathVariable("idPessoa") int idPessoa) {
		pessoa.setId(idPessoa);
		switch (tipo) {
		case "Pai":
			Pessoa.pessoaAtual.setPai(pessoa);
			break;
		case "Mae":
			Pessoa.pessoaAtual.setMae(pessoa);
			break;
		case "Filhos":
			if (Pessoa.pessoaAtual.getSexo().equalsIgnoreCase("MASCULINO")) {
				pessoa.setPai(Pessoa.pessoaAtual);
			} else {
				pessoa.setMae(Pessoa.pessoaAtual);
			}
			break;
		case "Irmaos":
			pessoa.getIrmaos().add(Pessoa.pessoaAtual);
			break;
		default:
			break;
		}

		if (pessoa.getCertidaoNascimento() == null) {
			pessoa.setCertidaoNascimento(cR.findById(1));
		} else {
			cR.save(pessoa.getCertidaoNascimento());
		}

		if (pessoa.getCertidaoObito() == null) {
			pessoa.setCertidaoObito(cR.findById(1));
		} else {
			cR.save(pessoa.getCertidaoObito());
		}

		if (pessoa.getPai() == null) {
			pessoa.setPai(pR.findById(1));
		}

		if (pessoa.getMae() == null) {
			pessoa.setMae(pR.findById(1));
		}

		pR.save(pessoa);

		return "redirect:/familytree-" + Pessoa.pessoaAtual.getId();
	}

	@RequestMapping("/DeletarPessoa")
	public String deletarPessoa(int id) {
		Pessoa pessoa = pR.findById(id);
		for (Pessoa p : buscarPessoas()) {
			if (p.getPai().getId() == pessoa.getId()) {
				p.setPai(pR.findById(1));
				pR.save(p);
			}
			if (p.getMae().getId() == pessoa.getId()) {
				p.setMae(pR.findById(1));
				pR.save(p);
			}
		}
		pR.delete(pessoa);
		if (pessoa.getCertidaoNascimento() != cR.findById(1)) {
			cR.delete(pessoa.getCertidaoNascimento());
		}
		if (pessoa.getCertidaoObito() != cR.findById(1)) {
			cR.delete(pessoa.getCertidaoObito());
		}
		return "redirect:/familytree-" + Pessoa.pessoaAtual.getId();
	}

	@RequestMapping(value = "formCadastroPessoa-{tipo}", method = RequestMethod.POST)
	public String CadastrarPessoa(Pessoa pessoa, @PathVariable("tipo") String tipo) {
		switch (tipo) {
		case "Pai":
			Pessoa.pessoaAtual.setPai(pessoa);
			break;
		case "Mae":
			Pessoa.pessoaAtual.setMae(pessoa);
			break;
		case "Filhos":
			if (Pessoa.pessoaAtual.getSexo().equalsIgnoreCase("MASCULINO")) {
				pessoa.setPai(Pessoa.pessoaAtual);
			} else {
				pessoa.setMae(Pessoa.pessoaAtual);
			}
			break;
		case "Irmaos":
			Pessoa.pessoaAtual.getIrmaos().add(pessoa);
			break;
		default:
			break;
		}
		if (pessoa.getCertidaoNascimento() == null) {
			pessoa.setCertidaoNascimento(cR.findById(1));
		} else {
			cR.save(pessoa.getCertidaoNascimento());
		}

		if (pessoa.getCertidaoObito() == null) {
			pessoa.setCertidaoObito(cR.findById(1));
		} else {
			cR.save(pessoa.getCertidaoObito());
		}

		if (pessoa.getPai() == null) {
			pessoa.setPai(pR.findById(1));
		}

		if (pessoa.getMae() == null) {
			pessoa.setMae(pR.findById(1));
		}

		pR.save(pessoa);
		pR.save(Pessoa.pessoaAtual);

		return "redirect:/familytree-" + Pessoa.pessoaAtual.getId();
	}

	public List<Pessoa> buscarPessoas() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		for (Pessoa p : pR.findAll()) {
			pessoas.add(p);
		}
		return pessoas;
	}
	

}