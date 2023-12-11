package application.database.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "participant",
            joinColumns = @JoinColumn(name = "chat_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    public List<User> participants;
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Message> messages;

    public Chat() {
    }

    public Chat(List<User> participants) {
        this.participants = participants;
    }
}
