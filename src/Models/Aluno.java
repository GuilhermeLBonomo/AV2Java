package Models;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "alunos")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Endereco", nullable = false)
    private String endereco;

    @Column(name = "Nascimento")
    private Calendar nascimento;

    @Column(name = "Naturalidade", nullable = false)
    private String naturalidade;

    @Column(name = "Nome", nullable = false)
    private String nome;

    public Aluno(String nome, String email, String cpf, Calendar nascimento, String naturalidade, String endereco) {
        this.setNome(nome);
        this.setEmail(email);
        this.setCpf(cpf);
        this.setNascimento(nascimento);
        this.setNaturalidade(naturalidade);
        this.setEndereco(endereco);
    }

    public Aluno() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id >= 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("ID deve ser maior ou igual a zero");
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        if (nome.length() > 100) {
            throw new IllegalArgumentException("Nome deve ter até 100 caracteres");
        }
        this.nome = nome.toUpperCase(); // Converte para maiúsculas
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (email.length() > 100) {
            throw new IllegalArgumentException("Email deve ter até 100 caracteres");
        }
        this.email = email.toUpperCase(); // Converte para maiúsculas
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF não pode ser nulo ou vazio");
        }
        if (cpf.length() != 11) {
            throw new IllegalArgumentException("CPF deve ter 11 caracteres");
        }
        this.cpf = cpf;
    }

    public Calendar getNascimento() {
        return nascimento;
    }

    public void setNascimento(Calendar nascimento) {
        this.nascimento = nascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        if (naturalidade == null || naturalidade.trim().isEmpty()) {
            throw new IllegalArgumentException("Naturalidade não pode ser nulo ou vazio");
        }
        if (naturalidade.length() > 100) {
            throw new IllegalArgumentException("Naturalidade deve ter até 100 caracteres");
        }
        this.naturalidade = naturalidade.toUpperCase(); // Converte para maiúsculas
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser nulo ou vazio");
        }
        if (endereco.length() > 100) {
            throw new IllegalArgumentException("Endereço deve ter até 100 caracteres");
        }
        this.endereco = endereco.toUpperCase(); // Converte para maiúsculas
    }
}
