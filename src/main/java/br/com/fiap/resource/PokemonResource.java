package br.com.fiap.resource;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pokedex")
@RestController
public class PokemonResource {
    private PokemonBO pokemonBO = new PokemonBO();
    @GetMapping
    public ResponseEntity<List<PokemonTO>> findAll(){
        List<PokemonTO> pokemons = pokemonBO.findAll();
        if (pokemons != null)
            return ResponseEntity.status(HttpStatus.OK).body(pokemons);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<?> findById(@PathVariable Long codigo){
        PokemonTO pokemon = pokemonBO.findByCodigo(codigo);
        if (pokemon != null)
            return ResponseEntity.status(HttpStatus.OK).body(pokemon);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).
                    body("Pokemon não encontrado");
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PokemonTO pokemon){
        try {
            PokemonTO response = pokemonBO.save(pokemon);
            return ResponseEntity.status(HttpStatus.CREATED).body(pokemon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                    body("Erro ao salvar pokemon");
        }

    }
}
