import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

final public class GerarBanco {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("alunos");
        factory.close();
    }
}