/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
// import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    // Método construtor para carregar a conexão
    public PMarca(){
        // O cnn recebe a conexão que o método util.Conexao já preparou
        this.cnn = Conexao.getConnection();
    }
    
    
    public Marca consultar(int codigo){

        Marca marca = new Marca();
        
        try {
            
            String sql = "SELECT * FROM marca WHERE codigo = ?";
            PreparedStatement prd = this.cnn.prepareStatement(sql);
            prd.setInt(1, codigo);
            
            ResultSet rst = prd.executeQuery();
            
            if(rst.next()){
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
    
    
    public List<Marca> listar(){
        
        List<Marca> lista = new ArrayList<Marca>();
        
        try {
            
            String sql = "SELECT * FROM marca";
            Statement prd = this.cnn.createStatement();
            
            ResultSet rst = prd.executeQuery(sql);
            
            while(rst.next()){
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
    
    
}
