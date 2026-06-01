<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manter Veículo</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp"/>
        <div class="card shadow mx-auto mt-4" style="max-width: 600px;">
            <div class="card-header bg-success text-white">
                <h4 class="mb-0">Manter Dados do Veículo</h4>
            </div>
            <div class="card-body p-4">
                <form method="POST" action="listarVeiculo.jsp">
                    <div class="mb-3">
                        <label class="form-label fw-bold">Código:</label>
                        <input type="text" readonly="readonly" class="form-control bg-light" name="codigo" value="1" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Placa:</label>
                        <input type="text" class="form-control" name="placa" value="BRA2E19" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Modelo / Ano:</label>
                        <input type="text" class="form-control" name="modelo" value="Honda Civic EX 2004" />
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Proprietário:</label>
                        <input type="text" class="form-control" name="proprietario" value="Lucas Eduardo" />
                    </div>
                    <div class="d-flex justify-content-between mt-4">
                        <input type="button" class="btn btn-secondary" value="Voltar" onclick="history.go(-1)" />
                        <input type="submit" class="btn btn-success px-4" value="Salvar" />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
