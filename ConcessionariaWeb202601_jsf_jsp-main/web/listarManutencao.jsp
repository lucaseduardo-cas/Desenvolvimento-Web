<%@page import="model.Manutencao"%>
<%@page import="persistencia.PManutencao"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Manutenções</title>
</head>
<body>
    <h1>Manutenções Realizadas</h1>
    <a href="inserirManutencao.jsp">Inserir Nova Manutenção</a>
    <br><br>
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Descrição</th>
                <th>Valor</th>
                <th>Data</th>
            </tr>
        </thead>
        <tbody>
            <%
                PManutencao persistencia = new PManutencao();
                List<Manutencao> lista = persistencia.listar();
                for(Manutencao m : lista) {
            %>
            <tr>
                <td><%= m.getId() %></td>
                <td><%= m.getDescricaoServico() %></td>
                <td>R$ <%= m.getValorTotal() %></td>
                <td><%= m.getDataRealizacao() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>