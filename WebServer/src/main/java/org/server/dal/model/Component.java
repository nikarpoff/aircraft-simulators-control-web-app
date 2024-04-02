package org.server.dal.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "component")
@Entity
@Data
public class Component implements DatabaseEntity {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "simulator_id")
    private Simulator simulator;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ComponentStatus> componentStatuses;

    @Override
    public String toString() {
        return "Component{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
