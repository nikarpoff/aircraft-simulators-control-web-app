namespace AircraftSimulatorsControl.DTO.Entity
{
    public class Report
    {

        public DateTime ReportDateTime { get; set; }

        public class SimulatorReport
        {

            public int SimulatorId { get; set; }

            public bool IsOccupied { get; set; }

            public bool IsActive { get; set; }

            public List<ComponentReport> ComponentReports { get; set; } = new List<ComponentReport>();

            public class ComponentReport
            {

                public int ComponentId { get; set; }

                public int ResponseTime { get; set; }

                public int Temperature { get; set; }

                public int Power { get; set; }

                public int Voltage { get; set; }

            }
        }

    }
}
