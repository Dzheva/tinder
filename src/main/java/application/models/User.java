package application.models;

import jakarta.persistence.*;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.MessageFormat;
import java.util.List;

@Entity
@Table(name = "user")
public final class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @Column(unique = true)
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

    public User(String username, String password, String fullName, String imageURL) {
        this.username = username;
        this.fullName = fullName;
        this.imageURL = imageURL;
        this.password = DigestUtils.sha256Hex(password);
    }

    @Override
    public String toString() {
        return MessageFormat.format("User'{'id={0}, username=''{1}'', password=''{2}'', " +
                        "fullName=''{3}'', imageURL=''{4}'', choices={5}, chats={6}'}'",
                id, username, password, fullName, imageURL, choices, chats);
    }
}
