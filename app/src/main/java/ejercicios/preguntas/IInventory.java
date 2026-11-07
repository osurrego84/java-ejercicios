package ejercicios.preguntas;
import java.util.List;
import java.util.Map;
interface IInventory {
    void addVehicle(IVehicle vehicle);
    void removeVehicle(IVehicle vehicle);
    int calculateTotalValue();
    List<IVehicle> getVehiclesByModel(String model);
    Map<Integer, List<IVehicle>> getVehiclesByYear();
    List<Triple<Integer, Integer, List<IVehicle>>> getVehiclesByYearRange();
}
