using AircraftSimulatorsControl.DAL.Entity;


namespace AircraftSimulatorsControl.DAL.Services
{
    public class ComponentStatusService : AbstractCRDService<ComponentStatus, ApplicationDbContext>
    {

        public ComponentStatusService(ApplicationDbContext context) : base(context) { }

    }
}
