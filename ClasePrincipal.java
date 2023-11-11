/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package practicas.claseprincipal;

/**
 *
 * @author Nheiel
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClasePrincipal {

    private static List<Paciente> pacientes = new ArrayList<>();
    private static List<Medico> medicos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    

    public static void main(String[] args) {

            pacientes.add(new Paciente("12345678", "Leonardo", "Calle 546", 80, 26.5));
        pacientes.add(new Paciente("13579086", "Ariana", "Avenida 421", 70, 27.0));
        pacientes.add(new Paciente("43334434", "Paola", "Calle 777", 50, 26.8));
        pacientes.add(new Paciente("13455555", "Marco", "Calle 1144", 70, 26.8));
        pacientes.add(new Paciente("44556666", "Ruben", "Calle 1142", 80, 26.8));
        
        medicos.add(new Medico(15555, "Dr. Raul", "Oftalmologia"));
        medicos.add(new Medico(61230, "Dra. Maryori", "Pediatria"));
        medicos.add(new Medico(21233, "Dr. Mijail", "Neurooftalmologia"));
        medicos.add(new Medico(77777, "Dra. Nadine", "Cardiologia"));
        

        boolean continuar = true;

        while (continuar) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar los datos de los pacientes uno a uno (agregar al final)");
            System.out.println("2. Eliminar los datos de un paciente dado su posición una lista");
            System.out.println("3. Modificar los datos de un paciente de la lista");
            System.out.println("4. Mostrar el peso que más se repite en la lista");
            System.out.println("5. Mostrar la cantidad de pacientes que tienen el peso que más se repite");
            System.out.println("6. Mostrar el peso mayor y menor");
            System.out.println("7. Mostrar cantidad por rango de pesos .");
            System.out.println("8. Mostrar la lista de pacientes ordenados por nombres");
            System.out.println("9. Dado un paciente indicar qué doctor lo atendió");
            System.out.println("10. Buscar a los doctores por especialidad");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1 -> RegistrarPaciente();
                case 2 -> eliminarPaciente("44556666");
                case 3 -> modificarDatosPaciente("43334434");
                case 4 -> mostrarPesoMasRepetido();
                case 5 -> mostrarCantidadPesoMasRepetido();
                case 6 -> {
                    mostrarPesoMayor();
                    mostrarPesoMenor();
                    }
                case 7 -> {
                    }
                case 8 -> mostrarPacientesOrdenadosPorApellidos();
                case 9 -> {
                    }
                case 10 -> buscarMedicosPorEspecialidad("Cardiología");
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
                //dificil
                //dificil sin base de datos
                        }
        scanner.close();
    }

    private static void RegistrarPaciente() {
        System.out.print("Ingrese el DNI del paciente: ");
        String dni = scanner.nextLine();
        System.out.print("Ingrese el nombre del paciente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la dirección del paciente: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese el peso del paciente: ");
        double peso = scanner.nextDouble();
        System.out.print("Ingrese la temperatura del paciente: ");
        double temperatura = scanner.nextDouble();
        Paciente paciente = new Paciente(dni, nombre, direccion, peso, temperatura);
        pacientes.add(paciente);
        System.out.println("Paciente creado exitosamente.");
    }

    public static void mostrarTodosLosPacientes() {

        for (Paciente paciente : pacientes) {
            System.out.println("DNI: " + paciente.getDni());
            System.out.println("Nombre: " + paciente.getNombre());
            System.out.println("Dirección: " + paciente.getDireccion());
            System.out.println("Peso: " + paciente.getPeso());
            System.out.println("Temperatura: " + paciente.getTemperatura());
            System.out.println("----------------------");
        }
    }

    public static void modificarDatosPaciente(String dni) {
        for (Paciente paciente : pacientes) {
            if (paciente.getDni().equals(dni)) {
                Scanner scanner = new Scanner(System.in);

                // Solicitar nuevos datos al usuario
                System.out.print("Ingrese la nueva dirección: ");
                String nuevaDireccion = scanner.nextLine();

                System.out.print("Ingrese el nuevo peso: ");
                double nuevoPeso = scanner.nextDouble();

                System.out.print("Ingrese la nueva temperatura: ");
                double nuevaTemperatura = scanner.nextDouble();

                // Actualizar los datos del paciente
                paciente.setDireccion(nuevaDireccion);
                paciente.setPeso(nuevoPeso);
                paciente.setTemperatura(nuevaTemperatura);

                System.out.println("Datos del paciente con DNI " + dni + " modificados con éxito.");

                // Cerrar el scanner
                scanner.close();
                return;
            }
        }
        System.out.println("Paciente con DNI " + dni + " no encontrado.");
    }

    public static void eliminarPaciente(String dni) {
        Iterator<Paciente> iterator = pacientes.iterator();
        while (iterator.hasNext()) {
            Paciente paciente = iterator.next();
            if (paciente.getDni().equals(dni)) {
                iterator.remove();
                System.out.println("Paciente con DNI " + dni + " eliminado con éxito.");
                return;
            }
        }
        System.out.println("Paciente con DNI " + dni + " no encontrado.");
    }

    public static void mostrarPesoMasRepetido() {
        // Crear un mapa para contar la frecuencia de cada peso
        Map<Double, Integer> frecuenciaPesos = new HashMap<>();
        for (Paciente paciente : pacientes) {
            double peso = paciente.getPeso();
            frecuenciaPesos.put(peso, frecuenciaPesos.getOrDefault(peso, 0) + 1);
        }

        // Encontrar el peso más repetido
        double pesoMasRepetido = 0;
        int maxFrecuencia = 0;
        for (Map.Entry<Double, Integer> entry : frecuenciaPesos.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                pesoMasRepetido = entry.getKey();
            }
        }

        // Mostrar el peso más repetido
        System.out.println("El peso más repetido es: " + pesoMasRepetido);
    }

    public static void mostrarCantidadPesoMasRepetido() {
        // Crear un mapa para contar la frecuencia de cada peso
        Map<Double, Integer> frecuenciaPesos = new HashMap<>();
        for (Paciente paciente : pacientes) {
            double peso = paciente.getPeso();
            frecuenciaPesos.put(peso, frecuenciaPesos.getOrDefault(peso, 0) + 1);
        }

        // Encontrar el peso más repetido y su frecuencia
        double pesoMasRepetido = 0;
        int maxFrecuencia = 0;
        for (Map.Entry<Double, Integer> entry : frecuenciaPesos.entrySet()) {
            if (entry.getValue() > maxFrecuencia) {
                maxFrecuencia = entry.getValue();
                pesoMasRepetido = entry.getKey();
            }
        }
        System.out.println("Cantidad de pacientes con ese peso: " + maxFrecuencia);
    }

    public static void mostrarPesoMayor() {
        if (!pacientes.isEmpty()) {
            // Ordenar la lista de pacientes por peso de forma descendente
            Collections.sort(pacientes, Collections.reverseOrder(Comparator.comparing(Paciente::getPeso)));

            // El paciente con el peso más alto estará en la primera posición después de ordenar
            Paciente pacientePesoMayor = pacientes.get(0);

            System.out.println("El peso mayor es: " + pacientePesoMayor.getPeso());
        } else {
            System.out.println("La lista de pacientes está vacía.");
        }
    }

    public static void mostrarPesoMenor() {
        if (!pacientes.isEmpty()) {
            // Ordenar la lista de pacientes por peso de forma ascendente
            Collections.sort(pacientes, Comparator.comparing(Paciente::getPeso));

            // El paciente con el peso más bajo estará en la primera posición después de ordenar
            Paciente pacientePesoMenor = pacientes.get(0);

            System.out.println("El peso menor es: " + pacientePesoMenor.getPeso());
        } else {
            System.out.println("La lista de pacientes está vacía.");
        }
    }

    public static void mostrarPacientesOrdenadosPorApellidos() {
        // Implementar el algoritmo de burbuja para ordenar la lista por apellidos
        int n = pacientes.size();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (pacientes.get(i - 1).getNombre().compareToIgnoreCase(pacientes.get(i).getNombre()) > 0) {
                    // Intercambiar los elementos si están en el orden incorrecto
                    Paciente temp = pacientes.get(i - 1);
                    pacientes.set(i - 1, pacientes.get(i));
                    pacientes.set(i, temp);
                    swapped = true;
                }
            }
            n--;
        } while (swapped);

        // Mostrar la lista ordenada
        System.out.println("Lista de pacientes ordenados por apellidos:");
        mostrarTodosLosPacientes();
    }
    
    
    public static List<Medico> buscarMedicosPorEspecialidad(String especialidadBuscada) {
        return medicos.stream()
                .filter(medico -> medico.getEspecialidad().equalsIgnoreCase(especialidadBuscada))
                .collect(Collectors.toList());
    }

}
