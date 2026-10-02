package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class PokemonDAO {
    public ArrayList<PokemonTO> listAll(){
        ArrayList<PokemonTO> pokemons = new ArrayList<>();
        PokemonTO pokemon = new PokemonTO(
                1L,
                "Bulbassauro",
                0.7,
                6.9,
                "Planta",
                LocalDate.now()
        );
        pokemons.add(pokemon);

        return pokemons;
    }
}
