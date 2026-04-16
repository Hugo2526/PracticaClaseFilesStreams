package app;

import modelos.Paciente;
import services.PacienteServicio;
import utils.CsvLoader;

import java.io.IOException;
import java.util.IntSummaryStatistics;
import java.util.List;

public class App {

    static void main() throws IOException {

        //Cargar fichero Pacientes.csv
        List<Paciente> pacientes = CsvLoader.cargarPacientes("src/main/resources/pacientes.csv");
        //pacientes.forEach(System.out::println);

        //Crear servicio consultasVenta
        PacienteServicio consPacientes = new PacienteServicio(pacientes);


        /* 1 */
        IO.println("---- 1. ---- getPacientesEdadMayor60");
        consPacientes.getPacientesEdadMayor60().forEach(IO::println);

        /* 2 */
        IO.println("---- 2. ---- getPacientesDerivados");
        consPacientes.getPacientesDerivados().forEach(IO::println);

        /* 3 */
        IO.println("---- 3. ---- getNombresPacientesEspecialidad");
        consPacientes.getNombresPacientesEspecialidad().forEach(IO::println);

        /* 4 */
        IO.println("---- 4. ---- getPrimerPacienteSevilla");
        //no hay nadie de sevilla pero funiona correctamente
        consPacientes.getPrimerPacienteSevilla().ifPresent(IO::println);

        /* 5 */
        IO.println("---- 5. ---- getPacientesMayorTiempoEspera");
        consPacientes.getPacientesMayorTiempoEspera().forEach(IO::println);

        /* 6 */
        IO.println("---- 6. ---- getTiempoEsperaMedio");
        consPacientes.getTiempoEsperaMedio();


        /* 7 */
        IO.println("---- 7. ---- mostrarEstadisticasEdad");
        IntSummaryStatistics stats = consPacientes.mostrarEstadisticasEdad();
        System.out.println("Media: " + stats.getAverage());
        System.out.println("Edad Maxima: " + stats.getMax());
        System.out.println("Edad Minima: " + stats.getMin());
        System.out.println("Total registros: " + stats.getCount());

        /* 8 */
        IO.println("---- 8. ---- getConsultasPorEspecialidad");
        consPacientes.getConsultasPorEspecialidad().
                forEach((k, v ) -> IO.println(k + ": " + v));

        /* 9 */
        IO.println("---- 9. ---- getPacientesAtendidosPorMunicipio");
        consPacientes.getPacientesAtendidosPorMunicipio()
                .forEach((k, v ) -> IO.println(k + ": " + v));

        /* 10 */
        IO.println("---- 10. ---- getTiempoEsperaMedioPorEspecialidad");
        consPacientes.getTiempoEsperaMedioPorEspecialidad()
                .forEach((k, v ) -> IO.println(k + ": " + v));

        /* 11 */
        IO.println("---- 11. ---- getPacientesDerivadosPorEspecialidad");
        consPacientes.getPacientesDerivadosPorEspecialidad()
                .forEach((k, v ) -> IO.println(k + ": " + v));

        /* 12 */
        IO.println("---- 12. ---- getNumeroDeConsultasPorMes");
        consPacientes.getNumeroDeConsultasPorMes()
                .forEach((k, v ) -> IO.println(k + ": " + v));


        /* 14 */
        IO.println("---- 14. ---- todosLosPacientesPediatriaMayor15");
        IO.println(consPacientes.todosLosPacientesPediatriaMayor15());

        /* 15 */
        IO.println("---- 15. ---- getPorcentajePacientesDerivados");
        IO.println(consPacientes.getPorcentajePacientesDerivados());

    }

}
