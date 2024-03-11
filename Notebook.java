public class Notebook {
    String model;
    int ram;
    int ssd;
    String color;
    String opSystem;

    @Override
    public String toString() {
        return String.format("Model: %s, Color: %s, SSD: %d, RAM: %d, OS: %s", model, color, ssd, ram, opSystem);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Notebook)) {
            return false;
        }
        Notebook nb = (Notebook) obj;
        return model.equals(nb.model) && color.equals(nb.color) && ram == nb.ram && ssd == nb.ssd && opSystem.equals(nb.opSystem);
    }

    @Override
    public int hashCode() {
        return model.hashCode() + 7*color.hashCode() + 13*ram + 17*ssd + 23*opSystem.hashCode();
    }
}
