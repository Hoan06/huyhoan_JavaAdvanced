package hoanhuy.model.entity;

public class Table {
    private int id;
    private boolean isEmpty;
    private int limited;

    public Table() {
    }

    public Table(int id, boolean isEmpty, int limited) {
        this.id = id;
        this.isEmpty = isEmpty;
        this.limited = limited;
    }

    public Table( boolean isEmpty, int limited) {
        this.isEmpty = isEmpty;
        this.limited = limited;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
