using AircraftSimulatorsControl.DTO.Entity;
using AircraftSimulatorsControl.DTO.Enums;

namespace AircraftSimulatorsControl.DTO.API
{
    public class SimulatorResponse
    {

        public AddSimulatorStatuses Status { get; set; } = AddSimulatorStatuses.InternalError;

        public Simulator? Simulator { get; set; }

    }
}
