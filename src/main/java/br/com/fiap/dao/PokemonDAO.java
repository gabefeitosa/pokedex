package br.com.fiap.dao;

import br.com.fiap.to.PokemonTO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PokemonDAO {
    public ArrayList<PokemonTO> listAll(){
        ArrayList<PokemonTO> pokemons = new ArrayList<>();
        String sql = "SELECT * FROM pokemon";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                while (rs.next()) {
                    PokemonTO pokemon = new PokemonTO();
                    pokemon.setNome(rs.getString("nome"));
                    pokemon.setAltura(rs.getDouble("altura"));
                    pokemon.setPeso(rs.getDouble("peso"));
                    pokemon.setCategoria(rs.getString("categoria"));
                    pokemon.setDataDaCaptura(rs.getDate("data_da_captura").toLocalDate());
                    pokemons.add(pokemon);
                }
            } else{
                    return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta\n" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pokemons;
    }

    public PokemonTO findByCodigo(Long codigo){
        PokemonTO pokemon = new PokemonTO();
        String sql = "SELECT * FROM pokemon WHERE codigo = ?";
        try (PreparedStatement ps = ConnectionFactory.getConnection().prepareStatement(sql))
        {
            ps.setLong(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs != null){
                pokemon.setCodigo(rs.getLong("codigo"));
                pokemon.setNome(rs.getString("nome"));
                pokemon.setAltura(rs.getDouble("altura"));
                pokemon.setPeso(rs.getDouble("peso"));
                pokemon.setCategoria(rs.getString("categoria"));
                pokemon.setDataDaCaptura(rs.getDate("data_da_captura").toLocalDate());
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta\n" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection();
        }
        return pokemon;
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
