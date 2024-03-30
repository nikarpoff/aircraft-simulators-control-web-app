using AircraftSimulatorsControl.DAL.Entity;

namespace AircraftSimulatorsControl.DAL.Services
{
    public class SimulatorStatusService : AbstractCRDService<SimulatorStatus, ApplicationDbContext>
    {

        public SimulatorStatusService(ApplicationDbContext context) : base(context) { }

    }
}
