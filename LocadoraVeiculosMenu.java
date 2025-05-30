import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class LocadoraVeiculosMenu {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Map<String, Map<String, String>> veiculos = new HashMap<>();
        Map<String, Map<String, String>> clientes = new HashMap<>();
        Map<String, Map<String, String>> colaboradores = new HashMap<>();
        Map<String, Map<String, String>> locacoes = new HashMap<>();
        int id_locacao = 0;

        while (true) {
            System.out.println("\n==== MENU LOCADORA ====");
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Cadastrar cliente");
            System.out.println("3 - Cadastrar colaborador");
            System.out.println("4 - Realizar locação");
            System.out.println("5 - Emitir relatório");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Código: ");
                    String codigoVeiculo = sc.nextLine();
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Marca: ");
                    String marca = sc.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = sc.nextLine();
                    System.out.print("Quantidade: ");
                    String qtd = sc.nextLine();
                    System.out.print("Valor do aluguel por dia: ");
                    String valor = sc.nextLine();

                    Map<String, String> dadosVeiculo = new HashMap<>();
                    dadosVeiculo.put("placa", placa);
                    dadosVeiculo.put("marca", marca);
                    dadosVeiculo.put("modelo", modelo);
                    dadosVeiculo.put("quantidade", qtd);
                    dadosVeiculo.put("valor_aluguel", valor);

                    veiculos.put(codigoVeiculo, dadosVeiculo);

                    System.out.println("Veículo cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Código: ");
                    String codCliente = sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeCliente = sc.nextLine();

                    Map<String, String> dadosCliente = new HashMap<>();
                    dadosCliente.put("nome", nomeCliente);

                    clientes.put(codCliente, dadosCliente);

                    System.out.println("Cliente cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.print("Código: ");
                    String codColab = sc.nextLine();
                    System.out.print("Nome: ");
                    String nomeColab = sc.nextLine();

                    Map<String, String> dadosColab = new HashMap<>();
                    dadosColab.put("nome", nomeColab);

                    colaboradores.put(codColab, dadosColab);

                    System.out.println("Colaborador cadastrado com sucesso!");
                    break;

                case 4:
                    System.out.print("Código do Cliente: ");
                    String codCliLoc = sc.nextLine();
                    System.out.print("Código do Colaborador: ");
                    String codColLoc = sc.nextLine();
                    System.out.print("Código do Veículo: ");
                    String codVeicLoc = sc.nextLine();
                    System.out.print("Quantidade de dias: ");
                    String dias = sc.nextLine();

                    if (!clientes.containsKey(codCliLoc)) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }
                    if (!colaboradores.containsKey(codColLoc)) {
                        System.out.println("Colaborador não encontrado!");
                        break;
                    }
                    if (!veiculos.containsKey(codVeicLoc)) {
                        System.out.println("Veículo não encontrado!");
                        break;
                    }

                    Map<String, String> dadosLocacao = new HashMap<>();

                    double valorAluguel = Double.parseDouble(veiculos.get(codVeicLoc).get("valor_aluguel"));
                    int diasInt = Integer.parseInt(dias);
                    double valorTotal = valorAluguel * diasInt;
                    double valorTotalComDesconto = valorTotal;

                    if (diasInt > 30) valorTotalComDesconto *= 0.80;
                    else if (diasInt > 15) valorTotalComDesconto *= 0.90;
                    else if (diasInt > 5) valorTotalComDesconto *= 0.95;

                    double valorSeguro = valorTotal * 0.25;

                    dadosLocacao.put("cliente", codCliLoc);
                    dadosLocacao.put("colaborador", codColLoc);
                    dadosLocacao.put("veiculo", codVeicLoc);
                    dadosLocacao.put("dias", dias);
                    dadosLocacao.put("valor_total", Double.toString(valorTotal));
                    dadosLocacao.put("valor_seguro", Double.toString(valorSeguro));
                    dadosLocacao.put("valor_total_com_desconto", Double.toString(valorTotalComDesconto + valorSeguro));

                    locacoes.put(Integer.toString(id_locacao), dadosLocacao);
                    id_locacao++;

                    System.out.println("==== LOCAÇÃO REALIZADA ====");
                    System.out.println("==== COMPROVANTE DE LOCAÇÃO ====");
                    System.out.println("Cliente: " + clientes.get(codCliLoc).get("nome"));
                    System.out.println("Colaborador: " + colaboradores.get(codColLoc).get("nome"));
                    System.out.println("Veículo:");
                    System.out.println("  Código: " + codVeicLoc);
                    System.out.println("  Placa: " + veiculos.get(codVeicLoc).get("placa"));
                    System.out.println("  Marca: " + veiculos.get(codVeicLoc).get("marca"));
                    System.out.println("  Modelo: " + veiculos.get(codVeicLoc).get("modelo"));
                    System.out.println("Valor unitário do aluguel: R$ " + valorAluguel);
                    System.out.println("Quantidade de dias: " + dias);
                    System.out.println("Valor total do aluguel: R$ " + valorTotal);
                    System.out.println("Valor do seguro: R$ " + valorSeguro);
                    System.out.println("Valor total da locação: R$ " + (valorTotal + valorSeguro));
                    if (valorTotalComDesconto != valorTotal) {
                        System.out.println("Valor total da locação com desconto: R$ " + (valorTotalComDesconto + valorSeguro));
                    }

                    break;

                case 5:
                    System.out.println("\n==== RELATÓRIO DE VEÍCULOS ====");
                    for (String cod : veiculos.keySet()) {
                        System.out.println("Código: " + cod + ", Dados: " + veiculos.get(cod));
                    }

                    System.out.println("\n==== RELATÓRIO DE CLIENTES ====");
                    for (String cod : clientes.keySet()) {
                        System.out.println("Código: " + cod + ", Nome: " + clientes.get(cod).get("nome"));
                    }

                    System.out.println("\n==== RELATÓRIO DE COLABORADORES ====");
                    for (String cod : colaboradores.keySet()) {
                        System.out.println("Código: " + cod + ", Nome: " + colaboradores.get(cod).get("nome"));
                    }

                    double somaTotalLocacoes = 0.0;
                    double somaTotalSeguros = 0.0;
                    double somaTotalComDesconto = 0.0;

                    System.out.println("\n==== RELATÓRIO DE LOCAÇÕES ====");
                    for (String id : locacoes.keySet()) {
                        Map<String, String> loc = locacoes.get(id);
                        System.out.println("ID: " + id + ", Dados: " + loc);

                        double valorLoc = Double.parseDouble(loc.get("valor_total"));
                        double valorSeg = Double.parseDouble(loc.get("valor_seguro"));
                        double valorDesc = Double.parseDouble(loc.get("valor_total_com_desconto"));

                        somaTotalLocacoes += valorLoc;
                        somaTotalSeguros += valorSeg;
                        somaTotalComDesconto += valorDesc;
                    }

                    System.out.println("\n==== TOTAIS GERAIS ====");
                    System.out.println("Total de locações: R$ " + somaTotalLocacoes);
                    System.out.println("Total de seguros: R$ " + somaTotalSeguros);
                    System.out.println("Total final com descontos: R$ " + somaTotalComDesconto);

                    break;

                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }
}
