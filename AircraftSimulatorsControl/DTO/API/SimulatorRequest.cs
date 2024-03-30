namespace AircraftSimulatorsControl.DTO.API
{
    public class SimulatorRequest
    {

        public string Model { get; set; } = "Unknown model";

        public string Type { get; set; } = "Unknown type";

        public string SimulatorName { get; set; } = "Unknown name";

        public DateOnly ProductionDate { get; set; }

        public DateOnly CommissioningDate { get; set; }

        public int TechFrequrency { get; set; }

        public List<Component> Components { get; set; } = new List<Component>();

        public class Component
        {

            public string Name { get; set; } = "Unknown component";

        }

    }
}