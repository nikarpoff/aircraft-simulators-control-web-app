using AircraftSimulatorsControl.DAL.Entity;

namespace AircraftSimulatorsControl.DAL.Services
{
    public class ComponentService : AbstractCRDService<Component, ApplicationDbContext>
    {

        public ComponentService(ApplicationDbContext context) : base(context) { }

    }
}
