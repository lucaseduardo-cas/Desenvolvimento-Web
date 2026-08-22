package persistencia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Manutencao;
import util.Conexao;

public class PManutencao {
    private Connection cnn;

    public PManutencao() {
        cnn = Conexao.getConnection();
    }

    // 1. INCLUIR (6 colunas + o ID que o banco gera sozinho)
    public void incluir(Manutencao m) {
        try {
            // Note que usei os nomes EXATOS do seu print do PgAdmin
            String sql = "INSERT INTO manutencao (descricao_servico, valor_total, data_realizacao, quilometragem, pecas_trocadas, automovel_id) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, m.getDescricaoServico());
            prd.setDouble(2, m.getValorTotal());
            prd.setDate(3, java.sql.Date.valueOf(m.getDataRealizacao()));
            prd.setInt(4, m.getQuilometragem());
            prd.setBoolean(5, m.getPecasTrocadas());
            prd.setLong(6, m.getAutomovel().getCodigo());
            prd.execute();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 2. LISTAR (Buscando todas as 7 colunas)
    public List<Manutencao> listar() {
        List<Manutencao> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM manutencao";
            Statement st = cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            while (rst.next()) {
                Manutencao m = new Manutencao();
                // Aqui preenchemos todas as variáveis da classe Manutencao
                m.setId(rst.getLong("id"));
                m.setDescricaoServico(rst.getString("descricao_servico"));
                m.setValorTotal(rst.getDouble("valor_total"));
                m.setDataRealizacao(rst.getDate("data_realizacao").toLocalDate());
                m.setQuilometragem(rst.getInt("quilometragem"));
                m.setPecasTrocadas(rst.getBoolean("pecas_trocadas"));
                // O automovel_id você pode tratar depois se precisar do objeto completo
                lista.add(m);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return lista;
    }

    // 3. ALTERAR
    public void alterar(Manutencao m) {
        try {
            String sql = "UPDATE manutencao SET descricao_servico=?, valor_total=? WHERE id=?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, m.getDescricaoServico());
            prd.setDouble(2, m.getValorTotal());
            prd.setLong(3, m.getId());
            prd.execute();
        } catch (Exception e) { e.printStackTrace(); }
    }

    // 4. CONSULTAR
    public Manutencao consultar(long id) {
        Manutencao m = null;
        try {
            String sql = "SELECT * FROM manutencao WHERE id = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setLong(1, id);
            ResultSet rst = prd.executeQuery();
            if (rst.next()) {
                m = new Manutencao();
                m.setId(rst.getLong("id"));
                m.setDescricaoServico(rst.getString("descricao_servico"));
                m.setValorTotal(rst.getDouble("valor_total"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return m;
    }

    // 5. EXCLUIR
    public void excluir(long id) {
        try {
            String sql = "DELETE FROM manutencao WHERE id = ?";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setLong(1, id);
            prd.execute();
        } catch (Exception e) { e.printStackTrace(); }
    }
}