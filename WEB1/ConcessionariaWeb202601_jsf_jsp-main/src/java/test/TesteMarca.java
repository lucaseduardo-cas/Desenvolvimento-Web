/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import persistencia.PMarca;
import model.Marca;

/**
 *
 * @author heube
 */
public class TesteMarca {

    public static void main(String[] args) {

        PMarca persistencia = new PMarca();

        // Lista todas as marcas cadastradas
        List<Marca> lista = persistencia.listar();
        for (Marca marca : lista) {
            System.out.println("Codigo: " + marca.getCodigo());
            System.out.println("Nome: " + marca.getNome());
        }

        // Exemplos de inclusão/alteração/exclusão (descomente para testar):
//        Marca marca = new Marca();
//        marca.setCodigo(15);
//        marca.setNome("MARCA ALTERADA COM SUCESSO.");
//        try {
//            persistencia.incluir(marca);
//            persistencia.alterar(marca);
//            persistencia.excluir(marca);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

}
