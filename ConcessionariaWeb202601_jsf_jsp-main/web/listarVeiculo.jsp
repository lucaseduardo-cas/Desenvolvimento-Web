<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listar Veículos</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp" />
        <div class="card shadow p-4 mt-3">
            <h4 class="text-secondary mb-4">Lista de Veículos em Manutenção</h4>
            <div class="mb-3 text-end">
                <a href="manterVeiculo.jsp" class="btn btn-success shadow-sm">Novo Veículo</a>
            </div>
            <table class="table table-striped table-hover border">
                <thead class="table-dark">
                    <tr>
                        <th>Código</th>
                        <th>Placa</th>
                        <th>Modelo / Ano</th>
                        <th>Proprietário</th>
                        <th colspan="2" class="text-center">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>BRA2E19</td>
                        <td>Honda Civic EX 2004</td>
                        <td>Lucas Eduardo</td>
                        <td class="text-center"><a href="manterVeiculo.jsp" class="btn btn-sm btn-warning">Alterar</a></td>
                        <td class="text-center"><a href="#" class="btn btn-sm btn-danger" onclick="return confirm('Confirma a exclusão?')">Excluir</a></td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>OGX4A12</td>
                        <td>Volkswagen Saveiro 2016</td>
                        <td>Carlos Silva</td>
                        <td class="text-center"><a href="manterVeiculo.jsp" class="btn btn-sm btn-warning">Alterar</a></td>
                        <td class="text-center"><a href="#" class="btn btn-sm btn-danger" onclick="return confirm('Confirma a exclusão?')">Excluir</a></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
