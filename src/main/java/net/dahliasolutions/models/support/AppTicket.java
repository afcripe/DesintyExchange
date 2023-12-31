package net.dahliasolutions.models.support;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import net.dahliasolutions.models.campus.Campus;
import net.dahliasolutions.models.department.DepartmentRegional;
import net.dahliasolutions.models.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppTicket {

    @Id
    private String id;
    private LocalDateTime ticketDate;
    private LocalDateTime ticketDue;        // updates based on SLA
    private LocalDateTime ticketClosed;     // date status change to closed or equivalent
    private String ticketDetail;            // issue submitted
    private String priority;                // user determined priority
    private boolean ticketAgent;             // user determined priority
    private boolean closeDate;

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

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    private User agent;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketDate=" + ticketDate +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}
