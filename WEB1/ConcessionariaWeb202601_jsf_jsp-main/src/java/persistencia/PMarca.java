/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Marca;
import util.Conexao;

/**
 *
 * @author heube
 */
public class PMarca {

    //Objeto local para a conexão com o banco
    private Connection cnn;

    //Metodo construtor para carregar a conexão
    public PMarca() {
        cnn = Conexao.getConnection();
    }

    public Marca consultar(int codigo) {

        Marca marca = new Marca();

        try {

            String sql = "SELECT * FROM marca WHERE codigo = ?";
            PreparedStatement prd = this.cnn.prepareStatement(sql);
            prd.setInt(1, codigo);

            ResultSet rst = prd.executeQuery();

            if (rst.next()) {
                //se entrar aqui, é porque achou algum registro
                marca.setCodigo(rst.getInt("codigo"));
                marca.setNome(rst.getString("nome"));
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return marca;
    }

    public List<Marca> listar() {

        List<Marca> lista = new ArrayList<Marca>();

        try {

            String sql = "SELECT * FROM marca";
            Statement prd = this.cnn.createStatement();

            ResultSet rst = prd.executeQuery(sql);

            while (rst.next()) {
                Marca marca = new Marca();

                marca.setCodigo(rst.getInt("codigo"));
                marca.setNome(rst.getString("nome"));

                lista.add(marca);
            }

            rst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    //Método para incluir uma nova marca
    public void incluir(Marca marca) throws SQLException {

        try {

            this.cnn.setAutoCommit(false);
            String sql = "INSERT INTO marca(nome) VALUES(?)";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, marca.getNome());

            //Executa a inserção do dado na tabela
            prd.executeUpdate();

            //Recupera o valor gerado automaticamente pelo banco
            sql = "SELECT currval('marca_codigo_seq') as codigo";
            Statement stm = this.cnn.createStatement();
            ResultSet rst = stm.executeQuery(sql);

            if (rst.next()) {
                marca.setCodigo(rst.getInt("codigo"));
            }

            //Dando certo, fecha a conexão e grava as alterações no banco
            rst.close();
            this.cnn.commit();

        } catch (Exception e) {

            this.cnn.rollback();

        } finally {
            this.cnn.setAutoCommit(true);
        }

    }//Fim do método

    public void alterar(Marca marca) throws SQLException {

        try {
            String sql = "UPDATE marca SET nome = ? WHERE codigo = ?";
            PreparedStatement prd = this.cnn.prepareStatement(sql);
            prd.setString(1, marca.getNome());
            prd.setInt(2, marca.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void excluir(Marca marca) throws SQLException {

        try {
            String sql = "DELETE FROM marca WHERE codigo = ?";
            PreparedStatement prd = this.cnn.prepareStatement(sql);
            prd.setInt(1, marca.getCodigo());

            prd.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}//Fim da classe
