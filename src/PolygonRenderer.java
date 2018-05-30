import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PolygonRenderer {

    private List<Vector2D> verties;
    private Polygon polygon;
    public double angle = 0.0;
    private Color color;

    public PolygonRenderer(Color color, Vector2D... verties) {
        this.verties = Arrays.asList(verties);
        this.color = color;
        this.polygon = new Polygon();
    }

    public void render(Graphics graphics, Vector2D position) {
        graphics.setColor(this.color);
        this.updatePolygon(position);
        graphics.fillPolygon(this.polygon);
    }

    public void updatePolygon(Vector2D position) {
        this.polygon.reset();

        Vector2D center = this.verties
                .stream()
                .reduce(new Vector2D(), (v1, v2) -> v1.add(v2))
                .multiply(1.0f / this.verties.size())
                .rotate(this.angle);

        Vector2D translate = position.subtract(center);

        this.verties.stream()
                .map(vector2D -> vector2D.rotate(angle))
                .map(vector2D -> vector2D.add(translate))
                .forEach(vertex -> polygon.addPoint((int)vertex.x, (int)vertex.y));
    }
}
