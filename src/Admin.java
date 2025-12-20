public class Admin extends User {
    private int id;
    private String password;

    /* Contructors */
    public Admin() {
        super();
        id = 0;
        password = "";
    }

    public Admin(String name, int phoneNumber, int id, String password) {
        super(name, phoneNumber);
        this.id = id;
        this.password = password;
    }

    public Admin(Admin other) {
        super(other);
        this.id = other.id;
        this.password = other.password;
    }
    /* Contructors */

    /* Setters */
    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    /* Setters */

    /* Getters */
    public int getId() {
        return id;
    }
    /* Getters */

    public boolean verifyID(int inputID) {
        if (id == inputID) {
            return true;
        }
        return false;
    }

    public boolean verifyPassword(String inputPassword) {
        if (password.equalsIgnoreCase(inputPassword)) {
            return true;
        }
        return false;
    }
}
