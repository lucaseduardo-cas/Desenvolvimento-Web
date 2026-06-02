<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Oficina - Gestão de Manutenções</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp" />
        <div class="card shadow p-5 text-center mt-5">
            <h1 class="display-5 text-primary mb-3">SGO - Sistema de Gestão de Oficinas</h1>
            <p class="lead text-muted">Módulo de Controle de Manutenções Práticas (Web I)</p>
            <hr class="my-4">
            <p>Utilize o menu superior ou o botão abaixo para gerenciar a listagem de entrada dos veículos.</p>
            <div class="mt-4">
                <a href="listarVeiculo.jsp" class="btn btn-primary btn-lg shadow">Acessar Painel de Veículos</a>
            </div>
        </div>
    </body>
</html>
