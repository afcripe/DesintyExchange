package net.dahliasolutions.models.campus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "campus_generator", sequenceName = "campus_gseq", allocationSize = 1)
    private BigInteger id;
    private String name;
    private String city;
    private boolean hidden;
    private BigInteger directorId;
    private String directorName;
}
