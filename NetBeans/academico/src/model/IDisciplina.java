package model;

import java.util.ArrayList;

public interface IDisciplina {
    public ArrayList<Disciplina> getAllDisciplinas();
    public void createDisciplina (Disciplina disciplina);
    public Disciplina readDisciplina(int id);
    public void updateDisciplina(Disciplina disciplina);
    public void deleteDisciplina(Disciplina disciplina);
}
