package br.com.fiap.resource;

import br.com.fiap.bo.PokemonBO;
import br.com.fiap.to.PokemonTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/pokedex")
@RestController
public class PokemonResource {
    private PokemonBO pokemonBO = new PokemonBO();
    @GetMapping
    public ResponseEntity<List<PokemonTO>> listAll(){
        List<PokemonTO> pokemons = pokemonBO.findAll();
        if (pokemons != null)
            return ResponseEntity.status(HttpStatus.OK).body(pokemons);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
