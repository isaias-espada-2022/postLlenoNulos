package domina.springboot.verduleria.back;

import java.net.URI;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@CrossOrigin // con esta línea logro que funcione desde Angular
@RequestMapping("/verduras")
public class VerduleriaController {
	
	private final VerduleriaRepository verduleriaRepository;
	
	private VerduleriaController(VerduleriaRepository verduleriaRepository) {
		this.verduleriaRepository = verduleriaRepository;
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<Verdura> findById(@PathVariable Long id) {
		Optional<Verdura> verduraOptional = verduleriaRepository.findById(id);
		if (verduraOptional.isPresent()) {
			return ResponseEntity.ok(verduraOptional.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping()
	private ResponseEntity<Void> create(@RequestBody Verdura nuevaVerdura, UriComponentsBuilder ucb) {
		System.out.println(nuevaVerdura);
		Verdura verdura = verduleriaRepository.save(nuevaVerdura);
		URI uriVerdura = ucb.path("verduras/{id}").buildAndExpand(verdura.id()).toUri();
		return ResponseEntity.created(uriVerdura).build();
	}
	
	@GetMapping
	private ResponseEntity<Iterable<Verdura>> findAll() {
		return ResponseEntity.ok(verduleriaRepository.findAll());
	}
}