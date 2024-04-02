package org.server.dal.model;

import lombok.Data;

import javax.persistence.*;

@Table(name = "component_status")
@Entity
@Data
public class ComponentStatus implements DatabaseEntity {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @ManyToOne
    @JoinColumn(name = "simulator_status_id")
    public SimulatorStatus simulatorStatus;

    @ManyToOne
    @JoinColumn(name = "component_id")
    public Component component;

    @Column(name = "response_time")
    public int responseTime;

    @Column(name = "temperature")
    public int temperature;

    @Column(name = "power")
    public int power;

    @Column(name = "voltage")
    public int voltage;

    @Override
    public String toString() {
        return "ComponentStatus{" +
                "id=" + id +
                ", responseTime=" + responseTime +
                ", temperature=" + temperature +
                ", power=" + power +
                ", voltage=" + voltage +
                '}';
    }
}
