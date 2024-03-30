namespace AircraftSimulatorsControl.DAL.Entity
{
    public class Simulator : IEntity
    {

        public int SimulatorId { get; set; }

        public string Model { get; set; } = "Unknown model";

        public string Type { get; set; } = "Unknown type";

        public string SimulatorName { get; set; } = "Unknown name";

        public DateOnly ProductionDate { get; set; }

        public DateOnly CommissioningDate { get; set; }

        public DateOnly LastTechCheckDate { get; set; }

        public int TechCheckFrequrency { get; set; }

        public List<Component> Components { get; set; }

    }
}
