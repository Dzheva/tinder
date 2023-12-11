package application.database.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    public String username;
    public String password;
    @Column(name = "full_name")
    public String fullName;
    @Column(name = "image_url")
    public String imageURL;
    @OneToMany(mappedBy = "initiator", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Choice> choices;
    @ManyToMany(mappedBy = "participants", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Chat> chats;

    public User() {
    }

    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }
}
