package test; // Ajuste o pacote para apenas 'test'

import model.Manutencao;
import model.Automovel;
import persistencia.PManutencao;
// import java.time.LocalDate;
import java.util.List;

public class TesteManutencao {
        public static void main(String[] args) {
        PManutencao persistencia = new PManutencao();
        
        // 1. INCLUIR
        Manutencao m = new Manutencao();
        m.setDescricaoServico("Teste Completo CRUD");
        m.setValorTotal(500.0);
        m.setDataRealizacao(java.time.LocalDate.now());
        m.setQuilometragem(10000);
        m.setPecasTrocadas(false);
        Automovel auto = new Automovel(); auto.setCodigo(1); 
        m.setAutomovel(auto);
        
        persistencia.incluir(m);
        System.out.println("1. INCLUIR: Sucesso");

        // 2. LISTAR (Para pegar o ID do que acabamos de inserir)
        List<Manutencao> lista = persistencia.listar();
        long idTeste = lista.get(lista.size() - 1).getId();
        System.out.println("2. LISTAR: Encontrados " + lista.size() + " registros");

        // 3. CONSULTAR
        Manutencao mConsultada = persistencia.consultar(idTeste);
        System.out.println("3. CONSULTAR: Achou: " + mConsultada.getDescricaoServico());

        // 4. ALTERAR
        mConsultada.setDescricaoServico("SERVIÇO ALTERADO");
        persistencia.alterar(mConsultada);
        System.out.println("4. ALTERAR: Sucesso");

        // 5. EXCLUIR
        persistencia.excluir(idTeste);
        System.out.println("5. EXCLUIR: Sucesso");
    }
}