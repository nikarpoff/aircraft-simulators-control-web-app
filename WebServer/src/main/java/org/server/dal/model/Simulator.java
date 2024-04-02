package org.server.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Table(name = "simulator")
@Entity
@Data
public class Simulator implements DatabaseEntity {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "model")
    private String model;

    @Column(name = "type")
    private String type;

    @Column(name = "simulator_name")
    private String simulatorName;

    @Column(name = "production_date")
    private LocalDate productionDate;

    @Column(name = "commissioning_date")
    private LocalDate commissioningDate;

    @Column(name = "last_tech_check_date")
    private LocalDate lastTechCheckDate;

    @Column(name = "tech_check_frequency")
    private int techCheckFrequency;

    @OneToMany(mappedBy = "simulator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Component> components;

    @OneToMany(mappedBy = "simulator", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<SimulatorStatus> simulatorStatuses;

    @Override
    public String toString() {
        return "Simulator{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", simulatorName='" + simulatorName + '\'' +
                ", productionDate=" + productionDate +
                ", commissioningDate=" + commissioningDate +
                ", lastTechCheckDate=" + lastTechCheckDate +
                ", techCheckFrequency=" + techCheckFrequency +
                '}';
    }
}
