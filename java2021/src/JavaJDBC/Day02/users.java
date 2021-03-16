package JavaJDBC.Day02;

public class users {
    private String account;
    private int password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "account='" + account + '\'' +
                ", password=" + password +
                '}';
    }
}
