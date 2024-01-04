package application.models;

import jakarta.persistence.*;

@Entity
@Table(name = "choice")
public final class Choice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "initiator_id", nullable = false)
    public User initiator;

    @ManyToOne
    @JoinColumn(name = "target_id", nullable = false)
    public User target;

    public String value;

    public Choice() {
    }

    public Choice(User initiator, User target, String value) {
        this.initiator = initiator;
        this.target = target;
        this.value = value;
    }
}
