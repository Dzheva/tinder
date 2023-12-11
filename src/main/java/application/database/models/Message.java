package application.database.models;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    public Chat chat;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    public User sender;

    @Column(nullable = false)
    public long timestamp;

    @Column(columnDefinition = "TEXT")
    public String text;

    public Message() {
    }

    public Message(Chat chat, User sender, long timestamp, String text) {
        this.chat = chat;
        this.sender = sender;
        this.timestamp = timestamp;
        this.text = text;
    }
}

