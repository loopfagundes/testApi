package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"title", "description", "deadline", "done"})
public class CriarTarefaModel {

    private String title;
    private String description;
    private String deadline;
    private boolean done;

    public CriarTarefaModel() {
        title = "Tarefa de Teste";
        description = "Passo a Passo";
        deadline = "2020-08-14 15:00:00";
        done = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}