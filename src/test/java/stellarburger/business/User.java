package stellarburger.business;

public class User {
    private String name;
    private String email;
    private String password;

    private User() {

    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public static UserBuilder with() {
        return new UserBuilder();
    }

    public static class UserBuilder  {
        private final User user;
        public UserBuilder() {
            this.user = new User();
        }

        public UserBuilder password(String password) {
            this.user.password = password;
            return this;
        }

        public UserBuilder email(String email) {
            this.user.email = email;
            return this;
        }

        public UserBuilder name(String name) {
            this.user.name = name;
            return this;
        }

        public User build() {
            return this.user;
        }

    }




}
