<%@page import="model.Manutencao"%>
<%@page import="model.Automovel"%>
<%@page import="persistencia.PManutencao"%>
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Pegando os dados do formulário
    String desc = request.getParameter("descricao");
    double valor = Double.parseDouble(request.getParameter("valor"));
    int km = Integer.parseInt(request.getParameter("km"));
    int autoId = Integer.parseInt(request.getParameter("auto_id"));

    // Montando o objeto
    Manutencao m = new Manutencao();
    m.setDescricaoServico(desc);
    m.setValorTotal(valor);
    m.setQuilometragem(km);
    m.setDataRealizacao(LocalDate.now());
    m.setPecasTrocadas(true);
    
    Automovel a = new Automovel();
    a.setCodigo(autoId);
    m.setAutomovel(a);

    // Salvando no banco "na unha"
    PManutencao persistencia = new PManutencao();
    persistencia.incluir(m);

    // Redireciona de volta para a lista
    response.sendRedirect("listarManutencao.jsp");
%>