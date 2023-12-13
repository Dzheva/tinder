package application.models;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Chat{id=")
                .append(id)
                .append(", participants=");

        if (participants != null) {
            sb.append("[");
            for (User participant : participants) {
                sb.append(participant.username).append(", ");
            }
            // Remove the trailing comma and space if there are participants
            if (!participants.isEmpty()) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("]");
        }

        sb.append(", messages=");

        if (messages != null) {
            sb.append("[");
            for (Message message : messages) {
                sb.append(message.text).append(", ");
            }
            // Remove the trailing comma and space if there are messages
            if (!messages.isEmpty()) {
                sb.setLength(sb.length() - 2);
            }
            sb.append("]");
        }

        sb.append("}");

        return sb.toString();
    }
}
