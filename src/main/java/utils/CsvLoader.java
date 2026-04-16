package utils;

import modelos.Paciente;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CsvLoader {

    public static List<Paciente> cargarPacientes(String ruta) throws IOException {

        List<Paciente> pacientes = new ArrayList<>();
        return Files.lines(Path.of(ruta))
                .skip(1) // ignorar cabecera
                .map(linea -> {
                    String[] campos = linea.split(",");
                    // parsear campos y crear objeto Paciente
                    // Recuerda parsear LocalDate con LocalDate.parse(campos[6])
                    // y boolean con Boolean.parseBoolean(campos[8])
                    return new Paciente(
                            Long.parseLong(campos[0]),
                            campos[1],
                            Integer.parseInt(campos[2]),
                            campos[3],
                            campos[4],
                            campos[5],
                            LocalDate.parse(campos[6]),
                            Integer.parseInt(campos[7]),
                            Boolean.parseBoolean(campos[8]));
                })
                .collect(Collectors.toList());
    }

}
