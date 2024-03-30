namespace AircraftSimulatorsControl.DTO
{
    public class PeriodReportResponse
    {

        public int Id { get; set; }

        public List<Component> Components { get; set; } = new List<Component>();

        public class Component
        {

            public int Id { get; set; }

            public List<int> ResponseTime { get; set; } = new List<int>();

            public List<int> Temperature { get; set; } = new List<int>();

            public List<int> Power { get; set; } = new List<int>();

            public List<int> Voltage { get; set; } = new List<int>();

        }

    }
}
