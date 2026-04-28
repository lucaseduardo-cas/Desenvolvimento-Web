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

        //Marca marca = persistencia.consultar(1);
        List<Marca> lista = persistencia.listar();

        for (Marca marca : lista) {
            System.out.println("Codigo: " + marca.getCodigo());
            System.out.println("Nome: " + marca.getNome());
        }

    }

}
