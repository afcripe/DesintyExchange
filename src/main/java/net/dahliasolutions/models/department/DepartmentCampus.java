package net.dahliasolutions.models.department;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.dahliasolutions.models.campus.Campus;

import java.math.BigInteger;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DepartmentCampus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String name;
    private BigInteger directorId;
    private String directorName;

    @ManyToOne(fetch = FetchType.EAGER)
    private DepartmentRegional regionalDepartment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Campus campus;
}