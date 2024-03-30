using AircraftSimulatorsControl.DAL.Services;

namespace AircraftSimulatorsControl.Configuration
{
    public class DependencyInjectionConfiguration
    {
        public static void RegisterServices(IServiceCollection services)
        {
            services.AddTransient<ComponentService>();
            services.AddTransient<ComponentStatusService>();
            services.AddTransient<SimulatorService>();
            services.AddTransient<SimulatorStatusService>();
            services.AddTransient<ReportService>();
        }

    }
}
