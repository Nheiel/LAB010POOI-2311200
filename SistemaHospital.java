/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package practicas.medico;

/**
 *
 * @author Nheiel
 */
import java.util.*;

public class SistemaHospital {
    private List<Paciente> listaPacientes;
    private List<Medico> listaMedicos;

    public SistemaHospital() {
        listaPacientes = new ArrayList<>();
        listaMedicos = new ArrayList<>();
    }

    public void registrarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Registro de Paciente");

        System.out.print("DNI del paciente: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre del paciente: ");
        String nombre = scanner.nextLine();

        System.out.print("Dirección del paciente: ");
        String direccion = scanner.nextLine();

        System.out.print("Peso del paciente: ");
        double peso = scanner.nextDouble();

        System.out.print("Temperatura del paciente: ");
        double temperatura = scanner.nextDouble();

        Paciente nuevoPaciente = new Paciente(dni, nombre, direccion, peso, temperatura);
        listaPacientes.add(nuevoPaciente);

        System.out.println("Paciente registrado con éxito.");
    }

    public void eliminarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Eliminar Paciente");

        System.out.print("Ingrese la posición del paciente a eliminar: ");
        int posicion = scanner.nextInt();

        if (posicion >= 0 && posicion < listaPacientes.size()) {
            listaPacientes.remove(posicion);
            System.out.println("Paciente eliminado con éxito.");
        } else {
            System.out.println("Posición inválida.");
        }
    }

    public void modificarPaciente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Modificar Paciente");

        System.out.print("Ingrese la posición del paciente a modificar: ");
        int posicion = scanner.nextInt();

        if (posicion >= 0 && posicion < listaPacientes.size()) {
            Paciente paciente = listaPacientes.get(posicion);

            System.out.println("Modificar datos:");
            System.out.print("Nuevo DNI: ");
            paciente.setDni(scanner.nextInt());
            scanner.nextLine();

            System.out.print("Nuevo nombre: ");
            paciente.setNombre(scanner.nextLine());

            System.out.print("Nueva dirección: ");
            paciente.setDireccion(scanner.nextLine());

            System.out.print("Nuevo peso: ");
            paciente.setPeso(scanner.nextDouble());

            System.out.print("Nueva temperatura: ");
            paciente.setTemperatura(scanner.nextDouble());

            System.out.println("Paciente modificado con éxito.");
        } else {
            System.out.println("Posición inválida.");
        }
    }

    public void mostrarPesoQueMasSeRepite() {
        if (!listaPacientes.isEmpty()) {
            Map<Double, Integer> frecuencias = new HashMap<>();

            for (Paciente paciente : listaPacientes) {
                frecuencias.put((Double) paciente.getPeso(), frecuencias.getOrDefault(paciente.getPeso(), 0) + 1);
            }

            double pesoMasRepetido = Collections.max(frecuencias.entrySet(), Map.Entry.comparingByValue()).getKey();
            int cantidadPacientes = frecuencias.get(pesoMasRepetido);

            System.out.println("Peso que más se repite: " + pesoMasRepetido);
            System.out.println("Cantidad de pacientes con ese peso: " + cantidadPacientes);
        } else {
            System.out.println("No hay pacientes registrados.");
        }
    }

    public void mostrarCantidadPacientesPorRango() {
    if (!listaPacientes.isEmpty()) {
        int rango1 = 0, rango2 = 0, rango3 = 0, rango4 = 0;

        for (Paciente paciente : listaPacientes) {
            // Convertimos el objeto a tipo int usando la conversión explícita
            int peso = ((Integer) paciente.getPeso());

            if (peso >= 40 && peso < 60) {
                rango1++;
            } else if (peso >= 60 && peso < 80) {
                rango2++;
            } else if (peso >= 80 && peso < 100) {
                rango3++;
            } else if (peso >= 100 && peso <= 120) {
                rango4++;
            }
        }

        System.out.println("Rango 40-60: " + rango1 + " pacientes");
        System.out.println("Rango 60-80: " + rango2 + " pacientes");
        System.out.println("Rango 80-100: " + rango3 + " pacientes");
        System.out.println("Rango 100-120: " + rango4 + " pacientes");
    } else {
        System.out.println("No hay pacientes registrados.");
    }
}

    // Resto de operaciones...

    public static void main(String[] args) {
        SistemaHospital sistema = new SistemaHospital();
        Scanner scanner = new Scanner(System.in);

        int opcion;

        do {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrar Paciente");
            System.out.println("2. Eliminar Paciente");
            System.out.println("3. Modificar Paciente");
            System.out.println("4. Mostrar Peso que más se repite");
            System.out.println("5. Mostrar Cantidad de Pacientes por Rango");
            System.out.println("6. Mostrar Peso Mayor y Menor");
            System.out.println("7. Mostrar Lista de Pacientes Ordenados por Apellidos");
            System.out.println("8. Dado un Paciente, indicar qué doctor lo atendió");
            System.out.println("9. Buscar Doctores por Especialidad");
            System.out.println("10. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> sistema.registrarPaciente();
                case 2 -> sistema.eliminarPaciente();
                case 3 -> sistema.modificarPaciente();
                case 4 -> sistema.mostrarPesoQueMasSeRepite();
                case 5 -> sistema.mostrarCantidadPacientesPorRango();
                case 10 -> System.out.println("Saliendo del programa.");
                default -> System.out.println("Opción inválida.");
            }
            // Agrega aquí el código para las otras operaciones...
            
        } while (opcion != 10);
    }
}

class Paciente {
    private int dni;
    private String nombre;
    private String direccion;
    private double peso;
    private double temperatura;

    public Paciente(int dni, String nombre, String direccion, double peso, double temperatura) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.peso = peso;
        this.temperatura = temperatura;
    }

    // Getters y setters...

    @Override
    public String toString() {
        return "Paciente{" +
                "dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", peso=" + peso +
                ", temperatura=" + temperatura +
                '}';
    }

    void setDni(int nextInt) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setNombre(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setDireccion(String nextLine) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setPeso(double nextDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setTemperatura(double nextDouble) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Object getPeso() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

class Medico {
    private int nroColegiatura;
    private String nombre;
    private String especialidad;

    public Medico(int nroColegiatura, String nombre, String especialidad) {
        this.nroColegiatura = nroColegiatura;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // Getters y setters...

    @Override
    public String toString() {
        return "Medico{" +
                "nroColegiatura=" + nroColegiatura +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
