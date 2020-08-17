package models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"title", "description", "deadline", "done"})
public class EditarTarefaModel {

    private String title;
    private String description;
    private String deadline;
    private boolean done;

    public EditarTarefaModel() {
        title = "Tarefa de Teste. Edit.";
        description = "Passo a Passo com teste";
        deadline = "2020-08-17 18:40:00";
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
