package org.server.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "report")
@Entity
@Data
public class Report implements DatabaseEntity {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "report_date_time")
    private LocalDate reportDateTime;

    @OneToMany(mappedBy = "report", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SimulatorStatus> simulatorStatuses;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", reportDateTime=" + reportDateTime +
                '}';
    }
}
