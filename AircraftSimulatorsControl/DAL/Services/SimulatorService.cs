using AircraftSimulatorsControl.DAL.Entity;

namespace AircraftSimulatorsControl.DAL.Services
{
    public class SimulatorService : AbstractCRDService<Simulator, ApplicationDbContext>
    {
        
        public SimulatorService(ApplicationDbContext context) : base(context) { }

    }

}
