package us.jmay;

public class MenuCategory {
    public String category;
    public MenuItem[] menuItems;

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("        ").append(category).append('\n');
        for (MenuItem menuItem : menuItems) {
            out.append("            ").append(menuItem.toString()).append('\n');
        }
        return out.toString();
    }
}
