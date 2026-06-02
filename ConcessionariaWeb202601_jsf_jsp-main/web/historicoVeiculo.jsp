<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SGO - Histórico de Serviços</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body class="container mt-4 bg-light">
        <jsp:include page="menu.jsp" />
        
        <div class="d-flex justify-content-between align-items-center mb-3">
            <h3 class="text-dark">Ficha de Histórico Técnico do Veículo</h3>
            <a href="listarVeiculo.jsp" class="btn btn-outline-secondary btn-sm">← Voltar para Oficina</a>
        </div>

        <div class="card shadow mb-4">
            <div class="card-header bg-dark text-white">
                <h5 class="mb-0">Abrir Nova Ordem de Serviço (O.S.)</h5>
            </div>
            <div class="card-body">
                <form onsubmit="return lancarOrdemServico(event)">
                    <div class="row g-3">
                        <div class="col-md-6">
                            <label class="form-label fw-bold">Descrição Técnica do Serviço:</label>
                            <input type="text" id="os_desc" class="form-control" placeholder="Ex: Troca de pastilhas de freio e inspeção técnica" required>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label fw-bold">Data do Serviço:</label>
                            <input type="date" id="os_data" class="form-control" required>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label fw-bold">Profissional Responsável:</label>
                            <input type="text" id="os_mecanico" class="form-control" readonly>
                        </div>
                        
                        <div class="col-md-4">
                            <label class="form-label fw-bold">Peças Compatíveis com este Veículo:</label>
                            <select id="os_select_peca" class="form-select">
                                </select>
                        </div>
                        <div class="col-md-2">
                            <label class="form-label fw-bold">Qtd. Utilizada:</label>
                            <div class="input-group">
                                <input type="number" id="os_qtd_peca" class="form-control" value="1" min="1">
                                <button class="btn btn-success fw-bold" type="button" onclick="adicionarPecaNaLista()">＋ Adicionar</button>
                            </div>
                        </div>

                        <div class="col-md-3">
                            <label class="form-label fw-bold">Custo Total das Peças (R$):</label>
                            <input type="number" step="0.01" id="os_val_pecas" class="form-control" placeholder="0.00" required>
                        </div>
                        <div class="col-md-3">
                            <label class="form-label fw-bold">Custo Mão de Obra (R$):</label>
                            <input type="number" step="0.01" id="os_val_mo" class="form-control" placeholder="0.00" required>
                        </div>
                    </div>
                    
                    <div id="lista_pecas_badge" class="mt-3 d-flex gap-1 flex-wrap"></div>
                    
                    <div class="text-end mt-3">
                        <button type="submit" class="btn btn-primary px-4 fw-bold">Gravar Ordem de Serviço</button>
                    </div>
                </form>
            </div>
        </div>

        <div class="card shadow p-4">
            <h5 class="text-secondary mb-3">Histórico de Intervenções Realizadas</h5>
            <div class="table-responsive">
                <table class="table table-bordered table-striped">
                    <thead class="table-secondary">
                        <tr>
                            <th>Data</th>
                            <th>Descrição do Serviço</th>
                            <th>Peças Trocadas (Qtd)</th>
                            <th>Mão de Obra</th>
                            <th>Total O.S.</th>
                            <th>Mecânico</th>
                        </tr>
                    </thead>
                    <tbody id="tabela-os-corpo">
                        </tbody>
                </table>
            </div>
        </div>

        <script>
            var urlParams = new URLSearchParams(window.location.search);
            var veiculoId = urlParams.get('id') || "1";
            
            var usuarioAtivo = localStorage.getItem("usuarioLogado") || "admin";
            var chaveOS = "banco_os_veiculo_" + veiculoId + ".json";
            var chavePecas = "banco_pecas_" + usuarioAtivo + ".json";

            var pecasSelecionadasParaOS = [];

            document.getElementById("os_data").value = new Date().toISOString().split('T')[0];
            document.getElementById("os_mecanico").value = usuarioAtivo.toUpperCase();

            function descobrirNomeCarro() {
                var chaveCarros = "banco_veiculos_" + usuarioAtivo + ".json";
                var lista = JSON.parse(localStorage.getItem(chaveCarros)) || [];
                var carro = lista.find(function(c) { return c.id == veiculoId; });
                return carro ? carro.modelo : "Veículo Cadastrado";
            }
            var modeloDoCarroAtual = descobrirNomeCarro();

            function carregarPecasCompatíveis() {
                var dados = localStorage.getItem(chavePecas);
                var select = document.getElementById("os_select_peca");
                select.innerHTML = "";
                var lista = dados ? JSON.parse(dados) : [];

                var modeloFiltrado = modeloDoCarroAtual.toLowerCase();
                var pecasFiltradas = lista.filter(function(p) {
                    return (p.modelo || "").toLowerCase().includes(modeloFiltrado) || 
                           (p.cruzada || "").toLowerCase().includes(modeloFiltrado);
                });

                if (pecasFiltradas.length === 0) {
                    var opt = document.createElement("option");
                    opt.value = "";
                    opt.innerText = "Nenhuma peça compatível em estoque";
                    select.appendChild(opt);
                    return;
                }

                pecasFiltradas.forEach(function(p) {
                    var opt = document.createElement("option");
                    opt.value = p.codigo + "|" + p.nome;
                    opt.innerText = p.nome + " [" + p.codigo + "] (Estoque: " + p.qtd + ")";
                    select.appendChild(opt);
                });
            }

            function adicionarPecaNaLista() {
                var select = document.getElementById("os_select_peca");
                if(!select.value) return;

                var parts = select.value.split('|');
                var pCodigo = parts[0];
                var pNome = parts[1];
                var qtdPedida = parseInt(document.getElementById("os_qtd_peca").value) || 1;

                var estoque = JSON.parse(localStorage.getItem(chavePecas)) || [];
                var pecaEstoque = estoque.find(function(item) { return item.codigo === pCodigo; });

                if (pecaEstoque) {
                    var estoqueDisponivel = parseInt(pecaEstoque.qtd) || 0;
                    if (qtdPedida > estoqueDisponivel) {
                        alert("⚠️ Erro de Validação!\nA quantidade digitada (" + qtdPedida + " un) ultrapassa o estoque disponível para " + pNome + " (" + estoqueDisponivel + " un em estoque).");
                        return;
                    }
                }

                var jaExiste = pecasSelecionadasParaOS.find(function(item) { return item.codigo === pCodigo; });
                if(!jaExiste) {
                    pecasSelecionadasParaOS.push({ codigo: pCodigo, nome: pNome, qtd: qtdPedida });
                    renderizarBadgesPecas();
                } else {
                    alert("Esta peça já foi adicionada!");
                }
            }

            function renderizarBadgesPecas() {
                var container = document.getElementById("lista_pecas_badge");
                container.innerHTML = "";
                pecasSelecionadasParaOS.forEach(function(p) {
                    var span = document.createElement("span");
                    span.className = "badge bg-info text-dark font-monospace me-1";
                    span.textContent = p.nome + " (" + p.qtd + " un)";
                    container.appendChild(span);
                });
            }

            function obterHistoricoOS() {
                var dados = localStorage.getItem(chaveOS);
                return dados ? JSON.parse(dados) : [];
            }

            function renderizarTabelaOS() {
                var lista = obterHistoricoOS();
                var corpo = document.getElementById("tabela-os-corpo");
                corpo.innerHTML = "";

                if(lista.length === 0) {
                    corpo.innerHTML = `<tr><td colspan="6" class="text-center text-muted py-3">Nenhum histórico de O.S. registrado para este veículo ainda.</td></tr>`;
                    return;
                }

                lista.forEach(function(os) {
                    var total = parseFloat(os.valPecas || 0) + parseFloat(os.valMo || 0);
                    
                    var pecasTexto = "Nenhuma";
                    if(os.pecasArray && os.pecasArray.length > 0) {
                        pecasTexto = os.pecasArray.map(function(p) { return p.nome + " (" + p.qtd + " un)"; }).join(', ');
                    } else if(os.pecas && os.pecas.length > 0) {
                        pecasTexto = os.pecas.join(', ');
                    }

                    var linha = document.createElement("tr");
                    linha.innerHTML = `
                        <td class="text-nowrap">\${os.data.split('-').reverse().join('/')}</td>
                        <td class="fw-bold text-primary">\${os.desc}</td>
                        <td>\${pecasTexto}</td>
                        <td>R$ \${parseFloat(os.valMo).toFixed(2)}</td>
                        <td class="fw-bold text-success">R$ \${total.toFixed(2)}</td>
                        <td><span class="badge bg-secondary">\${os.mecanico}</span></td>
                    `;
                    corpo.appendChild(linha);
                });
            }

            function lancarOrdemServico(event) {
                event.preventDefault();
                
                var estoque = JSON.parse(localStorage.getItem(chavePecas)) || [];
                pecasSelecionadasParaOS.forEach(function(pSel) {
                    var idx = estoque.findIndex(function(item) { return item.codigo === pSel.codigo; });
                    if(idx !== -1) {
                        estoque[idx].qtd = Math.max(0, parseInt(estoque[idx].qtd) - pSel.qtd);
                    }
                });
                localStorage.setItem(chavePecas, JSON.stringify(estoque));

                var novaOS = {
                    data: document.getElementById("os_data").value,
                    desc: document.getElementById("os_desc").value.trim(),
                    pecasArray: pecasSelecionadasParaOS,
                    pecas: pecasSelecionadasParaOS.map(function(p) { return p.nome + " (" + p.qtd + "un)"; }),
                    valPecas: parseFloat(document.getElementById("os_val_pecas").value) || 0,
                    valMo: parseFloat(document.getElementById("os_val_mo").value) || 0,
                    mecanico: document.getElementById("os_mecanico").value,
                    modeloCarro: modeloDoCarroAtual
                };

                var historico = obterHistoricoOS();
                historico.push(novaOS);
                localStorage.setItem(chaveOS, JSON.stringify(historico));

                document.getElementById("os_desc").value = "";
                document.getElementById("os_val_pecas").value = "";
                document.getElementById("os_val_mo").value = "";
                pecasSelecionadasParaOS = [];
                renderizarBadgesPecas();

                alert("Ordem de Serviço gravada e estoque atualizado!");
                carregarPecasCompatíveis();
                renderizarTabelaOS();
                return false;
            }

            carregarPecasCompatíveis();
            renderizarTabelaOS();
        </script>
    </body>
</html>
