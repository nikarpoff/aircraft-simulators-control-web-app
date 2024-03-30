namespace AircraftSimulatorsControl.DAL.Entity
{
    public class Component : IEntity
    {

        public int ComponentId { get; set; }

        public string Name { get; set; } = "Unknown component";

        public required int SimulatorId { get; set; }
        public required Simulator Simulator { get; set; }

    }

}
