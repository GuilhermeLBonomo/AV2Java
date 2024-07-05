package CRUDAluno;

import Models.Aluno;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Calendar;

public final class AlunoCRUD {
    private static final String PERSISTENCE_UNIT_NAME = "alunos";

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    private static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static boolean inserirAluno(final Aluno aluno) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            manager.persist(aluno);
            manager.getTransaction().commit();
            System.out.println("Aluno inserido com sucesso, ID: " + aluno.getId());
            return true;
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.err.println("Erro ao inserir aluno: " + ex.getMessage());
            return false;
        } finally {
            manager.close();
        }
    }

    public static boolean inserirAlunos(final ArrayList<Aluno> alunos) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            for (Aluno aluno : alunos) {
                manager.persist(aluno);
                System.out.println("Aluno inserido: " + aluno.getNome());
            }
            manager.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.err.println("Erro ao inserir alunos: " + ex.getMessage());
            return false;
        } finally {
            manager.close();
        }
    }

    public static Aluno buscarAlunoPorId(final Long id) {
        EntityManager manager = getEntityManager();
        try {
            return manager.find(Aluno.class, id);
        } catch (Exception ex) {
            System.err.println("Erro ao buscar aluno por ID: " + ex.getMessage());
            return null;
        } finally {
            manager.close();
        }
    }

    public static ArrayList<Aluno> buscarAlunosPorNome(final String nome) {
        EntityManager manager = getEntityManager();
        try {
            String jpql = "SELECT a FROM Aluno a WHERE UPPER(a.nome) LIKE UPPER(:nome)";
            Query query = manager.createQuery(jpql, Aluno.class);
            query.setParameter("nome", "%" + nome + "%");

            @SuppressWarnings("unchecked")
            ArrayList<Aluno> alunosEncontrados = new ArrayList<>(query.getResultList());
            return alunosEncontrados;
        } catch (Exception ex) {
            System.err.println("Erro ao buscar alunos por nome: " + ex.getMessage());
            return new ArrayList<>();
        } finally {
            manager.close();
        }
    }

    public static boolean removerAluno(final Long id) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            Aluno aluno = manager.find(Aluno.class, id);
            if (aluno != null) {
                manager.remove(aluno);
                System.out.println("Aluno removido com sucesso, ID: " + aluno.getId());
                manager.getTransaction().commit();
                return true;
            } else {
                System.out.println("Aluno não encontrado para remoção, ID: " + id);
                return false;
            }
        } catch (Exception ex) {
            System.err.println("Erro ao remover aluno: " + ex.getMessage());
            return false;
        } finally {
            manager.close();
        }
    }

    public static boolean alterarAluno(final Long id, String nomeAluno, String emailAluno, String cpfAluno, Calendar nascimentoAluno, String naturalidadeAluno, String enderecoAluno) {
        EntityManager manager = getEntityManager();
        try {
            manager.getTransaction().begin();
            Aluno aluno = manager.find(Aluno.class, id);

            if (aluno != null) {
                if (nomeAluno != null) {
                    aluno.setNome(nomeAluno);
                }
                if (emailAluno != null) {
                    aluno.setEmail(emailAluno);
                }
                if (cpfAluno != null) {
                    aluno.setCpf(cpfAluno);
                }
                manager.merge(aluno);
                manager.getTransaction().commit();
                System.out.println("Aluno alterado com sucesso, ID: " + aluno.getId());
                return true;
            } else {
                System.out.println("Aluno não encontrado para alteração, ID: " + id);
                return false;
            }
        } catch (Exception ex) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.err.println("Erro ao alterar aluno: " + ex.getMessage());
            return false;
        } finally {
            manager.close();
        }
    }
}
