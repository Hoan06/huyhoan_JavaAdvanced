package btvn.bai5;

public class SuperAdmin implements AdminActions , UserActions{
    @Override
    public void logActivity(String activity){
        System.out.println("Super Admin : " + activity);

        UserActions.super.logActivity(activity);
        AdminActions.super.logActivity(activity);
    }
}
