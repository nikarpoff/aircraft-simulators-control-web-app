using AircraftSimulatorsControl.DAL.Entity;

namespace AircraftSimulatorsControl.DAL.Services
{
    public class ReportService : AbstractCRDService<Report, ApplicationDbContext>
    {

        public ReportService(ApplicationDbContext context) : base(context) { }

    }
}
