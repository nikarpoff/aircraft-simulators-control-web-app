namespace AircraftSimulatorsControl.DAL.Entity
{
    public class SimulatorStatus : IEntity
    {
        public int SimulatorStatusId { get; set; }

        public int SimulatorId { get; set; }
        public required Simulator Simulator { get; set; }

        public int ReportId { get; set; }
        public required Report Report { get; set; }

        public bool IsOccupied { get; set; }

        public bool IsActive { get; set; }

        public List<ComponentStatus> ComponentsStatuses { get; set; } = new List<ComponentStatus>();
    }
}
