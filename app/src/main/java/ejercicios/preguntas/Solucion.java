package ejercicios.preguntas;

import java.util.List;
import java.util.Map;

public class Solucion {
    public static void main(String[] args) {
        // Caso 1 del enunciado
        ejecutar("Caso 1", new Vehicle[] {
                new Vehicle("Vehicle-1", "Model-1", 2013, 16762),
                new Vehicle("Vehicle-2", "Model-2", 2019, 28230)
        }, "Model-1",
                "Model-1:\nVehicle-1 Model-1 2013\n2013\nVehicle-1\n2019\nVehicle-2\n"
                + "2011-2015\nVehicle-1-Model-1-2013\n2016-2020\nVehicle-2-Model-2-2019\n2021-2023\n-\n"
                + "Total Value: 44992\n");

        // Caso 2 del enunciado
        ejecutar("Caso 2", new Vehicle[] {
                new Vehicle("Vehicle-1", "Model-1", 2017, 24134),
                new Vehicle("Vehicle-2", "Model-2", 2017, 16053)
        }, "Model-1",
                "Model-1:\nVehicle-1 Model-1 2017\n2017\nVehicle-1\nVehicle-2\n"
                + "2011-2015\n-\n2016-2020\nVehicle-1-Model-1-2017\nVehicle-2-Model-2-2017\n2021-2023\n-\n"
                + "Total Value: 40187\n");

        // Prueba extra de removeVehicle (objeto distinto, mismos campos)
        IInventory inv = new Inventory();
        inv.addVehicle(new Vehicle("A", "M", 2015, 10));
        inv.addVehicle(new Vehicle("B", "M", 2018, 5));
        inv.removeVehicle(new Vehicle("A", "M", 2015, 10));
        System.out.println("removeVehicle: " + (inv.calculateTotalValue() == 5 ? "OK" : "FALLO")
                + " (total = " + inv.calculateTotalValue() + ")");
    }

    private static void ejecutar(String nombre, Vehicle[] vehiculos, String modelo, String esperado) {
        IInventory inv = new Inventory();
        for (Vehicle v : vehiculos) inv.addVehicle(v);
        String obtenido = imprimir(inv, modelo);
        System.out.println("===== " + nombre + " =====");
        System.out.print(obtenido);
        System.out.println(">> " + (obtenido.equals(esperado) ? "OK" : "FALLO, se esperaba:\n" + esperado));
        System.out.println();
    }

    private static String imprimir(IInventory inv, String m) {
        StringBuilder sb = new StringBuilder();
        sb.append(m).append(":\n");
        for (IVehicle v : inv.getVehiclesByModel(m))
            sb.append(v.getMake()).append(' ').append(v.getModel()).append(' ').append(v.getYear()).append('\n');
        for (Map.Entry<Integer, List<IVehicle>> e : inv.getVehiclesByYear().entrySet()) {
            sb.append(e.getKey()).append('\n');
            for (IVehicle v : e.getValue()) sb.append(v.getMake()).append('\n');
        }
        for (Triple<Integer, Integer, List<IVehicle>> t : inv.getVehiclesByYearRange()) {
            sb.append(t.getFirst()).append('-').append(t.getSecond()).append('\n');
            if (t.getThird().isEmpty()) sb.append("-\n");
            for (IVehicle v : t.getThird())
                sb.append(v.getMake()).append('-').append(v.getModel()).append('-').append(v.getYear()).append('\n');
        }
        sb.append("Total Value: ").append(inv.calculateTotalValue()).append('\n');
        return sb.toString();
    }
}
