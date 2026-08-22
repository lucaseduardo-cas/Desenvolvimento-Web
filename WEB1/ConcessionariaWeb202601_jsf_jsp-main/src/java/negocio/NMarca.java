/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.sql.SQLException;
import java.util.List;
import model.Marca;
import persistencia.PMarca;

/**
 *
 * @author heube
 */
public class NMarca {

    PMarca persistencia;

    public NMarca() {
        persistencia = new PMarca();
    }

    public void salvar(Marca marca) throws SQLException, Exception {

        //Método para validar se o objeto marca está correto
        validar(marca);

        if (marca.getCodigo() == 0) {
            persistencia.incluir(marca);
        } else {
            persistencia.alterar(marca);
        }

    }

    
    public void excluir(Marca marca) throws SQLException{
        persistencia.excluir(marca);
    }
    
    public Marca consultar(int codigo){
        return persistencia.consultar(codigo);
    }
    
    public List<Marca> listar(){
        return persistencia.listar();
    }
    
    
    
    private void validar(Marca marca) throws Exception {

        if (marca.getNome() == null) {
            throw new Exception("É necessário informar o nome da marca.");
        }

        if (marca.getNome().isEmpty()) {
            throw new Exception("É necessário informar o nome da marca.");
        }
    }

}
