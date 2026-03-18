package creationalPatterns.FactoryMethod;

public class Factory {
    public static Shape createShape(String type){
        switch (type){
            case "Circle":
                return new Circle();
            case "Rectangle":
                return new Rectangle();
            default:
                throw new IllegalArgumentException("Không tạo được");
        }
    }

    public static void Main(String[] args){
        Shape shape = Factory.createShape("Rectangle");
        shape.draw();
    }

}



interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Hình tròn");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Hình tam giác");
    }
}
