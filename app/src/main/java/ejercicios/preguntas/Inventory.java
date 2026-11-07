package ejercicios.preguntas;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

class Inventory implements IInventory {
    private static final int[][] RANGES = { {2011, 2015}, {2016, 2020}, {2021, 2023} };
    private static final Comparator<IVehicle> BY_MAKE =
            Comparator.comparing(IVehicle::getMake, Comparator.nullsLast(Comparator.naturalOrder()));

    private final List<IVehicle> vehicles = new ArrayList<>();

    @Override
    public void addVehicle(IVehicle vehicle) {
        if (vehicle != null) vehicles.add(vehicle);
    }

    @Override
    public void removeVehicle(IVehicle vehicle) {
        if (vehicle == null) return;
        for (Iterator<IVehicle> it = vehicles.iterator(); it.hasNext(); ) {
            if (it.next() == vehicle) { it.remove(); return; }
        }
        for (Iterator<IVehicle> it = vehicles.iterator(); it.hasNext(); ) {
            if (sameFields(it.next(), vehicle)) { it.remove(); return; }
        }
    }

    private static boolean sameFields(IVehicle a, IVehicle b) {
        return a.getYear() == b.getYear() && a.getPrice() == b.getPrice()
                && Objects.equals(a.getMake(), b.getMake())
                && Objects.equals(a.getModel(), b.getModel());
    }

    @Override
    public int calculateTotalValue() {
        int total = 0;
        for (IVehicle v : vehicles) total += v.getPrice();
        return total;
    }

    @Override
    public List<IVehicle> getVehiclesByModel(String model) {
        List<IVehicle> result = new ArrayList<>();
        for (IVehicle v : vehicles) {
            if (Objects.equals(v.getModel(), model)) result.add(v);
        }
        result.sort(BY_MAKE);
        return result;
    }

    @Override
    public Map<Integer, List<IVehicle>> getVehiclesByYear() {
        Map<Integer, List<IVehicle>> byYear = new TreeMap<>();
        for (IVehicle v : vehicles) {
            byYear.computeIfAbsent(v.getYear(), k -> new ArrayList<>()).add(v);
        }
        for (List<IVehicle> list : byYear.values()) list.sort(BY_MAKE);
        return byYear;
    }

    @Override
    public List<Triple<Integer, Integer, List<IVehicle>>> getVehiclesByYearRange() {
        List<Triple<Integer, Integer, List<IVehicle>>> result = new ArrayList<>();
        for (int[] r : RANGES) {
            List<IVehicle> inRange = new ArrayList<>();
            for (IVehicle v : vehicles) {
                if (v.getYear() >= r[0] && v.getYear() <= r[1]) inRange.add(v);
            }
            inRange.sort(BY_MAKE);
            result.add(new Triple<>(r[0], r[1], inRange));
        }
        return result;
    }
}
