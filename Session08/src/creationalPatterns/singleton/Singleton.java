package creationalPatterns.singleton;

public class Singleton {
    public static void main(String[] args) {
        StudentMenu instance = StudentMenu.getInstance();
        instance.printMenu();
    }
}

class StudentMenu {
    private static StudentMenu instance = new  StudentMenu();

    public static StudentMenu getInstance() {
        if (instance == null) {
            instance = new StudentMenu();
        }
        return instance;
    }

    private StudentMenu() {

    }

    public void printMenu() {
        System.out.println("...");;
    }
}
