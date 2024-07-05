import CRUDAluno.AlunoCRUD;
import Utilidades.GerarAluno;
import Models.Aluno;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        exibirMenu();
        GerarAluno.fecharScanner();
    }

    private static void exibirMenu() {
        boolean sair = false;
        while (!sair) {
            try {
                System.out.println("\n### MENU ###");
                System.out.println("1. Inserir Aluno");
                System.out.println("2. Buscar Aluno por ID");
                System.out.println("3. Buscar Alunos por Nome");
                System.out.println("4. Remover Aluno");
                System.out.println("5. Alterar Aluno");
                System.out.println("6. Sair");
                System.out.print("\nEscolha uma opção: ");

                int opcao = lerInteiro();

                switch (opcao) {
                    case 1:
                        // Inserir Aluno
                        Aluno novoAluno = gerarAluno();
                        if (novoAluno != null) {
                            if (AlunoCRUD.inserirAluno(novoAluno)) {
                                System.out.println("Aluno inserido com sucesso.");
                            } else {
                                System.out.println("Falha ao inserir aluno.");
                            }
                        }
                        break;
                    case 2:
                        // Buscar Aluno por ID
                        System.out.print("Digite o ID do aluno: ");
                        Long idBuscar = lerLong();
                        Aluno alunoEncontrado = AlunoCRUD.buscarAlunoPorId(idBuscar);
                        if (alunoEncontrado != null) {
                            System.out.println("Aluno encontrado:");
                            System.out.println(alunoEncontrado);
                        } else {
                            System.out.println("Aluno não encontrado para o ID informado.");
                        }
                        break;
                    case 3:
                        // Buscar Alunos por Nome
                        System.out.print("Digite parte do nome do aluno: ");
                        String parteNome = scanner.nextLine();
                        ArrayList<Aluno> alunosEncontrados = AlunoCRUD.buscarAlunosPorNome(parteNome);
                        if (!alunosEncontrados.isEmpty()) {
                            System.out.println("Alunos encontrados:");
                            for (Aluno aluno : alunosEncontrados) {
                                System.out.println(aluno);
                            }
                        } else {
                            System.out.println("Nenhum aluno encontrado com o nome informado.");
                        }
                        break;
                    case 4:
                        // Remover Aluno
                        System.out.print("Digite o ID do aluno a ser removido: ");
                        Long idRemover = lerLong();
                        if (AlunoCRUD.removerAluno(idRemover)) {
                            System.out.println("Aluno removido com sucesso.");
                        } else {
                            System.out.println("Aluno não encontrado para o ID informado.");
                        }
                        break;
                    case 5:
                        // Alterar Aluno
                        System.out.print("Digite o ID do aluno a ser alterado: ");
                        Long idAlterar = lerLong();
                        System.out.print("Novo nome do aluno (deixe em branco para manter): ");
                        String novoNome = scanner.nextLine().trim();
                        System.out.print("Novo email do aluno (deixe em branco para manter): ");
                        String novoEmail = scanner.nextLine().trim();
                        System.out.print("Novo CPF do aluno (deixe em branco para manter): ");
                        String novoCpf = scanner.nextLine().trim();
                        // Ler nova data de nascimento
                        System.out.print("Nova data de nascimento do aluno (deixe em branco para manter): ");
                        String novaDataNascimentoStr = scanner.nextLine().trim();
                        Calendar novaDataNascimento = null;
                        if (!novaDataNascimentoStr.isEmpty()) {
                            novaDataNascimento = GerarAluno.parseData(novaDataNascimentoStr);
                        }
                        System.out.print("Nova naturalidade do aluno (deixe em branco para manter): ");
                        String novaNaturalidade = scanner.nextLine().trim();
                        System.out.print("Novo endereço do aluno (deixe em branco para manter): ");
                        String novoEndereco = scanner.nextLine().trim();
                        if (AlunoCRUD.alterarAluno(idAlterar, novoNome.isEmpty() ? null : novoNome,
                                novoEmail.isEmpty() ? null : novoEmail,
                                novoCpf.isEmpty() ? null : novoCpf,
                                novaDataNascimento,
                                novaNaturalidade.isEmpty() ? null : novaNaturalidade,
                                novoEndereco.isEmpty() ? null : novoEndereco)) {
                            System.out.println("Aluno alterado com sucesso.");
                        } else {
                            System.out.println("Aluno não encontrado para o ID informado.");
                        }
                        break;
                    case 6:
                        sair = true;
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static Aluno gerarAluno() {
        System.out.println("\n### Inserir Aluno ###");
        return GerarAluno.gerarAluno();
    }

    private static int lerInteiro() {
        while (!scanner.hasNextInt()) {
            System.out.print("Entrada inválida. Por favor, digite um número inteiro: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static Long lerLong() {
        while (!scanner.hasNextLong()) {
            System.out.print("Entrada inválida. Por favor, digite um número inteiro (ID): ");
            scanner.next();
        }
        return scanner.nextLong();
    }
}
