namespace AircraftSimulatorsControl.DAL.Entity
{
    public class ComponentStatus : IEntity
    {
        public int ComponentStatusId { get; set; }

        public required int SimulatorStatusId { get; set; }
        public required SimulatorStatus SimulatorStatus { get; set; }

        public required int ComponentId { get; set; }
        public required Component Component { get; set; }

        public int ResponseTime { get; set; }

        public int Temperature { get; set; }

        public int Power { get; set; }

        public int Voltage { get; set; }
    }
}
