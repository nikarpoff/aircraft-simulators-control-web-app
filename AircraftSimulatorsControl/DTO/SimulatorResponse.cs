using AircraftSimulatorsControl.DAL.Entity;
using AircraftSimulatorsControl.DTO.Enums;

namespace AircraftSimulatorsControl.DTO
{
    public class SimulatorResponse
    {

        public AddSimulatorStatuses Status { get; set; } = AddSimulatorStatuses.InternalError;

        public Simulator? Simulator { get; set; }

    }
}
