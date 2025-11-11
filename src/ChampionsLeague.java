import java.util.*;

class Time {
    String nome;
    int jogos;
    int vitorias;
    int empates;
    int derrotas;
    int golsFeitos;
    int golsSofridos;

    public Time(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return vitorias * 3 + empates;
    }

    public int getSaldoGols() {
        return golsFeitos - golsSofridos;
    }

    public void registrarJogo(int golsPro, int golsContra) {
        jogos++;
        golsFeitos += golsPro;
        golsSofridos += golsContra;

        if (golsPro > golsContra) vitorias++;
        else if (golsPro == golsContra) empates++;
        else derrotas++;
    }
}

public class ChampionsLeague {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 🔹 Criar os times
        List<Time> grupos = new ArrayList<>();
        grupos.add(new Time("Real Madrid"));
        grupos.add(new Time("Bayern"));
        grupos.add(new Time("Man City"));
        grupos.add(new Time("PSG"));

        // 🔹 Inserir resultados de forma simples (pode mudar depois)
        grupos.get(0).registrarJogo(2, 0); // Real 2x0 Bayern
        grupos.get(1).registrarJogo(0, 2); // Bayern perdeu pro Real
        grupos.get(2).registrarJogo(3, 1); // City 3x1 PSG
        grupos.get(3).registrarJogo(1, 3); // PSG perdeu pro City

        grupos.get(0).registrarJogo(1, 1); // Real 1x1 City
        grupos.get(2).registrarJogo(1, 1); // City 1x1 Real

        grupos.get(1).registrarJogo(2, 1); // Bayern 2x1 PSG
        grupos.get(3).registrarJogo(1, 2); // PSG 1x2 Bayern

        // 🔹 Ordenar por pontos e saldo de gols
        grupos.sort((a, b) -> {
            if (b.getPontos() == a.getPontos())
                return b.getSaldoGols() - a.getSaldoGols();
            return b.getPontos() - a.getPontos();
        });

        // 🔹 Exibir tabela
        System.out.println("\n🏆 Champions League - Grupo A 🏆");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-15s %3s %3s %3s %3s %5s %5s %5s\n",
                "Time", "J", "V", "E", "D", "GF", "GS", "Pts");
        System.out.println("-------------------------------------------------------------");

        for (Time t : grupos) {
            System.out.printf("%-15s %3d %3d %3d %3d %5d %5d %5d\n",
                    t.nome, t.jogos, t.vitorias, t.empates, t.derrotas,
                    t.golsFeitos, t.golsSofridos, t.getPontos());
        }

        System.out.println("-------------------------------------------------------------");
        System.out.println("Líder: 🥇 " + grupos.get(0).nome);
    }
}
