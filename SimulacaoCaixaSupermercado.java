import java.util.Arrays;
import java.util.Random;

public class SimulacaoCaixaSupermercado {

    private int numeroCaixas;
    private int numeroClientes;
    private double mediaTempoAtendimentoPorCliente;
    private double desvioPadraoTempoAtendimentoPorCliente;

    private static final double TEMPO_MINIMO_ATENDIMENTO = 0.1;

    private final Random rng = new Random(42);

    public void setNumeroCaixas(int numeroCaixas) { this.numeroCaixas = numeroCaixas; }
    public void setNumeroClientes(int n) { this.numeroClientes = n; }
    public void setMediaTempoAtendimentoPorCliente(double mu) { this.mediaTempoAtendimentoPorCliente = mu; }
    public void setDesvioPadraoTempoAtendimentoPorCliente(double sigma) { this.desvioPadraoTempoAtendimentoPorCliente = sigma; }

    private double tempoAtendimentoNormalTruncado() {
        double z = rng.nextGaussian();
        double tempo = mediaTempoAtendimentoPorCliente + desvioPadraoTempoAtendimentoPorCliente * z;
        return Math.max(tempo, TEMPO_MINIMO_ATENDIMENTO);
    }

    public double simular() {
        if (numeroCaixas <= 0) {
            return Double.POSITIVE_INFINITY;
        }

        double[] tempoOcupadoPorCaixa = new double[numeroCaixas];

        for (int i = 0; i < numeroClientes; i++) {
            int caixaDisponivel = 0;
            for (int j = 1; j < numeroCaixas; j++) {
                if (tempoOcupadoPorCaixa[j] < tempoOcupadoPorCaixa[caixaDisponivel]) {
                    caixaDisponivel = j;
                }
            }

            double tempoDeAtendimento = tempoAtendimentoNormalTruncado();

            tempoOcupadoPorCaixa[caixaDisponivel] += tempoDeAtendimento;
        }

        double tempoTotal = 0.0;
        for (double tempoFinal : tempoOcupadoPorCaixa) {
            if (tempoFinal > tempoTotal) {
                tempoTotal = tempoFinal;
            }
        }

        return tempoTotal;
    }
}
