package Classes;

import java.sql.Date;
import java.sql.Time;

public class Joins {
    int ID_paciente;
    int ID_medico;
    String nomeMedico;
    String nomePaciente;
    Date dataConsulta;
    Time horaConsulta;
    int compareceu;

    public int getID_paciente() {
        return ID_paciente;
    }

    public void setID_paciente(int ID_paciente) {
        this.ID_paciente = ID_paciente;
    }

    public int getID_medico() {
        return ID_medico;
    }

    public void setID_medico(int ID_medico) {
        this.ID_medico = ID_medico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Time getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(Time horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    public int getCompareceu() {
        return compareceu;
    }

    public void setCompareceu(int compareceu) {
        this.compareceu = compareceu;
    }

    public Joins() {
    }
    
    public Joins(int ID_paciente, int ID_medico, String nomeMedico, String nomePaciente, Date dataConsulta, Time horaConsulta) {
        this.ID_paciente = ID_paciente;
        this.ID_medico = ID_medico;
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
    }

    public Joins(Date dataConsulta, Time horaConsulta,  String nomePaciente, String nomeMedico, int compareceu) {
        this.compareceu = compareceu;
        this.nomeMedico = nomeMedico;
        this.nomePaciente = nomePaciente;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
    }
    
}
