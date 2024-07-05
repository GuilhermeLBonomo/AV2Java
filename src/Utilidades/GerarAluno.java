package Utilidades;

import Models.Aluno;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public final class GerarAluno {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static Aluno gerarAluno() {
        System.out.print("Digite o nome do aluno: ");
        String nome = lerString();
        System.out.print("Digite o email do aluno: ");
        String email = lerString();
        System.out.print("Digite o CPF do aluno: ");
        String cpf = lerString();
        Calendar dataNascimento = lerDataNascimento();
        System.out.print("Digite a naturalidade do aluno: ");
        String naturalidade = lerString();
        System.out.print("Digite o endereço do aluno: ");
        String endereco = lerString();
        return new Aluno(nome, email, cpf, dataNascimento, naturalidade, endereco);
    }

    public static ArrayList<Aluno> gerarAlunos() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        String continuar;
        do {
            Aluno aluno = gerarAluno();
            alunos.add(aluno);
            System.out.println("Para inserir mais alunos, digite qualquer tecla. Para encerrar, digite 'SAIR'.");
            continuar = scanner.nextLine();
        } while (!continuar.equalsIgnoreCase("SAIR"));
        return alunos;
    }

    private static String lerString() {
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Entrada inválida. Por favor, digite novamente:");
            input = scanner.nextLine().trim();
        }
        return input;
    }

    private static Calendar lerDataNascimento() {
        Calendar dataNascimento = Calendar.getInstance();
        boolean dataValida = false;
        while (!dataValida) {
            System.out.print("Digite a data de nascimento (formato DD/MM/AAAA): ");
            String dataString = scanner.nextLine().trim();
            try {
                dataNascimento.setTime(dateFormat.parse(dataString));
                dataValida = true;
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Digite novamente.");
            }
        }
        return dataNascimento;
    }

    public static Calendar parseData(String dataString) {
        Calendar data = Calendar.getInstance();
        try {
            data.setTime(dateFormat.parse(dataString));
        } catch (ParseException e) {
            System.out.println("Formato de data inválido.");
        }
        return data;
    }

    public static void fecharScanner() {
        scanner.close();
    }
}
