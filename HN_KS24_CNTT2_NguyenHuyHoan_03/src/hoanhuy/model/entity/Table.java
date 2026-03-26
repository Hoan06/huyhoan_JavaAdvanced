package hoanhuy.model.entity;

public class Table {
    private int id;
    private int number;
    private boolean isEmpty;
    private int limited;

    public Table() {
    }

    public Table(int id, int number, boolean isEmpty, int limited) {
        this.id = id;
        this.number = number;
        this.isEmpty = isEmpty;
        this.limited = limited;
    }

    public Table(int number, boolean isEmpty, int limited) {
        this.number = number;
        this.isEmpty = isEmpty;
        this.limited = limited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getLimited() {
        return limited;
    }

    public void setLimited(int limited) {
        this.limited = limited;
    }
}
