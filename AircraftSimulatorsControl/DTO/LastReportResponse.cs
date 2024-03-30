namespace AircraftSimulatorsControl.DTO
{
    public class LastReportResponse
    {
        public int Id { get; set; }
        public bool IsActive { get; set; }
        public bool IsOccupied { get; set; }

        public List<Component> Components { get; set; } = new List<Component>();


        public class Component
        {

            public int Id { get; set; }

            public int ResponseTime { get; set; }

            public int Temperature { get; set; }

            public int Power { get; set; }

            public int Voltage { get; set; }

        }
    }
}
