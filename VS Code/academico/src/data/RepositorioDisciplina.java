package data;

import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import model.Disciplina;
import model.IDisciplina;

public class RepositorioDisciplina implements IDisciplina {

    private final String ARQUIVO_DISCIPLINAS = "disciplina.ser";
    private final String ARQUIVO_ID_DISCIPLINAS = "idDisciplina.dat";

    @Override
    public ArrayList<Disciplina> getAllDisciplinas() {
        ArrayList<Disciplina> disciplinasCadastradas = new ArrayList<>();
        File arquivoDisciplinas = new File(ARQUIVO_DISCIPLINAS);

        // Se o arquivo ainda nao existir, cria um novo e retorna uma lista vazia
        if (!arquivoDisciplinas.exists()) {
            try {

                arquivoDisciplinas.createNewFile();

            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo de dados das disciplinas!" + e.getMessage());
                e.printStackTrace();
            }
            return disciplinasCadastradas;
        }

        // Se o arquivo existir, recurpera e retorna as disciplinas cadastradas
        try (ObjectInputStream leitorDeObjetos = new ObjectInputStream(new FileInputStream(ARQUIVO_DISCIPLINAS))) {

            disciplinasCadastradas = (ArrayList<Disciplina>) leitorDeObjetos.readObject();

        } catch (Exception e) {
            System.err.println("Erro no metodo getAllDisciplinas: " + e.getMessage());
            e.printStackTrace();
        }

        return disciplinasCadastradas;
    }

    private int gerarNovoIdParaDisciplina() {
        File arquivoDeIds = new File(ARQUIVO_ID_DISCIPLINAS);
        int idInicial = 1;
        int novoId = 0;
        int ultimoIdCadastrado = 0;

        try {
            // Se o arquivo nao existir, cria um novo, grava e retorna o id inicial
            if (!arquivoDeIds.exists()) {
                arquivoDeIds.createNewFile();
                gravarNovoId(idInicial, arquivoDeIds);
                return idInicial;
            }

            // Se o arquivo existir, le o ultimo id, incrementa, grava e retorna seu valor
            try (DataInputStream leitorDeDados = new DataInputStream(new FileInputStream(arquivoDeIds))) {
                ultimoIdCadastrado = leitorDeDados.readInt();
            }

            novoId = ultimoIdCadastrado + 1;
            gravarNovoId(novoId, arquivoDeIds);

        } catch (Exception e) {
            System.err.println("Erro no metodo gerarNovoIdParaDisciplina: " + e.getMessage());
            e.printStackTrace();
        }
        return novoId;
    }

    private void gravarNovoId(int novoId, File arquivoDeIds) throws FileNotFoundException, IOException {
        try (DataOutputStream escritorDeDados = new DataOutputStream(new FileOutputStream(arquivoDeIds))) {
            escritorDeDados.writeInt(novoId);
        }
    }

    @Override
    public void createDisciplina(Disciplina disciplina) {
        disciplina.setId(gerarNovoIdParaDisciplina());

        ArrayList<Disciplina> disciplinasCadastradas = getAllDisciplinas();
        disciplinasCadastradas.add(disciplina);

        atualizarArquivoDisciplina(disciplinasCadastradas);
    }

    private void atualizarArquivoDisciplina(ArrayList<Disciplina> disciplinasCadastradas) {

        try (ObjectOutputStream escritorDeObjetos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DISCIPLINAS))) {

            escritorDeObjetos.writeObject(disciplinasCadastradas);

        } catch (Exception e) {
            System.err.println("Erro no método atualizarArquivoDisciplina: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Disciplina readDisciplina(int id) {
        ArrayList<Disciplina> disciplinasCadastradas = getAllDisciplinas();

        for (Disciplina d : disciplinasCadastradas) {
            if (id == d.getId()) {
                return d;
            }
        }

        return null;
    }

    // Metodo auxiliar para descobrir indice(posicao) de um objeto na lista
    private int getIndiceDaDisciplina(int id) {
        ArrayList<Disciplina> disciplinasCadastradas = getAllDisciplinas();

        for (int i = 0; i < disciplinasCadastradas.size(); i++) {
            if (disciplinasCadastradas.get(i).getId() == id) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void updateDisciplina(Disciplina disciplina) {
        ArrayList<Disciplina> disciplinasCadastradas = getAllDisciplinas();
        int indiceDaDisciplina = getIndiceDaDisciplina(disciplina.getId());

        if (indiceDaDisciplina == -1) {
            throw new RuntimeException("Disciplina não encontrada.");
        }

        disciplinasCadastradas.set(indiceDaDisciplina, disciplina);
        atualizarArquivoDisciplina(disciplinasCadastradas);
    }

    public void deleteDisciplina(Disciplina disciplina) {
        ArrayList<Disciplina> disciplinasCadastradas = getAllDisciplinas();
        int indiceDaDisciplina = this.getIndiceDaDisciplina(disciplina.getId());

        if (indiceDaDisciplina == -1) {
            throw new RuntimeException("Disciplina não encontrada.");
        }

        disciplinasCadastradas.remove(indiceDaDisciplina);
        atualizarArquivoDisciplina(disciplinasCadastradas);
    }
}
