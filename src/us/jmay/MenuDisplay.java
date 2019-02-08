package us.jmay;

public class MenuDisplay {
    public String name;
    public MenuCategory[] categories;

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("    ").append(name).append('\n');
        for (MenuCategory category : categories) {
            out.append(category.toString());
        }
        return out.toString();
    }
}
