import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String... args) {
        System.out.println("--- INICIANDO SIMULADOR DE ATENDIMENTO ---");
        System.out.println("Parâmetros: Média de 5 min/cliente, Desvio Padrão de 0.5 min, 100 clientes.");
        System.out.println("-------------------------------------------------");

        int[] cenarioCaixas = {1, 2, 3, 4, 5};

        for (int caixas : cenarioCaixas) {
            executarSimulacaoParaCenario(caixas);
        }
    }

    public static void executarSimulacaoParaCenario(int numCaixas) {
        final int NUMERO_SIMULACOES = 1000;
        final List<Double> resultadosTempoTotal = new ArrayList<>();

        SimulacaoCaixaSupermercado simulador = new SimulacaoCaixaSupermercado();

        for (int i = 0; i < NUMERO_SIMULACOES; i++) {
            simulador.setNumeroCaixas(numCaixas);
            simulador.setNumeroClientes(100);
            simulador.setMediaTempoAtendimentoPorCliente(5.0);
            simulador.setDesvioPadraoTempoAtendimentoPorCliente(0.5);

            double tempoTotal = simulador.simular();
            resultadosTempoTotal.add(tempoTotal);
        }

        double media = media(resultadosTempoTotal);
        double dp = desvioPadrao(resultadosTempoTotal, media);

        System.out.printf("Cenário com %d caixa(s):\n", numCaixas);
        System.out.printf("  -> Tempo médio para atender todos os clientes: %.2f min\n", media);
        System.out.printf("  -> Desvio-padrão dos tempos: %.2f min\n\n", dp);
    }

    private static double media(List<Double> xs) {
        if (xs == null || xs.isEmpty()) return 0.0;
        double soma = 0.0;
        for (double x : xs) soma += x;
        return soma / xs.size();
    }

    private static double desvioPadrao(List<Double> xs, double m) {
        if (xs == null || xs.size() < 2) return 0.0;
        double somaDiferencasQuadradas = 0.0;
        for (double x : xs) {
            double d = x - m;
            somaDiferencasQuadradas += d * d;
        }
        return Math.sqrt(somaDiferencasQuadradas / (xs.size() - 1));
    }
}
