package br.edu.unoesc.exemplo_H2.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.unoesc.exemplo_H2.model.Aluno;
import br.edu.unoesc.exemplo_H2.repository.AlunoRepository;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {
	@Autowired 
	private AlunoRepository repositorio; 
	
	@GetMapping
	public Iterable<Aluno> listarTudo() {
		return repositorio.findAll();
	}
	
	@GetMapping("/find")
	public List<Aluno> listarComFiltro(@RequestParam("filtro") String filtro) {
		return repositorio.findByFiltro(filtro);
	}
	
	@GetMapping("/salario/{salario}")
	public List<Aluno> listarPorSalario(@PathVariable BigDecimal salario) {
		return repositorio.findBySalario(salario);
	}
	
    @PostMapping()
    public Aluno incluir(@RequestBody Aluno aluno) {
        return repositorio.save(aluno);
    }
 
    @PutMapping("/{id}")
    public Aluno atualizar(@PathVariable("id") Long id, @RequestBody Aluno aluno) throws RuntimeException {
    	Optional<Aluno> alunoExistente = repositorio.findById(id);
    	
    	if (alunoExistente.isPresent()) {
    		Aluno alunoAtualizado = alunoExistente.get();
    		alunoAtualizado.setNome(aluno.getNome());
    		alunoAtualizado.setSalario(aluno.getSalario());
    		alunoAtualizado.setNascimento(aluno.getNascimento());
    		
    		return repositorio.save(alunoAtualizado);
    	} else {
    		throw new RuntimeErrorException(null, "Aluno não encontrado com o ID: " + id);
    	}
    }
 
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id) {
		repositorio.deleteById(id);
    }
}