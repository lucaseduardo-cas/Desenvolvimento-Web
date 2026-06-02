<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGO - Cadastro de Veículo</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp" />
        
        <div class="card shadow mx-auto" style="max-width: 600px;">
            <div class="card-header bg-primary text-white">
                <h5 class="mb-0" id="titulo-tela">Cadastrar Veículo na Oficina</h5>
            </div>
            <div class="card-body">
                <form onsubmit="return salvarVeiculoLocal(event)">
                    <input type="hidden" id="v_id">

                    <div class="mb-3">
                        <label class="form-label fw-bold">Placa do Veículo:</label>
                        <input type="text" id="v_placa" class="form-control" placeholder="Ex: BRA2E19" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Modelo / Versão:</label>
                        <input type="text" id="v_modelo" class="form-control" placeholder="Ex: Honda Civic EX 1.7" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Ano de Fabricação / Modelo:</label>
                        <input type="text" id="v_ano" class="form-control" placeholder="Ex: 2004" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label fw-bold">Nome do Proprietário / Cliente:</label>
                        <input type="text" id="v_proprietario" class="form-control" placeholder="Ex: Lucas Eduardo" required>
                    </div>
                    
                    <div class="d-flex gap-2 justify-content-end mt-4">
                        <a href="listarVeiculo.jsp" class="btn btn-outline-secondary">Cancelar</a>
                        <button type="submit" class="btn btn-primary px-4 fw-bold" id="btn-salvar">Salvar Veículo</button>
                    </div>
                </form>
            </div>
        </div>

        <script>
            var usuarioAtivo = localStorage.getItem("usuarioLogado") || "global";
            var chaveVeiculos = "banco_veiculos_" + usuarioAtivo + ".json";

            // FUNÇÃO QUE DETECTA SE VEIO UM ID NA URL E CARREGA OS DADOS
            function verificarEdicao() {
                var urlParams = new URLSearchParams(window.location.search);
                var idParam = urlParams.get('id');

                if (idParam) {
                    var id = parseInt(idParam);
                    var dadosAtuais = localStorage.getItem(chaveVeiculos);
                    var listaVeiculos = dadosAtuais ? JSON.parse(dadosAtuais) : [];
                    
                    // Procura o veículo correspondente no banco local
                    var veiculo = listaVeiculos.find(function(v) { return v.id === id; });

                    if (veiculo) {
                        document.getElementById("titulo-tela").innerText = "Alterar Veículo #" + id;
                        document.getElementById("btn-salvar").innerText = "Atualizar Cadastro";
                        
                        // Preenche os inputs com os dados reais salvos anteriormente
                        document.getElementById("v_id").value = veiculo.id;
                        document.getElementById("v_placa").value = veiculo.placa;
                        document.getElementById("v_modelo").value = veiculo.modelo;
                        document.getElementById("v_ano").value = veiculo.ano;
                        document.getElementById("v_proprietario").value = veiculo.proprietario;
                    }
                }
            }

            function salvarVeiculoLocal(event) {
                event.preventDefault();
                
                var dadosAtuais = localStorage.getItem(chaveVeiculos);
                var listaVeiculos = dadosAtuais ? JSON.parse(dadosAtuais) : [];
                
                var idExistente = document.getElementById("v_id").value;

                if (idExistente) {
                    // MODO EDIÇÃO: Atualiza o veículo existente na lista
                    var id = parseInt(idExistente);
                    var index = listaVeiculos.findIndex(function(v) { return v.id === id; });
                    
                    if (index !== -1) {
                        listaVeiculos[index].placa = document.getElementById("v_placa").value.trim().toUpperCase();
                        listaVeiculos[index].modelo = document.getElementById("v_modelo").value.trim();
                        listaVeiculos[index].ano = document.getElementById("v_ano").value.trim();
                        listaVeiculos[index].proprietario = document.getElementById("v_proprietario").value.trim();
                    }
                } else {
                    // MODO NOVO CADASTRO: Adiciona um novo objeto com ID baseado no timestamp para evitar colisões
                    var novoVeiculo = {
                        id: Date.now(), 
                        placa: document.getElementById("v_placa").value.trim().toUpperCase(),
                        modelo: document.getElementById("v_modelo").value.trim(),
                        ano: document.getElementById("v_ano").value.trim(),
                        proprietario: document.getElementById("v_proprietario").value.trim()
                    };
                    listaVeiculos.push(novoVeiculo);
                }
                
                localStorage.setItem(chaveVeiculos, JSON.stringify(listaVeiculos));
                window.location.href = "listarVeiculo.jsp";
                return false;
            }

            // Executa a verificação ao carregar a página
            verificarEdicao();
        </script>
    </body>
</html>
