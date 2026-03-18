package structuralPatterns.facade;

public class FacadeRestaurant {
    private Chef chef = new Chef();
    private Staff staff = new Staff();
    private Manager manager = new Manager();

    void order(){
        staff.order();
        chef.cook();
    }

    void pay(){
        staff.invoice();
        manager.feedback();
        manager.complainToChef();
    }
}
