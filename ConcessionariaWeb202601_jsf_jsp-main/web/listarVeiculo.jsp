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

            <table class="table table-striped table-hover border align-middle">
                <thead class="table-dark">
                    <tr>
                        <th>Código</th>
                        <th>Placa</th>
                        <th>Modelo / Versão</th>
                        <th>Ano</th>
                        <th>Proprietário</th>
                        <th class="text-center">Ações</th>
                    </tr>
                </thead>
                <tbody id="tabela-veiculos-corpo">
                </tbody>
            </table>
            <div id="aviso-veiculo-vazio" class="text-center text-muted py-3 d-none">
                Nenhum veículo em manutenção cadastrado para este operador.
            </div>
        </div>

        <script>
            var usuarioAtivo = localStorage.getItem("usuarioLogado") || "global";
            var chaveVeiculos = "banco_veiculos_" + usuarioAtivo + ".json";

            function obterVeiculos() {
                var dados = localStorage.getItem(chaveVeiculos);
                if (!dados) {
                    if (usuarioAtivo === "admin" || usuarioAtivo === "lucas") {
                        var inicial = [
                            { id: 1, placa: "BRA2E19", modelo: "Honda Civic EX", ano: "2004", proprietario: "Lucas Eduardo" },
                            { id: 2, placa: "OGX4A12", modelo: "Volkswagen Saveiro", ano: "2016", proprietario: "Carlos Silva" }
                        ];
                        localStorage.setItem(chaveVeiculos, JSON.stringify(inicial));
                        return inicial;
                    }
                    return [];
                }
                return JSON.parse(dados);
            }

            function renderizarVeiculos() {
                var lista = obterVeiculos();
                var corpo = document.getElementById("tabela-veiculos-corpo");
                var aviso = document.getElementById("aviso-veiculo-vazio");
                corpo.innerHTML = "";

                if (lista.length === 0) {
                    aviso.classList.remove("d-none");
                    return;
                }
                aviso.classList.add("d-none");

                lista.forEach(function(v) {
                    var linha = document.createElement("tr");
                    linha.innerHTML = `
                        <td>\${v.id}</td>
                        <td><span class="badge bg-dark">\${v.placa || '---'}</span></td>
                        <td class="fw-bold">\${v.modelo || '---'}</td>
                        <td><span class="badge bg-secondary">\${v.ano || '---'}</span></td>
                        <td>\${v.proprietario || '---'}</td>
                        <td class="text-center">
                            <a href="historicoVeiculo.jsp?id=\${v.id}" class="btn btn-sm btn-info text-white me-1 fw-bold">📋 Histórico</a>
                            <a href="manterVeiculo.jsp?id=\${v.id}" class="btn btn-sm btn-warning me-1">Alterar</a>
                            <button class="btn btn-sm btn-danger" onclick="excluirVeiculoLocal(\${v.id})">Excluir</button>
                        </td>
                    `;
                    corpo.appendChild(linha);
                });
            }

            function excluirVeiculoLocal(id) {
                if (confirm("Deseja realmente remover este veículo do SGO?")) {
                    var lista = obterVeiculos();
                    var listaFiltrada = lista.filter(function(v) { return v.id !== id; });
                    localStorage.setItem(chaveVeiculos, JSON.stringify(listaFiltrada));
                    renderizarVeiculos();
                }
            }

            renderizarVeiculos();
        </script>
    </body>
</html>
