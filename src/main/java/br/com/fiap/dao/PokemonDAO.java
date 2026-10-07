package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public PokemonTO save(PokemonTO pokemon){
        String sql = "insert into pokemon(nome, altura, peso, categoria," +
                " data_da_captura) values(?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setString(1, pokemon.getNome());
            ps.setDouble(2, pokemon.getAltura());
            ps.setDouble(3, pokemon.getPeso());
            ps.setString(4, pokemon.getCategoria());
            ps.setDate(5, Date.valueOf(pokemon.getDataDaCaptura()));
            if (ps.executeUpdate() > 0)
                return pokemon;
        } catch (SQLException e) {
            System.out.println("Erro de SQL\n" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return null;
    }

}
