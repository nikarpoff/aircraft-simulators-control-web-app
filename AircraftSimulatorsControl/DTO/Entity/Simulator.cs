namespace AircraftSimulatorsControl.DTO.Entity
{
    public class Simulator
    {

        public int Id { get; set; }

        public string Model { get; set; } = "Unknown model";

        public string Type { get; set; } = "Unknown type";

        public string SimulatorName { get; set; } = "Unknown name";

        public DateOnly ProductionDate { get; set; }

        public DateOnly CommissioningDate { get; set; }

        public int TechFrequrency { get; set; }

        public List<Component> Components { get; set; } = new List<Component>();

        public class Component
        {

            public int Id { get; set; }

            public string Name { get; set; } = "Unknown component";

        }
    }
}
