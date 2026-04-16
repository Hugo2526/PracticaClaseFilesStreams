package services;

import modelos.Paciente;

import java.util.*;
import java.util.stream.Collectors;

public class PacienteServicio {
    private List<Paciente> pacientes;

    public PacienteServicio(List<Paciente> pacientes) {
        this.pacientes = pacientes; }


    /* Consulta 1 — Pacientes mayores de una edad dada */
    //Mostrar los pacientes con edad superior a 60 años, ordenados de mayor a menor edad.
    public List<Paciente> getPacientesEdadMayor60(){
        return pacientes.stream()
                .filter(p -> p.getEdad() > 60)
                .sorted(Comparator.comparing(Paciente::getEdad).reversed())
                .toList();
    }

    /* Consulta 2 — Pacientes derivados a otro especialista */
    //Obtener la lista de pacientes que han sido derivados, ordenados
    // alfabéticamente por nombre.
    public List<Paciente> getPacientesDerivados(){
        return pacientes.stream()
                .filter(Paciente::isDerivado)
                .sorted(Comparator.comparing(Paciente::getNombre))
                .toList();
    }

    /* Consulta 3 — Nombres de pacientes de una especialidad concreta */
    //Obtener los nombres de los pacientes atendidos en Cardiología , sin repeticiones.
    public List<String> getNombresPacientesEspecialidad(){
        return pacientes.stream()
                .filter(p -> p.getEspecialidad().equals("Cardiología"))
                .map(Paciente::getNombre)
                .distinct()
                .toList();

    }

    /* Consulta 4 — Primera consulta encontrada de un municipio */
    //Buscar el primer paciente registrado del municipio Sevilla .
    public Optional<Paciente> getPrimerPacienteSevilla(){
        return pacientes.stream()
                .filter(paciente -> paciente.getMunicipio().equals("Sevilla"))
                .findFirst();
    }

    /* Consulta 5 — Pacientes con mayor tiempo de espera */
    //Mostrar los 10 pacientes con mayor tiempo de espera, de mayor a menor.
    public List<Paciente> getPacientesMayorTiempoEspera(){
        return pacientes.stream()
                .sorted(Comparator.comparing(Paciente::getTiempoEsperaMin).reversed())
                .limit(10)
                .toList();
    }

    /**
     * PREGUNTAR
     * @return
     */
    /* Consulta 6 — Tiempo de espera medio general */
    //Calcular el tiempo de espera medio de todos los pacientes.
    public OptionalDouble getTiempoEsperaMedio(){
        return pacientes.stream()
                .mapToInt(Paciente::getTiempoEsperaMin)
                .average();
    }

    /* Consulta 7 — Estadísticas de edad */
    //Obtener las siguientes estadísticas sobre la edad de los pacientes:
    public IntSummaryStatistics mostrarEstadisticasEdad() {
        return pacientes.stream()
                .mapToInt(Paciente::getEdad)
                .summaryStatistics();
    }

    /* Consulta 8 — Número de consultas por especialidad */
    //Crear un mapa donde la clave sea la especialidad y el valor sea el número de consultas
    //realizadas de esa especialidad
    public Map<String, Long> getConsultasPorEspecialidad(){
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,
                        Collectors.counting()));
    }

    /* Consulta 9 — Número de pacientes por municipio */
    //Crear un mapa donde la clave sea el municipio y el valor sea el
    //número de pacientes atendidos en ese municipio.
    public Map<String, Long> getPacientesAtendidosPorMunicipio(){
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getMunicipio,
                        Collectors.counting()));
    }

    /* Consulta 10 — Tiempo de espera medio por especialidad */
    //Obtener el tiempo de espera medio de cada especialidad
    public Map<String, Double> getTiempoEsperaMedioPorEspecialidad(){
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,
                        Collectors.averagingInt(Paciente::getTiempoEsperaMin)));
    }

    /* Consulta 11 — Número de pacientes derivados por especialidad */
    //Crear un mapa con el número de pacientes derivados agrupados por especialidad
    public Map<String, Long> getPacientesDerivadosPorEspecialidad(){
        return pacientes.stream()
                .filter(Paciente::isDerivado)
                .collect(Collectors.groupingBy(Paciente::getEspecialidad,
                        Collectors.counting()));
    }

    /* Consulta 12 — Consultas agrupadas por mes */
    //Crear un mapa donde la clave sea el mes de consulta
    //(valor numérico del 1 al 12) y el valor sea
    //el número de consultas de ese mes.
    public Map<Integer, Long> getNumeroDeConsultasPorMes(){
        return pacientes.stream()
                .collect(Collectors.groupingBy(paciente -> paciente.getFechaConsulta()
                                .getMonthValue(),
                        Collectors.counting()));
    }

    /* Consulta 13 — Especialidad con más pacientes derivados */
    //Obtener la especialidad que más pacientes ha derivado.


    /* Consulta 14 — Comprobar si todos los pacientes de Pediatría son menores
    de 15 años */
    //Verificar si todos los pacientes atendidos en Pediatría tienen menos de 15 años.
    public boolean todosLosPacientesPediatriaMayor15(){
        return pacientes.stream()
                .filter(paciente -> paciente.getEspecialidad().equals("Pediatría"))
                .allMatch(paciente -> paciente.getEdad() > 15);
    }

    /* Consulta 15 — Porcentaje de pacientes derivados */
    //Calcular qué porcentaje del total de pacientes han sido derivados a otro especialista.
    public double getPorcentajePacientesDerivados(){
        double totalDerivados= pacientes.stream()
                .filter(Paciente::isDerivado)
                .count();

        return ((double) totalDerivados / pacientes.size()) * 100;
    }

}
