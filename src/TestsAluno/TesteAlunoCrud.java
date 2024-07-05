package TestsAluno;

import CRUDAluno.AlunoCRUD;
import Models.Aluno;

import java.util.ArrayList;

public class TesteAlunoCrud {

    private static Aluno aluno1;
    private static Aluno aluno2;

    public static void main(String[] args) {
        setUp();
        testBuscarAlunosPorNome();
        tearDown();
    }

    public static void setUp() {
        aluno1 = new Aluno("João da Silva", "joao@example.com", "12345678901", null, "São Paulo", "Rua A, 123");
        aluno2 = new Aluno("Maria Oliveira", "maria@example.com", "98765432109", null, "Rio de Janeiro", "Rua B, 456");
        AlunoCRUD.inserirAluno(aluno1);
        AlunoCRUD.inserirAluno(aluno2);
    }

    public static void testBuscarAlunosPorNome() {
        testBuscarAlunosPorNomeParte("Maria", "Maria Oliveira");
        testBuscarAlunosPorNomeParte("Silva", "João da Silva");
        testBuscarAlunosPorNomeParte("Pedro", null); // Teste para verificar se não encontra nenhum aluno
    }

    private static void testBuscarAlunosPorNomeParte(String parteNome, String nomeEsperado) {
        ArrayList<Aluno> alunosEncontrados = AlunoCRUD.buscarAlunosPorNome(parteNome);
        if (!alunosEncontrados.isEmpty()) {
            Aluno alunoEncontrado = alunosEncontrados.get(0);
            if (nomeEsperado == null) {
                System.out.println("Teste buscarAlunosPorNomeParte - Falha: Era esperado não encontrar alunos, mas foi encontrado: " + alunoEncontrado.getNome());
            } else if (nomeEsperado.equals(alunoEncontrado.getNome())) {
                System.out.println("Teste buscarAlunosPorNomeParte - OK: Nome encontrado corretamente: " + alunoEncontrado.getNome());
            } else {
                System.out.println("Teste buscarAlunosPorNomeParte - Falha: Nome encontrado incorreto: " + alunoEncontrado.getNome());
            }
        } else {
            if (nomeEsperado == null) {
                System.out.println("Teste buscarAlunosPorNomeParte - OK: Nenhum aluno encontrado, como esperado");
            } else {
                System.out.println("Teste buscarAlunosPorNomeParte - Falha: Nenhum aluno encontrado, mas era esperado encontrar: " + nomeEsperado);
            }
        }
    }

    public static void tearDown() {
        AlunoCRUD.removerAluno(aluno1.getId());
        AlunoCRUD.removerAluno(aluno2.getId());
    }
}
