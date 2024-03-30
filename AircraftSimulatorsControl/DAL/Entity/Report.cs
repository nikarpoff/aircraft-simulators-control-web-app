namespace AircraftSimulatorsControl.DAL.Entity
{
    public class Report : IEntity
    {

        public int ReportId { get; set; }

        public DateTime ReportDateTime { get; set; }

        public List<SimulatorStatus> SimulatorStatuses { get; set; }

    }
}
