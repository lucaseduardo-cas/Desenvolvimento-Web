<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGO - Gestão de Ordens de Serviço</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp" />
        
        <div class="card shadow mb-4">
            <div class="card-header bg-dark text-white">
                <h5 class="mb-0">Abrir Nova Ordem de Serviço (O.S.) Geral</h5>
            </div>
            <div class="card-body">
                <form onsubmit="return lancarOSGeral(event)">
                    <div class="row g-3">
                        <div class="col-md-3">
                            <label class="form-label fw-bold">Selecionar Veículo:</label>
                            <select id="os_geral_veiculo" class="form-select" onchange="filtrarPecasPorVeiculoGeral()" required>
                                <!-- Injetado dinamicamente -->
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Descrição Técnica do Serviço:</label>
                            <input type="text" id="os_geral_desc" class="form-control" placeholder="Ex: Troca preventiva do óleo de transmissão e filtro externo" required>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label fw-bold">Data do Serviço:</label>
                            <input type="date" id="os_geral_data" class="form-control" required>
                        </div>
                        
                        <div class="col-md-4">
                            <label class="form-label fw-bold">Peças Compatíveis com o Veículo:</label>
                            <select id="os_geral_select_peca" class="form-select">
                                <!-- Filtrado dinamicamente -->
                            </select>
                        </div>
                        <div class="col-md-2">
                            <label class="form-label fw-bold">Qtd. Utilizada:</label>
                            <div class="input-group">
                                <input type="number" id="os_geral_qtd_peca" class="form-control" value="1" min="1">
                                <button class="btn btn-success fw-bold" type="button" onclick="adicionarPecaGeral()">＋ Adicionar</button>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <label class="form-label fw-bold">Custo Peças (R$):</label>
                            <input type="number" step="0.01" id="os_geral_val_pecas" class="form-control" placeholder="0.00" required>
                        </div>
                        <div class="col-md-2">
                            <label class="form-label fw-bold">Mão de Obra (R$):</label>
                            <input type="number" step="0.01" id="os_geral_val_mo" class="form-control" placeholder="0.00" required>
                        </div>
                        <div class="col-md-2">
                            <label class="form-label fw-bold">Responsável:</label>
                            <input type="text" id="os_geral_mecanico" class="form-control" readonly>
                        </div>
                    </div>
                    
                    <div id="lista_pecas_badge_geral" class="mt-3 d-flex gap-1 flex-wrap"></div>
                    
                    <div class="text-end mt-3">
                        <button type="submit" class="btn btn-primary px-4 fw-bold">Gravar Ordem de Serviço</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="card shadow p-4">
            <h4 class="text-secondary mb-4">Todas as Ordens de Serviço Gravadas</h4>
            <div class="table-responsive">
                <table class="table table-bordered table-striped align-middle">
                    <thead class="table-secondary">
                        <tr>
                            <th>Data</th>
                            <th>Veículo</th>
                            <th>Descrição Técnica do Serviço</th>
                            <th>Peças Trocadas (Qtd)</th>
                            <th>Mão de Obra</th>
                            <th>Total O.S.</th>
                            <th>Mecânico</th>
                        </tr>
                    </thead>
                    <tbody id="tabela-geral-os-corpo">
                        <!-- Injetado via JS -->
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            var usuarioAtivo = localStorage.getItem("usuarioLogado") || "admin";
            var chavePecas = "banco_pecas_" + usuarioAtivo + ".json";
            var pecasSelecionadasGeral = [];

            document.getElementById("os_geral_data").value = new Date().toISOString().split('T')[0];
            document.getElementById("os_geral_mecanico").value = usuarioAtivo.toUpperCase();

            function carregarVeiculosNoSelectGeral() {
                var chaveVeiculos = "banco_veiculos_" + usuarioAtivo + ".json";
                var dados = localStorage.getItem(chaveVeiculos);
                var select = document.getElementById("os_geral_veiculo");
                select.innerHTML = "";
                var lista = dados ? JSON.parse(dados) : [];
                lista.forEach(function(v) {
                    var opt = document.createElement("option");
                    opt.value = v.id + "|" + v.modelo;
                    opt.innerText = v.modelo + " [" + v.placa + "]";
                    select.appendChild(opt);
                });
            }

            // REGRA DE NEGÓCIO: FILTRAR PEÇAS COMPATÍVEIS COM O VEÍCULO SELECIONADO
            function filtrarPecasPorVeiculoGeral() {
                var selectVeiculo = document.getElementById("os_geral_veiculo");
                var selectPeca = document.getElementById("os_geral_select_peca");
                selectPeca.innerHTML = "";

                if (!selectVeiculo.value) return;
                var modeloVeiculo = selectVeiculo.value.split('|')[1].toLowerCase();

                var dadosPecas = localStorage.getItem(chavePecas);
                var listaPecas = dadosPecas ? JSON.parse(dadosPecas) : [];

                // Filtra se o modelo do veículo estiver contido no campo 'modelo' ou 'cruzada' da peça
                var pecasCompativeis = listaPecas.filter(function(p) {
                    var aplicacao = (p.modelo || "").toLowerCase();
                    var cruzada = (p.cruzada || "").toLowerCase();
                    return aplicacao.includes(modeloVeiculo) || cruzada.includes(modeloVeiculo);
                });

                if (pecasCompativeis.length === 0) {
                    var opt = document.createElement("option");
                    opt.value = "";
                    opt.innerText = "Nenhuma peça compatível em estoque";
                    selectPeca.appendChild(opt);
                    return;
                }

                pecasCompativeis.forEach(function(p) {
                    var opt = document.createElement("option");
                    opt.value = p.codigo + "|" + p.nome;
                    opt.innerText = p.nome + " [" + p.codigo + "] (Estoque: " + p.qtd + ")";
                    selectPeca.appendChild(opt);
                });
            }

            function adicionarPecaGeral() {
                var select = document.getElementById("os_geral_select_peca");
                if(!select.value) return;

                var parts = select.value.split('|');
                var pCodigo = parts[0];
                var pNome = parts[1];
                var qtdPedida = parseInt(document.getElementById("os_geral_qtd_peca").value) || 1;

                var estoque = JSON.parse(localStorage.getItem(chavePecas)) || [];
                var pecaEstoque = estoque.find(function(item) { return item.codigo === pCodigo; });

                if (pecaEstoque) {
                    var estoqueDisponivel = parseInt(pecaEstoque.qtd) || 0;
                    if (qtdPedida > estoqueDisponivel) {
                        alert("⚠️ Erro de Estoque!\nA quantidade solicitada (" + qtdPedida + " un) é superior ao estoque disponível para a peça " + pNome + " (" + estoqueDisponivel + " un no estoque).");
                        return;
                    }
                }

                var jaExiste = pecasSelecionadasGeral.find(function(item) { return item.codigo === pCodigo; });
                if(!jaExiste) {
                    pecasSelecionadasGeral.push({ codigo: pCodigo, nome: pNome, qtd: qtdPedida });
                    renderizarBadgesGeral();
                } else {
                    alert("Esta peça já foi adicionada a esta O.S.!");
                }
            }

            function renderizarBadgesGeral() {
                var container = document.getElementById("lista_pecas_badge_geral");
                container.innerHTML = "";
                pecasSelecionadasGeral.forEach(function(p) {
                    var span = document.createElement("span");
                    span.className = "badge bg-info text-dark font-monospace me-1";
                    span.textContent = p.nome + " (" + p.qtd + " un)";
                    container.appendChild(span);
                });
            }

            function renderizarTabelaGeral() {
                var corpo = document.getElementById("tabela-geral-os-corpo");
                corpo.innerHTML = "";
                var temRegistros = false;

                for (var i = 0; i < localStorage.length; i++) {
                    var chave = localStorage.key(i);
                    if (chave.startsWith("banco_os_veiculo_")) {
                        var vId = chave.split('_')[3].replace(".json", "");
                        var lista = JSON.parse(localStorage.getItem(chave));
                        var modeloCarro = localStorage.getItem("nome_carro_" + vId) || "Veículo SGO";
                        
                        lista.forEach(function(os) {
                            temRegistros = true;
                            var total = parseFloat(os.valPecas || 0) + parseFloat(os.valMo || 0);
                            
                            var pecasTexto = "Nenhuma";
                            if(os.pecasArray) {
                                pecasTexto = os.pecasArray.map(function(p) { return p.nome + " (" + p.qtd + " un)"; }).join(', ');
                            } else if(os.pecas) {
                                pecasTexto = os.pecas.join(', ');
                            }

                            var linha = document.createElement("tr");
                            linha.innerHTML = `
                                <td>\${os.data.split('-').reverse().join('/')}</td>
                                <td class="fw-bold">\${os.modeloCarro || modeloCarro}</td>
                                <td class="text-primary fw-bold">\${os.desc}</td>
                                <td>\${pecasTexto}</td>
                                <td>R$ \${parseFloat(os.valMo).toFixed(2)}</td>
                                <td class="fw-bold text-success">R$ \${total.toFixed(2)}</td>
                                <td><span class="badge bg-secondary">\${os.mecanico}</span></td>
                            `;
                            corpo.appendChild(linha);
                        });
                    }
                }
                if (!temRegistros) {
                    corpo.innerHTML = `<tr><td colspan="7" class="text-center text-muted py-3">Nenhuma Ordem de Serviço cadastrada.</td></tr>`;
                }
            }

            function lancarOSGeral(event) {
                event.preventDefault();
                var selectVal = document.getElementById("os_geral_veiculo").value.split('|');
                var vId = selectVal[0];
                var vModelo = selectVal[1];

                var chaveOS = "banco_os_veiculo_" + vId + ".json";
                var dadosAtuais = localStorage.getItem(chaveOS);
                var historico = dadosAtuais ? JSON.parse(dadosAtuais) : [];

                var estoque = JSON.parse(localStorage.getItem(chavePecas)) || [];
                pecasSelecionadasGeral.forEach(function(pSel) {
                    var idx = estoque.findIndex(function(item) { return item.codigo === pSel.codigo; });
                    if(idx !== -1) {
                        estoque[idx].qtd = Math.max(0, parseInt(estoque[idx].qtd) - pSel.qtd);
                    }
                });
                localStorage.setItem(chavePecas, JSON.stringify(estoque));

                var novaOS = {
                    data: document.getElementById("os_geral_data").value, // Correção do ID do campo data
                    desc: document.getElementById("os_geral_desc").value.trim(),
                    pecasArray: pecasSelecionadasGeral,
                    pecas: pecasSelecionadasGeral.map(function(p) { return p.nome + " (" + p.qtd + "un)"; }),
                    valPecas: parseFloat(document.getElementById("os_geral_val_pecas").value) || 0,
                    valMo: parseFloat(document.getElementById("os_geral_val_mo").value) || 0,
                    mecanico: document.getElementById("os_geral_mecanico").value,
                    modeloCarro: vModelo
                };

                historico.push(novaOS);
                localStorage.setItem(chaveOS, JSON.stringify(historico));
                localStorage.setItem("nome_carro_" + vId, vModelo);

                document.getElementById("os_geral_desc").value = "";
                document.getElementById("os_geral_val_pecas").value = "";
                document.getElementById("os_geral_val_mo").value = "";
                pecasSelecionadasGeral = [];
                renderizarBadgesGeral();

                alert("Ordem de Serviço Geral gravada com sucesso e estoque atualizado!");
                filtrarPecasPorVeiculoGeral();
                renderizarTabelaGeral();
                return false;
            }

            carregarVeiculosNoSelectGeral();
            filtrarPecasPorVeiculoGeral();
            renderizarTabelaGeral();
        </script>
    </body>
</html>