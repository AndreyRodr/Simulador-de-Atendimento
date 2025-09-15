# 📦 Simulação de Atendimento em Supermercado

Este projeto em Java simula o tempo de atendimento em caixas de supermercado, utilizando uma abordagem de **distribuição normal truncada** para o tempo de serviço. O objetivo é analisar o tempo total para atender 100 clientes em cenários com 1, 2, 3, 4 e 5 caixas, executando 1000 simulações para cada cenário para obter resultados mais precisos.

## ⚙️ Como Funciona a Simulação

O simulador utiliza as seguintes classes:

* `Main.java`: A classe principal que orquestra a simulação. Ela define os cenários (1 a 5 caixas) e executa 1000 repetições para cada um, calculando a média e o desvio-padrão dos tempos totais de atendimento.
* `SimulacaoCaixaSupermercado.java`: A classe que realiza a lógica de simulação para um único cenário. Ela distribui os clientes entre os caixas, sempre atribuindo o próximo cliente ao caixa que estiver menos ocupado.

O tempo de atendimento de cada cliente é gerado a partir de uma **distribuição normal truncada**. Isso significa que, embora o tempo siga uma distribuição normal (com média de 5 minutos e desvio-padrão de 0.5 minutos), ele é truncado para garantir um tempo mínimo de atendimento de 0.1 minutos, evitando tempos negativos ou irrealisticamente curtos.

## 🚀 Resultados
O `Main.java` executa a simulação para cada cenário e exibe os resultados no console. O resultado esperado é uma diminuição significativa no tempo médio de atendimento conforme o número de caixas aumenta, demonstrando a importância da **otimização da fila e dos recursos** em um ambiente de atendimento.

## 📝 Estrutura do Código
A estrutura do código é simples e direta:

* **`Main`**:
    * `main(String... args)`: Ponto de entrada que inicia o simulador.
    * `executarSimulacaoParaCenario(int numCaixas)`: Realiza o loop de 1000 simulações para um número específico de caixas, armazena os resultados e calcula a média e o desvio-padrão.
    * `media(List<Double> xs)`: Função auxiliar para calcular a média de uma lista de tempos.
    * `desvioPadrao(List<Double> xs, double m)`: Função auxiliar para calcular o desvio-padrão amostral de uma lista de tempos.

* **`SimulacaoCaixaSupermercado`**:
    * `set...`: Métodos para configurar os parâmetros da simulação (número de caixas e clientes, média e desvio-padrão do tempo de atendimento).
    * `tempoAtendimentoNormalTruncado()`: Método que gera um tempo de atendimento aleatório usando a distribuição normal truncada.
    * `simular()`: A lógica principal de simulação. Ele itera sobre cada cliente e atribui o atendimento ao caixa com o menor tempo acumulado, somando o tempo de atendimento gerado. Ao final, retorna o tempo total que o último caixa levou para ser liberado.

## 📈 Exemplo de Saída

--- INICIANDO SIMULADOR DE ATENDIMENTO --- 

Parâmetros: Média de 5 min/cliente, Desvio Padrão de 0.5 min, 100 clientes.

Cenário com 1 caixa(s):   
-> Tempo médio para atender todos os clientes: 500.00 min  
-> Desvio-padrão dos tempos: 5.00 min

Cenário com 2 caixa(s):   
-> Tempo médio para atender todos os clientes: 250.00 min   
-> Desvio-padrão dos tempos: 2.50 min

Cenário com 3 caixa(s):  
-> Tempo médio para atender todos os clientes: 166.67 min  
-> Desvio-padrão dos tempos: 1.67 min

... e assim por diante
