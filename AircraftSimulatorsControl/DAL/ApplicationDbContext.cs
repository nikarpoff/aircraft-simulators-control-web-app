using AircraftSimulatorsControl.DAL.Entity;
using Microsoft.EntityFrameworkCore;

namespace AircraftSimulatorsControl.DAL
{
    public class ApplicationDbContext : DbContext
    {

        public ApplicationDbContext(DbContextOptions dbContextOptions) : base(dbContextOptions)
        {
            
        }

        public DbSet<Report> Reports { get; set; }
        public DbSet<Component> Components { get; set; }
        public DbSet<Simulator> Simulators { get; set; }
        public DbSet<SimulatorStatus> SimulatorStatuses { get; set; }
        public DbSet<ComponentStatus> ComponentStatuses { get; set; }

    }
}
