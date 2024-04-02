package org.server.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "simulator_status")
@Entity
@Data
public class SimulatorStatus implements DatabaseEntity {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "simulator_id")
    private Simulator simulator;

    @ManyToOne()
    @JoinColumn(name = "report_id")
    private Report report;

    @Column(name = "is_occupied")
    private boolean isOccupied;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "simulatorStatus", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ComponentStatus> componentsStatuses;

    @Override
    public String toString() {
        return "SimulatorStatus{" +
                "id=" + id +
                ", report=" + report +
                ", isOccupied=" + isOccupied +
                ", isActive=" + isActive +
                '}';
    }
}
