package net.dahliasolutions.models.support;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import net.dahliasolutions.models.campus.Campus;
import net.dahliasolutions.models.department.DepartmentRegional;
import net.dahliasolutions.models.user.User;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private LocalDateTime ticketDate;
    private LocalDateTime ticketDue;        // updates based on SLA
    private String ticketDetail;            // issue submitted
    private String priority;                // user determined priority

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    private SLA sla;                        // agent determined priority

    @Enumerated(EnumType.STRING)
    private TicketStatus ticketStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    private Campus campus;

    @ManyToOne(fetch = FetchType.EAGER)
    private DepartmentRegional department;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private User agent;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "ticket")
    private List<TicketNote> notes;

    @JsonBackReference
    @OneToMany(fetch = FetchType.EAGER)
    private List<User> agentList;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketDate=" + ticketDate +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
